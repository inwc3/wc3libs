package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.txt.app.jass.expr.Op;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class Connective implements BoolExpr {
    public interface IConnectiveOp extends Op {

    }

    public enum ConnectiveOp implements IConnectiveOp {
        CONJUNCT {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("and");
            }
        },
        DISJUNCT {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("or");
            }
        }
    }

    private BoolExpr _left;
    private ConnectiveOp _op;
    private BoolExpr _right;

    public Connective(@Nonnull BoolExpr left, @Nonnull ConnectiveOp connectiveOp, @Nonnull BoolExpr right) {
        _left = left;
        _op = connectiveOp;
        _right = right;
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        _left.write(sw);
        sw.write(" ");
        _op.write(sw);
        sw.write(" ");
        _right.write(sw);
    }
}
