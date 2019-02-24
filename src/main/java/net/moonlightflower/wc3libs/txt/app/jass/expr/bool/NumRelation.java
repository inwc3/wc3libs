package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.NumExpr;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public class NumRelation extends Relation<NumExpr, NumRelation.Op> {
    public enum Op implements Relation.IOp {
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
        },
        LESS {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("<");
            }
        },
        LESS_EQUAL {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write("<=");
            }
        },
        GREATER {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write(">");
            }
        },
        GREATER_EQUAL {
            @Override
            public void write(@Nonnull StringWriter sw) {
                sw.write(">=");
            }
        }
    }

    private NumExpr _left;
    private Op _op;
    private NumExpr _right;

    public NumRelation(@Nonnull NumExpr left, @Nonnull Op op, @Nonnull NumExpr right) {
        super(left, op, right);

        _left = left;
        _op = op;
        _right = right;
    }

    public static Op createOp(@Nonnull JassParser.Bool_num_order_relation_opContext bool_num_order_relation_opContext) {
        return ((Function<JassParser.Bool_num_order_relation_opContext, Op>) bool_num_order_relation_opContext1 -> {
            if (bool_num_order_relation_opContext1.LESS() != null) return Op.LESS;
            if (bool_num_order_relation_opContext1.LESS_EQUAL() != null) return Op.LESS_EQUAL;
            if (bool_num_order_relation_opContext1.GREATER() != null) return Op.GREATER;
            if (bool_num_order_relation_opContext1.GREATER_EQUAL() != null) return Op.GREATER_EQUAL;

            throw new AssertionError("no option for " + bool_num_order_relation_opContext1.getText());
        }).apply(bool_num_order_relation_opContext);
    }

    public static Op createNumOp(@Nonnull JassParser.Bool_relation_opContext bool_relation_opContext) {
        return ((Function<JassParser.Bool_relation_opContext, Op>) bool_relation_opContext1 -> {
            if (bool_relation_opContext1.EQUAL() != null) return Op.EQUAL;
            if (bool_relation_opContext1.UNEQUAL() != null) return Op.UNEQUAL;

            throw new AssertionError("no option for " + bool_relation_opContext1.getText());
        }).apply(bool_relation_opContext);
    }

    public static BoolExpr create(@Nonnull JassParser.Num_exprContext left, @Nonnull JassParser.Bool_num_order_relation_opContext bool_num_order_relation_opContext, @Nonnull JassParser.Num_exprContext right) {
        return new NumRelation(NumExpr.create(left), createOp(bool_num_order_relation_opContext), NumExpr.create(right));
    }

    public static BoolExpr create(@Nonnull JassParser.Num_exprContext left, @Nonnull JassParser.Bool_relation_opContext bool_relation_opContext, @Nonnull JassParser.Num_exprContext right) {
        return new NumRelation(NumExpr.create(left), createNumOp(bool_relation_opContext), NumExpr.create(right));
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        _left.write(sw);
        switch (_op) {
            case EQUAL:
                sw.write("=");

                break;
            case UNEQUAL:
                sw.write("!=");

                break;
            case LESS:
                sw.write("<");

                break;
            case LESS_EQUAL:
                sw.write("<=");

                break;
            case GREATER:
                sw.write(">");

                break;
            case GREATER_EQUAL:
                sw.write(">=");

                break;
            default:
                throw new AssertionError("no option for " + _op);
        }
        _right.write(sw);
    }
}
