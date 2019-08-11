package wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.mac.PListGameVersionParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;

public class MacGameVersionFinderTest extends Wc3LibTest {
    @Test
    public void test() throws Exception {
        PListGameVersionParser finder = new PListGameVersionParser();

        GameVersion gameVersion = finder.get(getFile("wc3data/context/Info.plist"));

        Assert.assertEquals(gameVersion, new GameVersion("1.31.1.12164"));
    }
}
