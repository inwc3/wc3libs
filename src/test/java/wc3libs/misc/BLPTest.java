package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import net.moonlightflower.wc3libs.misc.image.BLP;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class BLPTest extends Wc3LibTest {
    @Test
    public void testBlp() throws IOException, UnsupportedFormatException {
        File file = getFile("images/test.blp");
        BLP blp = new BLP(file);

        Assert.assertEquals(blp.getHeight(), 64);
        Assert.assertEquals(blp.getWidth(), 64);
    }
}
