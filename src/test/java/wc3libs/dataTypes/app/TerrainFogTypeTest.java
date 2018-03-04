package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.SpellDetail;
import net.moonlightflower.wc3libs.dataTypes.app.TerrainFogType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TerrainFogTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(TerrainFogType.valueOf(0), TerrainFogType.NONE);
        Assert.assertEquals(TerrainFogType.valueOf(1), TerrainFogType.LINEAR);
        Assert.assertEquals(TerrainFogType.valueOf(2), TerrainFogType.EXP);
        Assert.assertEquals(TerrainFogType.valueOf(3), TerrainFogType.EXP2);
    }
}
