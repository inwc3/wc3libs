package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;

import javax.annotation.Nonnull;

public class IntUnary extends UnaryNumExpr implements IntExpr {
    public IntUnary(@Nonnull Sign sign, @Nonnull NumExpr expr) {
        super(sign, expr);
    }

    public static IntUnary create(@Nonnull JassParser.Num_unary_opContext num_unary_opContext, @Nonnull JassParser.Int_maybe_unaryContext int_maybe_unaryContext) {
        return new IntUnary(UnaryNumExpr.createOp(num_unary_opContext), IntExpr.create(int_maybe_unaryContext));
    }
}
