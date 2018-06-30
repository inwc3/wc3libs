package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.UnitWeaponsSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class CleanSLKTest {

    @Test
    public void testCleanEmptyColumns() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/reduce/UnitWeapons.slk").getFile());
        File origR = new File(getClass().getClassLoader().getResource("slks/reduce/UnitWeaponsReduced.slk").getFile());
        UnitWeaponsSLK unitWeaponsSLK = new UnitWeaponsSLK(orig);

        unitWeaponsSLK.cleanEmptyColumns();

        File rebuild = new File("out/slkdat/UnitWeaponsReducedOut.slk");
        rebuild.delete();
        unitWeaponsSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(origR.toPath())));
    }
}
