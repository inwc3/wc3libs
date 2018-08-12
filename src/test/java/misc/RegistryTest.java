package misc;

import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;
import net.moonlightflower.wc3libs.misc.Registry;
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
            Log.info("not a windows system (" + Orient.getSystem() + "), skip setGet tests");

            return;
        }

        File dir = new File("HKCU\\wc3libs");

        Registry.set(dir, "setGet", Registry.EntryType.REG_SZ, "abc");

        Assert.assertEquals(Registry.get(dir, "setGet"), "abc");

        Registry.remove(dir, "setGet");

        Assert.assertEquals(Registry.get(dir, "setGet"), null);

        Registry.set(new Registry.Entry(dir, "setGet", Registry.EntryType.REG_SZ), "def");

        Assert.assertEquals(Registry.get(dir, "setGet"), "def");

        Registry.remove(new Registry.Entry(dir, "setGet", Registry.EntryType.REG_SZ));

        Assert.assertEquals(Registry.get(dir, "setGet"), null);
    }
}
