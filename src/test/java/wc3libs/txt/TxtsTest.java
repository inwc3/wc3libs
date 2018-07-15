package wc3libs.txt;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.txt.TXT;
import net.moonlightflower.wc3libs.txt.app.ui.MiscDataTXT;
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
        List<Path> files = getFiles("txts/generic/");

        files.forEach(file -> {
            try {
                TXT txt = new TXT(file.toFile());

                File rebuild = new File("out/txtdat/" + file.getFileName());
                rebuild.delete();

                txt.write(rebuild);

                System.out.println("Testfile: " + file.getFileName());
                assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(file)));
            } catch (IOException e) {
                Log.error(e.getMessage(), e);
            }
        });
    }

    @Test
    public void textMiscData() throws Exception {
        File file = getFile("txts/MiscData.txt");

        try {
            MiscDataTXT miscDataTXT = new MiscDataTXT(file);

            File rebuild = new File("out/txtdat/" + file.getName());
            rebuild.delete();

            miscDataTXT.write(rebuild);

            assertLinesEqual(new String(Files.readAllBytes(rebuild.toPath())), new String(Files.readAllBytes(file.toPath())));
        } catch (IOException e) {
            Log.error(e.getMessage(), e);
        }
    }


}
