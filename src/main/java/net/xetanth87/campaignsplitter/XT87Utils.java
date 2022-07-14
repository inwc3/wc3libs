package net.xetanth87.campaignsplitter;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class XT87Utils {
	public static final String STRING_PREFIX = "TRIGSTR_";
	public static Stack<File> createdFiles = new Stack<>();

	public static void createNewFile(File file) throws IOException {
		file.createNewFile();
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
}
