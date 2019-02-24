package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public class RealLiteral implements NumLiteral, RealExpr {
    private float _val;

    public RealLiteral(float val) {
        _val = val;
    }

    public static RealLiteral create(@Nonnull TerminalNode terminalNode) {
        return ((Function<Integer, RealLiteral>) tokenType -> {
            if (tokenType == JassLexer.REAL_LITERAL) {
                return new RealLiteral(Float.parseFloat(terminalNode.getText()));
            }

            throw new AssertionError("no option for tokenType " + tokenType + "(" + terminalNode + ")");
        }).apply(terminalNode.getSymbol().getType());
    }

    public static RealLiteral create(@Nonnull JassParser.Real_literalContext real_literalContext) {
        return create(real_literalContext.REAL_LITERAL());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        sw.write(Float.toString(_val));
    }
}
