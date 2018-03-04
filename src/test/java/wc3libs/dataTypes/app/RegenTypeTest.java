package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.PathingFlag;
import net.moonlightflower.wc3libs.dataTypes.app.RegenType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegenTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(RegenType.valueOf("none"), RegenType.NONE);
        Assert.assertEquals(RegenType.valueOf("always"), RegenType.ALWAYS);
        Assert.assertEquals(RegenType.valueOf("blight"), RegenType.BLIGHT);
        Assert.assertEquals(RegenType.valueOf("day"), RegenType.DAY);
        Assert.assertEquals(RegenType.valueOf("night"), RegenType.NIGHT);

        Assert.assertEquals(RegenType.valueOf(0), RegenType.NONE);
        Assert.assertEquals(RegenType.valueOf(1), RegenType.ALWAYS);
        Assert.assertEquals(RegenType.valueOf(2), RegenType.BLIGHT);
        Assert.assertEquals(RegenType.valueOf(3), RegenType.DAY);
        Assert.assertEquals(RegenType.valueOf(4), RegenType.NIGHT);
    }
}
