package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Op;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class UnaryBoolExpr implements BoolExpr {
    public interface IUnaryOp extends Op {

    }

    public enum UnaryOp implements IUnaryOp {
        NEG {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("not");
            }
        }
    }

    private UnaryOp _op;
    private BoolExpr _expr;

    public UnaryBoolExpr(@Nonnull UnaryOp op, @Nonnull BoolExpr expr) {
        _op = op;
        _expr = expr;
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        _op.write(sw);
        sw.write(" ");
        _expr.write(sw);
    }
}
