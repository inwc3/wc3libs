package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.testng.Assert.*;
import static wc3libs.slk.SLKTest.assertEqualsIgnoreWhitespace;

public class DoodSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/Doodads.slk").getFile());
        DoodSLK doodSLK = new DoodSLK(orig);

        File rebuild = new File("DoodadsOut.slk");
        doodSLK.write(rebuild);

        assertEqualsIgnoreWhitespace(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

}