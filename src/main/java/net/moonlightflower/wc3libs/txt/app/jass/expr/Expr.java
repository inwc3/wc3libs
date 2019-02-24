package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.Jass;
import net.moonlightflower.wc3libs.txt.app.jass.expr.bool.BoolExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.FuncCall;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.NullLiteral;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.*;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public interface Expr {
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

        throw new AssertionError("no option for " + any_expr_atomContext);
    }

    static Expr create(@Nonnull JassParser.ExprContext exprContext) {
        if (exprContext.handle_expr() != null) {
            return HandleExpr.create(exprContext.handle_expr());
        }
        if (exprContext.null_expr() != null) {
            return NullExpr.create(exprContext.null_expr());
        }
        if (exprContext.code_expr() != null) {
            return CodeExpr.create(exprContext.code_expr());
        }
        if (exprContext.bool_expr() != null) {
            return BoolExpr.create(exprContext.bool_expr());
        }
        if (exprContext.int_expr() != null) {
            return NumExpr.create(exprContext.int_expr());
        }
        if (exprContext.real_expr() != null) {
            return NumExpr.create(exprContext.real_expr());
        }
        if (exprContext.string_expr() != null) {
            return StringExpr.create(exprContext.string_expr());
        }

        throw new AssertionError("no option for " + exprContext.getText());
    }

    static Expr create(@Nonnull LightJassParser.ExprContext exprContext) {
        return LightExpr.create(exprContext);
    }

    static Expr create(@Nonnull String input) {
        return create(Jass.transform(input).expr());
    }

    void write(@Nonnull StringWriter sw);
}
