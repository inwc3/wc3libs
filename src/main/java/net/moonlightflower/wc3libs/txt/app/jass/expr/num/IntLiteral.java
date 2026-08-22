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

    public int getVal() {
        return _val;
    }

    @Nonnull
    public Type getType() {
        return _type;
    }

    /**
     * Reads any of JASS's four integer literal spellings from its source text,
     * prefix, quotes and all.
     * <p>
     * This lives here because it was open-coded in two places that had drifted
     * apart, each getting a different part of the same problem wrong.
     *
     * @param text a literal exactly as it appears in a script.
     * @return the literal, remembering which spelling it came from so it can be
     *         written back the same way.
     * @throws IllegalArgumentException if {@code text} is not an integer
     *                                  literal.
     */
    @Nonnull
    public static IntLiteral parse(@Nonnull String text) {
        if (text.isEmpty()) throw new IllegalArgumentException("empty integer literal");

        if (text.startsWith("0x") || text.startsWith("0X") || text.startsWith("$")) {
            return new IntLiteral(Math.decode(hexDigits(text), Math.CODE_HEX), Type.HEX);
        }
        if (text.startsWith("'")) {
            return new IntLiteral(Math.decode(idChars(text), Math.CODE_ASCII), Type.ID);
        }
        if (text.length() > 1 && text.charAt(0) == '0') {
            return new IntLiteral(Math.decode(text.substring(1), Math.CODE_OCT), Type.OCT);
        }

        return new IntLiteral(Math.decode(text, Math.CODE_DEC), Type.DEC);
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

    /**
     * Strips the {@code 0x}, {@code 0X} or {@code $} a hexadecimal literal
     * carries and upper-cases its digits.
     * <p>
     * The grammar keeps the prefix and allows either case, and the alphabet
     * {@link Math#CODE_HEX} defines is upper case and holds no {@code x}. So
     * decoding the token text as it came out of the lexer read {@code 0xFF} as
     * four digits, one of them not a digit at all, and read {@code 0xff} as
     * having no digits above 9.
     */
    @Nonnull
    private static String hexDigits(@Nonnull String text) {
        String digits = text.startsWith("$") ? text.substring(1) : text.substring(2);

        return digits.toUpperCase(java.util.Locale.ROOT);
    }

    /**
     * Strips the quotes around an id literal, which the grammar keeps in the
     * token text. Decoding them along with the id read {@code 'hfoo'} as a
     * six-character number.
     */
    @Nonnull
    private static String idChars(@Nonnull String text) {
        return text.substring(1, text.length() - 1);
    }

    public static IntLiteral create(@Nonnull JassParser.Int_literalContext int_literalContext) {
        if (int_literalContext.OCT_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(int_literalContext.OCT_INT_LITERAL().getText(), Math.CODE_OCT), Type.OCT);
        }
        if (int_literalContext.DEC_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(int_literalContext.DEC_INT_LITERAL().getText(), Math.CODE_DEC), Type.DEC);
        }
        if (int_literalContext.HEX_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(hexDigits(int_literalContext.HEX_INT_LITERAL().getText()), Math.CODE_HEX), Type.HEX);
        }
        if (int_literalContext.ID_INT_LITERAL() != null) {
            return new IntLiteral(Math.decode(idChars(int_literalContext.ID_INT_LITERAL().getText()), Math.CODE_ASCII), Type.ID);
        }
        //return create(int_literalContext.INT_LITERAL());

        throw new AssertionError("no option for " + int_literalContext.getText());
    }

    /**
     * Writes the literal back as JASS.
     * <p>
     * The syntax each form needs around its digits is written too. Without it
     * a hexadecimal literal came out as bare digits and an id came out without
     * its quotes, so neither survived being parsed again -- and a value that
     * happened to be all decimal digits changed meaning on the way through.
     */
    @Override
    public void write(@Nonnull StringWriter sw) {
        switch (_type) {
            case OCT -> {
                String digits = Math.encode(_val, Math.CODE_OCT);

                // Octal is spelled with a leading zero, and zero itself is
                // spelled "0" rather than as the empty string encode() gives.
                sw.write(digits.isEmpty() ? "0" : "0" + digits);
            }
            case DEC -> {
                String digits = Math.encode(_val, Math.CODE_DEC);

                sw.write(digits.isEmpty() ? "0" : digits);
            }
            case HEX -> {
                String digits = Math.encode(_val, Math.CODE_HEX);

                sw.write("0x" + (digits.isEmpty() ? "0" : digits));
            }
            case ID -> sw.write("'" + Math.encode(_val, Math.CODE_ASCII) + "'");
            default -> throw new AssertionError("no option for " + _type);
        }
    }
}
