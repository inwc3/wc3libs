package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.HandleExpr;

import javax.annotation.Nonnull;

public class HandleRelation extends Relation<HandleExpr, Relation.Op> {
    public HandleRelation(@Nonnull HandleExpr left, @Nonnull Op op, @Nonnull HandleExpr right) {
        super(left, op, right);
    }

    public static BoolExpr create(@Nonnull JassParser.Handle_exprContext left, @Nonnull JassParser.Bool_relation_opContext bool_relation_opContext, @Nonnull JassParser.Handle_exprContext right) {
        return new HandleRelation(HandleExpr.create(left), createOp(bool_relation_opContext), HandleExpr.create(right));
    }
}
