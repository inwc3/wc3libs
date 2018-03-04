package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.UnitRace;
import net.moonlightflower.wc3libs.dataTypes.app.UpgradeClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpgradeClassTest {
    @Test
    public void test() {
        Assert.assertEquals(UpgradeClass.valueOf("armor"), UpgradeClass.ARMOR);
        Assert.assertEquals(UpgradeClass.valueOf("artillery"), UpgradeClass.ARTILLERY);
        Assert.assertEquals(UpgradeClass.valueOf("caster"), UpgradeClass.CASTER);
        Assert.assertEquals(UpgradeClass.valueOf("melee"), UpgradeClass.MELEE);
        Assert.assertEquals(UpgradeClass.valueOf("ranged"), UpgradeClass.RANGED);
    }
}
