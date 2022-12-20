package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.app.Minimap;
import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.IMP;
import net.moonlightflower.wc3libs.bin.app.MMP;
import net.moonlightflower.wc3libs.bin.app.W3F;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.dataTypes.app.Controller;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;
import net.moonlightflower.wc3libs.txt.WTS;
import net.moonlightflower.wc3libs.txt.app.MiscTXT;
import net.moonlightflower.wc3libs.txt.app.SkinTXT;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.JMpqException;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static net.xetanth87.campaignsplitter.XT87Utils.STRING_PREFIX;
import static net.xetanth87.campaignsplitter.XT87Utils.getWithoutExtension;
import static net.xetanth87.campaignsplitter.XT87Utils.offsetCampaignDataString;
import static net.xetanth87.campaignsplitter.XT87Utils.stringIndexToInt;

public class MapInjector {
	public CampaignSplitter cs;
	public final File mapFile;
	public final int buttonIndex;
	public boolean withDifficultySelector = false;
	public int addedCoopPlayers = 0;
	public JMpqEditor mapEditor;
	public File tempFile;
	public StringBuffer scriptBuffer = null;
	public String scriptPath;
	public boolean isLua;

	public MapInjector(CampaignSplitter cs, File mapFile, int buttonIndex) throws IOException {
		if (!mapFile.getName().endsWith(".w3m") && !mapFile.getName().endsWith(".w3x"))
			throw new IllegalArgumentException("Argument must be a map file!");
		this.mapFile = mapFile;
		if (!mapFile.exists())
			throw new FileNotFoundException("Map file not found!");
		this.cs = cs;
		this.buttonIndex = buttonIndex;
	}

	public void insertDataFile(Object data, String fileName) throws IOException {
		if (data instanceof WTS)
			((WTS) data).write(tempFile);
		else if (data instanceof IMP)
			((IMP) data).write(tempFile);
		else if (data instanceof TXT)
			((TXT) data).write(tempFile);
		else if (data instanceof W3I)
			((W3I) data).write(tempFile);
		else if (data instanceof ObjMod) {
			Wc3BinOutputStream bos = new Wc3BinOutputStream(tempFile);
			((ObjMod) data).write(bos);
			bos.close();
		}
		mapEditor.insertFile(fileName, tempFile, false, true);
	}

	public void insertImports(IMP imports, String fileName) throws IOException {
		insertDataFile(imports, fileName);
		for (IMP.Obj o : imports.getObjs()) {
			String importPath = o.getPath().trim();
			if (importPath.isEmpty())
				continue;
			String localPath = cs.getImportsPath() + "/" + importPath;
			File importedFile = new File(localPath);
			if (mapEditor.hasFile(importPath) || !importedFile.exists())
				continue;
			mapEditor.insertFile(importPath, importedFile, false, true);
		}
	}

	public void offsetCampaignStrings(File extractedFile, int campaignKeyOffset) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(extractedFile), StandardCharsets.ISO_8859_1));
		StringBuffer sb = new StringBuffer();
		int s1 = 0;
		while ((s1 = br.read()) != -1) {
			sb.append((char) s1);
		}
		br.close();
		String s = sb.toString();

		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.ISO_8859_1);
		String[] splitStrings = s.split(XT87Utils.STRING_PREFIX);
		if (s.startsWith(XT87Utils.STRING_PREFIX))
			splitStrings[0] = offsetCampaignDataString(splitStrings[0], campaignKeyOffset);
		for (int i = 1; i < splitStrings.length; i++)
			splitStrings[i] = offsetCampaignDataString(splitStrings[i], campaignKeyOffset);
		writer.append(String.join("", splitStrings));
		writer.close();
	}

	public void mergeData(ObjMod data, int campaignKeyOffset) throws IOException, NoSuchFieldException, IllegalAccessException {
		String gamePath = ((File) data.getClass().getField("GAME_PATH").get(null)).getName();
		String campaignPath = ((File) data.getClass().getField("CAMPAIGN_PATH").get(null)).getName();
		ObjMod mapData = null;
		Wc3BinInputStream bis = null;
		try {
			offsetCampaignStrings(new File(cs.getTempPath(campaignPath)), campaignKeyOffset);
			bis = new Wc3BinInputStream(tempFile);
			data.read(bis);
			bis.close();
			if (data == null)
				throw new IOException();
		} catch (Throwable e) {
			if (bis != null)
				bis.close();
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
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
				ObjMod.Obj obj = (ObjMod.Obj) object;
				for (int i = 0; i < tempList.size(); i++) {
					ObjMod.Obj campaignObj = (ObjMod.Obj) tempList.get(i);
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
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
			if (e instanceof JMpqException) {
				String message = e.getMessage();
				if (message != null && message.contains("Bad header"))
					throw new NonReadableChannelException();
			}
		}

		insertDataFile(data, gamePath);
	}

	public void mergeImports() throws IOException {
		IMP imports;
		try {
			imports = new IMP(new File(cs.getTempPath() + "/" + IMP.CAMPAIGN_PATH));
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
			return;
		}
		try {
			mapEditor.extractFile(IMP.GAME_PATH, tempFile);
			XT87Utils.fixImportFile(tempFile, false);
			IMP mapImports = new IMP(tempFile);
			for (IMP.Obj o : mapImports.getObjs())
				if (o.getPath() != null && !o.getPath().isEmpty() && o.getStdFlag() != null)
					imports.addObj(o);
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
		}

		insertImports(imports, IMP.GAME_PATH);
	}

	public void changeMapName(File extractedFile, String originalMapName, String newMapName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(extractedFile), StandardCharsets.ISO_8859_1));
		StringBuffer sb = new StringBuffer();
		int s1 = 0;
		while ((s1 = br.read()) != -1) {
			sb.append((char) s1);
		}
		br.close();
		String s = sb.toString();

		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.ISO_8859_1);
		writer.append(s.replaceFirst(originalMapName, newMapName));
		writer.close();
	}

	static class StringOffsets {
		int campaignKeyOffset = 0;
	}

	public StringOffsets mergeStrings() throws Exception {
		WTS strings = null;
		StringOffsets offsets = new StringOffsets();
		try {
			mapEditor.extractFile(WTS.GAME_PATH.getPath(), tempFile);
			strings = new WTS(tempFile);
			offsets.campaignKeyOffset = Collections.max(strings.getKeyedEntries().keySet()) + 1;
		} catch (Throwable e) {
			strings = null;
			offsets.campaignKeyOffset = 0;
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
		}

		try {
			if (strings == null)
				strings = cs.campStrings;
			else {
				for (int i : cs.campStrings.getKeyedEntries().keySet())
					strings.addEntry(offsets.campaignKeyOffset + i, cs.campStrings.getEntry(i));
			}

			if (buttonIndex >= 0) {
				W3F.MapEntry mapEntry = cs.campaignData.getMaps().get(buttonIndex);
				int mapTitleIndex = stringIndexToInt(mapEntry.getMapTitle());
				mapEditor.extractFile(W3I.GAME_PATH.getPath(), tempFile);
				int buttonStringIndex = mapTitleIndex + offsets.campaignKeyOffset;
				changeMapName(tempFile, W3I.ofMapFile(mapFile).getMapName(), STRING_PREFIX + buttonStringIndex);
				insertDataFile(tempFile, W3I.GAME_PATH.getPath());
			}

			insertDataFile(strings, WTS.GAME_PATH.getPath());
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
		} finally {
			return offsets;
		}
	}

	public void mergeMisc() throws IOException {
		MiscTXT txt = null;
		try {
			txt = new MiscTXT();
			txt.read(new File(cs.getTempPath(MiscTXT.CAMPAIGN_PATH.getName())));
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
			if (!cs.withUpkeepRemoval)
				return;
		} finally {
		}
		try {
			if (!mapEditor.hasFile(MiscTXT.GAME_PATH.getPath()))
				throw new IOException();
			mapEditor.extractFile(MiscTXT.GAME_PATH.getPath(), tempFile);
			if (txt == null) {
				txt = new MiscTXT();
				txt.read(tempFile);
			} else {
				MiscTXT mapTxt = new MiscTXT();
				mapTxt.read(tempFile);
				txt.merge(mapTxt, true);
			}
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
		} finally {
			try {
				if (cs.withUpkeepRemoval) {
					if (txt == null)
						txt = new MiscTXT();
					final TXTSectionId sectionId = TXTSectionId.valueOf("Misc");
					TXT.Section section = txt.getSection(sectionId);
					if (section == null)
						section = txt.addSection(sectionId);
					String tax = "0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00";
					String usage = "10000,10000,10000,10000,10000,10000,10000,10000,10000,10000";
					section.setLine(FieldId.valueOf("UpkeepGoldTax"), tax);
					section.setLine(FieldId.valueOf("UpkeepLumberTax"), tax);
					section.setLine(FieldId.valueOf("UpkeepUsage"), usage);
				}
				insertDataFile(txt, MiscTXT.GAME_PATH.getPath());
			} catch (Throwable e) {
				if (!(e instanceof IOException))
					System.err.println(e.getMessage());
			}
		}
	}

	public void mergeSkin(int campaignKeyOffset) throws IOException {
		SkinTXT txt = null;
		try {
			txt = new SkinTXT();
			offsetCampaignStrings(new File(cs.getTempPath(SkinTXT.CAMPAIGN_PATH.getName())), campaignKeyOffset);
			txt.read(tempFile);
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
			return;
		}
		try {
			if (!mapEditor.hasFile(SkinTXT.GAME_PATH.getPath()))
				throw new IOException();
			mapEditor.extractFile(SkinTXT.GAME_PATH.getPath(), tempFile);
			if (txt == null) {
				txt = new SkinTXT();
				txt.read(tempFile);
			} else {
				SkinTXT mapTxt = new SkinTXT();
				mapTxt.read(tempFile);
				txt.merge(mapTxt, true);
			}
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
		} finally {
			insertDataFile(txt, SkinTXT.GAME_PATH.getPath());
		}
	}

	public void addSecondaryPlayers(int campaignKeyOffset) throws IOException {
		SkinTXT txt = null;
		try {
			txt = new SkinTXT();
			offsetCampaignStrings(new File(cs.getTempPath(SkinTXT.CAMPAIGN_PATH.getName())), campaignKeyOffset);
			txt.read(tempFile);
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
			return;
		}
		try {
			if (!mapEditor.hasFile(SkinTXT.GAME_PATH.getPath()))
				throw new IOException();
			mapEditor.extractFile(SkinTXT.GAME_PATH.getPath(), tempFile);
			if (txt == null) {
				txt = new SkinTXT();
				txt.read(tempFile);
			} else {
				SkinTXT mapTxt = new SkinTXT();
				mapTxt.read(tempFile);
				txt.merge(mapTxt, true);
			}
		} catch (Throwable e) {
			if (!(e instanceof IOException))
				System.err.println(e.getMessage());
		} finally {
			insertDataFile(txt, SkinTXT.GAME_PATH.getPath());
		}
	}

	public void changePreview() throws IOException {
		String campaignPreviewPath = cs.campaignData.getMinimapPath().getPath();
		if (campaignPreviewPath == null || campaignPreviewPath.isEmpty())
			return;
		File campaignPreviewFile = new File(cs.getImportsPath() + "/" + campaignPreviewPath);
		if (campaignPreviewFile.exists()) {
			System.out.println("Changing preview for map \"" + mapFile.getName() + "\".");
			String minimapImagePath = null;
			boolean hasMinimapImage = false, isTGA = false;
			if (mapEditor.hasFile(minimapImagePath = Minimap.BACKGROUND_BLP_GAME_PATH.getPath()))
				hasMinimapImage = true;
			else if (mapEditor.hasFile(minimapImagePath = Minimap.BACKGROUND_TGA_GAME_PATH.getPath()))
				hasMinimapImage = true;
			String mmpPath = MMP.GAME_PATH.getPath();
			if (mapEditor.hasFile(mmpPath))
				mapEditor.deleteFile(mmpPath);
			if (hasMinimapImage) {
				mapEditor.extractFile(minimapImagePath, tempFile);
				mapEditor.deleteFile(minimapImagePath);
				mapEditor.insertFile(MinimapRewriter.getNewPath(minimapImagePath), tempFile, false, true);
				new MinimapRewriter(this, minimapImagePath).modifyScript();
			}
			minimapImagePath = XT87Utils.getExtension(campaignPreviewPath).equals("tga") ? Minimap.BACKGROUND_TGA_GAME_PATH.getPath() : Minimap.BACKGROUND_BLP_GAME_PATH.getPath();
			mapEditor.insertFile(minimapImagePath, campaignPreviewFile, false, true);
			System.out.println("Finished changing preview for map \"" + mapFile.getName() + "\".");
			cs.IncrementValueProgressBar(1);
		}
	}

	public ReentrantLock rl = new ReentrantLock();

	public void addCampaignData() throws Exception {
		//rl.lock();
		mapEditor = new JMpqEditor(mapFile, MPQOpenOption.FORCE_V0);
		tempFile = new File(cs.getTempPath() + "/" + getWithoutExtension(mapFile.getName()) + ".temp.txt");
		tempFile.getParentFile().mkdirs();
		tempFile.createNewFile();

		// imports
		System.out.println("Adding imports to map \"" + mapFile.getName() + "\".");
		mergeImports();
		cs.IncrementValueProgressBar(1);
		// strings
		System.out.println("Adding strings to map \"" + mapFile.getName() + "\".");
		StringOffsets offsets = mergeStrings();
		cs.IncrementValueProgressBar(1);
		//rl.unlock();
		// misc/constants
		System.out.println("Adding misc/constants to map \"" + mapFile.getName() + "\".");
		mergeMisc();
		cs.IncrementValueProgressBar(1);
		// skin/interface
		System.out.println("Adding skin/interface to map \"" + mapFile.getName() + "\".");
		mergeSkin(offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);

		System.out.println("Adding data to map \"" + mapFile.getName() + "\".");
		// units
		mergeData(new W3U(), offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);
		// items
		mergeData(new W3T(), offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);
		// destructibles
		mergeData(new W3B(), offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);
		// doodads
		mergeData(new W3D(), offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);
		// abilities
		mergeData(new W3A(), offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);
		// buffs
		mergeData(new W3H(), offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);
		// upgrades
		mergeData(new W3Q(), offsets.campaignKeyOffset);
		cs.IncrementValueProgressBar(1);

		W3I.Player mainPlayer = null;
		W3I info = W3I.ofMapFile(mapFile);
		for (W3I.Player p : info.getPlayers())
			if (p.getType().equals(Controller.USER)) {
				mainPlayer = p;
				break;
			}

		boolean editScript = withDifficultySelector || cs.withCampaignPreview || cs.withLegacyAssets
				|| cs.withNextLevel || addedCoopPlayers > 0;

		List<W3I.Player> secondaryPlayers = null;
		if (addedCoopPlayers > 0) {
			W3I.Player finalMainPlayer = mainPlayer;
			W3I.Force force = info.getForces().stream().filter(x -> x.getPlayerNums().contains(finalMainPlayer.getNum())).findFirst().get();
			List<Integer> unusedPlayerNumbers = new ArrayList<>();
			for (int i = mainPlayer.getNum() + 1; i < XT87Utils.MAX_PLAYER_COUNT; i++)
				unusedPlayerNumbers.add(i);
			for (int i = 0; i < mainPlayer.getNum(); i++)
				unusedPlayerNumbers.add(i);
			for (W3I.Player p : info.getPlayers())
				unusedPlayerNumbers.remove((Object) p.getNum());
			unusedPlayerNumbers = unusedPlayerNumbers.subList(0, addedCoopPlayers);
			secondaryPlayers = new ArrayList<>();
			for (int i : unusedPlayerNumbers) {
				W3I.Player secondaryPlayer = XT87Utils.clonePlayer(mainPlayer, i);
				force.addPlayerNums(i);
				secondaryPlayers.add(secondaryPlayer);
				info.getPlayers().add(secondaryPlayer);
				for (W3I.TechMod techMod : info.getTechMods()) {
					int players = techMod.getPlayers();
					if (players >> mainPlayer.getNum() == 1) {
						techMod.setPlayers(players | 1 << i);
					}
				}
				for (W3I.UpgradeMod upgradeMod : info.getUpgradeMods()) {
					int players = upgradeMod.getPlayers();
					if (players >> mainPlayer.getNum() == 1) {
						upgradeMod.setPlayers(players | 1 << i);
					}
				}
			}
			insertDataFile(info, W3I.GAME_PATH.getPath());
		}

		if (editScript) {
			ScriptRewriter.readScript(this);

			if (withDifficultySelector) {

				System.out.println("Adding Difficulty Selector to map \"" + mapFile.getName() + "\".");
				new DifficultySelectorRewriter(this, mainPlayer.getNum()).modifyScript();
				System.out.println("Finished adding Difficulty Selector to map \"" + mapFile.getName() + "\".");
				cs.IncrementValueProgressBar(1);
			}

			if (cs.withCampaignPreview) {
				changePreview();
			}

			if (cs.withNextLevel) {
				System.out.println("Adding next level message to map \"" + mapFile.getName() + "\".");
				new NextLevelRewriter(this, offsets.campaignKeyOffset).modifyScript();
				System.out.println("Finished adding next level message to map \"" + mapFile.getName() + "\".");
				cs.IncrementValueProgressBar(1);
			}

			if (addedCoopPlayers > 0) {
				System.out.println("Adding coop support to map \"" + mapFile.getName() + "\".");
				new CoopRewriter(this, info, mainPlayer, secondaryPlayers).modifyScript();
				System.out.println("Finished adding coop support to map \"" + mapFile.getName() + "\".");
				cs.IncrementValueProgressBar(1);
			}

			ScriptRewriter.insertScript(this);
		}

		tempFile.delete();
		System.out.println("Saving map \"" + mapFile.getName() + "\".");
		mapEditor.close();
		cs.IncrementValueProgressBar(10);
		System.out.println("Finished saving map \"" + mapFile.getName() + "\".");
	}
}
