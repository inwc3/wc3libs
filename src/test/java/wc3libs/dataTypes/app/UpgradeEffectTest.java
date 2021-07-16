package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.UpgradeEffect;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpgradeEffectTest {
    @Test
    public void test() {
        Assert.assertEquals(UpgradeEffect.valueOf("rlev"), UpgradeEffect.ABIL_LEVEL_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("rart"), UpgradeEffect.ARMOR_CHANGE);
        Assert.assertEquals(UpgradeEffect.valueOf("rarm"), UpgradeEffect.ARMOR_UP_USE);
        Assert.assertEquals(UpgradeEffect.valueOf("raud"), UpgradeEffect.AURA_DATA_UP);

        Assert.assertEquals(UpgradeEffect.valueOf("rasd"), UpgradeEffect.ATTACK_SPILL_DIST_BONUS);
        Assert.assertEquals(UpgradeEffect.valueOf("rasr"), UpgradeEffect.ATTACK_SPILL_RAD_BONUS);
        Assert.assertEquals(UpgradeEffect.valueOf("ratx"), UpgradeEffect.ATTACK_DMG_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("radl"), UpgradeEffect.ATTACK_DMG_LOSS_UP);

        Assert.assertEquals(UpgradeEffect.valueOf("renw"), UpgradeEffect.ATTACK_ENABLE);
        Assert.assertEquals(UpgradeEffect.valueOf("rroo"), UpgradeEffect.ATTACK_ENABLE_ROOT);
        Assert.assertEquals(UpgradeEffect.valueOf("ruro"), UpgradeEffect.ATTACK_ENABLE_UNROOT);
        Assert.assertEquals(UpgradeEffect.valueOf("ratr"), UpgradeEffect.ATTACK_RANGE_UP);

        Assert.assertEquals(UpgradeEffect.valueOf("rats"), UpgradeEffect.ATTACK_SPEED_UP_PERC);
        Assert.assertEquals(UpgradeEffect.valueOf("ratc"), UpgradeEffect.ATTACK_TARGS_COUNT_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("ratd"), UpgradeEffect.ATTACK_UP_DICE);
        Assert.assertEquals(UpgradeEffect.valueOf("ratt"), UpgradeEffect.ATTACK_UP_USE);

        Assert.assertEquals(UpgradeEffect.valueOf("rmin"), UpgradeEffect.HARVEST_GOLD_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("rent"), UpgradeEffect.HARVEST_GOLD_UP_ENTANGLED);
        Assert.assertEquals(UpgradeEffect.valueOf("rlum"), UpgradeEffect.HARVEST_LUMBER_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("rhpx"), UpgradeEffect.LIFE_MAX);

        Assert.assertEquals(UpgradeEffect.valueOf("rhpo"), UpgradeEffect.LIFE_MAX_PERC);
        Assert.assertEquals(UpgradeEffect.valueOf("rhpr"), UpgradeEffect.LIFE_REGEN);
        Assert.assertEquals(UpgradeEffect.valueOf("rmim"), UpgradeEffect.MAGIC_IMMUNITY);
        Assert.assertEquals(UpgradeEffect.valueOf("rmnx"), UpgradeEffect.MANA_MAX);

        Assert.assertEquals(UpgradeEffect.valueOf("rmax"), UpgradeEffect.MANA_MAX_PERC);
        Assert.assertEquals(UpgradeEffect.valueOf("rmnr"), UpgradeEffect.MANA_REGEN);
        Assert.assertEquals(UpgradeEffect.valueOf("rmvx"), UpgradeEffect.MOVE_SPEED_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("rmov"), UpgradeEffect.MOVE_SPEED_UP_PERC);

        Assert.assertEquals(UpgradeEffect.valueOf("rrai"), UpgradeEffect.RAISE_DEAD_DURATION_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("rsig"), UpgradeEffect.SIGHT_RANGE_UP);
        Assert.assertEquals(UpgradeEffect.valueOf("rspi"), UpgradeEffect.SPIKE_BARRIER);
        Assert.assertEquals(UpgradeEffect.valueOf("rauv"), UpgradeEffect.ULTRA_VISION);
        Assert.assertEquals(UpgradeEffect.valueOf("rtma"), UpgradeEffect.UNIT_AVAIL_CHANGE);
    }
}
