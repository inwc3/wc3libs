package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.UnitBalanceSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class UnitBalanceSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UnitBalance.slk").getFile());
        UnitBalanceSLK itemSLK = new UnitBalanceSLK(orig);

        File rebuild = new File("out/slkdat/UnitBalanceOut.slk");
        rebuild.delete();
        itemSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

}