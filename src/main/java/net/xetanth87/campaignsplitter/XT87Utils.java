package net.xetanth87.campaignsplitter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class XT87Utils {
	public static final String STRING_PREFIX = "TRIGSTR_";
	public static final String IMPORT_DELIM = "\r";
	public static final String DATA_DELIM = "\n";
	public static final String PATH_PREFIX = "XT87CampaignSplitter";
	public static Stack<File> createdFiles = new Stack<>();

	public enum TriOption {
		NO,
		SMART,
		YES
	}

	public static String beautifyEnum(Enum e) {
		String s = e.toString().replace("_", " ");
		return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}

	public static void createNewFile(File file) throws IOException {
		file.createNewFile();
		createdFiles.push(file);
	}

	public static void createNewDir(File file) throws IOException {
		file.mkdir();
		createdFiles.push(file);
	}

	public static void deleteNewFiles() {
		while (!createdFiles.empty())
		{
			File file = createdFiles.pop();
			if (file.exists())
				file.delete();
		}
	}

	public static String zeroedNumber(int number, int digitCount)
	{
		String buttonIndexText = number + "";
		int addedZeroes = Math.max(digitCount - buttonIndexText.length(), 0);
		for (int i = 0; i < addedZeroes; i ++)
			buttonIndexText = "0" + buttonIndexText;
		return buttonIndexText;
	}

	public static boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}
		}
		return directoryToBeDeleted.delete();
	}

	public static void fixImportFile(File extractedFile, boolean isCampaignFile) throws IOException {
		Scanner sc = new Scanner(extractedFile);
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		while (sc.hasNextLine()) {
			if (first)
				first = false;
			else
				sb.append(IMPORT_DELIM);
			sb.append(sc.nextLine());
		}
		sc.close();

		String defaultPath = isCampaignFile ? "war3campImported\\" : "war3mapImported\\";
		String s = sb.toString();
		String x = s.substring(0, 8) + s.substring(8)
				.replace("\u0005", IMPORT_DELIM)
				.replace("\b" + defaultPath, IMPORT_DELIM + defaultPath)
				.replace("\b", IMPORT_DELIM + defaultPath)
				.replace("\0\0", "\0" + IMPORT_DELIM + defaultPath)
				.replace(defaultPath + "\0", "\b\0")
				.replace("\u0015", IMPORT_DELIM)
				.replace("\u001D", IMPORT_DELIM);

		FileWriter writer = new FileWriter(extractedFile);
		writer.append(x);
		writer.close();
	}

	public static int stringIndexToInt(String s) {
		return Integer.parseInt(s.replaceFirst(STRING_PREFIX, ""));
	}

	public static String buttonText(int buttonIndex, int buttonCount) {
		return zeroedNumber(buttonIndex + 1, (buttonCount + "").length()) + ". ";
	}

	public static String getWithoutExtension(String path) {
		return path.replaceFirst("[.][^.]+$", "");
	}

	public static String getExtension(String path) {
		return path.substring(path.lastIndexOf('.') + 1);
	}

	public static String offsetCampaignDataString(String string, int campaignKeyOffset) {
		int delimPos = 0;
		while (Character.isDigit(string.charAt(delimPos)))
			delimPos++;
		return XT87Utils.STRING_PREFIX + (Integer.parseInt(string.substring(0, delimPos)) + campaignKeyOffset) + string.substring(delimPos);
	}
}