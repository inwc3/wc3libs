package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import net.moonlightflower.wc3libs.misc.image.TGA;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TGATest extends Wc3LibTest {
    @Test
    public void testTga() throws IOException, UnsupportedFormatException {
        File file = getFile("images/test.tga");
        TGA tga = new TGA(file);

        Assert.assertEquals(tga.getHeight(), 64);
        Assert.assertEquals(tga.getWidth(), 64);
    }
}
