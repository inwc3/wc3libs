package misc;

import net.moonlightflower.wc3libs.app.Env;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3S;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import net.moonlightflower.wc3libs.dataTypes.app.War3Char;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.slk.app.objs.DestructableSLK;
import net.moonlightflower.wc3libs.slk.app.objs.UnitUISLK;
import net.moonlightflower.wc3libs.txt.Profile;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.TXTSectionId;
import net.moonlightflower.wc3libs.txt.WTS;
import net.moonlightflower.wc3libs.txt.app.ExtraTXT;
import net.moonlightflower.wc3libs.txt.app.MiscTXT;
import net.moonlightflower.wc3libs.txt.app.SkinTXT;
import net.moonlightflower.wc3libs.txt.app.profile.CampaignUnitFunc;
import net.moonlightflower.wc3libs.txt.app.profile.CommandFunc;
import org.testng.Assert;
import org.testng.annotations.Test;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.SkyModel.LORDAERON_WINTER_PURPLE;
import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.State.SKY_MODEL;
import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.State.TIME_OF_DAY;
import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.TimeOfDay.NOON;
import static net.moonlightflower.wc3libs.txt.app.MiscTXT.State.MOVE_SPEED_UNIT_MAX;
import static net.moonlightflower.wc3libs.txt.app.SkinTXT.States.State.IDLE_PEON;

public class ForceGrid {

	public static String loadOrder[] = {"war3patch.mpq", "war3xlocal.mpq", "war3x.mpq", "war3.mpq"};
	public static String mpqPath = "H:\\Jocuri\\Warcraft III\\Tools\\Mdlvis 1.41\\".replace("\\", "/");
	public static Map<TXTSectionId, Profile.Obj> txtDataObjects;
	public static String  BUTTONPOS = "Buttonpos";

	public static void mergeFuncs() throws Exception {
		JMpqEditor mpqEditor = new JMpqEditor(new File(mpqPath + loadOrder[2]), MPQOpenOption.READ_ONLY);
		Profile txtData = new Profile();
		File txtFile = new File("txtFile.slk");
		txtFile.deleteOnExit();
		for (File file: Profile.getNativePaths()) {
			mpqEditor.extractFile(file.getPath(), txtFile);
			txtData.merge(new Profile(txtFile));
		}
		txtDataObjects = txtData.getObjs();
		for (TXTSectionId o: txtDataObjects.keySet()) {
			Profile.Obj.Field x = txtDataObjects.get(o).getField(FieldId.valueOf("Buttonpos"));
			if (x != null)
				System.out.println(o + " >> " + x.getVals());
		}
	}

	public static void forceOnMap(File inFile) throws Exception {
		//File file = getFile(path);
		System.out.println(inFile.getAbsolutePath());
		JMpqEditor mpqEditor = new JMpqEditor(new File(mpqPath + loadOrder[2]), MPQOpenOption.READ_ONLY);
		JMpqEditor mapEditor = new JMpqEditor(inFile, MPQOpenOption.READ_ONLY);
//        File extra = new File("extra");
//        extra.deleteOnExit();
//        File misc = new File("misc");
//        misc.deleteOnExit();
//        File skin = new File("skin");
//        System.out.println(skin.getAbsolutePath());
//        skin.deleteOnExit();
		File data_mine = new File("data_mine");
		System.out.println(data_mine.getAbsolutePath());
//        jMpqEditor.extractFile(ExtraTXT.GAME_PATH.getName(), extra);
//        jMpqEditor.extractFile(MiscTXT.GAME_PATH.getName(), misc);
//        jMpqEditor.extractFile(SkinTXT.GAME_PATH.getName(), skin);
		//System.out.println(mpqEditor.getListfileEntries());
		//jMpqEditor.extractAllFiles(data_mine);
		//Env env = Env.ofMapFile(file);
		W3U u = W3U.ofMapFile(inFile);
		System.out.println(u.getSLKs());
		for (File f: u.getSLKs()) {
			System.out.println(f.getPath());
		}
		File unitData = new File("UnitData.slk");
		Map<String, String> s = WTS.ofMapFile(inFile).getNamedEntries();

		for (int i = 0; i < u.getCustomObjs().size(); i++) {
			W3U.Unit unit = u.getCustomObjs().get(i);
			System.out.println(unit + " >> " + s.get(unit.get(W3U.State.TEXT_NAME)));

			Profile.Obj.Field dataField = txtDataObjects.get(unit.getBaseId()).getField(FieldId.valueOf(BUTTONPOS));
			Integer posX = null, posY = null;
			if (dataField != null) {
				posX = Integer.parseInt(((War3String)dataField.getVals().get(0)).toString());
				posY = Integer.parseInt(((War3String)dataField.getVals().get(1)).toString());
			}
			System.out.println("mpq x:" + posX + " y:" + posY);
			System.out.println("map x:" + unit.get(W3U.State.ART_BUTTON_POS_X) + " y:" + unit.get(W3U.State.ART_BUTTON_POS_Y));
			War3Int mapPos = unit.get(W3U.State.ART_BUTTON_POS_X);
			if (mapPos != null)
				posX = mapPos.toInt();
			mapPos = unit.get(W3U.State.ART_BUTTON_POS_Y);
			if (mapPos != null)
				posY = mapPos.toInt();
			System.out.println("set x:" + posX + " y:" + posY);
			if (posX != null && posY != null)
			{
				unit.set(W3U.State.TEXT_HOTKEY, War3Char.valueOf('Q'));
				if (unit.get(W3U.State.TEXT_HOTKEY) != null)
					System.out.println(((War3Char)unit.get(W3U.State.TEXT_HOTKEY)).getVal());
			}
			//System.out.println("x:" + baseUnit.get(UnitUISLK.State.ART_BUTTON_POS_X) + " y:" + baseUnit.get(UnitUISLK.State.ART_BUTTON_POS_Y));
		}
		File outFile = new File("QWER" + inFile.getName());
		for (int i = 0; i < u.getCustomObjs().size(); i++) {
			W3U.Unit unit = u.getCustomObjs().get(i);
			System.out.println(((War3Char)unit.get(W3U.State.TEXT_HOTKEY)).getVal());
		}
		u.write(new Wc3BinOutputStream(outFile));
		mpqEditor.close();

	}

	public static void main(String args[]) {
		String filePath = "D:\\C\\Documents\\Warcraft III\\Maps\\TechTree\\(4)TurtleRock.w3x".replace("\\", "/");
		//filePath = mpqPath + loadOrder[2];
		System.out.println(filePath);
		try {
			mergeFuncs();
			if (!filePath.endsWith(".w3n")) {
				forceOnMap(new File(filePath));
				return;
			}
		}
		catch (Exception e)
		{
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