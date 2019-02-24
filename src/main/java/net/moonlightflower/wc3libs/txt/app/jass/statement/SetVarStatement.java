package net.moonlightflower.wc3libs.txt.app.jass.statement;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.LightExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.IntExpr;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;

public class SetVarStatement extends Statement {
    private String _varRef;
    private Expr _index;
    private Expr _val;

    public SetVarStatement(@Nonnull String varRef, @Nullable IntExpr index, @Nonnull Expr val) {
        _varRef = varRef;
        _index = index;
        _val = val;
    }

    public static SetVarStatement create(@Nonnull LightJassParser.Set_varContext set_varContext) {
        return new SetVarStatement(set_varContext.var_ref().getText(), set_varContext.index != null ? LightExpr.create(set_varContext.index) : null, LightExpr.create(set_varContext.val));
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        super.write(sw);

        sw.write("set ");

        sw.write(_varRef);

        if (_index != null) {
            sw.write("[");
            _index.write(sw);
            sw.write("]");
        }

        sw.write("=");

        _val.write(sw);
    }
}
