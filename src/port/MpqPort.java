package port;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Vector;

import de.peeeq.jmpq3.JMpqEditor;
import de.peeeq.jmpq3.JMpqException;

public final class MpqPort {
	private final static File tempDir = new File(Orient.localDir(), "Temp");

	private final static File exportDir = new File(tempDir, "exported");
	
	public static String enquote(String s) {
		return "\"" + s + "\"";
	}
	
	public static class MpqPortDel {
		private class FileDel {
			private File _file;
			
			public File getFile() {
				return _file;
			}
	
			FileDel(File file) {
				_file = file;
			}
		}
	
		private Vector<FileDel> fileDels = new Vector<>();
	
		public void add(File file) {
			fileDels.add(new FileDel(file));
		}
	
		public void commit(Vector<File> mpqFiles) throws IOException {
			for (File mpqFile : mpqFiles) {
				JMpqEditor jmpq = new JMpqEditor(mpqFile);
		
				for (FileDel fileDel : fileDels) {
					jmpq.deleteFile(fileDel.getFile().getAbsolutePath());
				}
				
				jmpq.close();
			}
		}
	
		public void commit(File mpqPath) throws IOException {
			Vector<File> mpqFiles = new Vector<>();
	
			mpqFiles.add(mpqPath);
	
			commit(mpqFiles);
		}
	}
	
	public static class MpqPortIn {
		private class FileImport {
			private File _outFile;
			private File _inFile;

			public File getOutFile() {
				return _outFile;
			}

			public File getInFile() {
				return _inFile;
			}

			FileImport(File outPath, File inPath) {
				_outFile = outPath;
				_inFile = inPath;
			}
		}
	
		private Vector<FileImport> fileImports = new Vector<>();
	
		public void add(File outPath, File inPath) {
			fileImports.add(new FileImport(outPath, inPath));
		}
		
		public void clear() {
			fileImports.clear();
		}
	
		public void commit(Vector<File> mpqFiles) throws IOException {
			Vector<String> lines = new Vector<>();
	
			for (File mpqFile : mpqFiles) {
				JMpqEditor jmpq = new JMpqEditor(mpqFile);
		
				for (FileImport fileImport : fileImports) {
					jmpq.insertFile(fileImport.getOutFile().getAbsolutePath(), fileImport.getInFile(), false);
				}
				
				jmpq.close();
			}
		}
	
		public void commit(File mpqFile) throws IOException {
			Vector<File> mpqFiles = new Vector<>();
	
			mpqFiles.add(mpqFile);
	
			commit(mpqFiles);
		}
	}
	
	public static class MpqPortOut {
		private class FileExport {
			private File _inFile;
			private File _outFile = null;
			private boolean _outFileIsDir;
			private OutputStream _outStream = null;

			public File getInFile() {
				return _inFile;
			}

			public File getOutFile() {
				return _outFile;
			}

			public boolean isOutFileDir() {
				return _outFileIsDir;
			}
			
			public OutputStream getOutStream() {
				return _outStream;
			}
			
			public FileExport(File inFile, OutputStream outStream) {
				_inFile = inFile;
				_outStream = outStream;
			}
			
			public FileExport(File inFile, File outFile, boolean outFileIsDir) {
				_inFile = inFile;
				_outFile = outFile;
				_outFileIsDir = outFileIsDir;
			}
		}
	
		private Vector<FileExport> fileExports = new Vector<>();
	
		public void add(File inFile, OutputStream outStream) {
			fileExports.add(new FileExport(inFile, outStream));
		}
		
		public void add(File inFile, File outFile, boolean outFileIsDir) {
			fileExports.add(new FileExport(inFile, outFile, outFileIsDir));
		}
		
		public void clear() {
			fileExports.clear();
		}
	
		/*private void removeExistingExports(Vector<FileExport> volExports) {
			Vector<FileExport> toRemove = new Vector<>();
	
			for (FileExport fileExport : volExports) {
				if (fileExport.getOutFile().exists()) {
					toRemove.add(fileExport);
				}
			}
	
			volExports.removeAll(toRemove);
		}*/
	
		public void commit(Vector<File> mpqFiles) throws IOException {
			Orient.removeDir(exportDir);
			Orient.createDir(exportDir);
	
			Vector<FileExport> volExports = (Vector<FileExport>) fileExports.clone();
	
			int c = 0;

			while ((c < mpqFiles.size()) && (volExports.size() > 0)) {
				File mpqFile = mpqFiles.get(c);

				if (Orient.fileIsLocked(mpqFile)) {
					File tempFile = new File(tempDir, Orient.getFileName(mpqFile));
	
					Orient.copyFileIfNewer(mpqFile, tempFile);
	
					mpqFile = tempFile;
				}

				JMpqEditor jmpq = new JMpqEditor(mpqFile);
				
				Vector<FileExport> failedExports = new Vector<>();
	
				for (FileExport fileExport: volExports) {
					System.out.println("export " + fileExport.getInFile() + " from " + mpqFile);
					File outFile = fileExport.getOutFile();
					OutputStream outStream = fileExport.getOutStream();

					if (outFile != null) {
						if (fileExport.isOutFileDir()) {
							outFile = new File(outFile, fileExport.getInFile().getPath());
						}
						
						if (outFile.getParentFile() != null) {
							outFile.getParentFile().mkdirs();
						}
					}

					try {
						if (outStream != null) {
							jmpq.extractFile(fileExport.getInFile().getPath(), outStream);
						} else {
							jmpq.extractFile(fileExport.getInFile().getPath(), outFile);
						}
					} catch (JMpqException e) {
						failedExports.add(fileExport);
					}
				}
				
				//jmpq.close();
				
				volExports = failedExports;
				//removeExistingExports(volExports);
				
				failedExports.clear();
				
				c++;
			}
	
			for (FileExport fileExport : fileExports) {
				//Orient.moveFile(new File(exportDir, fileExport.getInFile().getName()), fileExport.getOutFile());
			}
		}
	
		public void commit(File mpqFile) throws IOException {
			Vector<File> mpqFiles = new Vector<>();
	
			mpqFiles.add(mpqFile);
	
			commit(mpqFiles);
		}
	}
	
	public static Vector<File> getWc3Mpqs(File wc3dir) {
		Vector<File> files = new Vector<>();

		files.add(new File(wc3dir, "War3Patch.mpq"));
		files.add(new File(wc3dir, "War3x.mpq"));
		files.add(new File(wc3dir, "war3.mpq"));

		return files;
	}
	
	public static void importFile(Vector<File> mpqFiles, File outFile, File inFile) throws IOException {
		MpqPortIn port = new MpqPortIn();
	
		port.add(outFile, inFile);
	
		port.commit(mpqFiles);
	}
	
	public static void importFile(File mpqFile, File outFile, File inFile) throws IOException {
		Vector<File> mpqFiles = new Vector<>();

		mpqFiles.add(mpqFile);

		importFile(mpqFiles, outFile, inFile);
	}
	
	public static void extractFile(Vector<File> mpqFiles, File inFile, File outFile, boolean outFileIsDir) throws IOException {
		MpqPortOut port = new MpqPortOut();
	
		port.add(inFile, outFile, outFileIsDir);
	
		port.commit(mpqFiles);
	}
	
	public static void extractFile(File mpqFile, File inFile, File outFile, boolean outFileIsDir) throws IOException {
		Vector<File> mpqFiles = new Vector<>();

		mpqFiles.add(mpqFile);

		extractFile(mpqFiles, outFile, inFile, outFileIsDir);
	}
}