package net.moonlightflower.wc3libs.misc;

/**
 * Transforms wc3 colors codes |cFFFFFFFF |r to various outputs.
 */
public class ColorCodeTransformer {

    /**
     * Transforms all colors codes in the given input to html for display in swing
     */
    public static String transformToHtml(String input) {
        StringBuilder sb = new StringBuilder("<html>");

        input = input.replaceAll("\\|cff(\\d{6})", "<font color=#$1>" );
        input = input.replaceAll("\\|r", "</font>");
        sb.append(input);

        sb.append("</html>");
        return sb.toString();
    }
}
