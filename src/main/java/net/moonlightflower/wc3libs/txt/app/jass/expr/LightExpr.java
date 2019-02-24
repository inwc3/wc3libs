package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.LightJassParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class LightExpr implements AnyTypeExpr {
    private String _expr;

    public LightExpr(@Nonnull String expr) {
        _expr = expr;
    }

    private static void makeText(@Nonnull ParseTree cxt, @Nonnull StringBuilder sb) {
        if (cxt instanceof TerminalNode) {
            //System.out.println("terminal " + cxt.getText());
            if (sb.length() > 0) {
                sb.append(" ");
            }

            sb.append(cxt.getText());

            return;
        }

        for (int i = 0; i < cxt.getChildCount(); i++) {
            makeText(cxt.getChild(i), sb);
        }
    }

    public static LightExpr create(@Nonnull LightJassParser.ExprContext exprContext) {
        StringBuilder sb = new StringBuilder();

        makeText(exprContext, sb);

        return new LightExpr(sb.toString());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        sw.write(_expr);
    }
}
