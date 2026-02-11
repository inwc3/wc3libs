package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.WTS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
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
                Assert.fail("Failed for file " + p + ": " + e.getMessage(), e);
            }
        });
    }

    @Test
    public void testWritePreservesCRLFAndAddsEntrySeparator() throws Exception {
        String input =
            "STRING 1\r\n" +
                "{\r\n" +
                "Player 1\r\n" +
                "}\r\n" +
                "\r\n" +
                "STRING 2\r\n" +
                "{\r\n" +
                "Force 1\r\n" +
                "}\r\n";

        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        // mutate to simulate translation
        wts.addEntry(1, "helo");
        wts.addEntry(2, "helo");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wts.write(bos);
        String out = bos.toString(StandardCharsets.UTF_8);

        String expected =
            "STRING 1\r\n" +
                "{\r\n" +
                "helo\r\n" +
                "}\r\n" +
                "\r\n" +
                "STRING 2\r\n" +
                "{\r\n" +
                "helo\r\n" +
                "}\r\n";

        Assert.assertEquals(out, expected, "Serialized WTS must keep CRLF and blank separator line");
    }

    @Test
    public void testWritePreservesLFStyle() throws Exception {
        String input =
            "STRING 1\n" +
                "{\n" +
                "A\n" +
                "}\n" +
                "\n" +
                "STRING 2\n" +
                "{\n" +
                "B\n" +
                "}\n";

        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wts.write(bos);
        String out = bos.toString(StandardCharsets.UTF_8);

        // Should keep LF, not convert to CRLF
        Assert.assertFalse(out.contains("\r\n"), "Output should preserve LF style when input used LF");
        Assert.assertTrue(out.contains("\n\nSTRING 2\n"), "Output should include blank line between entries");
    }
}
