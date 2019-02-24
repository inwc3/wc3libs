package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.Condition;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.LightExpr;

import javax.annotation.Nonnull;

public interface BoolExpr extends Expr, Condition {
    static BoolExpr create(@Nonnull JassParser.Bool_string_relationContext bool_string_relationContext) {
        return StringRelation.create(bool_string_relationContext.left, bool_string_relationContext.bool_relation_op(), bool_string_relationContext.right);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_parensContext bool_parensContext) {
        return create(bool_parensContext.bool_expr());
    }

    static BoolExpr create(@Nonnull JassParser.Bool_atomContext bool_atomContext) {
        if (bool_atomContext.any_expr_atom() != null) {
            return Expr.create(bool_atomContext.any_expr_atom());
        }
        if (bool_atomContext.bool_literal() != null) {
            return BoolLiteral.create(bool_atomContext.bool_literal());
        }
        if (bool_atomContext.bool_parens() != null) {
            return create(bool_atomContext.bool_parens());
        }

        throw new AssertionError("no option for " + bool_atomContext.getText());
    }

    static BoolExpr create(@Nonnull JassParser.Bool_maybe_unaryContext bool_maybe_unaryContext) {
        if (bool_maybe_unaryContext.BOOL_OP_NEG() != null) {
            return new UnaryBoolExpr(UnaryBoolExpr.UnaryOp.NEG, create(bool_maybe_unaryContext.bool_maybe_unary()));
        }

        return create(bool_maybe_unaryContext.bool_atom());
    }

    static BoolExpr create(@Nonnull JassParser.Bool_num_order_relationContext bool_num_order_relationContext) {
        return NumRelation.create(bool_num_order_relationContext.left, bool_num_order_relationContext.bool_num_order_relation_op(), bool_num_order_relationContext.right);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_maybe_num_order_relationContext bool_maybe_num_order_relationContext) {
        if (bool_maybe_num_order_relationContext.bool_num_order_relation() != null) {
            return create(bool_maybe_num_order_relationContext.bool_num_order_relation());
        }

        return create(bool_maybe_num_order_relationContext.bool_maybe_unary());
    }

    static BoolExpr create(@Nonnull JassParser.Bool_maybe_num_order_relationContext left, @Nonnull JassParser.Bool_relation_opContext bool_relation_opContext, @Nonnull JassParser.Bool_maybe_num_order_relationContext right) {
        return new BoolRelation(create(left), BoolRelation.createOp(bool_relation_opContext), create(right));
    }

    static BoolExpr create(@Nonnull JassParser.Bool_num_relationContext bool_num_relationContext) {
        return NumRelation.create(bool_num_relationContext.left, bool_num_relationContext.bool_relation_op(), bool_num_relationContext.right);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_bool_relationContext bool_bool_relationContext) {
        return create(bool_bool_relationContext.left, bool_bool_relationContext.bool_relation_op(), bool_bool_relationContext.right);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_code_relationContext bool_code_relationContext) {
        return CodeRelation.create(bool_code_relationContext.left, bool_code_relationContext.bool_relation_op(), bool_code_relationContext.right);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_null_relationContext bool_null_relationContext) {
        return NullRelation.create(bool_null_relationContext.left, bool_null_relationContext.bool_relation_op(), bool_null_relationContext.right);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_full_relationContext bool_full_relationContext) {
        if (bool_full_relationContext.bool_null_relation() != null) {
            return create(bool_full_relationContext.bool_null_relation());
        }
        if (bool_full_relationContext.bool_code_relation() != null) {
            return create(bool_full_relationContext.bool_code_relation());
        }
        if (bool_full_relationContext.bool_bool_relation() != null) {
            return create(bool_full_relationContext.bool_bool_relation());
        }
        if (bool_full_relationContext.bool_num_relation() != null) {
            return create(bool_full_relationContext.bool_num_relation());
        }
        if (bool_full_relationContext.bool_string_relation() != null) {
            return create(bool_full_relationContext.bool_string_relation());
        }

        throw new AssertionError("no option for " + bool_full_relationContext.getText());
    }

    static BoolExpr create(@Nonnull JassParser.Bool_maybe_full_relationContext bool_maybe_full_relationContext) {
        if (bool_maybe_full_relationContext.bool_full_relation() != null) {
            return create(bool_maybe_full_relationContext.bool_full_relation());
        }

        return create(bool_maybe_full_relationContext.bool_maybe_num_order_relation());
    }

    static BoolExpr create(@Nonnull JassParser.Bool_maybe_conjunctContext bool_maybe_conjunctContext) {
        if (bool_maybe_conjunctContext.BOOL_OP_CONJUNCT() != null) {
            return new Connective(create(bool_maybe_conjunctContext.left), Connective.ConnectiveOp.CONJUNCT, create(bool_maybe_conjunctContext.right));
        }

        return create(bool_maybe_conjunctContext.exit);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_maybe_disjunctContext bool_maybe_disjunctContext) {
        if (bool_maybe_disjunctContext.BOOL_OP_DISJUNCT() != null) {
            return new Connective(create(bool_maybe_disjunctContext.left), Connective.ConnectiveOp.DISJUNCT, create(bool_maybe_disjunctContext.right));
        }

        return create(bool_maybe_disjunctContext.exit);
    }

    static BoolExpr create(@Nonnull JassParser.Bool_exprContext bool_exprContext) {
        return create(bool_exprContext.bool_maybe_disjunct());
    }

    static BoolExpr create(@Nonnull LightJassParser.ExprContext exprContext) {
        return LightExpr.create(exprContext);
    }
}
