package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.bool.BoolExpr;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public interface Condition {
    /*static Condition create(@Nonnull JassParser.ConditionContext conditionContext) {
        return BoolExpr.create(conditionContext.bool_expr());
    }*/

    static Condition create(@Nonnull LightJassParser.ConditionContext conditionContext) {
        return BoolExpr.create(conditionContext.expr());
    }

    void write(@Nonnull StringWriter sw);
}
