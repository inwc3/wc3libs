package net.moonlightflower.wc3libs.port;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public abstract class MpqPort {
	private final static File workDir = new File(Orient.getExecDir(), Orient.getExecPath().getName() + "_work");
	
	private final static File classWorkDir = new File(workDir, Orient.localClassPath().toString());
	
	private final static File tempDir = new File(classWorkDir, "temp");
	
	private final static File importDir = new File(tempDir, "import");
	
	private final static File importFilesDir = new File(importDir, "files");
	
	private final static File exportDir = new File(tempDir, "export");
	
	private final static File exportFilesDir = new File(exportDir, "files");

	public abstract List<File> listFiles(File mpqFile) throws IOException;
	
	public static abstract class In {
		class FileImport {
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
	
		private Vector<FileImport> _fileImports = new Vector<>();
		
		public Vector<FileImport> getFiles() {
			return _fileImports;
		}
	
		public void add(File outPath, File inPath) {
			_fileImports.add(new FileImport(outPath, inPath));
		}
		
		public void addDel(File inPath) {
			add(null, inPath);
		}
		
		public void clear() {
			_fileImports.clear();
		}
	
		public abstract void commit(Vector<File> mpqFiles) throws Exception;
	
		public void commit(File mpqFile) throws Exception {
			Vector<File> mpqFiles = new Vector<>();
	
			mpqFiles.add(mpqFile);
	
			commit(mpqFiles);
		}
	}
	
	public static abstract class Out {
		class DummyOutputStream extends OutputStream {
			private ByteArrayOutputStream _outBytes = new ByteArrayOutputStream();
			
			public byte[] getBytes() {
				return _outBytes.toByteArray();
			}
			
			@Override
			public void write(int val) {
				_outBytes.write(val);
			}
			
			@Override
			public void close() throws IOException {
				super.close();
			}
			
			public void close(OutputStream outStream) throws IOException {
				for (byte val : getBytes()) {
					outStream.write(val);
				}
				
				super.close();
			}
			
			public DummyOutputStream() {
			}
		};
		
		public static class Result {
			public class Segment {
				private FileExport _fileExport;
				private File _mpqFile;
				
				public FileExport getExport() {
					return _fileExport;
				}
				
				public File getMpqFile() {
					return _mpqFile;
				}
				
				private byte[] _outBytes = null;
				
				public byte[] getOutBytes() {
					return _outBytes;
				}
				
				private Segment(File mpqFile, FileExport fileExport, byte[] outBytes) {
					_fileExport = fileExport;
					_mpqFile = mpqFile;
					_outBytes = outBytes;
				}
				
				private Segment(File mpqFile, FileExport fileExport) {
					_fileExport = fileExport;
					_mpqFile = mpqFile;
				}
			}
			
			private Map<File, Segment> _exports = new HashMap<>();
			
			public Map<File, Segment> getExports() {
				return _exports;
			}
			
			public File getFile(File inFile) throws IOException {
				Segment segment = getExports().get(inFile);
				
				if (segment == null) throw new IOException(String.format("noSuchFile: %s", inFile.toString()));
				
				File outFile = segment.getExport().getOutFile();
				
				if (outFile != null) {
					return outFile;
				}
				
				outFile = new File(exportFilesDir, inFile.getName());
				
				outFile.getParentFile().mkdirs();
				
				FileOutputStream writer = new FileOutputStream(outFile);
				
				byte[] outBytes = segment.getOutBytes();
				
				writer.write(outBytes, 0, outBytes.length);
System.out.println(outFile);				
				writer.close();
				
				return outFile;
			}
			
			public InputStream getInputStream(File inFile) throws IOException {
				Segment segment = getExports().get(inFile);
				
				if (segment == null) throw new IOException(String.format("noSuchFile: %s", inFile.toString()));
				
				File outFile = segment.getExport().getOutFile();
				
				if (outFile != null) {
					return new FileInputStream(outFile);
				}
				
				byte[] outBytes = segment.getOutBytes();
				
				if (outBytes != null) {
					return new ByteArrayInputStream(outBytes);
				}
				
				return null;
			}
			
			public void addExport(File mpqFile, FileExport fileExport, byte[] outBytes) {
				_exports.put(fileExport.getInFile(), new Segment(mpqFile, fileExport, outBytes));
			}
			
			public void addExport(File mpqFile, FileExport fileExport) {
				_exports.put(fileExport.getInFile(), new Segment(mpqFile, fileExport));
			}
		}
		
		public class FileExport {
			private File _inFile;
			private File _outDir = null;
			private File _outFile = null;
			private OutputStream _outStream = null;

			public File getInFile() {
				return _inFile;
			}

			public File getOutDir() {
				return _outDir;
			}
			
			public File getOutFile() {
				return _outFile;
			}
			
			public OutputStream getOutStream() {
				return _outStream;
			}
			
			public FileExport(File inFile) {
				_inFile = inFile;
			}
			
			public FileExport(File inFile, OutputStream outStream) {
				_inFile = inFile;
				_outStream = outStream;
			}
			
			public FileExport(File inFile, File outFile, boolean outFileIsDir) {
				_inFile = inFile;
				_outFile = outFile;
				
				if (outFileIsDir) _outFile = new File(_outFile, _inFile.toString());
				
				_outDir = _outFile.getParentFile();
			}
		}
	
		private Vector<FileExport> _fileExports = new Vector<>();
	
		public Vector<FileExport> getFiles() {
			return _fileExports;
		}
		
		public void add(File inFile) {
			_fileExports.add(new FileExport(inFile));
		}
		
		public void add(File inFile, OutputStream outStream) {
			_fileExports.add(new FileExport(inFile, outStream));
		}
		
		public void add(File inFile, File outFile, boolean outFileIsDir) {
			_fileExports.add(new FileExport(inFile, outFile, outFileIsDir));
		}
		
		public void clear() {
			_fileExports.clear();
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
	
		public abstract Result commit(Vector<File> mpqFiles) throws IOException;
	
		public Result commit(File mpqFile) throws Exception {
			Vector<File> mpqFiles = new Vector<>();
	
			mpqFiles.add(mpqFile);
	
			return commit(mpqFiles);
		}
	}
	
	public static Vector<File> getWc3Mpqs(File wc3dir) {
		Vector<File> files = new Vector<>();

		files.add(new File(wc3dir, "War3Patch.mpq"));
		files.add(new File(wc3dir, "War3x.mpq"));
		files.add(new File(wc3dir, "war3.mpq"));
		files.add(new File(wc3dir, "War3xlocal.mpq"));

		return files;
	}
	
	private static File _wc3Dir = null;
	
	public static File getWc3Dir() {
		return _wc3Dir;
	}
	
	public static void setWc3Dir(File val) {		
		for (File mpqFile : getWc3Mpqs(val)) {
			if (!mpqFile.exists()) {
				throw new IllegalArgumentException(String.format("%s does not seem to be a wc3 directory (%s does not exist)", val, mpqFile));
			}
		}
		
		_wc3Dir = val;
	}
	
	public static Vector<File> getWc3Mpqs() throws IOException {
		if (_wc3Dir == null) throw new IOException("no wc3Dir set");
		
		return getWc3Mpqs(_wc3Dir);
	}
	
	public abstract Out.Result getGameFiles(File... files) throws IOException;
	
	private static Class<? extends MpqPort> _defaultImpl = LadikMpqPort.class;
	
	public static MpqPort getDefaultImpl() {
		try {
			return _defaultImpl.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void setDefaultImpl(Class<? extends MpqPort> type) {
		_defaultImpl = type;
	}
	
	static {
		String dirS = Registry.get("HKCU\\Software\\Blizzard Entertainment\\Warcraft III", "InstallPath");
		
		if (dirS == null) dirS = Registry.get("HKCU\\Software\\Blizzard Entertainment\\Warcraft III", "InstallPathX");
		
		if (dirS == null) dirS = Registry.get("HKLM\\Software\\Blizzard Entertainment\\Warcraft III", "InstallPath");
		
		if (dirS == null) dirS = Registry.get("HKLM\\Software\\Blizzard Entertainment\\Warcraft III", "InstallPathX");
		
		if (dirS == null) dirS = Registry.get("HKLM\\Software\\Blizzard Entertainment\\Warcraft III", "War3InstallPath");
		
		setWc3Dir(new File(dirS));
	}
}