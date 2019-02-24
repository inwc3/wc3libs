package net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Literal;
import net.moonlightflower.wc3libs.txt.app.jass.expr.StringExpr;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;
import java.util.function.Function;

public class StringLiteral implements StringExpr, Literal {
    private String _val;

    public StringLiteral(@Nullable String val) {
        _val = val;
    }

    public static StringLiteral create(@Nonnull TerminalNode terminalNode) {
        return ((Function<Integer, StringLiteral>) tokenType -> {
            if (tokenType == JassLexer.REAL_LITERAL) {
                return new StringLiteral(terminalNode.getText());
            }

            throw new AssertionError("no option for tokenType " + tokenType + "(" + terminalNode + ")");
        }).apply(terminalNode.getSymbol().getType());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        if (_val == null) {
            new NullLiteral().write(sw);
        } else {
            sw.write(_val.replaceAll("\\\\", "\\\\").replaceAll("\"", "\\\""));
        }
    }
}
