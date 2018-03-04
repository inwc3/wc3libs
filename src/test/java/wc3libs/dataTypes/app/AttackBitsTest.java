package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.ArmorSound;
import net.moonlightflower.wc3libs.dataTypes.app.AttackBits;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AttackBitsTest {
    @Test
    public void test() {
        Assert.assertEquals(AttackBits.valueOf(0), AttackBits.NONE);
        Assert.assertEquals(AttackBits.valueOf(1), AttackBits.FIRST);
        Assert.assertEquals(AttackBits.valueOf(2), AttackBits.SECOND);
        Assert.assertEquals(AttackBits.valueOf(3), AttackBits.BOTH);
    }
}
