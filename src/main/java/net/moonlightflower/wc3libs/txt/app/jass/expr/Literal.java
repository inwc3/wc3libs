package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.IntLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.RealLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.bool.BoolLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.CodeLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.NullLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.StringLiteral;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public interface Literal extends Expr {
    static Literal create(@Nonnull JassParser.Bool_literalContext literalContext) {
        return BoolLiteral.create(literalContext.BOOL_LITERAL());
    }

    static Literal create(@Nonnull JassParser.Int_literalContext literalContext) {
        return IntLiteral.create(literalContext);
    }

    static Literal create(@Nonnull JassParser.Real_literalContext literalContext) {
        return RealLiteral.create(literalContext.REAL_LITERAL());
    }

    static Literal create(@Nonnull JassParser.String_literalContext literalContext) {
        if (literalContext.STRING_LITERAL() != null) {
            return StringLiteral.create(literalContext.STRING_LITERAL());
        }

        return new NullLiteral();
    }

    void write(@Nonnull StringWriter sw);
}
