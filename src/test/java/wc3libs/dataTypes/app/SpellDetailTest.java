package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.RegenType;
import net.moonlightflower.wc3libs.dataTypes.app.SpellDetail;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpellDetailTest {
    @Test
    public void test() {
        Assert.assertEquals(SpellDetail.valueOf(0), SpellDetail.LOW);
        Assert.assertEquals(SpellDetail.valueOf(1), SpellDetail.MEDIUM);
        Assert.assertEquals(SpellDetail.valueOf(2), SpellDetail.HIGH);
    }
}
