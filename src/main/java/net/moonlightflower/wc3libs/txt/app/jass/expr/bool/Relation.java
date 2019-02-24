package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public abstract class Relation<T extends Expr, T2 extends Relation.IOp> implements BoolExpr {
    protected interface IOp {
        void write(@Nonnull StringWriter sw);
    }

    public enum Op implements IOp {
        EQUAL {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("=");
            }
        },
        UNEQUAL {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("!=");
            }
        }
    }

    private T _left;
    private T2 _op;
    private T _right;

    public Relation(@Nonnull T left, @Nonnull T2 op, @Nonnull T right) {
        _left = left;
        _op = op;
        _right = right;
    }

    public static Op createOp(@Nonnull JassParser.Bool_relation_opContext bool_relation_opContext) {
        return ((Function<JassParser.Bool_relation_opContext, Op>) bool_relation_opContext1 -> {
            if (bool_relation_opContext1.EQUAL() != null) return Op.EQUAL;
            if (bool_relation_opContext1.UNEQUAL() != null) return Op.UNEQUAL;

            throw new AssertionError("no option for " + bool_relation_opContext1);
        }).apply(bool_relation_opContext);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {

    }
}
