package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.CombatSound;
import net.moonlightflower.wc3libs.dataTypes.app.CombatTarget;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CombatTargetTest {
    @Test
    public void test() {
        Assert.assertEquals(CombatTarget.valueOf("air"), CombatTarget.AIR);
        Assert.assertEquals(CombatTarget.valueOf("alive"), CombatTarget.ALIVE);
        Assert.assertEquals(CombatTarget.valueOf("aliv"), CombatTarget.ALIVE);
        Assert.assertEquals(CombatTarget.valueOf("allies"), CombatTarget.ALLY);
        Assert.assertEquals(CombatTarget.valueOf("alli"), CombatTarget.ALLY);
        Assert.assertEquals(CombatTarget.valueOf("ally"), CombatTarget.ALLY);
        Assert.assertEquals(CombatTarget.valueOf("ancient"), CombatTarget.ANCIENT);
        Assert.assertEquals(CombatTarget.valueOf("bridge"), CombatTarget.BRIDGE);
        Assert.assertEquals(CombatTarget.valueOf("dead"), CombatTarget.DEAD);
        Assert.assertEquals(CombatTarget.valueOf("debris"), CombatTarget.DEBRIS);
        Assert.assertEquals(CombatTarget.valueOf("debr"), CombatTarget.DEBRIS);
        Assert.assertEquals(CombatTarget.valueOf("decoration"), CombatTarget.DECORATION);
        Assert.assertEquals(CombatTarget.valueOf("deco"), CombatTarget.DECORATION);
        Assert.assertEquals(CombatTarget.valueOf("enemies"), CombatTarget.ENEMY);
        Assert.assertEquals(CombatTarget.valueOf("enem"), CombatTarget.ENEMY);
        Assert.assertEquals(CombatTarget.valueOf("enemy"), CombatTarget.ENEMY);
        Assert.assertEquals(CombatTarget.valueOf("friend"), CombatTarget.FRIEND);
        Assert.assertEquals(CombatTarget.valueOf("frie"), CombatTarget.FRIEND);
        Assert.assertEquals(CombatTarget.valueOf("ground"), CombatTarget.GROUND);
        Assert.assertEquals(CombatTarget.valueOf("grou"), CombatTarget.GROUND);
        Assert.assertEquals(CombatTarget.valueOf("hero"), CombatTarget.HERO);
        Assert.assertEquals(CombatTarget.valueOf("invu"), CombatTarget.INVUL);
        Assert.assertEquals(CombatTarget.valueOf("invulnerable"), CombatTarget.INVUL);
        Assert.assertEquals(CombatTarget.valueOf("item"), CombatTarget.ITEM);
        Assert.assertEquals(CombatTarget.valueOf("mechanical"), CombatTarget.MECH);
        Assert.assertEquals(CombatTarget.valueOf("mech"), CombatTarget.MECH);
        Assert.assertEquals(CombatTarget.valueOf("neutral"), CombatTarget.NEUTRAL);
        Assert.assertEquals(CombatTarget.valueOf("neut"), CombatTarget.NEUTRAL);
        Assert.assertEquals(CombatTarget.valueOf("nonancient"), CombatTarget.NON_ANCIENT);
        Assert.assertEquals(CombatTarget.valueOf("nonhero"), CombatTarget.NON_HERO);
        Assert.assertEquals(CombatTarget.valueOf("nonh"), CombatTarget.NON_HERO);
        Assert.assertEquals(CombatTarget.valueOf("nonsapper"), CombatTarget.NON_SAPPER);
        Assert.assertEquals(CombatTarget.valueOf("none"), CombatTarget.NONE);
        Assert.assertEquals(CombatTarget.valueOf("notself"), CombatTarget.NOT_SELF);
        Assert.assertEquals(CombatTarget.valueOf("nots"), CombatTarget.NOT_SELF);
        Assert.assertEquals(CombatTarget.valueOf("organic"), CombatTarget.ORGANIC);
        Assert.assertEquals(CombatTarget.valueOf("orga"), CombatTarget.ORGANIC);
        Assert.assertEquals(CombatTarget.valueOf("player"), CombatTarget.PLAYER);
        Assert.assertEquals(CombatTarget.valueOf("play"), CombatTarget.PLAYER);
        Assert.assertEquals(CombatTarget.valueOf("sapper"), CombatTarget.SAPPER);
        Assert.assertEquals(CombatTarget.valueOf("self"), CombatTarget.SELF);
        Assert.assertEquals(CombatTarget.valueOf("structure"), CombatTarget.STRUCTURE);
        Assert.assertEquals(CombatTarget.valueOf("stru"), CombatTarget.STRUCTURE);
        Assert.assertEquals(CombatTarget.valueOf("terrain"), CombatTarget.TERRAIN);
        Assert.assertEquals(CombatTarget.valueOf("terr"), CombatTarget.TERRAIN);
        Assert.assertEquals(CombatTarget.valueOf("tree"), CombatTarget.TREE);
        Assert.assertEquals(CombatTarget.valueOf("vulnerable"), CombatTarget.VUL);
        Assert.assertEquals(CombatTarget.valueOf("vuln"), CombatTarget.VUL);
        Assert.assertEquals(CombatTarget.valueOf("wall"), CombatTarget.WALL);
        Assert.assertEquals(CombatTarget.valueOf("ward"), CombatTarget.WARD);
    }
}
