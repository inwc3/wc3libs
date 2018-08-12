package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class GameExeTest {
    private static final Logger log = LoggerFactory.getLogger(GameExeTest.class.getName());
    @Test()
    public void testReg() throws IOException {
        if (!Orient.isWindowsSystem()) {
            log.info("not a windows system (" + Orient.getSystem() + "), skip setGet tests");

            return;
        }

        GameExe gameExe = GameExe.fromRegistry();

        if (gameExe != null) System.out.println(gameExe.getVersion());
    }

    @Test()
    public void testDir() {
        GameExe gameExe = GameExe.fromDir(MpqPort.getWc3Dir());

        if (gameExe != null) System.out.println(gameExe.getFile());
    }
}
