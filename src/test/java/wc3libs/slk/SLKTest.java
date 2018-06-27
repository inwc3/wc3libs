package wc3libs.slk;

import static org.testng.Assert.assertEquals;

public class SLKTest {
    public static String stripSpace(String toBeStripped) {
        StringBuilder result = new StringBuilder();
        boolean lastWasSpace = true;
        for (int i = 0; i < toBeStripped.length(); i++) {
            char c = toBeStripped.charAt(i);
            if (Character.isWhitespace(c)) {
                if (!lastWasSpace) {
                    result.append(' ');
                }
                lastWasSpace = true;
            } else {
                result.append(c);
                lastWasSpace = false;
            }
        }
        return result.toString().trim();
    }

    public static void assertEqualsIgnoreWhitespace(String actual, String expected) {
        assertEquals(stripSpace(actual), stripSpace(expected));
    }

}
