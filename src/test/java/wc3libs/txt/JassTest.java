package wc3libs.txt;

import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.txt.app.jass.Jass;
import net.moonlightflower.wc3libs.txt.app.jass.JassScript;
import net.moonlightflower.wc3libs.txt.app.jass.LightJass;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import static org.testng.Assert.assertFalse;


public class JassTest extends Wc3LibTest {

    @Test()
    public void testRebuild() throws IOException {
        File file = getFile("jass/compiled.j");

        Jass jass = new Jass(file);

        assertFalse(System.err.checkError());
    }

    @Test()
    public void testRebuild2() throws IOException {
        File file = getFile("jass/compiled2.j");

        Jass jass = new Jass(file);

        assertFalse(System.err.checkError());
    }

    @Test()
    public void testRebuild3() throws IOException {
        File file = getFile("jass/compiled2.j");

        LightJass jass = new LightJass(file);

        JassScript script = new JassScript(jass.getRootContext());

        StringWriter sw = new StringWriter();

        script.write(sw);

        //System.out.println(sw.toString());

        /*Jass newJass = new Jass(sw.toString());

        assertFalse(System.err.checkError());*/
    }

    @Test(enabled = false)
    public void testRebuildDwc() throws IOException {
        File file = getFile("jass/dwc.j");

        LightJass jass = new LightJass(file);

        jass.getRootContext();

        JassScript script = new JassScript(jass.getRootContext());

        StringWriter sw = new StringWriter();

        script.write(sw);

        Jass newJass = new Jass(sw.toString());
    }

    @Test()
    public void injectW3IConfigs() throws IOException {
        File file = getFile("jass/dwc.j");

        LightJass jass = new LightJass(file);

        jass.getRootContext();

        JassScript script = new JassScript(jass.getRootContext());

        W3I w3i = new W3I();

        w3i.setMapName("mapName");
        w3i.setMapAuthor("mapAuthor");
        w3i.setMapDescription("description");
        w3i.setPlayersRecommendedAmount("playersRecommended");

        w3i.injectConfigsInJassScript(script, GameVersion.VERSION_1_32);

        StringWriter sw = new StringWriter();

        script.write(sw);

        Jass newJass = new Jass(sw.toString());
    }
}
