package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.UnitRace;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitRaceTest {
    @Test
    public void test() {
        Assert.assertEquals(UnitRace.valueOf("human"), UnitRace.HUMAN);
        Assert.assertEquals(UnitRace.valueOf("orc"), UnitRace.ORC);
        Assert.assertEquals(UnitRace.valueOf("undead"), UnitRace.UNDEAD);
        Assert.assertEquals(UnitRace.valueOf("nightelf"), UnitRace.NIGHT_ELF);
        Assert.assertEquals(UnitRace.valueOf("demon"), UnitRace.DEMON);
        Assert.assertEquals(UnitRace.valueOf("creeps"), UnitRace.CREEPS);
        Assert.assertEquals(UnitRace.valueOf("critters"), UnitRace.CRITTERS);
        Assert.assertEquals(UnitRace.valueOf("other"), UnitRace.OTHER);
        Assert.assertEquals(UnitRace.valueOf("commoner"), UnitRace.COMMONER);
        Assert.assertEquals(UnitRace.valueOf("naga"), UnitRace.NAGA);
        Assert.assertEquals(UnitRace.valueOf("unknown"), UnitRace.UNKNOWN);
    }
}
