package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.StringExpr;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.function.Function;

public class StringRelation extends Relation<StringExpr, Relation.Op> {
    public StringRelation(@Nonnull StringExpr left, @Nonnull Op op, @Nonnull StringExpr right) {
        super(left, op, right);
    }

    public static BoolExpr create(@Nonnull JassParser.String_exprContext left, @Nonnull JassParser.Bool_relation_opContext bool_relation_opContext, @Nonnull JassParser.String_exprContext right) {
        return new StringRelation(StringExpr.create(left), createOp(bool_relation_opContext), StringExpr.create(right));
    }
}
