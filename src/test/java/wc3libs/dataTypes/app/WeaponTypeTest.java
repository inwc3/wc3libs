package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.VersionFlags;
import net.moonlightflower.wc3libs.dataTypes.app.WeaponType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeaponTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(WeaponType.valueOf("artillery"), WeaponType.ARTILLERY);
        Assert.assertEquals(WeaponType.valueOf("aline"), WeaponType.ARTILLERY_LINE);
        Assert.assertEquals(WeaponType.valueOf("instant"), WeaponType.INSTANT);
        Assert.assertEquals(WeaponType.valueOf("missile"), WeaponType.MISSILE);
        Assert.assertEquals(WeaponType.valueOf("msplash"), WeaponType.MISSILE_SPLASH);
        Assert.assertEquals(WeaponType.valueOf("mbounce"), WeaponType.MISSILE_BOUNCE);
        Assert.assertEquals(WeaponType.valueOf("mline"), WeaponType.MISSILE_LINE);
        Assert.assertEquals(WeaponType.valueOf("normal"), WeaponType.NORMAL);
    }
}
