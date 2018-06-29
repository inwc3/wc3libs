package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.UnitAbilsSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class UnitAbilitiesSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/UnitAbilities.slk").getFile());
        UnitAbilsSLK unitAbilsSLK = new UnitAbilsSLK(orig);

        File rebuild = new File("out/slkdat/UnitAbilitiesOut.slk");
        rebuild.delete();
        unitAbilsSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

}
