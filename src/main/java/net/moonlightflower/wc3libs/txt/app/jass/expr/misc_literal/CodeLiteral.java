package net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Literal;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.CodeExpr;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;

public class CodeLiteral implements CodeExpr, Literal {
    private String _funcRef;

    public CodeLiteral(@Nullable String funcRef) {
        _funcRef = funcRef;
    }

    public static CodeLiteral create(@Nonnull JassParser.Code_literalContext code_literalContext) {
        if (code_literalContext.func_ref() != null) {
            return new CodeLiteral(code_literalContext.func_ref().getText());
        }

        return new CodeLiteral(null);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        if (_funcRef == null) {
            new NullLiteral().write(sw);
        } else {
            sw.write("function ");

            sw.write(_funcRef);
        }
    }
}
