package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import javax.annotation.Nonnull;

public class IntSum extends Sum<IntExpr> implements IntExpr {
    protected IntSum(@Nonnull IntExpr left, @Nonnull SumOp op, @Nonnull IntExpr right) {
        super(left, op, right);
    }
}
