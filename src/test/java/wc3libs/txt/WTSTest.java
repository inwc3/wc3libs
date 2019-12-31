package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.WTS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WTSTest extends Wc3LibTest {
    private static final Logger log = LoggerFactory.getLogger(WTSTest.class.getName());

    @Test
    public void testRebuild() throws Exception {
        List<Path> files = getFiles("wc3data/WTS/");

        files.forEach((Path p) -> {
            try {
                log.info("Testing: " + p.getFileName());
                WTS wts = new WTS(p.toFile());

                Assert.assertEquals(wts.getEntry(1), "Player 1");
                Assert.assertEquals(wts.getEntry(2), "Force 1");
                Assert.assertEquals(wts.getEntry(11), "xaxaxaxaxaxa");
                Assert.assertEquals(wts.getEntry(2224), "https://test.com");
                Assert.assertEquals(wts.getEntry(2228), "empty");
                Assert.assertEquals(wts.getEntry(4322), "Some" + System.lineSeparator() +
                    "implicit" + System.lineSeparator() +
                    "newline" + System.lineSeparator() +
                    "string");

                Path outPath = Paths.get("out.WTS");
                Files.deleteIfExists(outPath);
                Path temp = Files.createFile(outPath);
                wts.write(temp.toFile());

                WTS wtsOut = new WTS(temp.toFile());

                Assert.assertEquals(wtsOut, wts);

                Files.delete(temp);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });

    }

}
