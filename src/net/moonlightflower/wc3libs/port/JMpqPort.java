package net.moonlightflower.wc3libs.port;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import net.moonlightflower.wc3libs.port.MpqPort.In;
import net.moonlightflower.wc3libs.port.MpqPort.Out;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.JMpqException;

public final class JMpqPort {
	private final static File workDir = new File(Orient.getExecDir(), Orient.getExecPath().getName() + "_work");
	
	private final static File classWorkDir = new File(workDir, Orient.localClassPath().toString());
	
	private final static File tempDir = new File(classWorkDir, "temp");

	private final static File exportDir = new File(tempDir, "exported");
	
	public static String enquote(String s) {
		return "\"" + s + "\"";
	}
	
	public static class In extends MpqPort.In {	
		@Override
		public void commit(Vector<File> mpqFiles) throws IOException {
			Vector<String> lines = new Vector<>();
			
			for (File mpqFile : mpqFiles) {
				JMpqEditor jmpq = new JMpqEditor(mpqFile);
				
				for (FileImport fileImport : getFiles()) {
					//jmpq.insertFile(fileImport.getOutFile().getAbsolutePath(), fileImport.getInFile(), false);
					if (fileImport.getOutFile() != null) {
						jmpq.insertFile(fileImport.getInFile().toString(), fileImport.getOutFile(), false);
					} else {
						jmpq.deleteFile(fileImport.getInFile().toString());
					}
				}
				
				jmpq.close();
			}
		}
	}
	
	public static class Out extends MpqPort.Out {
		@Override
		public Result commit(Vector<File> mpqFiles) throws IOException {
			Orient.removeDir(exportDir);
			Orient.createDir(exportDir);
	
			Vector<FileExport> volExports = (Vector<FileExport>) getFiles().clone();
			Result result = new Result();
	
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

					try {
						File inFile = fileExport.getInFile();
						
						FileExport resultFileExport = null;
						
						if (outFile != null) {
							if (outFile != null) {
								if (fileExport.getOutDir() != null) {
									fileExport.getOutDir().mkdirs();
								}
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
					} catch (JMpqException e) {
						failedExports.add(fileExport);
					}
				}
				
				jmpq.close();
				
				volExports = failedExports;
				//removeExistingExports(volExports);
				
				failedExports.clear();
				
				c++;
			}
			
			return result;
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
		In port = new In();
	
		port.add(outFile, inFile);
	
		port.commit(mpqFiles);
	}
	
	public static void importFile(File mpqFile, File outFile, File inFile) throws IOException {
		Vector<File> mpqFiles = new Vector<>();

		mpqFiles.add(mpqFile);

		importFile(mpqFiles, outFile, inFile);
	}
	
	public static void extractFile(Vector<File> mpqFiles, File inFile, File outFile, boolean outFileIsDir) throws IOException {
		Out port = new Out();
	
		port.add(inFile, outFile, outFileIsDir);
	
		port.commit(mpqFiles);
	}
	
	public static void extractFile(File mpqFile, File inFile, File outFile, boolean outFileIsDir) throws IOException {
		Vector<File> mpqFiles = new Vector<>();

		mpqFiles.add(mpqFile);

		extractFile(mpqFiles, outFile, inFile, outFileIsDir);
	}
}