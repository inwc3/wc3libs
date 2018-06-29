package wc3libs.txt;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.txt.WTS;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WTSTest extends Wc3LibTest {

    @Test
    public void testRebuild() throws Exception {
        List<Path> files = getFiles("wc3data/WTS/");

        files.forEach((Path p) -> {
            try {
                Log.info("Testing: " + p.getFileName());
                WTS wts = new WTS(p.toFile());

                Assert.assertEquals(wts.getEntry(1), "Player 1");

                Path outPath = Paths.get("out.WTS");
                Files.deleteIfExists(outPath);
                Path temp = Files.createFile(outPath);
                wts.write(temp.toFile());

                WTS wtsOut = new WTS(temp.toFile());

                Assert.assertEquals(wtsOut, wts);

                Files.delete(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
