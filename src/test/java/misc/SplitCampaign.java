package misc;

import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.bin.app.IMP;
import net.moonlightflower.wc3libs.txt.WTS;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class SplitCampaign<T> {

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

	public static void mergeData(ObjMod data, File mapFile, File campaignFile, JMpqEditor mapEditor, String tempPath) throws IOException, NoSuchFieldException, IllegalAccessException {
		ObjMod campData;
		ObjMod mapData;
		List<ObjMod.Obj> objectList;
		try {
			campData = data.ofCampaignFile(data.getClass(), campaignFile);
			if (campData == null)
				throw new IOException();
			objectList = new ArrayList<>(campData.getObjsList());
		} catch (Exception e) {
			return;
		}
		try {
			mapData = data.ofMapFile(data.getClass(), mapFile);
			if (mapData == null)
				throw new IOException();
			objectList.addAll(mapData.getObjsList());
		} catch (Exception e) {
		}
		for (ObjMod.Obj o : objectList) {
			data.getObjsList().add(o);
		}

		insertData(data, ((File) data.getClass().getField("GAME_PATH").get(null)).getName(), mapEditor, tempPath);
	}

	public static void fixImportFile(File extractedFile) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(extractedFile.getAbsolutePath().toString()));
		FileWriter writer = new FileWriter(extractedFile);
		String s = new String(encoded, StandardCharsets.US_ASCII);
		String x = s.replaceAll("\t", "\r").replaceAll("\u0015", "\r");
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
		} catch (Exception e) {
			extractedFile.delete();
			return;
		}
		finally {
			extractedFile.delete();
		}
		try {
			mapEditor.extractFile(IMP.GAME_PATH, extractedFile);
			fixImportFile(extractedFile);
			IMP mapImports = new IMP(extractedFile);
			for (IMP.Obj o : mapImports.getObjs())
				imports.addObj(o);
		}
		catch (Exception e)
		{

		}
		finally {
			extractedFile.delete();
		}

		insertImports(imports, IMP.GAME_PATH, mapEditor, campEditor, tempPath);
	}

	public static void insertStrings(WTS strings, String fileName, JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File insertedFile = new File(tempPath + "_" + fileName);
		insertedFile.createNewFile();
		strings.write(insertedFile);
		mapEditor.insertFile(fileName, insertedFile, false, true);
		insertedFile.delete();
	}

	public static void mergeStrings(JMpqEditor mapEditor, JMpqEditor campEditor, String tempPath) throws IOException {
		File extractedFile = new File(tempPath + "_" + WTS.GAME_PATH);
		extractedFile.createNewFile();
		WTS strings;
		try {
			campEditor.extractFile(WTS.CAMPAIGN_PATH.getName(), extractedFile);
			strings = new WTS(extractedFile);
		} catch (Exception e) {
			//extractedFile.delete();
			return;
		}
		finally {
			//extractedFile.delete();
		}
		try {
			mapEditor.extractFile(WTS.GAME_PATH.getName(), extractedFile);
			WTS mapStrings = new WTS(extractedFile);
			for (int i : mapStrings.getKeyedEntries().keySet())
				strings.addEntry(i, mapStrings.getEntry(i));
		}
		catch (Exception e)
		{

		}
		finally {
			//extractedFile.delete();
		}

		insertStrings(strings, WTS.GAME_PATH.getName(), mapEditor, campEditor, tempPath);
	}

	public static void addCampaignData(File mapFile, File campaignFile) throws IOException, NoSuchFieldException, IllegalAccessException {
		JMpqEditor mapEditor = new JMpqEditor(mapFile);
		String tempPath = mapFile.getParentFile().getAbsolutePath() + "/"
				+ getWithoutExtension(mapFile);

		JMpqEditor campEditor = new JMpqEditor(campaignFile, MPQOpenOption.READ_ONLY);

		// imports
		mergeImports(mapEditor, campEditor, tempPath);
		// strings
		mergeStrings(mapEditor, campEditor, tempPath);
		campEditor.close();

		// units
		mergeData(new W3U(), mapFile, campaignFile, mapEditor, tempPath);
		// items
		mergeData(new W3T(), mapFile, campaignFile, mapEditor, tempPath);
		// destructibles
		mergeData(new W3B(), mapFile, campaignFile, mapEditor, tempPath);
		// doodads
		mergeData(new W3D(), mapFile, campaignFile, mapEditor, tempPath);
		// abilities
		mergeData(new W3A(), mapFile, campaignFile, mapEditor, tempPath);
		// buffs
		mergeData(new W3H(), mapFile, campaignFile, mapEditor, tempPath);
		// upgrades
		mergeData(new W3Q(), mapFile, campaignFile, mapEditor, tempPath);

		mapEditor.close();
	}

	public static void splitCampaign(File campaignFile) throws Exception {
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
		String filePath = "D:\\C\\Documents\\Warcraft III\\Maps\\Hobby\\War3Alternate0 - Prologue.w3n".replace("\\", "/");
		try {
			if (!filePath.endsWith(".w3n"))
				throw new IOException("Argument must be a campaign file!");
			splitCampaign(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Assert.assertEquals(env.getWidth(), 129);

//        ExtraTXT extraTXT = new ExtraTXT();
//        extraTXT.read(extra);
//
//        DataType actual = extraTXT.get(SKY_MODEL.getFieldId());
//        Assert.assertEquals(Integer.parseInt(((War3String) actual).getVal()), (int) LORDAERON_WINTER_PURPLE.getVal());
//
//        actual = extraTXT.get(TIME_OF_DAY.getFieldId());
//        Assert.assertEquals(Integer.parseInt(((War3String) actual).getVal()), (int) NOON.getVal());
//
//        MiscTXT miscTXT = new MiscTXT();
//        miscTXT.read(misc);
//
//        actual = miscTXT.get(MOVE_SPEED_UNIT_MAX.getFieldId());
//        Assert.assertEquals((((War3String) actual).getVal()), "522.0");
//
//        SkinTXT skinTXT = new SkinTXT();
//        skinTXT.read(skin);
//
//        actual = skinTXT.get(IDLE_PEON.getFieldId());
//        Assert.assertEquals((((War3String) actual).getVal()), "ReplaceableTextures\\CommandButtons\\BTNEditor-MultipleDoodads.blp");

	}
}