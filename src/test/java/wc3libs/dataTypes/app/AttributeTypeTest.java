package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.AttackType;
import net.moonlightflower.wc3libs.dataTypes.app.AttributeType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AttributeTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(AttributeType.valueOf("AGI"), AttributeType.AGI);
        Assert.assertEquals(AttributeType.valueOf("INT"), AttributeType.INT);
        Assert.assertEquals(AttributeType.valueOf("STR"), AttributeType.STR);
    }
}
