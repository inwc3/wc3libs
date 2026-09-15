package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.W3L;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

public class W3LTest extends Wc3LibTest {
    @Test
    public void readWriteCycleV3() throws Exception {
        W3L lights = new W3L(getFile("wc3data/W3L/war3map_v3.w3l"));

        Assert.assertEquals(lights.getVersion(), 3);
        Assert.assertEquals(lights.getModelPath(0), "");
        Assert.assertEquals(lights.getModelPath(1), "");
        Assert.assertEquals(lights.getTrailingData(), new byte[0]);
        readWriteCycle(W3L.class, getFile("wc3data/W3L/war3map_v3.w3l"));
    }

    @Test
    public void readWriteCycleV3Filled() throws Exception {
        W3L lights = new W3L(getFile("wc3data/W3L/war3map_v3_filled.w3l"));

        Assert.assertEquals(lights.getVersion(), 3);
        Assert.assertEquals(lights.getModelPath(0), "05a5f320-9ab4-47d3-85c4-142625f983e1.mdl");
        Assert.assertEquals(lights.getModelPath(1), "1067db77-5b50-4a5c-8b62-8008c4529a51.mdl");
        Assert.assertEquals(new int[] { lights.getOption(0), lights.getOption(1), lights.getOption(2) }, new int[] { 0, 1, 0 });
        Assert.assertEquals(lights.getTrailingData(), new byte[0]);
        readWriteCycle(W3L.class, getFile("wc3data/W3L/war3map_v3_filled.w3l"));
    }
}
