package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.W3GRP;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

public class W3GRPTest extends Wc3LibTest {
    @Test
    public void readWriteCycleV3() throws Exception {
        W3GRP groups = new W3GRP(getFile("wc3data/W3GRP/war3map_v3.w3grp"));

        Assert.assertEquals(groups.getData(), new byte[12]);
        readWriteCycle(W3GRP.class, getFile("wc3data/W3GRP/war3map_v3.w3grp"));
    }

    @Test
    public void readWriteCycleV3Filled() throws Exception {
        W3GRP groups = new W3GRP(getFile("wc3data/W3GRP/war3map_v3_filled.w3grp"));

        Assert.assertEquals(groups.getData(), new byte[12]);
        readWriteCycle(W3GRP.class, getFile("wc3data/W3GRP/war3map_v3_filled.w3grp"));
    }
}
