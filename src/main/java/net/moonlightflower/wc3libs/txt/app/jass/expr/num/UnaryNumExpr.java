package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Op;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public abstract class UnaryNumExpr implements NumExpr {
    public interface IUnaryOp extends Op {

    }

    public enum Sign implements IUnaryOp {
        PLUS {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("+");
            }
        },
        MINUS {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("-");
            }
        }
    }

    private Sign _sign;
    private NumExpr _expr;

    public UnaryNumExpr(@Nonnull Sign sign, @Nonnull NumExpr expr) {
        _sign = sign;
        _expr = expr;
    }

    public static Sign createOp(@Nonnull JassParser.Num_unary_opContext num_unary_opContext) {
        return ((Function<JassParser.Num_unary_opContext, Sign>) num_unary_opContext1 -> {
            if (num_unary_opContext1.ADD() != null) return Sign.PLUS;
            if (num_unary_opContext1.SUB() != null) return Sign.MINUS;

            throw new AssertionError("no option for " + num_unary_opContext1.getText());
        }).apply(num_unary_opContext);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        _sign.write(sw);

        if (_expr instanceof Sum || _expr instanceof Prod) sw.write("(");
        _expr.write(sw);
        if (_expr instanceof Sum || _expr instanceof Prod) sw.write(")");
    }
}
