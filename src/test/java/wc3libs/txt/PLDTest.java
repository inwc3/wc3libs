package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.PLD;
import net.moonlightflower.wc3libs.txt.app.jass.FuncImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.StringWriter;

public class PLDTest {
    @Test()
    public void test() {
        PLD pld = new PLD();

        pld.addPreload("A");
        pld.addPreload("B.mdx");
        pld.addPreload("C\\D.blp");
        pld.addPreload("quoted\"name\\E.blp");
        pld.addPreload("A");

        FuncImpl funcImpl = pld.toJassFunc();

        Assert.assertNotNull(funcImpl);
        StringWriter writer = new StringWriter();
        funcImpl.write(writer, false);
        Assert.assertEquals(writer.toString(), "function PreloadFiles takes nothing returns nothing\n"
                + "call Preload(\"A\")\n"
                + "call Preload(\"B.mdx\")\n"
                + "call Preload(\"C\\\\D.blp\")\n"
                + "call Preload(\"quoted\\\"name\\\\E.blp\")\n"
                + "endfunction");
    }
}
