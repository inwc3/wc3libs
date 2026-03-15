package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import net.moonlightflower.wc3libs.misc.image.BLP;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
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

    @Test
    public void testBlpJpegAlphaDecode() throws IOException, UnsupportedFormatException {
        File file = getFile("images/transparent_example.blp");
        BLP blp = new BLP(file);
        BufferedImage image = blp.getBufImg();

        int nonOpaque = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int alpha = (image.getRGB(x, y) >> 24) & 0xFF;

                if (alpha < 255) nonOpaque++;
            }
        }

        Assert.assertTrue(nonOpaque > 0, "Expected at least one transparent/semi-transparent pixel after BLP decode");
    }
}
