package archive;

public class _MpqPort {
	/*public class Mpq {
	public static String enquote(String s) {
		return "\"" + s + "\"";
	}

	public void exec2() {
		String portDataScriptPath = Orient.localDir() + "portDataScript.txt";

		ProcessBuilder pb = new ProcessBuilder(Orient.localDir() + "MPQEditor.exe", "/console", portDataScriptPath);

		try {
			Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void exec(Vector<String> lines) {
		String portDataScriptPath = Orient.localDir() + "portDataScript.txt";
		
		File f = new File(portDataScriptPath);
		
		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(f));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (String line : lines) {
			try {
				out.write(line);
				out.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProcessBuilder pb = new ProcessBuilder(Orient.localDir() + "MPQEditor.exe", "/console", portDataScriptPath);

		try {
			Process p = pb.start();

			p.waitFor();

			p.destroy();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	String tempDir = Orient.localDir() + "Temp" + java.io.File.separator;

	String getSizeDir = tempDir + "_getSize" + java.io.File.separator;
	String exportDir = tempDir + "_export" + java.io.File.separator;

	int getSize(String mpqPath) {
		Orient.removeDir(getSizeDir);

		Orient.createDir(getSizeDir);

		Vector<String> lines = new Vector<String>();

		lines.add("e " + enquote(mpqPath) + " * " + enquote(getSizeDir) + " /fp");

		exec(lines);

		return Orient.getFiles(new File(getSizeDir), ".*").size();
	}

	public void compact(Vector<String> mpqPaths) {
		Vector<String> lines = new Vector<String>();

		for (String mpqPath : mpqPaths) {
			int size = (int) Math.ceil(Math.log(getSize(mpqPath)) / Math.log(2));

			size = (int) Math.pow(2, size);

			if (size < 0x4) {
				size = 0x4;
			}

			String sizeHex = String.format("0x%x", size);

			lines.add("htsize " + enquote(mpqPath) + " " + sizeHex);
			lines.add("compact " + enquote(mpqPath));
		}

		exec(lines);
	}

	public class MpqPortDel {
		class FileDel {
			public String path;

			FileDel(String _path) {
				path = _path;
			}
		}

		Vector<FileDel> fileDels = new Vector<FileDel>();

		public void add(String path) {
			fileDels.add(new FileDel(path));
		}

		public void commit(Vector<String> mpqPaths) {
			Vector<String> lines = new Vector<String>();
	
			for (String mpqPath : mpqPaths) {
				File f = new File(Orient.localDir() + "del.txt");

				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (FileDel fileDel : fileDels) {
					lines.add("a " + enquote(mpqPath) + " " + enquote(Orient.localDir() + "del.txt") + " " + enquote(fileDel.path));
					lines.add("d " + enquote(mpqPath) + " " + enquote(fileDel.path));
				}
			}

			exec(lines);
		}
	
		public void commit(String mpqPath) {
			Vector<String> mpqPaths = new Vector<String>();
	
			mpqPaths.add(mpqPath);
	
			commit(mpqPaths);
		}
	}

	public class MpqPortIn {
		class FileImport {
			public String outPath;
			public String inPath;
	
			FileImport(String _outPath, String _inPath) {
				outPath = _outPath;
				inPath = _inPath;
			}
		}
	
		Vector<FileImport> fileImports = new Vector<FileImport>();
	
		public void add(String outPath, String inPath) {
			fileImports.add(new FileImport(outPath, inPath));
		}
	
		public void commit(Vector<String> mpqPaths) {
			Vector<String> lines = new Vector<String>();
	
			for (String mpqPath : mpqPaths) {
				lines.add("o " + enquote(mpqPath));
		
				for (FileImport fileImport : fileImports) {
					lines.add("a " + enquote(mpqPath) + " " + enquote(fileImport.outPath) + " " + enquote(fileImport.inPath));
				}
			}

			exec(lines);

			compact(mpqPaths);
		}
	
		public void commit(String mpqPath) {
			Vector<String> mpqPaths = new Vector<String>();
	
			mpqPaths.add(mpqPath);
	
			commit(mpqPaths);
		}
	}

	public class MpqPortOut {
		class FileExport {
			public String inPath;
			public String outPath;
	
			FileExport(String _inPath, String _outPath) {
				outPath = _inPath;
				inPath = _outPath;
			}
		}

		Vector<FileExport> fileExports = new Vector<FileExport>();

		public void add(String inPath, String outPath) {
			fileExports.add(new FileExport(outPath, inPath));
		}

		void removeExistingExports(Vector<FileExport> volExports) {
			Vector<FileExport> toRemove = new Vector<FileExport>();

			for (FileExport fileExport : volExports) {
				if (new File(fileExport.outPath).exists()) {
					toRemove.add(fileExport);
				}
			}

			volExports.removeAll(toRemove);
		}

		public void commit(Vector<String> mpqPaths) {
			Orient.removeDir(exportDir);
			Orient.createDir(exportDir);

			Vector<String> lines = new Vector<String>();

			for (String mpqPath : mpqPaths) {
				lines.add("o " + enquote(mpqPath));
		
				for (FileExport fileExport : fileExports) {
					lines.add("e " + enquote(mpqPath) + " " + enquote(fileExport.inPath) + " " + enquote(exportDir) + " /fp");
				}
			}

			exec(lines);

			Vector<FileExport> volExports = (Vector<FileExport>) fileExports.clone();

			removeExistingExports(volExports);

			int c = 2;

			while ((c < mpqPaths.size()) && (volExports.size() > 0)) {
				String mpqPath = mpqPaths.get(c);
				lines.clear();

				if (Orient.fileIsLocked(mpqPath)) {
					String tempPath = tempDir + Orient.getFileName(mpqPath);

					Orient.copyFileIfNewer(mpqPath, tempPath);

					mpqPath = tempPath;
				}

				for (FileExport fileExport: volExports) {
					lines.add("e " + enquote(mpqPath) + " " + enquote(fileExport.inPath) + " " + enquote(Orient.getDir(fileExport.outPath)) + " /fp");
				}

				exec(lines);
				
				removeExistingExports(volExports);
			}

			for (FileExport fileExport : fileExports) {
				Orient.moveFile(exportDir + fileExport.inPath, fileExport.outPath);
			}
		}

		public void commit(String mpqPath) {
			Vector<String> mpqPaths = new Vector<String>();
	
			mpqPaths.add(mpqPath);
	
			commit(mpqPaths);
		}
	}

	public static void importFile(String mpqPath, String outPath, String inPath) {
		MpqPortIn port = new Mpq().new MpqPortIn();

		port.add(outPath, inPath);

		port.commit(mpqPath);
	}

	public static void extractFile(String mpqPath, String inPath, String outPath) {
		MpqPortOut port = new Mpq().new MpqPortOut();

		port.add(inPath, outPath);

		port.commit(mpqPath);
	}
}*/
}
