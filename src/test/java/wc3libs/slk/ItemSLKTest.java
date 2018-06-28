package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.app.objs.ItemSLK;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class ItemSLKTest {

    @Test
    public void testRebuild() throws IOException {
        File orig = new File(getClass().getClassLoader().getResource("slks/ItemData.slk").getFile());
        ItemSLK itemSLK = new ItemSLK(orig);

        itemSLK.cleanEmptyColumns();

        File rebuild = new File("out/slkdat/ItemData.slk");
        rebuild.delete();
        itemSLK.write(rebuild);

        assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(orig.toPath())));
    }

}