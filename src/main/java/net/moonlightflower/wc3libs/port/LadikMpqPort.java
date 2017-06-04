package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.misc.ProcCaller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LadikMpqPort extends MpqPort {
	private final static File workDir = new File(Orient.getExecDir(), Orient.getExecPath().getName() + "_work");
	
	private final static File classWorkDir = new File(workDir, Orient.localClassPath().toString());
	
	private final static File tempDir = new File(classWorkDir, "temp");
	
	private static File editorPath = new File(tempDir, "MPQEditor.exe");
	
	private final static File importDir = new File(tempDir, "import");
	
	private final static File importFilesDir = new File(importDir, "files");
	
	private final static File exportDir = new File(tempDir, "export");
	
	private final static File exportFilesDir = new File(exportDir, "files");
	
	private static String enquote(String s) {
		return "\"" + s + "\"";
	}
	
	private static String exec(Vector<String> lines) throws IOException {
		File scriptPath = File.createTempFile("temp", ".txt");
		System.out.println(scriptPath);
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.elementAt(i);
			
			line = line.replaceAll("\\\\\\\\", "\\");
			
			lines.set(i, line);
		}
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(scriptPath));
		
		for (String line : lines) {
			//line = line.replaceAll("\\\\", "/");
			
			writer.write(line);
			writer.newLine();
			Orient.log(line);
			
			//System.out.println(line);
		}
		
		writer.close();
		
		if (!editorPath.exists()) {
			editorPath.getParentFile().mkdirs();
			
			InputStream inStream = new LadikMpqPort() {}.getClass().getResourceAsStream("MPQEditor.exe");
			
			OutputStream outStream = new FileOutputStream(editorPath);
			
			int val;
			
			while ((val = inStream.read()) >= 0) {
				outStream.write(val);
			}
			
			outStream.close();
		}

		ProcCaller proc = new ProcCaller(editorPath.toString(), "/console", scriptPath.toString());
		
		proc.setMinimized(true);

		proc.exec();
		
		if (proc.exitVal() != 0) {
			throw new IOException("editor crashed");
		}
		
		return proc.getOutString();
		
		/*ProcessBuilder procBuild = new ProcessBuilder(editorPath.toString(), "/console", scriptPath.toString());
		
		Process proc = procBuild.start();

		//wtf? need to exhaust input stream else proc hangs
        InputStream in = proc.getInputStream();
        InputStream err = proc.getErrorStream();

        while ((in.read() != -1) || (err.read() != -1)) {}
		
		try {
			proc.waitFor();
		} catch (InterruptedException e) {
			throw new IOException(e.getMessage());
		}
		
		int exitVal = proc.exitValue();

		if (exitVal != 0) {
			throw new IOException("editor crashed");
		}*/
	}

	@Override
	public List<File> listFiles(File mpqFile) throws IOException {
		Vector<String> lines = new Vector<>();
		
		lines.add(String.format("list %s", enquote(mpqFile.getAbsolutePath())));
		
		String filesS = exec(lines);
		
		/*BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\list.txt")));
		
		writer.write(filesS);
		
		writer.close();*/
		
		Orient.log(filesS);
		
		List<File> files = new ArrayList<>();
		
		for (String fileS : filesS.split("\n")) {
			if (fileS.isEmpty() || fileS.contains("\"") || fileS.startsWith("(")) continue;
			
			files.add(new File(fileS));
		}
		
		return files;
	}

	private final static File getSizeDir = new File(tempDir, "_getSize");
	
	private static int mpqGetSize(File mpqFile) throws Exception {
		Orient.removeDir(getSizeDir);
		Orient.createDir(getSizeDir);
	
		Vector<String> lines = new Vector<>();
		
		lines.add(String.format("e %s * %s /fp", "%s", enquote(getSizeDir.getAbsolutePath())));
	
		for (int i = 0; i < lines.size(); i++) {
			lines.set(i, String.format(lines.get(i), enquote(mpqFile.getAbsolutePath())));
		}
	
		exec(lines);
	
		return Orient.getFiles(getSizeDir).size();
	}
	
	//private final static File delFile = File.createTempFile("delTemp", ".txt");//new File(Orient.localDir(), "del.txt");
	
	public static class In extends MpqPort.In {
		@Override
		public void commit(Vector<File> mpqFiles) throws Exception {
			if (getFiles().isEmpty()) return;
			if (mpqFiles.isEmpty()) return;
			
			Orient.removeDir(importDir);
			Orient.createDir(importDir);
			
			Orient.removeDir(importFilesDir);
			Orient.createDir(importFilesDir);
			
			File delFile = File.createTempFile("delTemp", ".txt");
			
			for (File mpqFile : mpqFiles) {
				Vector<String> lines = new Vector<>();
				
				for (FileImport fileImport : getFiles()) {
					if (fileImport.getOutFile() != null) {
						lines.add(String.format("a %s %s %s", "%s", enquote(fileImport.getOutFile().getAbsolutePath()), enquote(fileImport.getInFile().toString())));
					} else {
						//lines.add(String.format("n %s", enquote(delFile.getAbsolutePath())));
						//lines.add(String.format("a %s %s %s", "%s", enquote(delFile.getAbsolutePath()), enquote(fileImport.getInFile().getName())));
						lines.add(String.format("d %s %s", "%s", enquote(fileImport.getInFile().toString())));
					}
				}
				
				File importMpqFile = new File(importDir, mpqFile.getName());
				
				Orient.copyFile(mpqFile, importMpqFile, true);
				
				lines.add("f %s");
				
				for (int i = 0; i < lines.size(); i++) {
					lines.set(i, String.format(lines.get(i), enquote(importMpqFile.getAbsolutePath())));
				}
				
				exec(lines);
				
				Orient.moveFile(importMpqFile, mpqFile, true);
			}
		}
	}
	
	public static class Out extends MpqPort.Out {		
		@Override
		public Result commit(Vector<File> mpqFiles) throws IOException {
			if (getFiles().isEmpty()) return new Result();
			if (mpqFiles.isEmpty()) return new Result();
			
			/*double sizeD = Math.log(mpqGetSize(mpqFiles.firstElement())) / Math.log(2);
			
			if (sizeD > Math.floor(sizeD)) sizeD++;
			
			int size = (int) Math.pow(2, Math.floor(sizeD));
			
			if (size < 0x4) size = 0x4;*/
			
			Vector<String> lines = new Vector<>();
			
			/*lines.add("htsize %s" + String.format("0x%x", size));
			lines.add("compact %s");*/
			
			/*if (Orient.fileIsLocked(mpqFiles.firstElement())) {
				File tempFile = File.createTempFile(exportDir.getAbsolutePath(), ".mpq");
				
				Orient.copyFileIfNewer(mpqFiles.firstElement(), tempFile);
				
				mpqFiles.set(0, tempFile);
			}*/
			
			for (int i = 0; i < lines.size(); i++) {
				lines.set(i, String.format(lines.get(i), enquote(mpqFiles.firstElement().getAbsolutePath())));
			}
			
			//
			File listFile = File.createTempFile("listTemp", ".txt");
			
			String openLine = String.format("o %s %s", "%s", enquote(listFile.getAbsolutePath()));
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(listFile));
			
			for (FileExport fileExport : getFiles()) {
				writer.write(fileExport.getInFile().toString());
				writer.newLine();
			}
			
			writer.close();
			
			for (FileExport fileExport : getFiles()) {
				File outDir = fileExport.getOutDir();
				
				if (outDir != null) Orient.createDir(outDir);
			}
			
			Orient.removeDir(exportDir);
			//Orient.createDir(exportDir);
			
			Orient.removeDir(exportFilesDir);
			Orient.createDir(exportFilesDir);

			Vector<FileExport> volExports = (Vector<FileExport>) getFiles().clone();
			Result result = new Result();

			int c = 0;

			while ((c < mpqFiles.size()) && (volExports.size() > 0)) {
				File mpqFile = mpqFiles.get(c);

				File exportMpqFile = mpqFile;

				lines.clear();
				
				lines.add(openLine);
				
				for (FileExport fileExport : volExports) {
					File exportFile = new File(exportFilesDir, fileExport.getInFile().toString());
					
					//Orient.createDir(exportFile.getParent());
					
					lines.add(String.format("e %s %s %s /fp", "%s", enquote(fileExport.getInFile().toString()), enquote(exportFilesDir.getAbsolutePath())));
				}

				if (Orient.fileIsLocked(mpqFile)) {
					exportMpqFile = new File(exportDir, Orient.getFileName(mpqFile));

					if (!exportMpqFile.equals(mpqFile)) {
						Orient.copyFileIfNewer(mpqFile, exportMpqFile);
					}
				}
				
				for (int i = 0; i < lines.size(); i++) {
					lines.set(i, String.format(lines.get(i), enquote(exportMpqFile.getAbsolutePath())));
				}

				exec(lines);
				
				Vector<FileExport> failedExports = new Vector<>();
				
				for (FileExport fileExport : volExports) {
					File exportFile = new File(exportFilesDir, fileExport.getInFile().toString());

					if (exportFile.exists()) {
						File outFile = fileExport.getOutFile();
						
						if (outFile != null) {							
							Orient.moveFile(exportFile, fileExport.getOutFile(), true);
							
							result.addExport(mpqFile, fileExport);
						} else {							
							DummyOutputStream dummyStream = new DummyOutputStream();

							InputStream inStream = new FileInputStream(exportFile);
							
							int val;
							
							while ((val = inStream.read()) >= 0) {
								dummyStream.write(val);
							}
							
							inStream.close();
							
							OutputStream outStream = fileExport.getOutStream();
							
							if (outStream != null) {
								dummyStream.close(outStream);
							} else {
								dummyStream.close();
							}
							
							result.addExport(mpqFile, fileExport, dummyStream.getBytes());
						}
					} else {
						failedExports.add(fileExport);
					}
				}

				volExports = failedExports;
				
				c++;
			}
			
			return result;
		}
	}

	public static void importFile(Vector<File> mpqFiles, File outFile, File inFile) throws Exception {
		In port = new In();
	
		port.add(outFile, inFile);
	
		port.commit(mpqFiles);
	}
	
	public static void importFile(File mpqFile, File outFile, File inFile) throws Exception {
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
