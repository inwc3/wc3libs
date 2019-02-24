package net.moonlightflower.wc3libs.txt.app.jass.statement;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.misc_literal.FuncCall;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class CallStatement extends Statement {
    private FuncCall _funcCall;

    public CallStatement(@Nonnull FuncCall funcCall) {
        _funcCall = funcCall;
    }

    public static CallStatement create(@Nonnull LightJassParser.CallContext callContext) {
        return new CallStatement(FuncCall.create(callContext.func_call()));
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        super.write(sw);

        sw.write("call ");

        _funcCall.write(sw);
    }
}
