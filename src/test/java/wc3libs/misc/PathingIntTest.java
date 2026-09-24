package wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.PathMap.PathingInt;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PathingIntTest {
    @Test
    public void decodesIntegralValuesWithoutLoss() throws DataTypeInfo.CastException {
        PathingInt decoder = PathingInt.valueOf(0);

        Assert.assertEquals(decoder.decode(7).toInt(), 7);
        Assert.assertEquals(decoder.decode(7L).toInt(), 7);
        Assert.assertEquals(decoder.decode("-12").toInt(), -12);
        Assert.assertEquals(decoder.decode(7.0).toInt(), 7);

        PathingInt existing = PathingInt.valueOf(3);
        Assert.assertSame(decoder.decode(existing), existing);
    }

    @Test
    public void serializesAsAnInteger() {
        PathingInt pathing = PathingInt.valueOf(0x35);

        Assert.assertEquals(pathing.toSLKVal(), 0x35);
        Assert.assertEquals(pathing.toTXTVal(), 0x35);
    }

    @Test
    public void rejectsNullFractionalAndOutOfRangeValues() {
        PathingInt decoder = PathingInt.valueOf(0);

        Assert.expectThrows(DataTypeInfo.CastException.class, () -> decoder.decode(null));
        Assert.expectThrows(DataTypeInfo.CastException.class, () -> decoder.decode("not a number"));
        Assert.expectThrows(DataTypeInfo.CastException.class, () -> decoder.decode(1.5));
        Assert.expectThrows(DataTypeInfo.CastException.class, () -> decoder.decode(Long.MAX_VALUE));
    }
}
