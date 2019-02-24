package net.moonlightflower.wc3libs.txt.app.jass.statement;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;

public class ReturnStatement extends Statement {
    private Expr _expr;

    public ReturnStatement(@Nullable Expr expr) {
        _expr = expr;
    }

    public static ReturnStatement create(@Nonnull LightJassParser.Rule_returnContext ruleReturnContext) {
        Expr expr = ruleReturnContext.expr() != null ? Expr.create(ruleReturnContext.expr()) : null;

        return new ReturnStatement(expr);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        super.write(sw);

        sw.write("return");

        if (_expr != null) {
            sw.write(" ");

            _expr.write(sw);
        }
    }
}
