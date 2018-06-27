package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.AbilSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertEqualsIgnoreWhitespace;

public class AbilSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/AbilityDataSmall.slk").getFile());
        AbilSLK abilSLK = new AbilSLK(orig);

        File rebuild = new File("AbilityDataOut.slk");
        abilSLK.write(rebuild);

        assertEqualsIgnoreWhitespace(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }
}
