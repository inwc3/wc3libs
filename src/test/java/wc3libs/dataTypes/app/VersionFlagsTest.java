package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.UpgradeEffect;
import net.moonlightflower.wc3libs.dataTypes.app.VersionFlags;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VersionFlagsTest {
    @Test
    public void test() {
        Assert.assertEquals(VersionFlags.valueOf(0x0), VersionFlags.NONE);
        Assert.assertEquals(VersionFlags.valueOf(0x1), VersionFlags.ROC);
        Assert.assertEquals(VersionFlags.valueOf(0x2), VersionFlags.TFT);
        Assert.assertEquals(VersionFlags.valueOf(0x3), VersionFlags.BOTH);
    }
}
