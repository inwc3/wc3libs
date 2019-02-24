package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.misc.Math;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public class IntLiteral implements NumLiteral, IntExpr {
    private int _val;

    public enum Type {
        OCT,
        DEC,
        HEX,
        ID
    }

    private Type _type;

    public IntLiteral(int val, @Nonnull Type type) {
        _val = val;
        _type = type;
    }

    /*public static IntLiteral create(@Nonnull TerminalNode terminalNode) {
        return ((Function<Integer, IntLiteral>) tokenType -> {
            if (tokenType == JassLexer.OCT_INT_LITERAL) {
                return new IntLiteral(Math.decode(terminalNode.getText(), Math.CODE_OCT), Type.OCT);
            }
            if (tokenType == JassLexer.DEC_INT_LITERAL) {
                return new IntLiteral(Math.decode(terminalNode.getText(), Math.CODE_DEC), Type.DEC);
            }
            if (tokenType == JassLexer.HEX_INT_LITERAL) {
                return new IntLiteral(Math.decode(terminalNode.getText(), Math.CODE_HEX), Type.HEX);
            }
            if (tokenType == JassLexer.ID_INT_LITERAL) {
                return new IntLiteral(Math.decode(terminalNode.getText(), Math.CODE_ASCII), Type.ID);
            }

            throw new AssertionError("no option for tokenType " + tokenType + " (" + terminalNode + ")");
        }).apply(terminalNode.getSymbol().getType());
    }*/

    public static IntLiteral create(@Nonnull JassParser.Int_literalContext int_literalContext) {
        if (int_literalContext.OCT_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(int_literalContext.OCT_INT_LITERAL().getText(), Math.CODE_OCT), Type.OCT);
        }
        if (int_literalContext.DEC_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(int_literalContext.DEC_INT_LITERAL().getText(), Math.CODE_DEC), Type.DEC);
        }
        if (int_literalContext.HEX_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(int_literalContext.HEX_INT_LITERAL().getText(), Math.CODE_HEX), Type.HEX);
        }
        if (int_literalContext.ID_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(int_literalContext.ID_INT_LITERAL().getText(), Math.CODE_ASCII), Type.ID);
        }
        //return create(int_literalContext.INT_LITERAL());

        throw new AssertionError("no option for " + int_literalContext.getText());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        switch (_type) {
            case OCT:
                sw.write(Math.encode(_val, Math.CODE_OCT));

                break;
            case DEC:
                sw.write(Math.encode(_val, Math.CODE_DEC));

                break;
            case HEX:
                sw.write(Math.encode(_val, Math.CODE_HEX));

                break;
            case ID:
                sw.write(Math.encode(_val, Math.CODE_ASCII));

                break;
            default:
                throw new AssertionError("no option for " + _type);
        }
    }
}
