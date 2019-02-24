package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.IntExpr;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class ArrayRead implements AnyTypeExpr {
    private String _varRef;
    private IntExpr _index;

    public ArrayRead(@Nonnull String varRef, @Nonnull IntExpr index) {
        _varRef = varRef;
        _index = index;
    }

    public static ArrayRead create(@Nonnull JassParser.Array_readContext array_readContext) {
        return new ArrayRead(array_readContext.var_ref().getText(), IntExpr.create(array_readContext.int_expr()));
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        sw.write(_varRef);
        sw.write("[");
        _index.write(sw);
        sw.write("]");
    }
}
