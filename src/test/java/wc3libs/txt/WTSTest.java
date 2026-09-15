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
        List<Path> files = List.of(getFile("wc3data/WTS/war3map.wts").toPath());

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
    public void testVersion3MapStrings() throws Exception {
        Path source = getFile("wc3data/WTS/war3map_v3.wts").toPath();
        WTS wts = new WTS(source.toFile());

        Assert.assertEquals(wts.getEntry(3), "11111");
        Assert.assertEquals(wts.getEntry(4), "22222");
        Assert.assertEquals(wts.getEntry(5), "33333");
        Assert.assertEquals(wts.getEntry(6), "44444");
        Assert.assertEquals(wts.getEntry(10), "Player 11111");
        Assert.assertEquals(wts.getEntry(16), "cccc");
        Assert.assertEquals(wts.getEntry(17), "dddd");

        Path output = Files.createTempFile("war3map-v3-", ".wts");
        try {
            wts.write(output.toFile());
            byte[] outputBytes = Files.readAllBytes(output);
            Assert.assertEquals(new byte[]{outputBytes[0], outputBytes[1], outputBytes[2]},
                new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}, "UTF-8 BOM must survive the cycle");
            Assert.assertEquals(new WTS(output.toFile()), wts);
        } finally {
            Files.deleteIfExists(output);
        }
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

    @Test
    public void valuesPreserveCommentLinesBracesAndSignificantWhitespace() throws Exception {
        String input =
            "// comment outside an entry\n" +
                "STRING 7\n" +
                "{\n" +
                "  leading spaces\n" +
                "// literal value line\n" +
                "contains } inline\n" +
                "trailing spaces  \n" +
                "\n" +
                "}\n";

        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        String expected = "  leading spaces\n// literal value line\ncontains } inline\ntrailing spaces  \n";
        Assert.assertEquals(wts.getEntry(7), expected);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wts.write(output);
        WTS reparsed = new WTS(new ByteArrayInputStream(output.toByteArray()));
        Assert.assertEquals(reparsed.getEntry(7), expected);
    }

    @Test
    public void streamWriterFlushesButDoesNotCloseCallerStream() throws Exception {
        class CloseTrackingOutputStream extends ByteArrayOutputStream {
            private boolean closed;

            @Override
            public void close() throws java.io.IOException {
                closed = true;
                super.close();
            }
        }

        WTS wts = new WTS();
        wts.addEntry(1, "value");
        CloseTrackingOutputStream output = new CloseTrackingOutputStream();

        wts.write(output);

        Assert.assertFalse(output.closed, "caller retains ownership of the stream");
        Assert.assertTrue(output.size() > 0, "write must flush buffered text");
    }

    @Test
    public void keywordMatchingRemainsCaseInsensitive() throws Exception {
        String input = "sTrInG 12\n{\nmixed case keyword\n}\n";

        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        Assert.assertEquals(wts.getEntry(12), "mixed case keyword");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wts.write(output);
        Assert.assertEquals(new WTS(new ByteArrayInputStream(output.toByteArray())).getEntry(12),
            "mixed case keyword");
    }

    @Test
    public void mutationPreservesSurroundingSourceStyle() throws Exception {
        String input = "\uFEFF// leading comment\r\nsTrInG 12  \r\n  // header comment\r\n {\r\nold value\r\n}\r\n// trailing comment\r\n";
        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        wts.addEntry(12, "new value");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wts.write(output);
        Assert.assertEquals(output.toByteArray(), input.replace("old value", "new value").getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void malformedLegacyLocaleBytesRoundTripExactly() throws Exception {
        ByteArrayOutputStream input = new ByteArrayOutputStream();
        input.write("STRING 1\r\n{\r\n".getBytes(StandardCharsets.US_ASCII));
        input.write(0xE4);
        input.write("\r\n}\r\n".getBytes(StandardCharsets.US_ASCII));

        WTS wts = new WTS(new ByteArrayInputStream(input.toByteArray()));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wts.write(output);

        Assert.assertEquals(output.toByteArray(), input.toByteArray());
    }

    @Test
    public void unchangedDuplicateKeysRemainByteIdentical() throws Exception {
        String input = "STRING 1\n{\nfirst\n}\n// between\nSTRING 1\n{\nsecond\n}\n";
        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wts.write(output);

        Assert.assertEquals(output.toByteArray(), input.getBytes(StandardCharsets.UTF_8));
    }

    @Test
    public void fillingEmptyEntryRetainsClosingBraceDelimiter() throws Exception {
        String input = "STRING 1\r\n{\r\n}\r\n";
        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        wts.addEntry(1, "filled");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wts.write(output);
        Assert.assertEquals(output.toString(StandardCharsets.UTF_8), "STRING 1\r\n{\r\nfilled\r\n}\r\n");
        Assert.assertEquals(new WTS(new ByteArrayInputStream(output.toByteArray())).getEntry(1), "filled");
    }

    @Test
    public void mutationPreservesCrOnlySourceStyle() throws Exception {
        String input = "STRING 1\r{\rold\r}\r";
        WTS wts = new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        wts.addEntry(1, "new\nline");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wts.write(output);
        Assert.assertEquals(output.toString(StandardCharsets.UTF_8), "STRING 1\r{\rnew\rline\r}\r");
    }

    @Test(expectedExceptions = java.io.IOException.class, expectedExceptionsMessageRegExp = "unterminated WTS entry 9")
    public void unterminatedEntryFailsInsteadOfSilentlyDisappearing() throws Exception {
        String input = "STRING 9\n{\nmissing delimiter\n";
        new WTS(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
    }
}
