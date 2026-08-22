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

        System.out.println(Math.encode(1747988549, Math.CODE_ASCII));
    }

    /**
     * A four-character id whose first byte has its high bit set encodes a
     * negative int. The value used to be accumulated through a double, and
     * narrowing a double back to an int saturates instead of wrapping, so every
     * such id decoded to Integer.MAX_VALUE.
     */
    @Test()
    public void decodesAndEncodesHighBitIds() {
        int expected = 0x80666F6F;

        Assert.assertEquals(Math.decode("foo", Math.CODE_ASCII), expected);
        Assert.assertEquals(Math.encode(expected, Math.CODE_ASCII), "foo");
    }

    /** Encoding read the value as signed, so every negative one came out empty. */
    @Test()
    public void encodesNegativeValues() {
        Assert.assertEquals(Math.encode(-1, Math.CODE_HEX), "FFFFFFFF");
        Assert.assertEquals(Math.decode("FFFFFFFF", Math.CODE_HEX), -1);
    }

    /**
     * An unmappable character used to contribute -1, which turned a caller
     * passing the wrong alphabet into a plausible-looking wrong number.
     */
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void rejectsACharacterTheAlphabetDoesNotDefine() {
        Math.decode("0xFF", Math.CODE_HEX);
    }
}
