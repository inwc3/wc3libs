package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.NullLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.StringLiteral;

import javax.annotation.Nonnull;

public interface StringExpr extends Expr {
    static StringExpr create(@Nonnull JassParser.String_literalContext string_literalContext) {
        if (string_literalContext.null_literal() != null) {
            return NullLiteral.create(string_literalContext.null_literal());
        }

        return new StringLiteral(string_literalContext.getText());
    }

    static StringExpr create(@Nonnull JassParser.String_parensContext string_parensContext) {
        return create(string_parensContext.string_expr());
    }

    static StringExpr create(@Nonnull JassParser.String_atomContext string_atomContext) {
        if (string_atomContext.string_literal() != null) {
            return create(string_atomContext.string_literal());
        }
        if (string_atomContext.string_parens() != null) {
            return create(string_atomContext.string_parens());
        }

        return AnyTypeExpr.create(string_atomContext.any_expr_atom());
    }

    static StringExpr create(@Nonnull JassParser.String_maybe_concatContext string_maybe_concatContext) {
        if (string_maybe_concatContext.string_concat_op() != null) {
            return new StringConcat(StringExpr.create(string_maybe_concatContext.left), create(string_maybe_concatContext.right));
        }

        return create(string_maybe_concatContext.string_atom());
    }

    static StringExpr create(@Nonnull JassParser.String_exprContext string_exprContext) {
        return create(string_exprContext.string_maybe_concat());
    }
}
