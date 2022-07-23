package net.xetanth87.campaignsplitter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public abstract class JassRewritter {
	public static final String JASS_DELIM = "\r\n";
	public static final String END_FUNCTION = "endfunction";
	public static final String INIT_CALL = "call ConditionalTriggerExecute(";
	public File extractedFile;

	public JassRewritter(File extractedFile)
	{
		this.extractedFile = extractedFile;
	}

	public String initializationTriggerFromCall(String line)
	{
		String s = line.trim().replace(INIT_CALL, "");
		s = s.substring(0, s.indexOf(')')).trim();
		return s;
	}

	public Set<String> findInitializationTriggers(String s)
	{
		Scanner sc = new Scanner(s);
		Set<String> initializationTriggers = new HashSet<>();
		boolean insideInitialization = false;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (insideInitialization)
			{
				if (line.equals(END_FUNCTION))
					insideInitialization = false;
				else if (line.contains(INIT_CALL))
					initializationTriggers.add(initializationTriggerFromCall(line));
			}
			else if (isInitFunction(line))
				insideInitialization = true;
		}
		sc.close();
		return initializationTriggers;
	}

	public static boolean isInitFunction(String line)
	{
		return line.contains("function RunInitializationTriggers takes") || line.contains("function main takes");
	}

	public abstract void onStartRead(String text, StringBuffer sb);
	public abstract void onReadLine(String line, StringBuffer sb);

	public void rewriteFile() throws IOException {
		Scanner sc = new Scanner(extractedFile);
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		while (sc.hasNextLine()) {
			if (first)
				first = false;
			else
				sb.append(JASS_DELIM);
			sb.append(sc.nextLine());
		}
		sc.close();
		String text = sb.toString();
		sc = new Scanner(text);
		sb = new StringBuffer();
		onStartRead(text, sb);
		while (sc.hasNextLine()) {
			onReadLine(sc.nextLine(), sb);
		}

		FileWriter writer = new FileWriter(extractedFile);
		writer.append(sb.toString());
		writer.close();
	}
}
