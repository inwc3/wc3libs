package net.xetanth87.campaignsplitter;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import static net.xetanth87.campaignsplitter.XT87Utils.buttonText;
import static net.xetanth87.campaignsplitter.XT87Utils.getWithoutExtension;
import static net.xetanth87.campaignsplitter.XT87Utils.stringIndexToInt;

public class CampaignSplitter<T> {
	public static final String TEMP_DIR_NAME = "temp";
	public static final String IMPORTS_DIR_NAME = "imports";
	public final File campFile;
	public final String splitPath;
	public int buttonCount;
	public final boolean withDifficultySelection;
	public JMpqEditor campEditor;
	public W3F campaignData;
	protected boolean initializedProgressBar = false;
	private int stepsPerMap = 0;

	public void InitializeProgressBar(int i) {
		initializedProgressBar = true;
	}

	public void SetValueProgressBar(int i) {
	}

	public void IncrementValueProgressBar(int i) {
		if (!initializedProgressBar)
			stepsPerMap++;
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

	public CampaignSplitter(File campFile, boolean withDifficultySelection) throws IOException {
		if (!campFile.getName().endsWith(".w3n"))
			throw new IllegalArgumentException("Argument must be a campaign file!");
		this.campFile = campFile;
		if (!campFile.exists())
			throw new FileNotFoundException("Campaign file not found!");
		splitPath = campFile.getParentFile().getAbsolutePath() + "/" + getWithoutExtension(campFile);
		this.withDifficultySelection = withDifficultySelection;
	}

	static class MapThread extends Thread {
		//static Semaphore mapSemaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
		CampaignSplitter cs;
		File mapFile;
		int buttonIndex;
		int buttonCount;

		public MapThread(CampaignSplitter cs, File mapFile, int buttonIndex, int buttonCount) {
			this.mapFile = mapFile;
			this.cs = cs;
			this.buttonIndex = buttonIndex;
			this.buttonCount = buttonCount;
		}

		public void run() {
			try {
				//mapSemaphore.acquire();
				System.out.println(mapFile.getName());
				//MapInjector mi = new MapInjector(mapFile, buttonIndex, cs.withDifficultySelection);
				//mi.addCampaignData();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//mapSemaphore.release();
			}
		}
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
		if (campEditor.hasFile(IMP.CAMPAIGN_PATH.getName())) {
			File importsListFile = new File(getTempPath() + "/" + IMP.CAMPAIGN_PATH);
			importsListFile.createNewFile();
			campEditor.extractFile(IMP.CAMPAIGN_PATH.getName(), importsListFile);
			XT87Utils.fixImportFile(importsListFile, true);
			IMP imports = new IMP(importsListFile);
			System.out.println("Extracting campaign imported files.");
			for (IMP.Obj o : imports.getObjs()) {
				if (!campEditor.hasFile(o.getPath()))
				{
					System.err.println("Missing campaign import with path \"" + o.getPath() + "\"!");
					continue;
				}
				File importedFile = new File(getImportsPath() + "/" + o.getPath());
				importedFile.getParentFile().mkdirs();
				importedFile.createNewFile();
				campEditor.extractFile(o.getPath(), importedFile);
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
		List<Thread> mapThreads = new ArrayList<>();
		for (int i = 0; i < buttonCount; i++) {
			W3F.MapEntry mapEntry = campaignData.getMaps().get(i);
			String fileName = mapEntry.getMapPath();
			String mapPath = splitPath + "/" + fileName;
			if (!campEditor.hasFile(fileName))
				continue;
			File mapFile = new File(mapPath);
			mapFile.createNewFile();
			System.out.println("Extracting map \"" + mapFile.getName() + "\".");
			campEditor.extractFile(fileName, mapFile);
			MapInjector mi = new MapInjector(this, mapFile, i, withDifficultySelection);
			mi.addCampaignData();
			if (i == 0) {
				InitializeProgressBar(1 + stepsPerMap * buttonCount);
				SetValueProgressBar(1 + stepsPerMap);
				System.err.println("SAOS " + stepsPerMap + " " + (1 + stepsPerMap) + "/" + (1 + stepsPerMap * buttonCount));
			}
//			Thread thread = new MapThread(mapFile, campaignFile, i, buttonCount, withDifficultySelection);
//			thread.start();
//			mapThreads.add(thread);
		}
//		for (Thread thread : mapThreads)
//			thread.join();
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
			cs = new CampaignSplitter(new File(campaignPath), true);
			cs.splitCampaign();
		} catch (Exception e) {
			e.printStackTrace();
			XT87Utils.deleteNewFiles();
			cs.removeTemporaryFiles();
		}
	}
}