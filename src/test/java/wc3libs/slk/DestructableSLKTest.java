package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.DestructableSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class DestructableSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/DestructableData.slk").getFile());
        DestructableSLK destrSLK = new DestructableSLK(orig);

        destrSLK.cleanEmptyColumns();

        File rebuild = new File("out/slkdat/DestructableDataOut.slk");
        rebuild.delete();
        destrSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

}