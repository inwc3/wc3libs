package net.moonlightflower.wc3libs.misc;

import java.util.regex.Pattern;

/**
 * Transforms wc3 colors codes |cFFFFFFFF |r to various outputs.
 */
public class ColorCodeTransformer {

    private static final Pattern COLOR_CODE_PATTERN = Pattern.compile("\\|cff(\\d{6})", Pattern.CASE_INSENSITIVE);
    
    /**
     * Transforms all colors codes in the given input to html for display in swing
     */
    public static String transformToHtml(String input) {
        StringBuilder sb = new StringBuilder("<html>");

        input = COLOR_CODE_PATTERN.matcher(input).replaceAll("<font color=#$1>");
        input = input.replaceAll("\\|r", "</font>");
        sb.append(input);

        sb.append("</html>");
        return sb.toString();
    }
}
