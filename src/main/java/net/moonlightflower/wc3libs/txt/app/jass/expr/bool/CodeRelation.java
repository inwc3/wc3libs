package net.moonlightflower.wc3libs.txt.app.jass.expr.bool;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.CodeExpr;

import javax.annotation.Nonnull;

public class CodeRelation extends Relation<CodeExpr, Relation.Op> {
    public CodeRelation(@Nonnull CodeExpr left, @Nonnull Op op, @Nonnull CodeExpr right) {
        super(left, op, right);
    }

    public static BoolExpr create(@Nonnull JassParser.Code_exprContext left, @Nonnull JassParser.Bool_relation_opContext bool_relation_opContext, @Nonnull JassParser.Code_exprContext right) {
        return new CodeRelation(CodeExpr.create(left), createOp(bool_relation_opContext), CodeExpr.create(right));
    }
}
