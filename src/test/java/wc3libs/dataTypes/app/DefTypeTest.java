package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.DefType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DefTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(DefType.valueOf(0), DefType.SMALL);
        Assert.assertEquals(DefType.valueOf(1), DefType.MEDIUM);
        Assert.assertEquals(DefType.valueOf(2), DefType.LARGE);
        Assert.assertEquals(DefType.valueOf(3), DefType.FORT);
        Assert.assertEquals(DefType.valueOf(4), DefType.NORMAL);
        Assert.assertEquals(DefType.valueOf(5), DefType.HERO);
        Assert.assertEquals(DefType.valueOf(6), DefType.DIVINE);
        Assert.assertEquals(DefType.valueOf(7), DefType.NONE);

        Assert.assertEquals(DefType.valueOf("small"), DefType.SMALL);
        Assert.assertEquals(DefType.valueOf("medium"), DefType.MEDIUM);
        Assert.assertEquals(DefType.valueOf("large"), DefType.LARGE);
        Assert.assertEquals(DefType.valueOf("fort"), DefType.FORT);
        Assert.assertEquals(DefType.valueOf("normal"), DefType.NORMAL);
        Assert.assertEquals(DefType.valueOf("hero"), DefType.HERO);
        Assert.assertEquals(DefType.valueOf("divine"), DefType.DIVINE);
        Assert.assertEquals(DefType.valueOf("none"), DefType.NONE);
    }
}
