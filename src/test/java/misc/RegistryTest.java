package misc;

import com.esotericsoftware.minlog.Log;
import com.sun.media.jfxmedia.logging.Logger;
import net.moonlightflower.wc3libs.misc.Registry;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class RegistryTest {
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
