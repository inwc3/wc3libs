package net.moonlightflower.wc3libs.port;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public abstract class MpqPort {		
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
		
		class FileExport {
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
	
		public abstract Result commit(Vector<File> mpqFiles) throws Exception;
	
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

		return files;
	}
}