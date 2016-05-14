package port;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

	public static String getExecPath() throws URISyntaxException {
		File f = new File(Orient.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		
		String res = f.getAbsolutePath();
		
		return res;
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
	
	public static File localDir()
	{
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		
		//String res = getWorkingDir() + java.io.File.separator + "src" + java.io.File.separator + traces[2].getClassName().replace(".", java.io.File.separator);
		String ret = null;

		try {
			ret = getExecPath() + java.io.File.separator + traces[2].getClassName().replace(".", java.io.File.separator);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assert (ret != null);

		return getDir(new File(ret));
	}

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

		return lock.isValid();
	}

	public static void moveFile(File inFile, File outFile) {
		try {
			Files.move(inFile.toPath(), outFile.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copyFile(String inPath, String outPath, boolean replace) {
		try {
			if (replace) {
				Files.copy(Paths.get(inPath), Paths.get(outPath), StandardCopyOption.REPLACE_EXISTING);
			} else {
				Files.copy(Paths.get(inPath), Paths.get(outPath));
			}
			
			File f = new File(outPath);
			
			BufferedReader out = null;

			try {
				out = new BufferedReader(new FileReader(f));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copyFile(String inPath, String outPath) {
		copyFile(inPath, outPath, false);
	}

	public static void copyFileIfNewer(File inFile, File outFile) {
		long targetMod = outFile.lastModified();

		long sourceMod = inFile.lastModified();

		if (targetMod >= sourceMod) {
			return;
		}

		try {
			Files.copy(inFile.toPath(), outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
