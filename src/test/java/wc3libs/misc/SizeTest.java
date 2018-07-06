package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.Size;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SizeTest {
    @Test()
    public void test() {
        Size size = new Size(10, 20);

        Assert.assertEquals(size.getWidth(), 10);
        Assert.assertEquals(size.getHeight(), 20);
        Assert.assertEquals(size.getArea(), 10*20);

        Assert.assertEquals(size, new Size(10, 20));

        Assert.assertEquals(size.toString(), "10x20");
    }
}
