package wc3libs.slk;

import net.moonlightflower.wc3libs.slk.SLK;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SLKTest {
    @Test()
    public void formatTest() {
        Assert.assertEquals(SLK.formatVal(1.0), 1);
        Assert.assertEquals(SLK.formatVal(0.5), 0.5F);

        Assert.assertEquals(SLK.formatVal("0"), null);
        Assert.assertEquals(SLK.formatVal("-0"), null);
        Assert.assertEquals(SLK.formatVal("0.0"), null);
        Assert.assertEquals(SLK.formatVal("0.123"), 0.123D);
        Assert.assertEquals(SLK.formatVal("0.-0"), "\"0.-0\"");
        Assert.assertEquals(SLK.formatVal("000 4"), "\"000 4\"");
        Assert.assertEquals(SLK.formatVal("0"), null);

        Assert.assertEquals(SLK.formatVal(";"), "\";\"");
        Assert.assertEquals(SLK.formatVal("abc"), "abc");

        Assert.assertEquals(SLK.formatVal("1"), 1);
    }
}
