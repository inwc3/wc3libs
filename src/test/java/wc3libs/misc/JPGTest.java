package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.image.JPG;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class JPGTest extends Wc3LibTest {
    @Test
    public void testJpg() throws FileNotFoundException {
        File file = getFile("test.jpg");
        JPG jpg = new JPG(file);

        Assert.assertEquals(jpg.getHeight(), 256);
        Assert.assertEquals(jpg.getWidth(), 256);
    }
}
