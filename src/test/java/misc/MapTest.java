package misc;

import net.moonlightflower.wc3libs.app.Env;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;

public class MapTest extends Wc3LibTest {
    @Test
    public void testMap() throws Exception {
        File file = getFile("in.w3x");
        Env env = Env.ofMapFile(file);

        Assert.assertEquals(env.getWidth(), 129);
    }
}
