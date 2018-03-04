package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.AttackBits;
import net.moonlightflower.wc3libs.dataTypes.app.AttackType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AttackTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(AttackType.valueOf(0), AttackType.UNKNOWN);
        Assert.assertEquals(AttackType.valueOf(1), AttackType.NORMAL);
        Assert.assertEquals(AttackType.valueOf(2), AttackType.PIERCE);
        Assert.assertEquals(AttackType.valueOf(3), AttackType.SIEGE);
        Assert.assertEquals(AttackType.valueOf(4), AttackType.SPELLS);
        Assert.assertEquals(AttackType.valueOf(5), AttackType.CHAOS);
        Assert.assertEquals(AttackType.valueOf(6), AttackType.MAGIC);
        Assert.assertEquals(AttackType.valueOf(7), AttackType.HERO);

        Assert.assertEquals(AttackType.valueOf("unknown"), AttackType.UNKNOWN);
        Assert.assertEquals(AttackType.valueOf("normal"), AttackType.NORMAL);
        Assert.assertEquals(AttackType.valueOf("pierce"), AttackType.PIERCE);
        Assert.assertEquals(AttackType.valueOf("siege"), AttackType.SIEGE);
        Assert.assertEquals(AttackType.valueOf("spells"), AttackType.SPELLS);
        Assert.assertEquals(AttackType.valueOf("chaos"), AttackType.CHAOS);
        Assert.assertEquals(AttackType.valueOf("magic"), AttackType.MAGIC);
        Assert.assertEquals(AttackType.valueOf("hero"), AttackType.HERO);
    }
}
