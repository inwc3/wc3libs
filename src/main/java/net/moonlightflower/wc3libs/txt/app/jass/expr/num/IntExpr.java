package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.AnyTypeExpr;

import javax.annotation.Nonnull;

public interface IntExpr extends NumExpr, RealExpr {
    static IntExpr create(@Nonnull JassParser.Any_expr_atomContext any_expr_atomContext) {
        return AnyTypeExpr.create(any_expr_atomContext);
    }

    static IntExpr create(@Nonnull JassParser.Int_parensContext int_parensContext) {
        return create(int_parensContext.int_expr());
    }

    static IntExpr create(@Nonnull JassParser.Int_atomContext int_atomContext) {
        if (int_atomContext.int_literal() != null) {
            return IntLiteral.create(int_atomContext.int_literal());
        }
        if (int_atomContext.int_parens() != null) {
            return create(int_atomContext.int_parens());
        }

        return create(int_atomContext.any_expr_atom());
    }

    static IntExpr create(@Nonnull JassParser.Int_maybe_unaryContext int_maybe_unaryContext) {
        if (int_maybe_unaryContext.num_unary_op() != null) {
            return IntUnary.create(int_maybe_unaryContext.num_unary_op(), int_maybe_unaryContext.int_maybe_unary());
        }

        return create(int_maybe_unaryContext.int_atom());
    }

    static IntProd create(@Nonnull JassParser.Int_maybe_prodContext left, @Nonnull JassParser.Num_prod_opContext num_prod_opContext, @Nonnull JassParser.Int_maybe_unaryContext right) {
        return new IntProd(create(left), Prod.createOp(num_prod_opContext), create(right));
    }

    static IntExpr create(@Nonnull JassParser.Int_maybe_prodContext int_maybe_prodContext) {
        if (int_maybe_prodContext.num_prod_op() != null) {
            return create(int_maybe_prodContext.left, int_maybe_prodContext.num_prod_op(), int_maybe_prodContext.right);
        }

        return create(int_maybe_prodContext.int_maybe_unary());
    }

    static IntSum create(@Nonnull JassParser.Int_maybe_sumContext left, @Nonnull JassParser.Num_sum_opContext num_sum_opContext, @Nonnull JassParser.Int_maybe_prodContext right) {
        return new IntSum(create(left), Sum.createOp(num_sum_opContext), create(right));
    }

    static IntExpr create(@Nonnull JassParser.Int_maybe_sumContext int_maybe_sumContext) {
        if (int_maybe_sumContext.num_sum_op() != null) {
            return create(int_maybe_sumContext.left, int_maybe_sumContext.num_sum_op(), int_maybe_sumContext.right);
        }

        return create(int_maybe_sumContext.int_maybe_prod());
    }

    static IntExpr create(@Nonnull JassParser.Int_exprContext int_exprContext) {
        return create(int_exprContext.int_maybe_sum());
    }
}
