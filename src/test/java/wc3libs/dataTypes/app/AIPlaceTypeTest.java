package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.AIPlaceType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AIPlaceTypeTest {
    @Test
    public void test() {
        Assert.assertEquals(AIPlaceType.valueOf(0), AIPlaceType.NONE);
        Assert.assertEquals(AIPlaceType.valueOf(1), AIPlaceType.TOWNHALL);
        Assert.assertEquals(AIPlaceType.valueOf(2), AIPlaceType.RESOURCE);
        Assert.assertEquals(AIPlaceType.valueOf(3), AIPlaceType.FACTORY);
        Assert.assertEquals(AIPlaceType.valueOf(4), AIPlaceType.GENERAL);
    }
}
