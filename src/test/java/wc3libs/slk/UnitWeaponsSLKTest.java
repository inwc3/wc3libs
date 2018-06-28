package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.UnitWeaponsSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class UnitWeaponsSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UnitWeapons.slk").getFile());
        UnitWeaponsSLK unitWeaponsSLK = new UnitWeaponsSLK(orig);

        unitWeaponsSLK.cleanEmptyColumns();

        File rebuild = new File("UnitWeaponsOut.slk");
        unitWeaponsSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }
}
