package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;

public class Math {
    public final static String CODE_ASCII;
    public final static String CODE_HEX = "0123456789ABCDEF";
    public final static String CODE_OCT = "01234567";
    public final static String CODE_DEC = "0123456789";

    public static int decode(@Nonnull String s, @Nonnull String map) {
        int ret = 0;

        for (int i = 0; i < s.length(); i++) {
            ret += map.indexOf(s.charAt(i)) * java.lang.Math.pow(map.length(), s.length() - i - 1);
        }

        return ret;
    }

    public static String encode(int val, @Nonnull String map) {
        StringBuilder sb = new StringBuilder();

        while (val > 0) {
            int div = val / map.length();
            int rem = val % map.length();

            sb.append(map.charAt(rem));

            val = div;
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
