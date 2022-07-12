package net.xetanth87.campaignsplitter;

import javafx.application.Application;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3F;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.bin.app.IMP;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.WTS;
import net.moonlightflower.wc3libs.txt.app.MiscTXT;
import net.moonlightflower.wc3libs.txt.app.SkinTXT;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class CampaignSplitter<T> {
	public static final String IMPORT_DELIM = "\r";
	public static final String DATA_DELIM = "\n";
	public static final String STRING_PREFIX = "TRIGSTR_";

	private static String getWithoutExtension(File file) {
		return file.getName().replaceFirst("[.][^.]+$", "");
	}

	public static void insertData(ObjMod mapData, String fileName, JMpqEditor mapEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		insertedFile.createNewFile();
		Wc3BinOutputStream bos = new Wc3BinOutputStream(insertedFile);
		mapData.write(bos);
		bos.close();
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static String offsetCampaignDataString(String string, int campaignKeyOffset) {
		int nullPos = string.indexOf(0);
		return STRING_PREFIX + (Integer.parseInt(string.substring(0, nullPos)) + campaignKeyOffset) + string.substring(nullPos);
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
		String[] splitStrings = s.split(STRING_PREFIX);
		if (s.startsWith(STRING_PREFIX))
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
		extractedFile.createNewFile();
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
		insertedFile.createNewFile();
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
		extractedFile.createNewFile();
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
		insertedFile.createNewFile();
		strings.write(insertedFile);
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static int mergeStrings(JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File extractedFile = new File(tempPath + "_" + WTS.GAME_PATH);
		extractedFile.createNewFile();
		WTS strings = null;
		int campaignKeyOffset = 0;
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
			if (strings == null)
				strings = new WTS(extractedFile);
			else {
				WTS campStrings = new WTS(extractedFile);
				for (int i : campStrings.getKeyedEntries().keySet())
					strings.addEntry(campaignKeyOffset + i, campStrings.getEntry(i));
			}
			insertStrings(strings, WTS.GAME_PATH.getName(), mapEditor, tempPath);
		} catch (Exception e) {
			//extractedFile.delete();
		} finally {
			//extractedFile.delete();
			return campaignKeyOffset;
		}
	}

	public static void insertTxt(TXT txt, String fileName, JMpqEditor mapEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		insertedFile.createNewFile();
		txt.write(insertedFile);
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static void mergeMisc(JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File extractedFile = new File(tempPath + "_" + MiscTXT.GAME_PATH);
		extractedFile.createNewFile();
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
		extractedFile.createNewFile();
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

	public static void addCampaignData(File mapFile, File campaignFile) throws Exception {
		JMpqEditor mapEditor = new JMpqEditor(mapFile);
		String tempPath = mapFile.getParentFile().getAbsolutePath() + "/"
				+ getWithoutExtension(mapFile);

		JMpqEditor campEditor = new JMpqEditor(campaignFile, MPQOpenOption.READ_ONLY);

		// imports
		mergeImports(mapEditor, campEditor, tempPath);
		// strings
		int campaignKeyOffset = mergeStrings(mapEditor, campEditor, tempPath);
		// misc / constants
		mergeMisc(mapEditor, campEditor, tempPath);
		// skin / interface
		mergeSkin(mapEditor, campEditor, tempPath);

		// units
		mergeData(new W3U(), mapFile, mapEditor, campEditor, tempPath, campaignKeyOffset);
		// items
		mergeData(new W3T(), mapFile, mapEditor, campEditor, tempPath, campaignKeyOffset);
		// destructibles
		mergeData(new W3B(), mapFile, mapEditor, campEditor, tempPath, campaignKeyOffset);
		// doodads
		mergeData(new W3D(), mapFile, mapEditor, campEditor, tempPath, campaignKeyOffset);
		// abilities
		mergeData(new W3A(), mapFile, mapEditor, campEditor, tempPath, campaignKeyOffset);
		// buffs
		mergeData(new W3H(), mapFile, mapEditor, campEditor, tempPath, campaignKeyOffset);
		// upgrades
		mergeData(new W3Q(), mapFile, mapEditor, campEditor, tempPath, campaignKeyOffset);
		// campaign data (W3F) is not needed

		W3F campaignData = W3F.ofCampaignFile(campaignFile);

		mapEditor.close();
		campEditor.close();
	}

	public static void splitCampaign(String filePath) throws Exception {
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
		for (String fileName : campaignEditor.getFileNames()) {
			if (fileName.endsWith(".w3m") || fileName.endsWith(".w3x")) {
				String mapPath = splitPath + "/" + fileName;
				System.out.println(mapPath);
				File mapFile = new File(mapPath);
				mapFile.createNewFile();
				campaignEditor.extractFile(fileName, mapFile);
				addCampaignData(mapFile, campaignFile);
			}
		}
	}

	public static void main(String args[]) {
		String filePath = null;
		boolean acceptZeroParam = false;
		int acceptedArgsLength = 1;
		if (args.length != acceptedArgsLength && (args.length != 0 || !acceptZeroParam))
			throw new IllegalArgumentException("Wrong number of arguments! Expected: " + acceptedArgsLength + ", Got: " + args.length);
		if (args.length == 0) {
			String[] a = new String[]{"D:\\C\\Documents\\Warcraft III\\Maps\\Hobby\\War3Alternate0 - Prologue.w3n",
					"D:\\C\\Documents\\Warcraft III\\Maps\\Hobby\\War3Alternate1 - Undead.w3n",
					"D:\\C\\Documents\\Warcraft III\\Maps\\Hobby\\War3Alternate1 - UndeadBonus.w3n",
					"D:\\C\\Documents\\Warcraft III\\Maps\\Hobby\\War3Alternate4 - Horde.w3n",
					"D:\\C\\Documents\\Warcraft III\\Maps\\Hobby\\War3Alternate4 - HordeSimple.w3n",
					"D:\\C\\Documents\\Warcraft III\\Maps\\Hobby\\War3Alternate3 - AllianceSimple.w3n"
			};
			filePath = a[5].replace("\\", "/");
		}
		else {
			filePath = args[0];
		}
		try {
			splitCampaign(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}