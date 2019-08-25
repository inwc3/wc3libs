package wc3libs.misc;

import org.testng.Assert;
import org.testng.annotations.Test;
import net.moonlightflower.wc3libs.misc.StringHash;

public class StringHashTest {

    @Test
    public void testStringHash() throws Exception {
        Assert.assertEquals(StringHash.hash(""), 0);
        Assert.assertEquals(StringHash.hash("0"), -242600650);
        Assert.assertEquals(StringHash.hash("12345678901234"), 306791019);
        /*Assert.assertEquals(StringHash.hash("äöü"), 1243485226);
        Assert.assertEquals(StringHash.hash("abc"), 1043745117);
        Assert.assertEquals(StringHash.hash("我"), 1458977629);*/
    }

}