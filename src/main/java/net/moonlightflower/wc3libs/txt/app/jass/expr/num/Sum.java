package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Op;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public abstract class Sum<T extends NumExpr> implements NumExpr {
    interface ISumOp extends Op {
    }

    enum SumOp implements ISumOp {
        ADD {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("+");
            }
        },
        SUB {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("-");
            }
        }
    }

    private T _left;
    private Op _op;
    private T _right;

    protected Sum(@Nonnull T left, @Nonnull Op op, @Nonnull T right) {
        _left = left;
        _op = op;
        _right = right;
    }

    public static SumOp createOp(@Nonnull JassParser.Num_sum_opContext num_sum_opContext) {
        return ((Function<JassParser.Num_sum_opContext, SumOp>) num_sum_opContext1 -> {
            if (num_sum_opContext1.ADD() != null) return SumOp.ADD;
            if (num_sum_opContext1.SUB() != null) return SumOp.SUB;

            return null;
        }).apply(num_sum_opContext);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        _left.write(sw);
        _op.write(sw);
        _right.write(sw);
    }
}
