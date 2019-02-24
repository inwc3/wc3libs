package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.bool.BoolExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.FuncCall;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.CodeExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.HandleExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.IntExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.RealExpr;

import javax.annotation.Nonnull;

public interface AnyTypeExpr extends NullExpr, HandleExpr, CodeExpr, BoolExpr, IntExpr, RealExpr, StringExpr {
    static AnyTypeExpr create(@Nonnull JassParser.Any_expr_atomContext any_expr_atomContext) {
        if (any_expr_atomContext.func_call() != null) {
            return FuncCall.create(any_expr_atomContext.func_call());
        }
        if (any_expr_atomContext.var_ref() != null) {
            return VarRef.create(any_expr_atomContext.var_ref());
        }
        if (any_expr_atomContext.array_read() != null) {
            return ArrayRead.create(any_expr_atomContext.array_read());
        }

        throw new AssertionError("no option for " + any_expr_atomContext.getText());
    }
}
