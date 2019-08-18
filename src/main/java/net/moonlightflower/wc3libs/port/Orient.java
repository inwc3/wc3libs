package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class Orient {
	public static String separator = java.io.File.separator;

	public static String getSystem() {
		return System.getProperty("os.name");
	}

	public static boolean isMacSystem() {
		return getSystem().toLowerCase().startsWith("mac");
	}

	public static boolean isWindowsSystem() {
		return getSystem().toLowerCase().startsWith("win");
	}

  public static boolean isLinuxSystem() {
    return getSystem().toLowerCase().startsWith("lin");
  }

	public enum WinArch {
		X86,
		X64
	}

	public static WinArch getWinArch() {
		if (isWindowsSystem()) {
			if (System.getenv("ProgramFiles(x86)") != null) return WinArch.X64;
		}

		// default to x86
		return WinArch.X86;
	}

	@Nonnull
	public static File getExecPath(@Nullable Class<?> c) {
		if (c == null) c = Orient.class;

		try {
			URI uri = c.getProtectionDomain().getCodeSource().getLocation().toURI();

			return new File(uri);
		} catch (URISyntaxException | IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
	}

	@Nonnull
	public static File getExecPath() {
		return getExecPath(null);
	}

	@Nonnull
	public static File getExecDir(@Nullable Class<?> c) {
		return getExecPath(c).getParentFile();
	}

	@Nonnull
	public static File getExecDir() {
		return getExecDir(null);
	}

	@Nonnull
	public static String getWorkingDir() {
		return new File("").getAbsolutePath();
	}

	@Nonnull
	public static String getFileName(@Nonnull File file, boolean ignoreExtension) {
		String fileName = getFileName(file);

		if (ignoreExtension) {
			if (fileName.lastIndexOf(".") != -1) {
				fileName = fileName.substring(0, fileName.lastIndexOf(".") - 1);
			}
		}

		return fileName;
	}

	@Nonnull
	public static String getFileName(@Nonnull File file) {
		return file.toPath().getFileName().toString();
	}

	@Nullable
	public static String getFileExt(@Nonnull File file) {
		int index = file.getName().lastIndexOf('.');

		if (index < 0) return null;

		return file.getName().substring(index + 1);
	}

	@Nonnull
	public static File getDir(@Nonnull File file) {
		return file.getParentFile();
	}

	public static void checkFileExists(@Nonnull File file) throws IOException {
		if (!file.exists()) throw new IOException(String.format("file %s does not exist", file));
	}

	@Nonnull
	private static File localClassPath(int offset) {
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();

		return new File(traces[2 + offset].getClassName().replace(".", java.io.File.separator));
	}

	@Nonnull
	public static File localClassPath() {
		return localClassPath(1);
	}

	@Nonnull
	public static File localClassDir() {
		return localClassPath(1).getParentFile();
	}

	public static boolean fileIsLocked(@Nonnull File file) {
		FileChannel fileChannel = null;

		try {
			fileChannel = FileChannel.open(file.toPath(),  StandardOpenOption.READ);
		} catch (IOException e) {
			return true;
		}

		FileLock lock = null;

		try {
			lock = fileChannel.lock(0, Long.MAX_VALUE, true);
		} catch (Exception e) {
			return true;
		}

		if (!lock.isValid()) return true;

		try {
			fileChannel.close();
		} catch (IOException e) {
			return true;
		}

		return false;
	}

	public static void moveFile(@Nonnull File inFile, @Nonnull File outFile, boolean replace) throws IOException {
		if (replace) {
			Files.move(inFile.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} else {
			Files.move(inFile.toPath(), outFile.toPath());
		}
	}

	public static void copyFile(@Nonnull File inFile, @Nonnull File outFile, boolean replace) throws IOException {
		File outDir = outFile.getParentFile();

		if (outDir != null) outDir.mkdirs();

		if (replace) {
			Files.copy(inFile.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} else {
			Files.copy(inFile.toPath(), outFile.toPath());
		}
	}

	public static void copyFile(@Nonnull File inFile, @Nonnull File outFile) throws IOException {
		copyFile(inFile, outFile, false);
	}

	public static void copyFileIfNewer(@Nonnull File inFile, @Nonnull File outFile) throws IOException {
		long targetMod = outFile.lastModified();

		long sourceMod = inFile.lastModified();

		if (targetMod >= sourceMod) {
			return;
		}

		copyFile(inFile, outFile, true);
	}

	public static void removeDir(@Nonnull File f) {
		File[] sub = f.listFiles();

		if (sub != null) {
			for (File f2 : sub) {
				removeDir(f2);
			}
		}

		f.delete();
	}

	public static void removeDir(@Nonnull String path) {
		removeDir(new File(path));
	}

	public static void createDir(@Nonnull File f) {
		f.mkdirs();
	}

	public static void createDir(@Nonnull String path) {
		if (path.endsWith(separator)) {
			path = path.substring(0, path.length() - 1);
		}

		createDir(new File(path));
	}

	@Nonnull
	public static Vector<File> getFiles(@Nonnull File dir) {
		Vector<File> res = new Vector<>();

		File[] sub = dir.listFiles();

		if (sub != null) {
			for (File f2 : sub) {
				res.addAll(getFiles(f2));
			}
		} else {
			res.add(dir);
		}

		return res;
	}

	@Nonnull
	public static Vector<File> getFiles(@Nonnull File dir, @Nonnull String match) {
		Vector<File> ret = new Vector<>();

		File[] sub = dir.listFiles();

		if (sub != null) {
			for (File f : sub) {
				ret.addAll(getFiles(f));
			}
		}

		Vector<File> toRemove = new Vector<>();

		for (File f : ret) {
			String shortPath = f.getAbsolutePath().substring(dir.getAbsolutePath().length() + 1);

			if (!shortPath.matches(match)) {
				toRemove.add(f);
			}
		}

		ret.removeAll(toRemove);

		return ret;
	};

	public static boolean createFile(@Nonnull File file) throws IOException {
		File parentFile = file.getParentFile();

		if (parentFile != null) parentFile.mkdirs();

		return file.createNewFile();
	}

	public static FileOutputStream createFileOutputStream(@Nonnull File file) throws FileNotFoundException {
		File parentFile = file.getParentFile();

		if (parentFile != null) parentFile.mkdirs();

		return new FileOutputStream(file);
	}
}
