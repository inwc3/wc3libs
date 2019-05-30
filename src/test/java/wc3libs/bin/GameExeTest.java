package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.NotFoundException;
import net.moonlightflower.wc3libs.port.Orient;
import net.moonlightflower.wc3libs.port.RegistryGameExeFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class GameExeTest {
    private static final Logger log = LoggerFactory.getLogger(GameExeTest.class.getName());
    @Test()
    public void testReg() throws IOException {
        if (!Orient.isWindowsSystem()) {
            log.info("not a windows system (" + Orient.getSystem() + "), skip setGet tests");

            return;
        }

        File gameExeFile = null;
        try {
            gameExeFile = new RegistryGameExeFinder().get();

            System.out.println(GameExe.getVersion(gameExeFile));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
