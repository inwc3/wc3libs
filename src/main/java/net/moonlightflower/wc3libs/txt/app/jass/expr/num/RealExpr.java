package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.AnyTypeExpr;

import javax.annotation.Nonnull;

public interface RealExpr extends NumExpr {
    static RealExpr create(@Nonnull JassParser.Any_expr_atomContext any_expr_atomContext) {
        return AnyTypeExpr.create(any_expr_atomContext);
    }

    static RealExpr create(@Nonnull JassParser.Real_parensContext real_parensContext) {
        return create(real_parensContext.real_expr());
    }

    static RealExpr create(@Nonnull JassParser.Real_atomContext real_atomContext) {
        if (real_atomContext.int_atom() != null) {
            return IntExpr.create(real_atomContext.int_atom());
        }
        if (real_atomContext.real_literal() != null) {
            return RealLiteral.create(real_atomContext.real_literal());
        }
        if (real_atomContext.real_parens() != null) {
            return create(real_atomContext.real_parens());
        }

        return create(real_atomContext.any_expr_atom());
    }

    static RealExpr create(@Nonnull JassParser.Real_maybe_unaryContext real_maybe_unaryContext) {
        if (real_maybe_unaryContext.num_unary_op() != null) {
            return RealUnary.create(real_maybe_unaryContext.num_unary_op(), real_maybe_unaryContext.real_maybe_unary());
        }

        return create(real_maybe_unaryContext.real_atom());
    }

    static RealProd create(@Nonnull JassParser.Real_maybe_prodContext left, @Nonnull JassParser.Num_prod_opContext num_prod_opContext, @Nonnull JassParser.Real_maybe_unaryContext right) {
        return new RealProd(create(left), Prod.createOp(num_prod_opContext), create(right));
    }

    static RealExpr create(@Nonnull JassParser.Real_maybe_prodContext real_maybe_prodContext) {
        if (real_maybe_prodContext.num_prod_op() != null) {
            return create(real_maybe_prodContext.left, real_maybe_prodContext.num_prod_op(), real_maybe_prodContext.right);
        }

        return create(real_maybe_prodContext.real_maybe_unary());
    }

    static RealSum create(@Nonnull JassParser.Real_maybe_sumContext left, @Nonnull JassParser.Num_sum_opContext num_sum_opContext, @Nonnull JassParser.Real_maybe_prodContext right) {
        return new RealSum(create(left), Sum.createOp(num_sum_opContext), create(right));
    }

    static RealExpr create(@Nonnull JassParser.Real_maybe_sumContext real_maybe_sumContext) {
        if (real_maybe_sumContext.num_sum_op() != null) {
            return create(real_maybe_sumContext.left, real_maybe_sumContext.num_sum_op(), real_maybe_sumContext.right);
        }

        return create(real_maybe_sumContext.real_maybe_prod());
    }

    static RealExpr create(@Nonnull JassParser.Real_exprContext real_exprContext) {
        return create(real_exprContext.real_maybe_sum());
    }
}
