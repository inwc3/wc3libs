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
        if (!Orient.isWindowsSystem()) {
            log.info("not a windows system (" + Orient.getSystem() + "), skip setGet tests");

            return;
        }

        File dir = new File("HKCU\\wc3libs");

        WinRegistryHandler.set(dir, "setGet", WinRegistryHandler.EntryType.REG_SZ, "abc");

        Assert.assertEquals(WinRegistryHandler.get(dir, "setGet"), "abc");

        WinRegistryHandler.remove(dir, "setGet");

        Assert.assertEquals(WinRegistryHandler.get(dir, "setGet"), null);

        WinRegistryHandler.set(new WinRegistryHandler.Entry(dir, "setGet", WinRegistryHandler.EntryType.REG_SZ), "def");

        Assert.assertEquals(WinRegistryHandler.get(dir, "setGet"), "def");

        WinRegistryHandler.remove(new WinRegistryHandler.Entry(dir, "setGet", WinRegistryHandler.EntryType.REG_SZ));

        Assert.assertEquals(WinRegistryHandler.get(dir, "setGet"), null);
    }
}
