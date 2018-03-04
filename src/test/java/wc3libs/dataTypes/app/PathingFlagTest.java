package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.MoveType;
import net.moonlightflower.wc3libs.dataTypes.app.PathingFlag;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PathingFlagTest {
    @Test
    public void test() {
        Assert.assertEquals(PathingFlag.valueOf("blighted"), PathingFlag.BLIGHT);
        Assert.assertEquals(PathingFlag.valueOf("unamph"), PathingFlag.UNAMPH);
        Assert.assertEquals(PathingFlag.valueOf("unbuildable"), PathingFlag.UNBUILD);
        Assert.assertEquals(PathingFlag.valueOf("unfloat"), PathingFlag.UNFLOAT);
        Assert.assertEquals(PathingFlag.valueOf("unflyable"), PathingFlag.UNFLY);
        Assert.assertEquals(PathingFlag.valueOf("unwalkable"), PathingFlag.UNWALK);

        Assert.assertEquals(PathingFlag.valueOf(0x1), PathingFlag.BLIGHT);
        Assert.assertEquals(PathingFlag.valueOf(0x0), PathingFlag.UNAMPH);
        Assert.assertEquals(PathingFlag.valueOf(0x8), PathingFlag.UNBUILD);
        Assert.assertEquals(PathingFlag.valueOf(0x0), PathingFlag.UNFLOAT);
        Assert.assertEquals(PathingFlag.valueOf(0x4), PathingFlag.UNFLY);
        Assert.assertEquals(PathingFlag.valueOf(0x2), PathingFlag.UNWALK);
    }
}
