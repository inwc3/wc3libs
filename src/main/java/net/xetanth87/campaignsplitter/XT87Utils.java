package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.bin.app.W3I;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class XT87Utils {
	public static final String STRING_PREFIX = "TRIGSTR_";
	public static final String IMPORT_DELIM = "\r";
	public static final String DATA_DELIM = "\n";
	public static final String PATH_PREFIX = "XT87CampaignSplitter";
	public static final String DISABLE_TRIGGER = "call DisableTrigger(";
	public static final String ENABLE_TRIGGER = "call EnableTrigger(";
	public static final String THIS_TRIGGER = "GetTriggeringTrigger()";
	public static final int MAX_PLAYER_COUNT = 24;
	public static Stack<File> createdFiles = new Stack<>();

	public enum TriOption {
		NO,
		SMART,
		YES
	}

	public static Set<Integer> getUsedPlayerNumbers(MapInjector mi) throws Exception {
		W3I info = W3I.ofMapFile(mi.mapFile);
		UsedPlayersScriptReader upsr = new UsedPlayersScriptReader(mi, info.getPlayers().stream()
				.map(W3I.Player::getNum).collect(Collectors.toSet()));
		boolean hadEditor = mi.mapEditor != null;
		if (!hadEditor)
			mi.initEditor();
		ScriptRewriter.readScript(mi);
		upsr.modifyScript();
		if (!hadEditor)
			mi.closeEditor();
		return upsr.getUsedSet();
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
		String defaultPath = isCampaignFile ? "war3campImported\\" : "war3mapImported\\";
		byte[] bytes = Files.readAllBytes(extractedFile.toPath());
		byte[] content = new byte[bytes.length - 8];
		for (int i = 0; i < content.length; i++)
			content[i] = bytes[i + 8];
		String s = new String(content, StandardCharsets.US_ASCII)
				.replace("\u0005", IMPORT_DELIM)
				.replace("\b" + defaultPath, IMPORT_DELIM + defaultPath)
				.replace("\b", IMPORT_DELIM + defaultPath)
				.replace("\0\0", "\0" + IMPORT_DELIM + defaultPath)
				.replace(defaultPath + "\0", "\b\0")
				.replace("\u0015", IMPORT_DELIM)
				.replace("\u001D", IMPORT_DELIM);

		try (FileOutputStream output = new FileOutputStream(extractedFile)) {
			output.write(Arrays.copyOf(bytes, 8));
		}

		FileWriter writer = new FileWriter(extractedFile, true);
		writer.append(s);
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

	public static W3I.Player clonePlayer(W3I.Player player, int number) {
		W3I.Player result = new W3I.Player();
		result.setNum(number);
		result.setName(player.getName());
		result.setRace(player.getRace());
		result.setType(player.getType());
		result.setAllyHighPrioFlags(player.getAllyHighPrioFlags());
		result.setAllyLowPrioFlags(player.getAllyLowPrioFlags());
		result.setEnemyHighPrioFlags(player.getEnemyHighPrioFlags());
		result.setEnemyLowPrioFlags(player.getEnemyLowPrioFlags());
		result.setStartPos(player.getStartPos());
		result.setStartPosFixed(player.getStartPosFixed());
		return result;
	}
}
