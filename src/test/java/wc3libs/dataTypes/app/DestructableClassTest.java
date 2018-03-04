package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.DefType;
import net.moonlightflower.wc3libs.dataTypes.app.DestructableClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DestructableClassTest {
    @Test
    public void test() {
        Assert.assertEquals(DestructableClass.valueOf("B"), DestructableClass.BRIDGE);
        Assert.assertEquals(DestructableClass.valueOf("D"), DestructableClass.DESTRUCTABLE);
        Assert.assertEquals(DestructableClass.valueOf("P"), DestructableClass.PATHING);
    }
}
