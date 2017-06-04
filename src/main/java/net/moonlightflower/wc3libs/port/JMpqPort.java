package net.moonlightflower.wc3libs.port;

import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.JMpqException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class JMpqPort extends MpqPort {
	private final static File workDir = new File(Orient.getExecDir(), Orient.getExecPath().getName() + "_work");
	
	private final static File classWorkDir = new File(workDir, Orient.localClassPath().toString());
	
	private final static File tempDir = new File(classWorkDir, "temp");

	private final static File exportDir = new File(tempDir, "exported");
	
	public static String enquote(String s) {
		return "\"" + s + "\"";
	}
	
	@Override
	public List<File> listFiles(File mpqFile) throws IOException {
		try {
			JMpqEditor jmpq = new JMpqEditor(mpqFile);
			
			List<File> ret = new ArrayList<>();
			
			for (String fileS : jmpq.getFileNames()) {
				ret.add(new File(fileS));
			}
			
			jmpq.close();
			
			return ret;
		} catch (JMpqException e) {
			throw new IOException(e.getMessage());
		}
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
				
				jmpq.close(true, true, false);
			}
		}
	}
	
	public static class Out extends MpqPort.Out {
		@Override
		public Result commit(Vector<File> mpqFiles) throws Exception {
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
					//System.out.println("export " + fileExport.getInFile() + " from " + mpqFile);
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
					} catch (JMpqException e) {
						//System.err.println("failed " + fileExport.getInFile() + " at " + mpqFile);
						//e.printStackTrace();
						failedExports.add(fileExport);
					}
				}
				
				try {
					jmpq.close();
				} catch (Exception e) {
					//e.printStackTrace();
					
					//if (e instanceof IOException) throw e;
				}
				
				volExports = failedExports;
				//removeExistingExports(volExports);
				
				//failedExports.clear();
				
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
	
	public static void extractFile(Vector<File> mpqFiles, File inFile, File outFile, boolean outFileIsDir) throws Exception {
		Out port = new Out();
	
		port.add(inFile, outFile, outFileIsDir);
	
		port.commit(mpqFiles);
	}
	
	public static void extractFile(File mpqFile, File inFile, File outFile, boolean outFileIsDir) throws Exception {
		Vector<File> mpqFiles = new Vector<>();

		mpqFiles.add(mpqFile);

		extractFile(mpqFiles, outFile, inFile, outFileIsDir);
	}
	
	@Override
	public Out.Result getGameFiles(File... files) throws Exception {
		MpqPort.Out portOut = new Out();
		
		for (File file : files) {
			portOut.add(file);
		}
		
		return portOut.commit(MpqPort.getWc3Mpqs());
	}
}