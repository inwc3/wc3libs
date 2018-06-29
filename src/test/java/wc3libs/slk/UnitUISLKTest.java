package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.UnitUISLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class UnitUISLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UnitUI.slk").getFile());
        UnitUISLK itemSLK = new UnitUISLK(orig);

        File rebuild = new File("out/slkdat/UnitUIOut.slk");
        rebuild.delete();
        itemSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

}