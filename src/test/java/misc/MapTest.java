package misc;

import net.moonlightflower.wc3libs.app.Env;
import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.txt.app.ExtraTXT;
import net.moonlightflower.wc3libs.txt.app.MiscTXT;
import net.moonlightflower.wc3libs.txt.app.SkinTXT;
import org.testng.Assert;
import org.testng.annotations.Test;
import systems.crigges.jmpq3.JMpqEditor;
import systems.crigges.jmpq3.MPQOpenOption;
import wc3libs.misc.Wc3LibTest;

import java.io.File;

import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.SkyModel.LORDAERON_WINTER_PURPLE;
import static net.moonlightflower.wc3libs.txt.app.ExtraTXT.State.SKY_MODEL;
import static net.moonlightflower.wc3libs.txt.app.MiscTXT.State.MOVE_SPEED_UNIT_MAX;
import static net.moonlightflower.wc3libs.txt.app.SkinTXT.States.State.IDLE_PEON;

public class MapTest extends Wc3LibTest {
    @Test
    public void testMap() throws Exception {
        File file = getFile("in.w3x");
        JMpqEditor jMpqEditor = new JMpqEditor(file, MPQOpenOption.READ_ONLY);
        File extra = new File("extra");
        extra.deleteOnExit();
        File misc = new File("misc");
        misc.deleteOnExit();
        File skin = new File("skin");
        skin.deleteOnExit();
        jMpqEditor.extractFile(ExtraTXT.GAME_PATH.getName(), extra);
        jMpqEditor.extractFile(MiscTXT.GAME_PATH.getName(), misc);
        jMpqEditor.extractFile(SkinTXT.GAME_PATH.getName(), skin);
        jMpqEditor.close();
        Env env = Env.ofMapFile(file);

        Assert.assertEquals(env.getWidth(), 129);

        ExtraTXT extraTXT = new ExtraTXT();
        extraTXT.read(extra);

        DataType actual = extraTXT.get(SKY_MODEL.getFieldId());
        Assert.assertEquals(Integer.parseInt(((War3String) actual).getVal()), (int) LORDAERON_WINTER_PURPLE.getVal());

        MiscTXT miscTXT = new MiscTXT();
        miscTXT.read(misc);

        actual = miscTXT.get(MOVE_SPEED_UNIT_MAX.getFieldId());
        Assert.assertEquals((((War3String) actual).getVal()), "522.0");

        SkinTXT skinTXT = new SkinTXT();
        skinTXT.read(skin);

        actual = skinTXT.get(IDLE_PEON.getFieldId());
        Assert.assertEquals((((War3String) actual).getVal()), "ReplaceableTextures\\CommandButtons\\BTNEditor-MultipleDoodads.blp");

    }
}
