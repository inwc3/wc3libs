package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.Tileset;
import net.moonlightflower.wc3libs.dataTypes.app.UnitClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitClassTest {
    @Test
    public void test() {
        Assert.assertEquals(UnitClass.valueOf("ancient"), UnitClass.ANCIENT);
        Assert.assertEquals(UnitClass.valueOf("giant"), UnitClass.GIANT);
        Assert.assertEquals(UnitClass.valueOf("mechanical"), UnitClass.MECH);
        Assert.assertEquals(UnitClass.valueOf("neutral"), UnitClass.NEUTRAL);
        Assert.assertEquals(UnitClass.valueOf("peon"), UnitClass.PEON);
        Assert.assertEquals(UnitClass.valueOf("sapper"), UnitClass.SAPPER);
        Assert.assertEquals(UnitClass.valueOf("summoned"), UnitClass.SUMMON);
        Assert.assertEquals(UnitClass.valueOf("tauren"), UnitClass.TAUREN);
        Assert.assertEquals(UnitClass.valueOf("townhall"), UnitClass.TOWNHALL);
        Assert.assertEquals(UnitClass.valueOf("tree"), UnitClass.TREE);
        Assert.assertEquals(UnitClass.valueOf("undead"), UnitClass.UNDEAD);
        Assert.assertEquals(UnitClass.valueOf("standon"), UnitClass.WALKABLE);
        Assert.assertEquals(UnitClass.valueOf("ward"), UnitClass.WARD);
    }
}
