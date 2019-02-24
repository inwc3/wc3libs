package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.NullExpr;

import javax.annotation.Nonnull;

public class NullRelation extends Relation<NullExpr,Relation.Op> {
    public NullRelation(@Nonnull NullExpr left, @Nonnull Op op, @Nonnull NullExpr right) {
        super(left, op, right);
    }

    public static BoolExpr create(@Nonnull JassParser.Null_exprContext left, @Nonnull JassParser.Bool_relation_opContext bool_relation_opContext, @Nonnull JassParser.Null_exprContext right) {
        return new NullRelation(NullExpr.create(left), createOp(bool_relation_opContext), NullExpr.create(right));
    }
}
