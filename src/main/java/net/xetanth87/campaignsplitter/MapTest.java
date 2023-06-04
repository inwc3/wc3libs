package net.xetanth87.campaignsplitter;

import net.moonlightflower.wc3libs.app.Env;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.txt.app.ExtraTXT;
import net.moonlightflower.wc3libs.txt.app.MiscTXT;
import net.moonlightflower.wc3libs.txt.app.SkinTXT;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;

import java.io.File;

import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.SkyModel.LORDAERON_WINTER_PURPLE;
import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.State.SKY_MODEL;
import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.State.TIME_OF_DAY;
import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.TimeOfDay.NOON;
import static net.moonlightflower.wc3libs.txt.app.MiscTXT.State.MOVE_SPEED_UNIT_MAX;
import static net.moonlightflower.wc3libs.txt.app.SkinTXT.States.State.IDLE_PEON;

public class MapTest {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\C\\Documents\\Warcraft III\\Maps\\SomeFolderName\\War3Alternate2 - Night Elf\\undead03.w3m");
        W3I info = W3I.ofMapFile(file);
        File file2 = new File("D:\\C\\Documents\\Warcraft III\\Maps\\SomeFolderName\\War3Alternate2 - Night Elf\\2undead01.w3x");
        W3I info2 = W3I.ofMapFile(file2);
        File file3 = new File("D:\\C\\Documents\\Warcraft III\\Maps\\SomeFolderName\\SoHv2.3.0\\Maiev03.w3x");
        W3I info3 = W3I.ofMapFile(file2);
        String breakp = "point";






//        File extra = new File("extra");
//        extra.deleteOnExit();
//        File misc = new File("misc");
//        misc.deleteOnExit();
//        File skin = new File("skin");
//        skin.deleteOnExit();
//        jMpqEditor.extractFile(ExtraTXT.GAME_PATH.getName(), extra);
//        jMpqEditor.extractFile(MiscTXT.GAME_PATH.getName(), misc);
//        jMpqEditor.extractFile(SkinTXT.GAME_PATH.getName(), skin);
        //Env env = Env.ofMapFile(file);

////        ExtraTXT extraTXT = new ExtraTXT();
////        extraTXT.read(extra);
//
//        DataType actual = extraTXT.get(SKY_MODEL.getFieldId());
//
//        actual = extraTXT.get(TIME_OF_DAY.getFieldId());
//
//        MiscTXT miscTXT = new MiscTXT();
//        miscTXT.read(misc);
//
//        actual = miscTXT.get(MOVE_SPEED_UNIT_MAX.getFieldId());
//
//        SkinTXT skinTXT = new SkinTXT();
//        skinTXT.read(skin);
//
//        actual = skinTXT.get(IDLE_PEON.getFieldId());
    }
}
