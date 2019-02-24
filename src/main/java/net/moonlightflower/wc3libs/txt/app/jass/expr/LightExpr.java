package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.LightJassParser;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class LightExpr implements AnyTypeExpr {
    private String _expr;

    public LightExpr(@Nonnull String expr) {
        _expr = expr;
    }

    public static LightExpr create(@Nonnull LightJassParser.ExprContext exprContext) {
        return new LightExpr(exprContext.getText());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        sw.write(_expr);
    }
}
