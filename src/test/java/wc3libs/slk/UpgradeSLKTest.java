package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.RawMetaSLK;
import net.moonlightflower.wc3libs.slk.app.objs.UpgradeSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;


public class UpgradeSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UpgradeData.slk").getFile());
        UpgradeSLK upgradeSLK = new UpgradeSLK(orig);

        File rebuild = new File("out/slkdat/UpgradeDataOut.slk");
        rebuild.delete();

        upgradeSLK.cleanEmptyColumns();

        upgradeSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

    @Test
    public void testMeta() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UpgradeMetaData.slk").getFile());
        RawMetaSLK upgradeSLK = new RawMetaSLK(orig);

        File rebuild = new File("out/slkdat/UpgradeMetaDataOut.slk");
        rebuild.delete();

        upgradeSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }
}
