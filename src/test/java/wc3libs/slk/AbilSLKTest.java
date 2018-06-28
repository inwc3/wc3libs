package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.AbilSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;


public class AbilSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/AbilityData.slk").getFile());
        AbilSLK abilSLK = new AbilSLK(orig);

        File rebuild = new File("out/slkdat/AbilityDataOut.slk");
        rebuild.delete();

        abilSLK.cleanEmptyColumns();

        abilSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }
}
