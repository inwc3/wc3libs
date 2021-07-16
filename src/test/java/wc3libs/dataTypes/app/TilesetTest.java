package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.Tileset;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TilesetTest {
    @Test
    public void test() {
        Assert.assertEquals(Tileset.valueOf('*'), Tileset.ALL);

        Assert.assertEquals(Tileset.valueOf('A'), Tileset.ASHENVALE);
        Assert.assertEquals(Tileset.valueOf('B'), Tileset.BARRENS);
        Assert.assertEquals(Tileset.valueOf('C'), Tileset.FELWOOD);
        Assert.assertEquals(Tileset.valueOf('D'), Tileset.DUNGEON);
        Assert.assertEquals(Tileset.valueOf('F'), Tileset.LORDAERON_FALL);
        Assert.assertEquals(Tileset.valueOf('G'), Tileset.UNDERGROUND);
        Assert.assertEquals(Tileset.valueOf('L'), Tileset.LORDAERON_SUMMER);
        Assert.assertEquals(Tileset.valueOf('N'), Tileset.NORTHREND);
        Assert.assertEquals(Tileset.valueOf('Q'), Tileset.VILLAGE_FALL);
        Assert.assertEquals(Tileset.valueOf('V'), Tileset.VILLAGE);
        Assert.assertEquals(Tileset.valueOf('W'), Tileset.LORDAERON_WINTER);
        Assert.assertEquals(Tileset.valueOf('X'), Tileset.DALARAN);
        Assert.assertEquals(Tileset.valueOf('Y'), Tileset.CITYSCAPE);
        Assert.assertEquals(Tileset.valueOf('Z'), Tileset.SUNKEN_RUINS);
        Assert.assertEquals(Tileset.valueOf('I'), Tileset.ICECROWN);
        Assert.assertEquals(Tileset.valueOf('J'), Tileset.DALARAN_RUINS);
        Assert.assertEquals(Tileset.valueOf('O'), Tileset.OUTLAND);
        Assert.assertEquals(Tileset.valueOf('K'), Tileset.BLACK_CITADEL);
    }
}
