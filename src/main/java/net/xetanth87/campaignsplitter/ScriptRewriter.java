package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.txt.app.jass.Jass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public abstract class ScriptRewriter {
	public static final String JASS_DELIM = "\r\n";
	public static final String END_FUNCTION = "endfunction";
	public static final String INIT_CALL = "call ConditionalTriggerExecute(";
	public MapInjector mi;

	public ScriptRewriter(MapInjector mi) {
		this.mi = mi;
	}

	public String initializationTriggerFromCall(String line) {
		String s = line.trim().replace(INIT_CALL, "");
		s = s.substring(0, s.indexOf(')')).trim();
		return s;
	}

	public Set<String> findInitializationTriggers(String s) {
		Scanner sc = new Scanner(s);
		Set<String> initializationTriggers = new HashSet<>();
		boolean insideInitialization = false;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (insideInitialization) {
				if (line.equals(END_FUNCTION))
					insideInitialization = false;
				else if (line.contains(INIT_CALL))
					initializationTriggers.add(initializationTriggerFromCall(line));
			} else if (isInitFunction(line))
				insideInitialization = true;
		}
		sc.close();
		return initializationTriggers;
	}

	public static boolean isInitFunction(String line) {
		return line.contains("function RunInitializationTriggers takes") || line.contains("function main takes");
	}

	public abstract void onStartRead(String text, StringBuffer sb);

	public abstract void onReadLine(String line, StringBuffer sb);

	public void rewriteFile(boolean isLua) throws IOException {
		Scanner sc = new Scanner(mi.tempFile);
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

		FileWriter writer = new FileWriter(mi.tempFile);
		writer.append(sb.toString());
		writer.close();
	}

	public static void rewrite(ScriptRewriter rewriter) {
		try {
			String path = null, tempPath, scriptsPath = "scripts\\", luaName = "war3map.lua";
			MapInjector mi = rewriter.mi;
			if (mi.mapEditor.hasFile(tempPath = Jass.GAME_PATH.getName()))
				path = tempPath;
			else if (mi.mapEditor.hasFile(tempPath = scriptsPath + Jass.GAME_PATH.getName()))
				path = tempPath;
			else if (mi.mapEditor.hasFile(luaName) || mi.mapEditor.hasFile(scriptsPath + luaName))
				throw new Exception("Difficulty selection won't be added to the map with lua script \"" + mi.mapFile.getName() + "\".");
			else
				throw new Exception("No JASS script found for map \"" + mi.mapFile.getName() + "\".");
			mi.mapEditor.extractFile(path, mi.tempFile);
			rewriter.rewriteFile(false);
			mi.mapEditor.insertFile(path, mi.tempFile, false, true);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
