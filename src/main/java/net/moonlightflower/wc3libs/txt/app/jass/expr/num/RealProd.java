package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import javax.annotation.Nonnull;

public class RealProd extends Prod implements RealExpr {
    public RealProd(@Nonnull NumExpr left, @Nonnull ProdOp op, @Nonnull NumExpr right) {
        super(left, op, right);
    }
}
