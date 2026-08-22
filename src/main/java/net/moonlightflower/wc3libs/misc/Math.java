package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;

public class Math {
    public final static String CODE_ASCII;
    public final static String CODE_HEX = "0123456789ABCDEF";
    public final static String CODE_OCT = "01234567";
    public final static String CODE_DEC = "0123456789";

    /**
     * Reads {@code s} as a number written in the positional system whose digits
     * are the characters of {@code map}, in order.
     * <p>
     * The digits are accumulated as integers. This used to multiply by
     * {@code java.lang.Math.pow}, which is a double: narrowing a double back to
     * an int saturates instead of wrapping, so a four-character id whose first
     * character has its high bit set -- anything from {@code ''} up, which
     * is what an id encoding a negative int looks like -- decoded to
     * {@link Integer#MAX_VALUE} rather than to the value it stands for.
     *
     * @param s   digits to read, most significant first.
     * @param map the digit alphabet; a character's index is its value.
     * @return the value, wrapping on overflow as integer arithmetic does.
     * @throws IllegalArgumentException if {@code s} holds a character the
     *                                  alphabet does not define. Silently
     *                                  treating it as -1, as this did, turned a
     *                                  caller's mistake into a plausible-looking
     *                                  wrong number.
     */
    public static int decode(@Nonnull String s, @Nonnull String map) {
        int radix = map.length();
        int ret = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int digit = map.indexOf(c);

            if (digit < 0) {
                throw new IllegalArgumentException(String.format(
                    "'%c' (0x%02X) at index %d is not a digit of the given alphabet", c, (int) c, i));
            }

            ret = ret * radix + digit;
        }

        return ret;
    }

    /**
     * The inverse of {@link #decode}: writes {@code val} in the positional
     * system whose digits are the characters of {@code map}.
     * <p>
     * {@code val} is taken as unsigned, so a value that decoded from a
     * high-bit id encodes back to the same id. Reading it as signed, as this
     * did, made every negative value encode to the empty string.
     *
     * @param val value to write.
     * @param map the digit alphabet; a character's index is its value.
     * @return the digits, most significant first; empty for zero.
     */
    @Nonnull
    public static String encode(int val, @Nonnull String map) {
        long radix = map.length();
        long rest = Integer.toUnsignedLong(val);

        StringBuilder sb = new StringBuilder();

        while (rest > 0) {
            sb.append(map.charAt((int) (rest % radix)));

            rest /= radix;
        }

        return sb.reverse().toString();
    }

    static {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 256; i++) {
            sb.append((char) i);
        }

        CODE_ASCII = sb.toString();
    }
}
