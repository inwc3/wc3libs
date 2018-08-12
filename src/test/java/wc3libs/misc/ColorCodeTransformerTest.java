package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.ColorCodeTransformer;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ColorCodeTransformerTest {

    @Test
    public void testTransformToHtml() {
        String result = ColorCodeTransformer.transformToHtml("|cff122132Hello World!|r");
        assertEquals(result, "<html><font color=#122132>Hello World!</font></html>");
    }
}