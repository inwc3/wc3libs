package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.TXT;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static wc3libs.slk.SLKTest.assertLinesEqual;

public class TxtsTest extends Wc3LibTest {

    @Test
    public void testRebuild() throws Exception {
        List<Path> files = getFiles("txts/");

        files.forEach(file -> {
            try {
                TXT txt = new TXT(file.toFile());

                File rebuild = new File("out/txtdat/" + file.getFileName());
                rebuild.delete();

                txt.write(rebuild);

                System.out.println("Testfile: " + file.getFileName());
                assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
