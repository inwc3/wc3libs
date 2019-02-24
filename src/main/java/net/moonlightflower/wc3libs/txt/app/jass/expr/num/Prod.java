package net.moonlightflower.wc3libs.txt.app.jass.expr.num;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Op;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public abstract class Prod implements NumExpr {
    public interface IProdOp extends net.moonlightflower.wc3libs.txt.app.jass.expr.Op {
        void write(@Nonnull StringWriter sw);
    }

    public enum ProdOp implements IProdOp {
        MULT {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("*");
            }
        },
        DIV {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("/");
            }
        },
        MOD {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("%");
            }
        }
    }

    private NumExpr _left;
    private Op _op;
    private NumExpr _right;

    public Prod(@Nonnull NumExpr left, @Nonnull Op op, @Nonnull NumExpr right) {
        _left = left;
        _op = op;
        _right = right;
    }

    public static ProdOp createOp(@Nonnull JassParser.Num_prod_opContext num_prod_opContext) {
        return ((Function<JassParser.Num_prod_opContext, ProdOp>) num_prod_opContext1 -> {
            if (num_prod_opContext1.MULT() != null) return ProdOp.MULT;
            if (num_prod_opContext1.DIV() != null) return ProdOp.DIV;
            if (num_prod_opContext1.MOD() != null) return ProdOp.MOD;

            return null;
        }).apply(num_prod_opContext);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        _left.write(sw);
        _op.write(sw);
        _right.write(sw);
    }
}
