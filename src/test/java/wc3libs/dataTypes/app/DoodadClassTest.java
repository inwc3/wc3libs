package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.DoodadClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DoodadClassTest {
    @Test
    public void test() {
        Assert.assertEquals(DoodadClass.valueOf("Z"), DoodadClass.CINEMATIC);
        Assert.assertEquals(DoodadClass.valueOf("C"), DoodadClass.CLIFF);
        Assert.assertEquals(DoodadClass.valueOf("E"), DoodadClass.ENVIRONMENT);
        Assert.assertEquals(DoodadClass.valueOf("O"), DoodadClass.ITEM);
        Assert.assertEquals(DoodadClass.valueOf("S"), DoodadClass.STRUCTURE);
        Assert.assertEquals(DoodadClass.valueOf("W"), DoodadClass.WATER);
    }
}
