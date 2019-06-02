package net.moonlightflower.wc3libs.port;

import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.JMpqException;
import systems.crigges.jmpq3.MPQOpenOption;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class JMpqPort extends MpqPort {
	private final static File workDir = new File(Orient.getExecDir(), Orient.getExecPath().getName() + "_work");

	private final static File classWorkDir = new File(workDir, Orient.localClassPath().toString());

	private final static File tempDir = new File(classWorkDir, "temp");

	private final static File exportDir = new File(tempDir, "exported");

	public static String enquote(@Nonnull String s) {
		return "\"" + s + "\"";
	}

	@Nonnull
	@Override
	public List<File> listFiles(@Nonnull File mpqFile) throws IOException {
		try {
			try(JMpqEditor jmpq = new JMpqEditor(mpqFile, MPQOpenOption.READ_ONLY)) {
				List<File> ret = new ArrayList<>();

				for (String fileS : jmpq.getFileNames()) {
					ret.add(new File(fileS));
				}
				return ret;
			}

		} catch (JMpqException e) {
			throw new IOException(e.getMessage());
		}
	}

	@Override
	public MpqPort.In createIn() {
		return new In();
	}

	@Override
	public MpqPort.Out createOut() {
		return new Out();
	}

	public static class In extends MpqPort.In {
		private void commitJ(@Nonnull Vector<File> mpqFiles) throws IOException {
			Vector<String> lines = new Vector<>();

			for (File mpqFile : mpqFiles) {
				try (JMpqEditor jmpq = new JMpqEditor(mpqFile, MPQOpenOption.FORCE_V0)) {

                    for (FileImport fileImport : getFiles()) {
                        //jmpq.insertFile(fileImport.getOutFile().getAbsolutePath(), fileImport.getInFile(), false);
                        if (fileImport.getOutFile() != null) {
                            jmpq.insertFile(fileImport.getInFile().toString(), fileImport.getOutFile(), false);
                        } else {
                            jmpq.deleteFile(fileImport.getInFile().toString());
                        }
                    }
                }
			}
		}

		@Override
		public void commit(@Nonnull Vector<File> mpqFiles) throws PortException {
			try {
				commitJ(mpqFiles);
			} catch (IOException e) {
				throw new PortException(e);
			}
		}
	}

	public static class Out extends MpqPort.Out {
		@Nonnull
		@Override
		public Result commit(@Nonnull Vector<File> mpqFiles) throws IOException {
			Orient.removeDir(exportDir);
			Orient.createDir(exportDir);

			Vector<FileExport> volExports = (Vector<FileExport>) getFiles().clone();
			Result result = new Result();

			int c = 0;

			while ((c < mpqFiles.size()) && (volExports.size() > 0)) {
				File mpqFile = mpqFiles.get(c);

				if (mpqFile instanceof ResourceFile) {
					Vector<FileExport> failedExports = new Vector<>();

					for (FileExport fileExport : volExports) {
						File outFile = fileExport.getOutFile();

						try {
							File inFile = fileExport.getInFile();

							inFile = new File(mpqFile, inFile.toString());

							FileExport resultFileExport = null;

							if (outFile != null) {
								if (fileExport.getOutDir() != null) {
									fileExport.getOutDir().mkdirs();
								}

								InputStream inStream = getClass().getResourceAsStream(inFile.getName());

								if (inStream == null) throw new FileNotFoundException();

								byte[] buf = new byte[8192];
								int len = 0;

								OutputStream outStream = Orient.createFileOutputStream(outFile);

								while ((len = inStream.read(buf, 0, buf.length)) > 0) {
									outStream.write(buf, 0, len);
								}

								resultFileExport = new FileExport(inFile, outFile, false);

								result.addExport(mpqFile, resultFileExport);
							} else {
								DummyOutputStream dummyStream = new DummyOutputStream();

								InputStream inStream = getClass().getClassLoader().getResourceAsStream(inFile.getName());

								if (inStream == null) throw new FileNotFoundException();

								byte[] buf = new byte[8192];
								int len = 0;

								while ((len = inStream.read(buf, 0, buf.length)) > 0) {
									dummyStream.write(buf, 0, len);
								}

								inStream.close();

								OutputStream outStream = fileExport.getOutStream();

								if (outStream != null) {
									dummyStream.close(outStream);
								} else {
									dummyStream.close();
								}

								resultFileExport = new FileExport(inFile, outStream);

								result.addExport(mpqFile, resultFileExport, dummyStream.getBytes());
							}
						} catch (IOException e) {
							//Log.info("failed " + fileExport.getInFile() + " at " + mpqFile", e);
							failedExports.add(fileExport);
						}
					}

					volExports = failedExports;
				} else if (mpqFile.isDirectory()) {
					Vector<FileExport> failedExports = new Vector<>();

					for (FileExport fileExport : volExports) {
						File outFile = fileExport.getOutFile();

						try {
							File inFile = new File(mpqFile, fileExport.getInFile().toString());

							FileExport resultFileExport = null;

							if (outFile != null) {
								if (fileExport.getOutDir() != null) {
									fileExport.getOutDir().mkdirs();
								}

								Orient.copyFile(inFile, outFile);

								resultFileExport = new FileExport(inFile, outFile, false);

								result.addExport(mpqFile, resultFileExport);
							} else {
								DummyOutputStream dummyStream = new DummyOutputStream();

								FileInputStream inStream = new FileInputStream(inFile);

								byte[] buf = new byte[8192];
								int len = 0;

								while ((len = inStream.read(buf, 0, buf.length)) > 0) {
									dummyStream.write(buf, 0, len);
								}

								inStream.close();

								OutputStream outStream = fileExport.getOutStream();

								if (outStream != null) {
									dummyStream.close(outStream);
								} else {
									dummyStream.close();
								}

								resultFileExport = new FileExport(inFile, outStream);

								result.addExport(mpqFile, resultFileExport, dummyStream.getBytes());
							}
						} catch (IOException e) {
							//Log.info("failed " + fileExport.getInFile() + " at " + mpqFile, e);
							failedExports.add(fileExport);
						}
					}

					volExports = failedExports;
				} else {
					if (Orient.fileIsLocked(mpqFile)) {
						File tempFile = new File(tempDir, Orient.getFileName(mpqFile));

						Orient.copyFileIfNewer(mpqFile, tempFile);

						mpqFile = tempFile;
					}

					try (JMpqEditor jmpq = new JMpqEditor(mpqFile, MPQOpenOption.READ_ONLY)) {
						Vector<FileExport> failedExports = new Vector<>();

						for (FileExport fileExport: volExports) {
							File outFile = fileExport.getOutFile();

							try {
								File inFile = fileExport.getInFile();

								FileExport resultFileExport = null;

								if (outFile != null) {
									if (fileExport.getOutDir() != null) {
										fileExport.getOutDir().mkdirs();
									}

									jmpq.extractFile(inFile.toString(), outFile);

									resultFileExport = new FileExport(inFile, outFile, false);

									result.addExport(mpqFile, resultFileExport);
								} else {
									DummyOutputStream dummyStream = new DummyOutputStream();

									jmpq.extractFile(inFile.toString(), dummyStream);

									OutputStream outStream = fileExport.getOutStream();

									if (outStream != null) {
										dummyStream.close(outStream);
									} else {
										dummyStream.close();
									}

									resultFileExport = new FileExport(inFile, outStream);

									result.addExport(mpqFile, resultFileExport, dummyStream.getBytes());
								}
								volExports = failedExports;
							} catch (JMpqException e) {
								failedExports.add(fileExport);
							}
						}
					}


					//removeExistingExports(volExports);

					//failedExports.clear();
				}

				c++;
			}

			return result;
		}
	}

	@Nonnull
	public static Vector<File> getWc3Mpqs(@Nonnull File wc3dir) {
		Vector<File> files = new Vector<>();

		files.add(new File(wc3dir, War3MPQs.WAR3PATCH.toString()));
		files.add(new File(wc3dir, War3MPQs.WAR3X.toString()));
		files.add(new File(wc3dir, War3MPQs.WAR3.toString()));

		return files.stream().filter(File::exists).collect(Collectors.toCollection(Vector::new));
	}

	public static void importFile(@Nonnull Vector<File> mpqFiles, @Nonnull File outFile, @Nonnull File inFile) throws IOException {
		In port = new In();

		port.add(outFile, inFile);

		port.commit(mpqFiles);
	}

	public static void importFile(@Nonnull File mpqFile, @Nonnull File outFile, @Nonnull File inFile) throws IOException {
		Vector<File> mpqFiles = new Vector<>();

		mpqFiles.add(mpqFile);

		importFile(mpqFiles, outFile, inFile);
	}

	public static void extractFile(@Nonnull Vector<File> mpqFiles, @Nonnull File inFile, @Nonnull File outFile, boolean outFileIsDir) throws Exception {
		Out port = new Out();

		port.add(inFile, outFile, outFileIsDir);

		port.commit(mpqFiles);
	}

	public static void extractFile(@Nonnull File mpqFile, @Nonnull File inFile, @Nonnull File outFile, boolean outFileIsDir) throws Exception {
		Vector<File> mpqFiles = new Vector<>();

		mpqFiles.add(mpqFile);

		extractFile(mpqFiles, outFile, inFile, outFileIsDir);
	}

	@Nonnull
	@Override
	public Out.Result getGameFiles(@Nonnull File... files) throws IOException, NotFoundException {
		MpqPort.Out portOut = new Out();

		for (File file : files) {
			portOut.add(file);
		}

		return portOut.commit(MpqPort.getWar3Mpqs());
	}
}