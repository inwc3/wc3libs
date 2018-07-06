package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.Math;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MathTest {
    @Test()
    public void decodeEncode() {
        Assert.assertEquals(Math.decode("A", Math.CODE_ASCII), 65);

        Assert.assertEquals(Math.encode(65, Math.CODE_ASCII), "A");

        Assert.assertEquals(Math.encode(1747988548, Math.CODE_ASCII), "h00D");

        Assert.assertEquals(Math.decode("D00h", Math.CODE_ASCII), 1144008808);
    }
}
