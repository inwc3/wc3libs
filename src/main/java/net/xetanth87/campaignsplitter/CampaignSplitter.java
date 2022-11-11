package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.bin.app.W3F;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.bin.app.IMP;
import net.moonlightflower.wc3libs.txt.WTS;
import net.moonlightflower.wc3libs.txt.app.MiscTXT;
import net.moonlightflower.wc3libs.txt.app.SkinTXT;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.xetanth87.campaignsplitter.XT87Utils.STRING_PREFIX;
import static net.xetanth87.campaignsplitter.XT87Utils.buttonText;
import static net.xetanth87.campaignsplitter.XT87Utils.getWithoutExtension;
import static net.xetanth87.campaignsplitter.XT87Utils.stringIndexToInt;

public class CampaignSplitter {
	public static final String TEMP_DIR_NAME = "temp";
	public static final String IMPORTS_DIR_NAME = "imports";
	private static final int STEPS_CAMP_DATA = 4;
	public static final String MAP_COUNT_FORMAT = "Maps converted: {0}/{1}.";
	public final File campFile;
	public final String splitPath;
	public int buttonCount;
	public XT87Utils.TriOption difficultySelectorOption = XT87Utils.TriOption.SMART;
	public boolean withCampaignPreview = false, withUpkeepRemoval = false, withLegacyAssets = true, withNextLevel = true;
	public JMpqEditor campEditor;
	public W3F campaignData;
	protected boolean initializedProgressBar = false;
	public WTS campStrings = null;
	public Map<String, String> buttonNameMap = new HashMap<>();
	private int stepsPerMap = 0;

	public void InitializeProgressBar(int i) {
		initializedProgressBar = true;
	}

	public void SetValueProgressBar(int i) {
	}

	public void IncrementValueProgressBar(int i) {
		if (!initializedProgressBar)
			stepsPerMap += i;
	}

	public void CompleteProgressBar() {
	}

	public String getTempPath() {
		return splitPath + "/" + TEMP_DIR_NAME;
	}

	public String getTempPath(String filename) {
		return getTempPath() + "/" + filename;
	}

	public String getImportsPath() {
		return getTempPath() + "/" + IMPORTS_DIR_NAME;
	}

	public String getImportsPath(String filename) {
		return getImportsPath() + "/" + filename;
	}

	public CampaignSplitter(File campFile) throws IOException, IllegalArgumentException {
		if (!campFile.getName().endsWith(".w3n"))
			throw new IllegalArgumentException("Argument must be a campaign file!");
		if (!campFile.exists())
			throw new FileNotFoundException("Campaign file not found!");
		this.campFile = campFile;
		splitPath = campFile.getParentFile().getAbsolutePath() + "/" + getWithoutExtension(campFile.getName());
	}

	public void removeTemporaryFiles() {
		try {
			String importsPath = splitPath + "/" + TEMP_DIR_NAME;
			File importsDir = new File(importsPath);
			if (importsDir.exists())
				XT87Utils.deleteDirectory(importsDir);
		} catch (Exception e) {
		}
	}

	public void extractCampaignFiles() throws IOException {
		File importsDir = new File(getImportsPath());
		importsDir.mkdirs();
		List<File> extractedFiles = Arrays.asList(WTS.CAMPAIGN_PATH, MiscTXT.CAMPAIGN_PATH,
				SkinTXT.CAMPAIGN_PATH, W3U.CAMPAIGN_PATH, W3T.CAMPAIGN_PATH, W3B.CAMPAIGN_PATH,
				W3D.CAMPAIGN_PATH, W3A.CAMPAIGN_PATH, W3H.CAMPAIGN_PATH, W3Q.CAMPAIGN_PATH);
		System.out.println("Extracting campaign configuration files.");
		for (File file : extractedFiles) {
			if (!campEditor.hasFile(file.getName()))
				continue;
			File extractedFile = new File(getTempPath() + "/" + file);
			extractedFile.createNewFile();
			campEditor.extractFile(file.getName(), extractedFile);
		}
		File importsListFile = null;
		IMP imports = null;

		File wtsFile = new File(getTempPath(WTS.CAMPAIGN_PATH.getName()));
		if (wtsFile.exists()) {
			campStrings = new WTS(wtsFile);
			wtsFile.delete();
		}

		if (campEditor.hasFile(IMP.CAMPAIGN_PATH.getName())) {
			importsListFile = new File(getTempPath() + "/" + IMP.CAMPAIGN_PATH);
			importsListFile.createNewFile();
			campEditor.extractFile(IMP.CAMPAIGN_PATH.getName(), importsListFile);
			XT87Utils.fixImportFile(importsListFile, true);
			imports = new IMP(importsListFile);
			System.out.println("Extracting campaign imported files.");
			for (IMP.Obj o : imports.getObjs()) {
				if (o.getPath().isEmpty())
					continue;
				if (!campEditor.hasFile(o.getPath()))
				{
					System.err.println("Missing campaign import with path \"" + o.getPath() + "\"!");
					continue;
				}
				File importedFile = new File(getImportsPath() + "/" + o.getPath().trim());
				importedFile.getParentFile().mkdirs();
				importedFile.createNewFile();
				campEditor.extractFile(o.getPath(), importedFile);
			}
			System.out.println("Finished extracting campaign imported files.");
		}

		if (withLegacyAssets) {
			System.out.println("Extracting legacy asset files.");
			if (importsListFile == null) {
				importsListFile = new File(getTempPath() + "/" + IMP.CAMPAIGN_PATH);
				importsListFile.createNewFile();
			}

			boolean noCampaignImports = imports == null;
			if (noCampaignImports)
				imports = new IMP();

			if (LegacyMerger.AddLegacyAssets(this, imports)) {
				System.out.println("Finished extracting legacy asset files.");
				imports.write(importsListFile);
			}
			else if (noCampaignImports) {
				importsListFile.delete();
			}
		}
	}

	public void splitCampaign() throws Exception {
		System.out.println(campFile.getAbsolutePath());
		campEditor = new JMpqEditor(campFile, MPQOpenOption.READ_ONLY);
		campaignData = W3F.ofCampaignFile(campFile);
		buttonCount = campaignData.getMaps().size();
		SetValueProgressBar(0);
		File splitDir = new File(splitPath);
		splitDir.mkdirs();
		extractCampaignFiles();
		boolean withDifficultySelector = difficultySelectorOption.equals(XT87Utils.TriOption.YES) ||
				(difficultySelectorOption.equals(XT87Utils.TriOption.SMART) && campaignData.getFlag(W3F.Flags.Flag.VAR_DIFFICULTY));
		System.out.println("Difficulty selector: " + (withDifficultySelector ? "enabled" : "disabled") + ".");
		for (int i = 0; i < buttonCount; i++) {
			W3F.MapEntry mapEntry = campaignData.getMaps().get(i);
			String title = mapEntry.getChapterTitle();
			if (title == null || !title.contains(STRING_PREFIX))
				throw new Exception("Invalid chapter title for map " + mapEntry.getMapPath() + ": \"" + title + "\" (Broken campaign)");
			int chapterTitleIndex = stringIndexToInt(title);
			title = mapEntry.getMapTitle();
			if (title == null || !title.contains(STRING_PREFIX))
				throw new Exception("Invalid map title for map " + mapEntry.getMapPath() + ": \"" + title + "\" (Broken campaign)");
			int mapTitleIndex = stringIndexToInt(title);
			String buttonTitle = buttonText(i, buttonCount) + campStrings.getKeyedEntries().get(chapterTitleIndex) + ": " + campStrings.getKeyedEntries().get(mapTitleIndex);
			campStrings.addEntry(mapTitleIndex, buttonTitle);
			buttonNameMap.put(mapEntry.getMapPath().toLowerCase(), buttonTitle);
		}
		for (int i = 0; i < buttonCount; i++) {
			System.out.println(MessageFormat.format(MAP_COUNT_FORMAT, i, buttonCount));
			W3F.MapEntry mapEntry = campaignData.getMaps().get(i);
			String fileName = mapEntry.getMapPath();
			String mapPath = splitPath + "/" + fileName;
			if (i != 0)
				campEditor = new JMpqEditor(campFile, MPQOpenOption.READ_ONLY);
			if (!campEditor.hasFile(fileName))
				continue;
			File mapFile = new File(mapPath);
			mapFile.createNewFile();
			System.out.println("Extracting map \"" + mapFile.getName() + "\".");
			campEditor.extractFile(fileName, mapFile);
			campEditor.close();
			MapInjector mi = new MapInjector(this, mapFile, i);
			mi.withDifficultySelector = withDifficultySelector;
			try {
				mi.addCampaignData();
			}
			catch (NonReadableChannelException | NonWritableChannelException e)
			{
				throw new Exception("Map \"" + mapFile.getName() + "\" is protected and cannot be read/edited.");
			}
			if (i == 0) {
				InitializeProgressBar(STEPS_CAMP_DATA + stepsPerMap * buttonCount);
				SetValueProgressBar(STEPS_CAMP_DATA + stepsPerMap);
			}
		}
		System.out.println(MessageFormat.format(MAP_COUNT_FORMAT, buttonCount, buttonCount));
		XT87Utils.deleteNewFiles();
		removeTemporaryFiles();
		campEditor.close();
		CompleteProgressBar();
	}

	public static void main(String args[]) {
		String campaignPath = null;
		boolean acceptZeroParam = false;
		int acceptedArgsLength = 1;
		if (args.length != acceptedArgsLength && (args.length != 0 || !acceptZeroParam))
			throw new IllegalArgumentException("Wrong number of arguments! Expected: " + acceptedArgsLength + ", Got: " + args.length);
		if (args.length == 0) {
		} else {
			campaignPath = args[0];
		}
		CampaignSplitter cs = null;
		try {
			cs = new CampaignSplitter(new File(campaignPath));
			cs.splitCampaign();
		} catch (Exception e) {
			e.printStackTrace();
			XT87Utils.deleteNewFiles();
			cs.removeTemporaryFiles();
		}
	}
}