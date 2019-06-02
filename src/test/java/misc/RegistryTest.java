package misc;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;
import net.moonlightflower.wc3libs.port.Orient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class RegistryTest {
    private static final Logger log = LoggerFactory.getLogger(RegistryTest.class.getName());

    @Test()
    public void setGet() throws IOException {
        try {
            WinRegistryHandler winRegistryHandler = new WinRegistryHandler();

            File dir = new File("HKCU\\wc3libs");

            winRegistryHandler.set(dir, "setGet", WinRegistryHandler.EntryType.REG_SZ, "abc");

            Assert.assertEquals(winRegistryHandler.get(dir, "setGet"), "abc");

            winRegistryHandler.remove(dir, "setGet");

            Assert.assertEquals(winRegistryHandler.get(dir, "setGet"), null);

            winRegistryHandler.set(new WinRegistryHandler.Entry(dir, "setGet", WinRegistryHandler.EntryType.REG_SZ), "def");

            Assert.assertEquals(winRegistryHandler.get(dir, "setGet"), "def");

            winRegistryHandler.remove(new WinRegistryHandler.Entry(dir, "setGet", WinRegistryHandler.EntryType.REG_SZ));

            Assert.assertEquals(winRegistryHandler.get(dir, "setGet"), null);
        } catch (UnsupportedOperationException e) {
            log.info("not a windows system (" + Orient.getSystem() + "), skip setGet tests");
        }
    }
}
