package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.NullLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.CodeExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.HandleExpr;

import javax.annotation.Nonnull;

public interface NullExpr extends HandleExpr, CodeExpr, StringExpr {
    static NullExpr create(@Nonnull JassParser.Null_parensContext null_parensContext) {
        return create(null_parensContext.null_expr());
    }

    static NullExpr create(@Nonnull JassParser.Null_exprContext null_exprContext) {
        if (null_exprContext.null_parens() != null) {
            return create(null_exprContext.null_parens());
        }
        if (null_exprContext.any_expr_atom() != null) {
            return AnyTypeExpr.create(null_exprContext.any_expr_atom());
        }

        return NullLiteral.create(null_exprContext.null_literal());
    }
}
