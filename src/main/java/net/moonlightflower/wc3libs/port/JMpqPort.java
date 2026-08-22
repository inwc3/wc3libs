package net.moonlightflower.wc3libs.port;

import org.inwc3.jmpq.MpqArchive;
import org.inwc3.jmpq.MpqArchiveWriter;
import org.inwc3.jmpq.MpqOpenOptions;
import org.inwc3.jmpq.MpqWriteOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@link MpqPort} backed by JMPQ3.
 * <p>
 * Reading and writing are separate concerns in JMPQ3 2.0: {@link MpqArchive}
 * opens an archive and never modifies it, and {@link MpqArchiveWriter} builds a
 * new image that is written only when asked. That is why {@link In} assembles
 * the whole archive in memory and writes it after closing the source: a
 * memory-mapped file cannot be replaced on Windows, and the rebuild-on-close
 * behaviour this replaces meant a read could rewrite the file it was reading.
 */
public class JMpqPort extends MpqPort {
	private final static Logger log = LoggerFactory.getLogger(JMpqPort.class);

	private final static File workDir = new File(Orient.getExecDir(), Orient.getExecPath().getName() + "_work");

	private final static File classWorkDir = new File(workDir, Orient.localClassPath().toString());

	private final static File tempDir = new File(classWorkDir, "temp");

	private final static File exportDir = new File(tempDir, "exported");

	/**
	 * Reads every archive the way Warcraft III reads its own: format version 0
	 * is forced, so a header whose size or version a protector mangled still
	 * opens. Everything this library touches is a Warcraft III archive, and
	 * none of them are large enough to need the 64-bit offsets a later format
	 * version exists to express.
	 */
	@Nonnull
	private static MpqOpenOptions readOptions() {
		return MpqOpenOptions.warcraft3();
	}

	/**
	 * Stores rather than compresses, and keeps the 512-byte header a map
	 * carries ahead of its archive. Both match what the previous
	 * rebuild-on-close did, and storing is the right trade for a build step's
	 * intermediate artefact.
	 */
	@Nonnull
	private static MpqWriteOptions writeOptions() {
		return MpqWriteOptions.defaults();
	}

	public static String enquote(@Nonnull String s) {
		return "\"" + s + "\"";
	}

	@Nonnull
	@Override
	public List<File> listFiles(@Nonnull File mpqFile) throws IOException {
		try (MpqArchive archive = MpqArchive.open(mpqFile.toPath(), readOptions())) {
			List<File> ret = new ArrayList<>();

			for (String name : archive.names()) {
				ret.add(new File(name));
			}

			// An archive with no usable (listfile) names nothing, yet still
			// serves the paths the game asks for by name. Recovering those
			// beats reporting an empty archive.
			if (!archive.isEnumerable()) {
				for (String name : War3MapFiles.paths()) {
					if (archive.contains(name)) ret.add(new File(name));
				}
			}

			return ret;
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
		/**
		 * Applies this port's imports and deletions to one archive, rebuilding
		 * it in place.
		 * <p>
		 * The image is assembled while the source is still open, because
		 * content is read from it lazily, and written once it is closed,
		 * because the source is the file being replaced.
		 */
		private void commitOne(@Nonnull File mpqFile) throws IOException {
			Path path = mpqFile.toPath();

			byte[] image = Files.exists(path) ? rebuild(path) : create();

			Files.write(path, image);
		}

		@Nonnull
		private byte[] rebuild(@Nonnull Path path) throws IOException {
			try (MpqArchive archive = MpqArchive.open(path, readOptions())) {
				MpqArchiveWriter writer = MpqArchiveWriter.from(archive, writeOptions());

				recoverUnnamedFiles(archive, writer);

				apply(writer);

				return writer.toByteArray();
			}
		}

		@Nonnull
		private byte[] create() throws IOException {
			MpqArchiveWriter writer = MpqArchiveWriter.create(writeOptions());

			apply(writer);

			return writer.toByteArray();
		}

		private void apply(@Nonnull MpqArchiveWriter writer) {
			for (FileImport fileImport : getFiles()) {
				// Naming here is inherited and reads backwards: getInFile() is
				// the path inside the archive, getOutFile() the local file to
				// take the bytes from, or null to delete.
				String name = fileImport.getInFile().toString();
				File source = fileImport.getOutFile();

				if (source != null) {
					writer.put(name, source.toPath());
				} else {
					writer.remove(name);
				}
			}
		}

		/**
		 * Carries over the standard map files an archive holds but cannot name.
		 * <p>
		 * A writer seeded from an archive copies what the archive can list, so
		 * injecting one file into a map whose {@code (listfile)} was stripped
		 * would otherwise discard its terrain, objects and script. These paths
		 * can be asked for by name even when nothing lists them.
		 */
		private static void recoverUnnamedFiles(@Nonnull MpqArchive archive, @Nonnull MpqArchiveWriter writer)
			throws IOException {
			if (archive.isEnumerable() && archive.filesLostOnRebuild() == 0) return;

			int recovered = 0;

			for (String name : War3MapFiles.paths()) {
				if (writer.contains(name) || !archive.contains(name)) continue;

				writer.put(name, archive.read(name));
				recovered++;
			}

			if (recovered > 0) {
				log.info("recovered {} unlisted map file(s) from {} by name", recovered, archive);
			}
		}

		@Override
		public void commit(@Nonnull Vector<File> mpqFiles) throws PortException {
			try {
				for (File mpqFile : mpqFiles) {
					commitOne(mpqFile);
				}
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

			List<FileExport> pending = new ArrayList<>(getFiles());
			Result result = new Result();

			for (File mpqFile : mpqFiles) {
				if (pending.isEmpty()) break;

				pending = exportFrom(mpqFile, pending, result);
			}

			return result;
		}

		/**
		 * @return the exports {@code mpqFile} could not satisfy, to be tried
		 *         against the next volume.
		 */
		@Nonnull
		private List<FileExport> exportFrom(@Nonnull File mpqFile,
											@Nonnull List<FileExport> exports,
											@Nonnull Result result) throws IOException {
			if (mpqFile instanceof ResourceFile resourceFile) return exportFromResources(resourceFile, exports, result);

			if (mpqFile.isDirectory()) return exportFromDir(mpqFile, exports, result);

			return exportFromArchive(mpqFile, exports, result);
		}

		/** Where one export's bytes come from, for a given volume. */
		@FunctionalInterface
		private interface Source {
			@Nonnull
			byte[] read(@Nonnull File inFile) throws IOException;
		}

		/**
		 * Reads each export's bytes out of one volume and delivers them.
		 * <p>
		 * Only the read is allowed to mean "not in this volume". Delivering the
		 * bytes is deliberately outside that guard: a full disk, a permission
		 * error or a broken destination stream is not the volume's fault, and
		 * treating it as one hid the failure, moved on to the next volume, and
		 * could append the same bytes twice after a partial write.
		 *
		 * @return the exports this volume did not have.
		 */
		@Nonnull
		private List<FileExport> exportEach(@Nonnull File volume,
											@Nonnull List<FileExport> exports,
											@Nonnull Result result,
											@Nonnull Function<FileExport, File> resolve,
											@Nonnull Source source) throws IOException {
			List<FileExport> failed = new ArrayList<>();

			for (FileExport fileExport : exports) {
				File inFile = resolve.apply(fileExport);

				byte[] bytes;

				try {
					bytes = source.read(inFile);
				} catch (IOException e) {
					log.debug("{} does not have {}", volume, fileExport.getInFile(), e);

					failed.add(fileExport);

					continue;
				}

				deliver(volume, fileExport, inFile, bytes, result);
			}

			return failed;
		}

		@Nonnull
		private List<FileExport> exportFromResources(@Nonnull ResourceFile source,
													@Nonnull List<FileExport> exports,
													@Nonnull Result result) throws IOException {
			return exportEach(source, exports, result,
				fileExport -> new File(source, fileExport.getInFile().toString()),
				inFile -> {
					try (InputStream inStream = openResource(inFile)) {
						return inStream.readAllBytes();
					}
				});
		}

		@Nonnull
		private List<FileExport> exportFromDir(@Nonnull File dir,
											   @Nonnull List<FileExport> exports,
											   @Nonnull Result result) throws IOException {
			return exportEach(dir, exports, result,
				fileExport -> new File(dir, fileExport.getInFile().toString()),
				inFile -> Files.readAllBytes(inFile.toPath()));
		}

		@Nonnull
		private List<FileExport> exportFromArchive(@Nonnull File mpqFile,
												   @Nonnull List<FileExport> exports,
												   @Nonnull Result result) throws IOException {
			File source = mpqFile;

			if (Orient.fileIsLocked(source)) {
				File tempFile = new File(tempDir, Orient.getFileName(source));

				Orient.copyFileIfNewer(source, tempFile);

				source = tempFile;
			}

			try (MpqArchive archive = MpqArchive.open(source.toPath(), readOptions())) {
				return exportEach(mpqFile, exports, result,
					FileExport::getInFile,
					inFile -> archive.read(inFile.toString()));
			}
		}

		/**
		 * Hands one file's bytes to wherever the export asked for them: a file
		 * on disk, a caller-supplied stream, or the result itself.
		 */
		private void deliver(@Nonnull File mpqFile,
							 @Nonnull FileExport fileExport,
							 @Nonnull File inFile,
							 @Nonnull byte[] bytes,
							 @Nonnull Result result) throws IOException {
			File outFile = fileExport.getOutFile();

			if (outFile != null) {
				if (fileExport.getOutDir() != null) fileExport.getOutDir().mkdirs();

				try (OutputStream outStream = Orient.createFileOutputStream(outFile)) {
					outStream.write(bytes);
				}

				result.addExport(mpqFile, new FileExport(inFile, outFile, false));

				return;
			}

			OutputStream outStream = fileExport.getOutStream();

			if (outStream != null) {
				// The stream belongs to the caller, who may be writing several
				// exports into one destination, so it is written to and flushed
				// but never closed.
				outStream.write(bytes);
				outStream.flush();
			}

			result.addExport(mpqFile, new FileExport(inFile, outStream), bytes);
		}

		@Nonnull
		private InputStream openResource(@Nonnull File inFile) throws IOException {
			// Resources are addressed by their full path with forward slashes.
			// Looking one up by file name alone, as this did, found nothing
			// whenever the resource was not at the class path root.
			String name = inFile.toString().replace(File.separatorChar, '/').replace('\\', '/');

			InputStream inStream = JMpqPort.class.getClassLoader().getResourceAsStream(name);

			if (inStream == null) throw new FileNotFoundException(name);

			return inStream;
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

		// The arguments used to be handed on swapped, which asked the archive
		// for the output path and wrote the result over the input path.
		extractFile(mpqFiles, inFile, outFile, outFileIsDir);
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
