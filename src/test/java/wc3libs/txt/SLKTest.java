package wc3libs.txt;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.misc.Printer;
import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public class SLKTest extends Wc3LibTest {
    @Test()
    public void test() throws IOException {
        List<Path> files = getFiles("slks/");

        files.forEach(path -> {
            if (!path.getFileName().toString().startsWith("AbilityBuffMeta")) return;

            try {
                SLK slk = new RawSLK(path.toFile());

                slk.print(new Printer(System.out));
            } catch (IOException e) {
                Log.error(path.toString() + ":");

                Log.error(e.getMessage(), e);
            }
        });
    }
}
