package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import javax.annotation.Nonnull;

public class RealSum extends Sum<RealExpr> implements RealExpr {
    protected RealSum(@Nonnull RealExpr left, @Nonnull SumOp op, @Nonnull RealExpr right) {
        super(left, op, right);
    }
}
