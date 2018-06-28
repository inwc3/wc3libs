package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.RawMetaSLK;
import net.moonlightflower.wc3libs.slk.app.objs.BuffSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class BuffSLKTest {

    @Test
    public void testRebuildData() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/AbilityBuffData.slk").getFile());
        BuffSLK buffSLK = new BuffSLK(orig);

        buffSLK.cleanEmptyColumns();

        File rebuild = new File("out/slkdat/AbilityBuffDataOut.slk");
        rebuild.delete();
        buffSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

    @Test
    public void testRebuildMetaData() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/AbilityBuffMetaData.slk").getFile());
        RawMetaSLK buffSLK = new RawMetaSLK(orig);

        File rebuild = new File("out/slkdat/AbilityBuffMetaDataOut.slk");
        rebuild.delete();
        buffSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

}