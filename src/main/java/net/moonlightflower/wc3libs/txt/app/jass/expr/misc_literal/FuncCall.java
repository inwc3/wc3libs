package net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.AnyTypeExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.StringExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.bool.BoolExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.CodeExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.HandleExpr;
import net.moonlightflower.wc3libs.txt.app.jass.expr.num.NumExpr;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class FuncCall<T extends Expr> implements AnyTypeExpr {
    private String _funcRef;
    private List<Expr> _params;

    public FuncCall(@Nonnull String funcRef, @Nonnull List<Expr> params) {
        _funcRef = funcRef;
        _params = params;
    }

    public static FuncCall create(@Nonnull LightJassParser.Func_refContext func_refContext, @Nonnull LightJassParser.Arg_listContext arg_listContext) {
        List<Expr> params = new ArrayList<>();

        for (LightJassParser.ExprContext exprContext : arg_listContext.expr()) {
            params.add(Expr.create(exprContext));
        }

        return new FuncCall(func_refContext.getText(), params);
    }

    public static FuncCall create(@Nonnull JassParser.Func_refContext func_refContext, @Nonnull JassParser.Arg_listContext arg_listContext) {
        List<Expr> params = new ArrayList<>();

        for (JassParser.ExprContext exprContext : arg_listContext.expr()) {
            params.add(Expr.create(exprContext));
        }

        return new FuncCall(func_refContext.getText(), params);
    }

    public static FuncCall create(@Nonnull LightJassParser.Func_callContext func_callContext) {
        return create(func_callContext.func_ref(), func_callContext.arg_list());
    }

    public static FuncCall create(@Nonnull JassParser.Func_callContext func_callContext) {
        return create(func_callContext.func_ref(), func_callContext.arg_list());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        sw.write(_funcRef);
        sw.write("(");

        boolean first = true;

        for (Expr param : _params) {
            if (first) {
                first = false;
            } else  {
                sw.write(", ");
            }

            param.write(sw);
        }
        sw.write(")");
    }
}
