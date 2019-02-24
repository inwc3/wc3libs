package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;

import javax.annotation.Nonnull;

public class RealUnary extends UnaryNumExpr implements RealExpr {
    public RealUnary(@Nonnull Sign sign, @Nonnull NumExpr expr) {
        super(sign, expr);
    }

    public static RealUnary create(@Nonnull JassParser.Num_unary_opContext num_unary_opContext, @Nonnull JassParser.Real_maybe_unaryContext real_maybe_unaryContext) {
        return new RealUnary(UnaryNumExpr.createOp(num_unary_opContext), RealExpr.create(real_maybe_unaryContext));
    }
}
