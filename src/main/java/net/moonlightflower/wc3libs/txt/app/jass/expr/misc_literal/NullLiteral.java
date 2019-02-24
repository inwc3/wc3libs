package net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Literal;
import net.moonlightflower.wc3libs.txt.app.jass.expr.NullExpr;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public class NullLiteral implements NullExpr, Literal {
    public NullLiteral() {
    }

    public static NullLiteral create(@Nonnull TerminalNode terminalNode) {
        return ((Function<Integer, NullLiteral>) tokenType -> {
            if (tokenType == JassLexer.NULL_LITERAL) {
                return new NullLiteral();
            }

            throw new AssertionError("no option for tokenType " + tokenType + "(" + terminalNode + ")");
        }).apply(terminalNode.getSymbol().getType());
    }

    public static NullLiteral create(@Nonnull JassParser.Null_literalContext null_literalContext) {
        return new NullLiteral();
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        sw.write("null");
    }
}
