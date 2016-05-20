package net.moonlightflower.wc3libs.port;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Vector;

public class Orient {
	public static String separator = java.io.File.separator;

	public static File getExecPath() {
		File f;
		
		try {
			f = new File(Orient.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			
			return f;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static File getExecDir() {
		return getExecPath().getParentFile();
	}
	
	public static String getWorkingDir() {
		return new File("").getAbsolutePath();
	}
	
	public static String getFileName(File file, boolean ignoreExtension) {
		String fileName = getFileName(file);
		
		if (ignoreExtension) {
			if (fileName.lastIndexOf(".") != -1) {
				fileName = fileName.substring(0, fileName.lastIndexOf(".") - 1);
			}
		}
		
		return fileName;
	}

	public static String getFileName(File file) {
		return file.toPath().getFileName().toString();
	}

	public static File getDir(File file) {
		return file.getParentFile();
	}
	
	public static void log(String s) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("D:\\log.txt", true));
			
			writer.write(s);
			
			writer.newLine();
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static File localClassPath(int offset) {
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		
		return new File(traces[2 + offset].getClassName().replace(".", java.io.File.separator));
	}
	
	public static File localClassPath() {
		return localClassPath(1);
	}
	
	public static File localClassDir() {
		return localClassPath(1).getParentFile();
	}
	
	/*public static File localDir() {
		File ret = null;
		
		log(traces[2].getClassName().replace(".", java.io.File.separator));

		ret = new File(getExecPath(), traces[2].getClassName().replace(".", java.io.File.separator));

		log(ret.toString());
		
		assert (ret != null);

		return getDir(ret);
	}*/

	public static boolean fileIsLocked(File file) {
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

	public static void moveFile(File inFile, File outFile, boolean replace) throws IOException {
		if (replace) {
			Files.move(inFile.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} else {
			Files.move(inFile.toPath(), outFile.toPath());
		}
	}

	public static void copyFile(File inFile, File outFile, boolean replace) throws IOException {
		File outDir = outFile.getParentFile();
		
		if (outDir != null) outDir.mkdirs();
		
		if (replace) {
			Files.copy(inFile.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} else {
			Files.copy(inFile.toPath(), outFile.toPath());
		}
	}

	public static void copyFile(File inFile, File outFile) throws IOException {
		copyFile(inFile, outFile, false);
	}

	public static void copyFileIfNewer(File inFile, File outFile) throws IOException {
		long targetMod = outFile.lastModified();

		long sourceMod = inFile.lastModified();

		if (targetMod >= sourceMod) {
			return;
		}

		copyFile(inFile, outFile, true);
	}

	public static void removeDir(File f) {
		File[] sub = f.listFiles();

		if (sub != null) {
			for (File f2 : sub) {
				removeDir(f2);
			}
		}

		f.delete();
	}
	
	public static void removeDir(String path) {
		removeDir(new File(path)); 
	}

	public static void createDir(File f) {
		f.mkdirs();
	}

	public static void createDir(String path) {
		if (path.endsWith(separator)) {
			path = path.substring(0, path.length() - 1);
		}

		createDir(new File(path));
	}

	public static Vector<File> getFiles(File dir) {
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

	public static Vector<File> getFiles(File dir, String match) {
		Vector<File> res = new Vector<>();

		File[] sub = dir.listFiles();

		if (sub != null) {
			for (File f : sub) {
				res.addAll(getFiles(f));
			}
		}

		Vector<File> toRemove = new Vector<>();

		for (File f : res) {
			String shortPath = f.getAbsolutePath().substring(dir.getAbsolutePath().length() + 1);

			if (!shortPath.matches(match)) {
				toRemove.add(f);
			}
		}

		res.removeAll(toRemove);

		return res;
	};
}
