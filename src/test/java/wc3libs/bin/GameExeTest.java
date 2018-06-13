package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.misc.Registry;
import net.moonlightflower.wc3libs.port.MpqPort;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class GameExeTest {
    @Test()
    public void test() throws IOException {
        GameExe gameExe = GameExe.fromRegistry();

        if (gameExe != null) System.out.println(gameExe.getVersion());
    }
}
