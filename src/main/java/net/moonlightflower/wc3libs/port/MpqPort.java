package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.bin.GameExe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MpqPort {
	private final static File workDir = new File(Orient.getExecDir(), Orient.getExecPath().getName() + "_work");

	private final static File classWorkDir = new File(workDir, Orient.localClassPath().toString());

	private final static File tempDir = new File(classWorkDir, "temp");

	private final static File importDir = new File(tempDir, "import");

	private final static File importFilesDir = new File(importDir, "files");

	private final static File exportDir = new File(tempDir, "export");

	private final static File exportFilesDir = new File(exportDir, "files");

	public static class ResourceFile extends File {
		public ResourceFile(@Nonnull String pathname) {
			super(pathname);
		}

		public ResourceFile(@Nonnull File parent, @Nonnull String child) {
			super(parent, child);
			// TODO Auto-generated constructor stub
		}
	}

	@Nonnull
	public abstract List<File> listFiles(@Nonnull File mpqFile) throws IOException;

	public static abstract class In {
		public class FileImport {
			private File _outFile;
			private File _inFile;

			public File getOutFile() {
				return _outFile;
			}

			public File getInFile() {
				return _inFile;
			}

			public FileImport(@Nullable File outPath, @Nonnull File inPath) {
				_outFile = outPath;
				_inFile = inPath;
			}
		}

		private Vector<FileImport> _fileImports = new Vector<>();

		@Nonnull
		public Vector<FileImport> getFiles() {
			return new Vector<>(_fileImports);
		}

		public void add(@Nullable File outPath, @Nonnull File inPath) {
			_fileImports.add(new FileImport(outPath, inPath));
		}

		public void addDel(@Nonnull File inPath) {
			add(null, inPath);
		}

		public void clear() {
			_fileImports.clear();
		}

		public abstract void commit(@Nonnull Vector<File> mpqFiles) throws Exception;

		public void commit(@Nonnull File mpqFile) throws Exception {
			Vector<File> mpqFiles = new Vector<>();

			mpqFiles.add(mpqFile);

			commit(mpqFiles);
		}
	}

	public abstract In createIn();

	public static class PortException extends IOException {
		public PortException(@Nonnull String msg) {
			super(msg);
		}

		public PortException(@Nonnull IOException e) {
			super(e);
		}
	}

	public static abstract class Out {
		class DummyOutputStream extends OutputStream {
			private final ByteArrayOutputStream _outBytes = new ByteArrayOutputStream();

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

			public void close(@Nonnull OutputStream outStream) throws IOException {
				for (byte val : getBytes()) {
					outStream.write(val);
				}

				super.close();
			}

			public DummyOutputStream() {
			}
		}

		public static class Result {
			public class Segment {
				private final FileExport _fileExport;
				private final File _mpqFile;

				@Nonnull
				public FileExport getExport() {
					return _fileExport;
				}

				@Nonnull
				public File getMpqFile() {
					return _mpqFile;
				}

				private byte[] _outBytes = null;

				public byte[] getOutBytes() {
					return Arrays.copyOf(_outBytes, _outBytes.length);
				}

				private Segment(@Nonnull File mpqFile, @Nonnull FileExport fileExport, byte[] outBytes) {
					_fileExport = fileExport;
					_mpqFile = mpqFile;
					_outBytes = outBytes;
				}

				private Segment(@Nonnull File mpqFile, @Nonnull FileExport fileExport) {
					_fileExport = fileExport;
					_mpqFile = mpqFile;
				}
			}

			private Map<File, Segment> _exports = new LinkedHashMap<>();

			@Nonnull
			public Map<File, Segment> getExports() {
				return _exports;
			}

			@Nullable
			public File getFile(@Nonnull File inFile) throws IOException {
				Segment segment = getExports().get(inFile);

				if (segment == null) throw new NoSuchFileException(String.format("noSuchFile: %s", inFile.toString()));

				File outFile = segment.getExport().getOutFile();

				if (outFile != null) {
					return outFile;
				}

				outFile = new File(exportFilesDir, inFile.getName());

				outFile.getParentFile().mkdirs();

				FileOutputStream writer = new FileOutputStream(outFile);

				byte[] outBytes = segment.getOutBytes();

				writer.write(outBytes, 0, outBytes.length);

				writer.close();

				return outFile;
			}

			@Nullable
			public InputStream getInputStream(@Nonnull File inFile) throws IOException {
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

			public void addExport(@Nonnull File mpqFile, @Nonnull FileExport fileExport, byte[] outBytes) {
				_exports.put(fileExport.getInFile(), new Segment(mpqFile, fileExport, outBytes));
			}

			public void addExport(@Nonnull File mpqFile, @Nonnull FileExport fileExport) {
				_exports.put(fileExport.getInFile(), new Segment(mpqFile, fileExport));
			}
		}

		public class FileExport {
			private File _inFile;
			private File _outDir = null;
			private File _outFile = null;
			private OutputStream _outStream = null;

			@Nonnull
			public File getInFile() {
				return _inFile;
			}

			@Nullable
			public File getOutDir() {
				return _outDir;
			}

			@Nullable
			public File getOutFile() {
				return _outFile;
			}

			@Nullable
			public OutputStream getOutStream() {
				return _outStream;
			}

			public FileExport(@Nonnull File inFile) {
				_inFile = inFile;
			}

			public FileExport(@Nonnull File inFile, @Nullable OutputStream outStream) {
				_inFile = inFile;
				_outStream = outStream;
			}

			public FileExport(@Nonnull File inFile, @Nonnull File outFile, boolean outFileIsDir) {
				_inFile = inFile;
				_outFile = outFile;

				if (outFileIsDir) _outFile = new File(_outFile, _inFile.toString());

				_outDir = _outFile.getParentFile();
			}
		}

		private Vector<FileExport> _fileExports = new Vector<>();

		@Nonnull
		public Vector<FileExport> getFiles() {
			return _fileExports;
		}

		public void add(@Nonnull File inFile) {
			_fileExports.add(new FileExport(inFile));
		}

		public void add(@Nonnull File inFile, @Nonnull OutputStream outStream) {
			_fileExports.add(new FileExport(inFile, outStream));
		}

		public void add(@Nonnull File inFile, @Nonnull File outFile, boolean outFileIsDir) {
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

		@Nonnull
		public abstract Result commit(@Nonnull Vector<File> mpqFiles) throws IOException;

		@Nonnull
		public Result commit(@Nonnull File mpqFile) throws IOException {
			Vector<File> mpqFiles = new Vector<>();

			mpqFiles.add(mpqFile);

			return commit(mpqFiles);
		}
	}

	public abstract Out createOut();

	protected static GameVersion getGameVersion() throws NotFoundException {
		return Context.getService(GameVersionFinder.class).get();
	}

	@Nonnull
	public static Vector<File> getWar3Mpqs(@Nonnull File war3dir) throws NotFoundException {
		Vector<File> files = new Vector<>();

		GameVersion gameVersion = getGameVersion();

		if (gameVersion != null && gameVersion.compareTo(GameVersion.VERSION_1_29) < 0) {
			files.add(new File(war3dir, War3MPQs.WAR3PATCH.toString()));
		}

		files.add(new File(war3dir, War3MPQs.WAR3X.toString()));
		files.add(new File(war3dir, War3MPQs.WAR3.toString()));
		files.add(new File(war3dir, War3MPQs.WAR3X_LOCAL.toString()));

		return files.stream().filter(File::exists).collect(Collectors.toCollection(Vector::new));
	}

	@Nonnull
	public static Vector<File> getWar3Mpqs() throws IOException, NotFoundException {
		GameDirFinder finder = Context.getService(GameDirFinder.class);

		File war3dir = finder.get();

		return getWar3Mpqs(war3dir);
	}

	@Nonnull
	public abstract Out.Result getGameFiles(@Nonnull File... files) throws IOException, NotFoundException;
}
