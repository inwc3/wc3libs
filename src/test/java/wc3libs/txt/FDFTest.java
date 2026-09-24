package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.FDF;
import net.moonlightflower.wc3libs.txt.TXT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.assertEquals;

public class FDFTest extends Wc3LibTest {
    private static final Logger log = LoggerFactory.getLogger(FDFTest.class.getName());

    @Test
    public void testRebuild() throws Exception {
        File file = getFile("fdfs/GlobalStrings.fdf");

        try {
            FDF fdf = new FDF(file);

            File rebuild = new File("out/txtdat/" + file.getName());
            rebuild.delete();

            TXT txt = fdf.toTXT();
            txt.write(rebuild);

            TXT.Section defaultSection = txt.getDefaultSection();
            TXT.Section.Field firstField = defaultSection.getFields().values().iterator().next();
            assertEquals(firstField.getVals().get(0).toString(), "Accept");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void writesAndReadsStringListsWithEscapedValues() throws Exception {
        FDF source = new FDF();
        source.set("GREETING", "Hello, \"world\" \\ path");
        source.set("COMMENT_MARKERS", "// and /* are text */");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        source.write(bytes, true);
        String compact = bytes.toString(StandardCharsets.UTF_8);
        assertEquals(compact, "StringList{GREETING \"Hello, \\\"world\\\" \\\\ path\",COMMENT_MARKERS \"// and /* are text */\"}");

        FDF reparsed = new FDF(new ByteArrayInputStream(bytes.toByteArray()));
        assertEquals(reparsed.getEntries(), source.getEntries());
    }

    @Test
    public void writesAnEmptyStringListAndRejectsRawLineBreaks() throws Exception {
        FDF source = new FDF();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        source.write(bytes, true);

        assertEquals(bytes.toString(StandardCharsets.UTF_8), "StringList{}");
        assertEquals(new FDF(new ByteArrayInputStream(bytes.toByteArray())).getEntries(), source.getEntries());

        source.set("INVALID", "two\nlines");
        Assert.expectThrows(IllegalArgumentException.class, () -> source.write(new ByteArrayOutputStream(), true));
    }

    @Test
    public void minifiesCommentsAndLayoutButPreservesStringContents() throws Exception {
        String source = "// header\nFrame \"Panel\" { /* layout */\n Width 0.50,\n Text \"keep // and /* markers */\" // trailing\n}";

        assertEquals(FDF.minify(source), "Frame \"Panel\"{Width 0.50,Text \"keep // and /* markers */\"}");

        String compactStringList = FDF.minify("StringList { KEY \"value\" }");
        FDF reparsed = new FDF(new ByteArrayInputStream(compactStringList.getBytes(StandardCharsets.UTF_8)));
        assertEquals(reparsed.getEntries().get("KEY"), "value");
    }

    @Test
    public void minifierRejectsUnterminatedCommentsAndStrings() {
        Assert.expectThrows(IllegalArgumentException.class, () -> FDF.minify("Frame{ /*"));
        Assert.expectThrows(IllegalArgumentException.class, () -> FDF.minify("Frame{Text \"unfinished}"));
    }

    @Test
    public void structuredWriterRefusesToDropFrameDefinitions() throws Exception {
        byte[] source = "Frame \"Panel\" { Width 0.50, }".getBytes(StandardCharsets.UTF_8);
        FDF fdf = new FDF(new ByteArrayInputStream(source));

        Assert.expectThrows(IllegalStateException.class, () -> fdf.write(new ByteArrayOutputStream()));
        assertEquals(FDF.minify(new String(source, StandardCharsets.UTF_8)), "Frame \"Panel\"{Width 0.50,}");
    }

    @Test
    public void fileWriterRefusesBeforeTruncatingUnsupportedSourceOrDestination() throws Exception {
        Path source = Files.createTempFile("fdf-unsupported", ".fdf");
        Path destination = Files.createTempFile("fdf-preserve", ".fdf");
        byte[] sourceBytes = "Frame \"Panel\" { Width 0.50, }".getBytes(StandardCharsets.UTF_8);
        byte[] destinationBytes = "keep existing contents".getBytes(StandardCharsets.UTF_8);
        try {
            Files.write(source, sourceBytes);
            Files.write(destination, destinationBytes);
            FDF parsed = new FDF(new ByteArrayInputStream(sourceBytes));

            Assert.expectThrows(IllegalStateException.class, () -> parsed.write(source.toFile()));
            assertEquals(Files.readAllBytes(source), sourceBytes);

            Assert.expectThrows(IllegalStateException.class, () -> parsed.write(destination.toFile(), true));
            assertEquals(Files.readAllBytes(destination), destinationBytes);
        } finally {
            Files.deleteIfExists(source);
            Files.deleteIfExists(destination);
        }
    }
}
