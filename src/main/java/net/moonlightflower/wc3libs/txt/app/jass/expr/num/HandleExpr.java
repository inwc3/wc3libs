package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.NullLiteral;

import javax.annotation.Nonnull;

public interface HandleExpr extends Expr {
    static HandleExpr create(@Nonnull JassParser.Handle_literalContext handle_literalContext) {
        return NullLiteral.create(handle_literalContext.null_literal());
    }

    static HandleExpr create(@Nonnull JassParser.Handle_parensContext handle_parensContext) {
        return create(handle_parensContext.handle_expr());
    }

    static HandleExpr create(@Nonnull JassParser.Handle_exprContext handle_exprContext) {
        if (handle_exprContext.handle_literal() != null) {
            return create(handle_exprContext.handle_literal());
        }
        if (handle_exprContext.handle_parens() != null) {
            return create(handle_exprContext.handle_parens());
        }

        return Expr.create(handle_exprContext.any_expr_atom());
    }
}
