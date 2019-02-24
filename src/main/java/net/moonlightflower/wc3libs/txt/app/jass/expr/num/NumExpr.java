package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;

import javax.annotation.Nonnull;

public interface NumExpr extends Expr {
    static NumExpr create(@Nonnull JassParser.Num_exprContext num_exprContext) {
        if (num_exprContext.int_expr() != null) {
            return create(num_exprContext.int_expr());
        }
        if (num_exprContext.real_expr() != null) {
            return create(num_exprContext.real_expr());
        }

        throw new AssertionError("no option for " + num_exprContext.getText());
    }

    static NumExpr create(@Nonnull JassParser.Int_exprContext int_exprContext) {
        return IntExpr.create(int_exprContext);
    }

    static NumExpr create(@Nonnull JassParser.Real_exprContext real_exprContext) {
        return RealExpr.create(real_exprContext);
    }
}
