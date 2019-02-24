package net.moonlightflower.wc3libs.txt.app.jass.expr;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class StringConcat implements StringExpr {
    public interface IConcatOp extends Op {

    }

    public enum ConcatOp implements IConcatOp {
        CONCAT {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("+");
            }
        }
    }

    private StringExpr _left;
    private ConcatOp _op = ConcatOp.CONCAT;
    private StringExpr _right;

    public StringConcat(@Nonnull StringExpr left, @Nonnull StringExpr right) {
        _left = left;
        _right = right;
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        _left.write(sw);
        _op.write(sw);
        _right.write(sw);
    }
}
