package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.ItemClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemClassTest {
    @Test
    public void test() {
        Assert.assertEquals(ItemClass.valueOf("Artifact"), ItemClass.ARTIFACT);
        Assert.assertEquals(ItemClass.valueOf("Campaign"), ItemClass.CAMPAIGN);
        Assert.assertEquals(ItemClass.valueOf("Charged"), ItemClass.CHARGED);
        Assert.assertEquals(ItemClass.valueOf("Miscellaneous"), ItemClass.MISC);
        Assert.assertEquals(ItemClass.valueOf("Permanent"), ItemClass.PERMANENT);
        Assert.assertEquals(ItemClass.valueOf("PowerUp"), ItemClass.POWERUP);
        Assert.assertEquals(ItemClass.valueOf("Purchasable"), ItemClass.PURCHASE);
    }
}
