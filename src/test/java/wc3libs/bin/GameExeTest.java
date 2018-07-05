package wc3libs.bin;

import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.misc.Registry;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class GameExeTest {
    @Test()
    public void testReg() throws IOException {
        if (!Orient.isWindowsSystem()) {
            Log.info("not a windows system (" + Orient.getSystem() + "), skip setGet tests");

            return;
        }

        GameExe gameExe = GameExe.fromRegistry();

        if (gameExe != null) System.out.println(gameExe.getVersion());
    }

    @Test()
    public void testDir() throws IOException {
        GameExe gameExe = GameExe.fromDir(MpqPort.getWc3Dir());

        if (gameExe != null) System.out.println(gameExe.getFile());
    }
}
