package wc3libs.txt;

import net.moonlightflower.wc3libs.slk.RawSLK;
import net.moonlightflower.wc3libs.slk.SLK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class SLKTest extends Wc3LibTest {
    private static final Logger log = LoggerFactory.getLogger(SLKTest.class.getName());
    @Test()
    public void test() throws IOException {
        List<Path> files = getFiles("slks/");

        files.forEach(path -> {
            if (!path.getFileName().toString().startsWith("AbilityBuffMeta")) return;

            try {
                SLK slk = new RawSLK(path.toFile());
            } catch (IOException e) {
                log.error(path.toString() + ":");

                log.error(e.getMessage(), e);
            }
        });
    }
}
