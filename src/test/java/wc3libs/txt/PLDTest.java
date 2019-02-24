package wc3libs.txt;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.PLD;
import net.moonlightflower.wc3libs.txt.app.jass.FuncImpl;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.misc.Interval;
import org.testng.annotations.Test;

public class PLDTest {
    @Test()
    public void test() {
        PLD pld = new PLD();

        pld.addPreload("A");
        pld.addPreload("B.mdx");
        pld.addPreload("C\\D.blp");

        FuncImpl funcImpl = pld.toJassFunc();

        //System.out.println(CharStreams.fromString(funcImpl.getStart().getInputStream().getText(new Interval(func.start.getStartIndex(), func.stop.getStopIndex()))));
    }
}
