package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Literal;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public class BoolLiteral implements BoolExpr, Literal {
    private boolean _val;

    public BoolLiteral(boolean val) {
        _val = val;
    }

    public static BoolLiteral create(@Nonnull TerminalNode terminalNode) {
        return ((Function<Integer, BoolLiteral>) tokenType -> {
            if (tokenType == JassLexer.BOOL_LITERAL) {
                return new BoolLiteral(((Function<String, Boolean>) s -> {
                    if (s.equals("true")) return Boolean.TRUE;
                    if (s.equals("false")) return Boolean.FALSE;

                    throw new AssertionError("no option for " + s + "(" + terminalNode + ")");
                }).apply(terminalNode.getText()));
            }

            throw new AssertionError("no option for tokenType " + tokenType + "(" + terminalNode + ")");
        }).apply(terminalNode.getSymbol().getType());
    }

    public static BoolLiteral create(@Nonnull JassParser.Bool_literalContext bool_literalContext) {
        return create(bool_literalContext.BOOL_LITERAL());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        if (_val) {
            sw.write("true");
        } else {
            sw.write("false");
        }
    }
}
