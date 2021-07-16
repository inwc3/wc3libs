package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.DeathType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeathTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(DeathType.valueOf(0x0), DeathType.NONE);
        Assert.assertEquals(DeathType.valueOf(0x1), DeathType.RAISES);
        Assert.assertEquals(DeathType.valueOf(0x2), DeathType.DECAYS);
        Assert.assertEquals(DeathType.valueOf(0x3), DeathType.RAISES_AND_DECAYS);
    }
}
