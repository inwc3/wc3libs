package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.ItemClass;
import net.moonlightflower.wc3libs.dataTypes.app.MoveType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MoveTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(MoveType.valueOf("amph"), MoveType.AMPHIBIOUS);
        Assert.assertEquals(MoveType.valueOf("float"), MoveType.FLOAT);
        Assert.assertEquals(MoveType.valueOf("fly"), MoveType.FLY);
        Assert.assertEquals(MoveType.valueOf("foot"), MoveType.FOOT);
        Assert.assertEquals(MoveType.valueOf("horse"), MoveType.HORSE);
        Assert.assertEquals(MoveType.valueOf("hover"), MoveType.HOVER);
    }
}
