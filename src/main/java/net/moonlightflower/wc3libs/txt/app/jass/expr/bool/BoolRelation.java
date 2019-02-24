package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassParser;

import javax.annotation.Nonnull;

public class BoolRelation extends Relation<BoolExpr, Relation.Op> {
    public BoolRelation(@Nonnull BoolExpr left, @Nonnull Op op, @Nonnull BoolExpr right) {
        super(left, op, right);
    }

    public static BoolExpr create(@Nonnull JassParser.Bool_exprContext left, @Nonnull JassParser.Bool_relation_opContext bool_relation_opContext, @Nonnull JassParser.Bool_exprContext right) {
        return new BoolRelation(BoolExpr.create(left), createOp(bool_relation_opContext), BoolExpr.create(right));
    }
}
