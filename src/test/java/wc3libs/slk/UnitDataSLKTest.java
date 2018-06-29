package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.RawMetaSLK;
import net.moonlightflower.wc3libs.slk.app.objs.UnitDataSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class UnitDataSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UnitData.slk").getFile());
        UnitDataSLK unitDataSLK = new UnitDataSLK(orig);

        File rebuild = new File("out/slkdat/UnitDataOut.slk");
        rebuild.delete();
        unitDataSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

    @Test
    public void testMeta() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UnitMetaData.slk").getFile());
        RawMetaSLK unitDataSLK = new RawMetaSLK(orig);

        File rebuild = new File("out/slkdat/UnitMetaDataOut.slk");
        rebuild.delete();
        unitDataSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }
}
