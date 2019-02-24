package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import javax.annotation.Nonnull;

public class IntProd extends Prod implements IntExpr {
    public IntProd(@Nonnull NumExpr left, @Nonnull ProdOp op, @Nonnull NumExpr right) {
        super(left, op, right);
    }
}
