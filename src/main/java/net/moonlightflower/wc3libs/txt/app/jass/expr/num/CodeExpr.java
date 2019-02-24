package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.CodeLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.NullLiteral;

import javax.annotation.Nonnull;

public interface CodeExpr extends Expr {
    static CodeExpr create(@Nonnull JassParser.Code_parensContext code_parensContext) {
        return create(code_parensContext.code_expr());
    }

    static CodeExpr create(@Nonnull JassParser.Code_exprContext code_exprContext) {
        if (code_exprContext.code_literal() != null) {
            return CodeLiteral.create(code_exprContext.code_literal());
        }
        if (code_exprContext.code_parens() != null) {
            return create(code_exprContext.code_parens());
        }

        return Expr.create(code_exprContext.any_expr_atom());
    }
}
