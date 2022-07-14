package net.xetanth87.campaignsplitter;

import javafx.util.Pair;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3F;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.bin.app.IMP;
import net.moonlightflower.wc3libs.dataTypes.app.Controller;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.WTS;
import net.moonlightflower.wc3libs.txt.app.MiscTXT;
import net.moonlightflower.wc3libs.txt.app.SkinTXT;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class CampaignSplitter<T> {
	public static final String IMPORT_DELIM = "\r";
	public static final String DATA_DELIM = "\n";

	private static String getWithoutExtension(File file) {
		return file.getName().replaceFirst("[.][^.]+$", "");
	}

	public static void insertData(ObjMod mapData, String fileName, JMpqEditor mapEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		XT87Utils.createNewFile(insertedFile);
		Wc3BinOutputStream bos = new Wc3BinOutputStream(insertedFile);
		mapData.write(bos);
		bos.close();
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static String offsetCampaignDataString(String string, int campaignKeyOffset) {
		int nullPos = string.indexOf(0);
		return XT87Utils.STRING_PREFIX + (Integer.parseInt(string.substring(0, nullPos)) + campaignKeyOffset) + string.substring(nullPos);
	}

	public static void offsetCampaignDataStrings(File extractedFile, int campaignKeyOffset) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(extractedFile), StandardCharsets.ISO_8859_1));
		StringBuffer sb = new StringBuffer();
		int s1 = 0;
		while ((s1 = br.read()) != -1) {
			sb.append((char) s1);
		}
		br.close();
		String s = sb.toString();

		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(extractedFile), StandardCharsets.ISO_8859_1);
		String[] splitStrings = s.split(XT87Utils.STRING_PREFIX);
		if (s.startsWith(XT87Utils.STRING_PREFIX))
			splitStrings[0] = offsetCampaignDataString(splitStrings[0], campaignKeyOffset);
		for (int i = 1; i < splitStrings.length; i++)
			splitStrings[i] = offsetCampaignDataString(splitStrings[i], campaignKeyOffset);
		writer.append(String.join("", splitStrings));
		writer.close();
	}

	public static void mergeData(ObjMod data, File mapFile, JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath, int campaignKeyOffset) throws IOException, NoSuchFieldException, IllegalAccessException {
		String gamePath = ((File) data.getClass().getField("GAME_PATH").get(null)).getName();
		String campaignPath = ((File) data.getClass().getField("CAMPAIGN_PATH").get(null)).getName();
		File extractedFile = new File(tempPath + "_" + gamePath);
		XT87Utils.createNewFile(extractedFile);
		ObjMod mapData = null;
		Wc3BinInputStream bis = null;
		try {
			campEditor.extractFile(campaignPath, extractedFile);
			offsetCampaignDataStrings(extractedFile, campaignKeyOffset);
			bis = new Wc3BinInputStream(extractedFile);
			data.read(bis);
			bis.close();
			if (data == null)
				throw new IOException();
			extractedFile.delete();
		} catch (Exception e) {
			extractedFile.delete();
			if (bis != null)
				bis.close();
			return;
		}
		try {
			mapData = data.ofMapFile(data.getClass(), mapFile);
			if (mapData == null)
				throw new IOException();
			List<ObjMod.Obj> campaignList = data.getObjsList();
			List<ObjMod.Obj> tempList = new ArrayList<>(campaignList);
			boolean found;
			for (Object object : mapData.getObjsList()) {
				found = false;
				ObjMod.Obj obj = (ObjMod.Obj)object;
				for (int i = 0; i < tempList.size(); i++) {
					ObjMod.Obj campaignObj = (ObjMod.Obj)tempList.get(i);
					if (!campaignObj.getId().equals(obj.getId()))
						continue;
					found = true;
					for (ObjMod.Obj.Mod mod : obj.getMods()) {
						for (ObjMod.Obj.Mod cmod : campaignObj.getMods())
							if (cmod.getId().equals(mod.getId())) {
								campaignObj.getMods().remove(cmod);
								break;
							}
						campaignObj.addMod(mod);
					}
					break;
				}
				if (!found)
					campaignList.add(obj);
			}
		} catch (Exception e) {
		}

		insertData(data, gamePath, mapEditor, tempPath);
	}

	public static void fixImportFile(File extractedFile) throws IOException {
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
		String s = sb.toString();

		//byte[] encoded = Files.readAllBytes(Paths.get(extractedFile.getAbsolutePath().toString()));
		FileWriter writer = new FileWriter(extractedFile);
		//String s = new String(encoded, StandardCharsets.US_ASCII);
		String x = s
				.replaceAll("\0\b", "\0" + IMPORT_DELIM + "war3campImported\\\\")
				.replaceAll("\t", IMPORT_DELIM)
				.replaceAll("\u0015", IMPORT_DELIM)
				.replaceAll("\u001D", IMPORT_DELIM)
				.replaceAll("\u001D", IMPORT_DELIM)
				;
		writer.append(x);
		writer.close();
	}

	public static void insertImports(IMP imports, String fileName, JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		XT87Utils.createNewFile(insertedFile);
		Wc3BinOutputStream bos = new Wc3BinOutputStream(insertedFile);
		imports.write(bos);
		bos.close();
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
		for (IMP.Obj o : imports.getObjs()) {
			if (mapEditor.hasFile(o.getPath()) || !campEditor.hasFile(o.getPath()))
				continue;
			insertedFile = new File(tempPath + "_i_" + fileName);
			campEditor.extractFile(o.getPath(), insertedFile);
			mapEditor.insertFile(o.getPath(), insertedFile, false, false);
			insertedFile.delete();
		}
	}

	public static void mergeImports(JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File extractedFile = new File(tempPath + "_" + IMP.GAME_PATH);
		XT87Utils.createNewFile(extractedFile);
		IMP imports;
		try {
			campEditor.extractFile(IMP.CAMPAIGN_PATH.getName(), extractedFile);
			fixImportFile(extractedFile);
			imports = new IMP(extractedFile);
			extractedFile.delete();
		} catch (Exception e) {
			extractedFile.delete();
			return;
		} finally {
		}
		try {
			mapEditor.extractFile(IMP.GAME_PATH, extractedFile);
			fixImportFile(extractedFile);
			IMP mapImports = new IMP(extractedFile);
			extractedFile.delete();
			for (IMP.Obj o : mapImports.getObjs())
				imports.addObj(o);
		} catch (Exception e) {
			extractedFile.delete();
		} finally {
		}

		insertImports(imports, IMP.GAME_PATH, mapEditor, campEditor, tempPath);
	}

	public static void insertStrings(WTS strings, String fileName, JMpqEditor mapEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		XT87Utils.createNewFile(insertedFile);
		strings.write(insertedFile);
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static void insertInfo(W3I info, String fileName, JMpqEditor mapEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		XT87Utils.createNewFile(insertedFile);
		info.write(insertedFile);
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static int stringIndexToInt(String s)
	{
		return Integer.parseInt(s.replaceFirst(XT87Utils.STRING_PREFIX, ""));
	}

	public static String buttonText(int buttonIndex, int buttonCount)
	{
		return XT87Utils.zeroedNumber(buttonIndex + 1, (buttonCount + "").length()) + ". ";
	}

	public static Pair<Integer, Integer> mergeStrings(File mapFile, JMpqEditor mapEditor, File campaignFile,
	                                                  JMpqEditor campEditor, int buttonIndex, int buttonCount,
	                                                  String tempPath, boolean withDifficultySelection) throws Exception {
		File extractedFile = new File(tempPath + "_" + WTS.GAME_PATH);
		XT87Utils.createNewFile(extractedFile);
		WTS strings = null;
		int campaignKeyOffset = 0, difficultyStringOffset = 0;
		try {
			mapEditor.extractFile(WTS.GAME_PATH.getName(), extractedFile);
			strings = new WTS(extractedFile);
			campaignKeyOffset = Collections.max(strings.getKeyedEntries().keySet()) + 1;
		} catch (Exception e) {

		} finally {
			//extractedFile.delete();
		}

		try {
			campEditor.extractFile(WTS.CAMPAIGN_PATH.getName(), extractedFile);
			WTS campStrings = new WTS(extractedFile);
			if (strings == null)
				strings = campStrings;
			else {
				for (int i : campStrings.getKeyedEntries().keySet())
					strings.addEntry(campaignKeyOffset + i, campStrings.getEntry(i));
			}

			if (buttonIndex >= 0) {
				W3F campaignData = W3F.ofCampaignFile(campaignFile);
				W3F.MapEntry mapEntry = campaignData.getMaps().get(buttonIndex);
				int chapterTitleIndex = stringIndexToInt(mapEntry.getChapterTitle());
				int mapTitleIndex = stringIndexToInt(mapEntry.getMapTitle());
				String buttonTitle = buttonText(buttonIndex, buttonCount) + campStrings.getKeyedEntries().get(chapterTitleIndex) + ": " + campStrings.getKeyedEntries().get(mapTitleIndex);
				System.out.println(buttonTitle);
				W3I info = W3I.ofMapFile(mapFile);
				String mapNameStringIndex = info.getMapName();
				if (mapNameStringIndex != null && !mapNameStringIndex.isEmpty())
					strings.addEntry(stringIndexToInt(mapNameStringIndex), buttonTitle);
				else
				{
					info.setMapName(mapEntry.getChapterTitle());
					strings.addEntry(chapterTitleIndex, buttonTitle);
					insertInfo(info, W3I.GAME_PATH.getName(), mapEditor, tempPath);
				}
			}

			if (withDifficultySelection)
			{
				difficultyStringOffset = Collections.max(strings.getKeyedEntries().keySet()) + 1;
				strings.addEntry(difficultyStringOffset, "Choose Difficulty");
				strings.addEntry(difficultyStringOffset + 1, "|cff00ff00Easy|r");
				strings.addEntry(difficultyStringOffset + 2, "|cffffff00Normal|r");
				strings.addEntry(difficultyStringOffset + 3, "|cffff0000Hard|r");
			}

			insertStrings(strings, WTS.GAME_PATH.getName(), mapEditor, tempPath);
		} catch (Exception e) {
			//extractedFile.delete();
		} finally {
			//extractedFile.delete();
			return new Pair<>(campaignKeyOffset, difficultyStringOffset);
		}
	}

	public static void insertTxt(TXT txt, String fileName, JMpqEditor mapEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		XT87Utils.createNewFile(insertedFile);
		txt.write(insertedFile);
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static void mergeMisc(JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File extractedFile = new File(tempPath + "_" + MiscTXT.GAME_PATH);
		XT87Utils.createNewFile(extractedFile);
		MiscTXT txt = null;
		try {
			campEditor.extractFile(MiscTXT.CAMPAIGN_PATH.getName(), extractedFile);
			txt = new MiscTXT();
			txt.read(extractedFile);
		} catch (Exception e) {
			extractedFile.delete();
			return;
		} finally {
		}
		try {
			mapEditor.extractFile(MiscTXT.GAME_PATH.getName(), extractedFile);
			if (txt == null) {
				txt = new MiscTXT();
				txt.read(extractedFile);
			}
			else {
				MiscTXT mapTxt = new MiscTXT();
				mapTxt.read(extractedFile);
				txt.merge(mapTxt, true);
			}
		} catch (Exception e) {
			//extractedFile.delete();
		} finally {
			insertTxt(txt, MiscTXT.GAME_PATH.getName(), mapEditor, tempPath);
			extractedFile.delete();
		}
	}

	public static void mergeSkin(JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File extractedFile = new File(tempPath + "_" + SkinTXT.GAME_PATH);
		XT87Utils.createNewFile(extractedFile);
		SkinTXT txt = null;
		try {
			campEditor.extractFile(SkinTXT.CAMPAIGN_PATH.getName(), extractedFile);
			txt = new SkinTXT();
			txt.read(extractedFile);
		} catch (Exception e) {
			extractedFile.delete();
			return;
		} finally {
		}
		try {
			mapEditor.extractFile(SkinTXT.GAME_PATH.getName(), extractedFile);
			if (txt == null) {
				txt = new SkinTXT();
				txt.read(extractedFile);
			}
			else {
				SkinTXT mapTxt = new SkinTXT();
				mapTxt.read(extractedFile);
				txt.merge(mapTxt, true);
			}
		} catch (Exception e) {
			//extractedFile.delete();
		} finally {
			insertTxt(txt, SkinTXT.GAME_PATH.getName(), mapEditor, tempPath);
			extractedFile.delete();
		}
	}

	public static ReentrantLock rl = new ReentrantLock();

	public static void addCampaignData(File mapFile, File campaignFile, int buttonIndex, int buttonCount, boolean withDifficultySelection) throws Exception {
		rl.lock();
		JMpqEditor mapEditor = new JMpqEditor(mapFile);
		String tempPath = mapFile.getParentFile().getAbsolutePath() + "/"
				+ getWithoutExtension(mapFile);

		JMpqEditor campEditor = new JMpqEditor(campaignFile, MPQOpenOption.READ_ONLY);

		// imports
		mergeImports(mapEditor, campEditor, tempPath);
		// strings
		Pair<Integer, Integer> offsets = mergeStrings(mapFile, mapEditor, campaignFile, campEditor, buttonIndex, buttonCount, tempPath, withDifficultySelection);
		rl.unlock();
		// misc / constants
		mergeMisc(mapEditor, campEditor, tempPath);
		// skin / interface
		mergeSkin(mapEditor, campEditor, tempPath);

		// units
		mergeData(new W3U(), mapFile, mapEditor, campEditor, tempPath, offsets.getKey());
		// items
		mergeData(new W3T(), mapFile, mapEditor, campEditor, tempPath, offsets.getKey());
		// destructibles
		mergeData(new W3B(), mapFile, mapEditor, campEditor, tempPath, offsets.getKey());
		// doodads
		mergeData(new W3D(), mapFile, mapEditor, campEditor, tempPath, offsets.getKey());
		// abilities
		mergeData(new W3A(), mapFile, mapEditor, campEditor, tempPath, offsets.getKey());
		// buffs
		mergeData(new W3H(), mapFile, mapEditor, campEditor, tempPath, offsets.getKey());
		// upgrades
		mergeData(new W3Q(), mapFile, mapEditor, campEditor, tempPath, offsets.getKey());

		if (withDifficultySelection) {
			int playerId = 0;
			W3I info = W3I.ofMapFile(mapFile);
			for (W3I.Player p : info.getPlayers())
				if (p.getType().equals(Controller.USER))
				{
					playerId = p.getNum();
					break;
				}
			DifficultySelector.addDifficultySelection(mapEditor, tempPath, offsets.getValue(), playerId);
		}

		mapEditor.close();
		campEditor.close();
	}

	static class MapThread extends Thread {
		//static Semaphore mapSemaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
		File mapFile;
		File campaignFile;
		int buttonIndex;
		int buttonCount;
		boolean withDifficultySelection;

		public MapThread(File mapFile, File campaignFile, int buttonIndex, int buttonCount, boolean withDifficultySelection) {
			this.mapFile = mapFile;
			this.campaignFile = campaignFile;
			this.buttonIndex = buttonIndex;
			this.buttonCount = buttonCount;
			this.withDifficultySelection = withDifficultySelection;
		}

		public void run()
		{
			try {
				//mapSemaphore.acquire();
				System.out.println(mapFile.getName());
				addCampaignData(mapFile, campaignFile, buttonIndex, buttonCount, withDifficultySelection);
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				//mapSemaphore.release();
			}
		}
	}

	public static void splitCampaign(String filePath, boolean withDifficultySelection) throws Exception {
		if (!filePath.endsWith(".w3n"))
			throw new IllegalArgumentException("Argument must be a campaign file!");
		File campaignFile = new File(filePath);
		if (!campaignFile.exists())
			throw  new FileNotFoundException("Campaign file not found!");
		System.out.println(campaignFile.getAbsolutePath());
		JMpqEditor campaignEditor = new JMpqEditor(campaignFile, MPQOpenOption.READ_ONLY);
		WTS wtsC = WTS.ofCampaignFile(campaignFile);
		String splitPath = campaignFile.getParentFile().getAbsolutePath() + "/" + getWithoutExtension(campaignFile);
		File splitDir = new File(splitPath);
		if (!splitDir.exists())
			splitDir.mkdirs();
		W3F campaignData = W3F.ofCampaignFile(campaignFile);
		int buttonCount = campaignData.getMaps().size();
		List<Thread> mapThreads = new ArrayList<>();
		for (int i = 0; i < buttonCount; i++) {
			W3F.MapEntry mapEntry = campaignData.getMaps().get(i);
			String fileName = mapEntry.getMapPath();
			String mapPath = splitPath + "/" + fileName;
			if (!campaignEditor.hasFile(fileName))
				continue;
			File mapFile = new File(mapPath);
			mapFile.createNewFile();
			campaignEditor.extractFile(fileName, mapFile);
			addCampaignData(mapFile, campaignFile, i, buttonCount, withDifficultySelection);
//			Thread thread = new MapThread(mapFile, campaignFile, i, buttonCount, withDifficultySelection);
//			thread.start();
//			mapThreads.add(thread);
		}
//		for (Thread thread : mapThreads)
//			thread.join();
		XT87Utils.deleteNewFiles();
	}

	public static void main(String args[]) {
		String filePath = null;
		boolean acceptZeroParam = false;
		int acceptedArgsLength = 1;
		if (args.length != acceptedArgsLength && (args.length != 0 || !acceptZeroParam))
			throw new IllegalArgumentException("Wrong number of arguments! Expected: " + acceptedArgsLength + ", Got: " + args.length);
		if (args.length == 0) {
		}
		else {
			filePath = args[0];
		}
		try {
			splitCampaign(filePath, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}