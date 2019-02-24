package net.moonlightflower.wc3libs.txt.app.jass.statement;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.Jass;
import net.moonlightflower.wc3libs.txt.app.jass.LightJass;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public abstract class Statement {
    private String _stmt;
    private boolean _isDebug = false;

    @Nonnull
    public static Statement create(@Nonnull LightJassParser.DebugContext debugContext) {
        Statement ret = null;

        LightJassParser.CallContext callFuncContext = debugContext.call();

        if (callFuncContext != null) {
            ret = CallStatement.create(callFuncContext);
        }

        LightJassParser.Set_varContext setVarContext = debugContext.set_var();

        if (setVarContext != null) {
            ret = SetVarStatement.create(setVarContext);
        }

        LightJassParser.SelectionContext selectionContext = debugContext.selection();

        if (selectionContext != null) {
            ret = SelectionStatement.create(selectionContext);
        }

        LightJassParser.LoopContext loopContext = debugContext.loop();

        if (loopContext != null) {
            ret = LoopStatement.create(loopContext);
        }

//        JassParser.ExitwhenContext exitwhenContext = debugContext.exitwhen();
//
//        if (exitwhenContext != null) {
//            ret = new FuncImpl.ExitwhenStatement(exitwhenContext);
//        }
//
//        JassParser.Rule_returnContext returnContext = debugContext.rule_return();
//
//        if (returnContext != null) {
//            ret = new FuncImpl.ReturnStatement(returnContext);
//        }

        if (ret != null) {
            ret._isDebug = true;
        }

        throw new AssertionError("no option for " + debugContext);
    }

    @Nonnull
    public static Statement create(@Nonnull LightJassParser.StatementContext statementContext) {
        LightJassParser.CallContext callFuncContext = statementContext.call();

        if (callFuncContext != null) {
            return CallStatement.create(callFuncContext);
        }

        LightJassParser.Set_varContext setVarContext = statementContext.set_var();

        if (setVarContext != null) {
            return SetVarStatement.create(setVarContext);
        }

        LightJassParser.SelectionContext selectionContext = statementContext.selection();

        if (selectionContext != null) {
            return SelectionStatement.create(selectionContext);
        }

        LightJassParser.LoopContext loopContext = statementContext.loop();

        if (loopContext != null) {
            return LoopStatement.create(loopContext);
        }

        LightJassParser.ExitwhenContext exitwhenContext = statementContext.exitwhen();

        if (exitwhenContext != null) {
            return ExitwhenStatement.create(exitwhenContext);
        }

        LightJassParser.Rule_returnContext returnContext = statementContext.rule_return();

        if (returnContext != null) {
            return ReturnStatement.create(returnContext);
        }

        LightJassParser.DebugContext debugContext = statementContext.debug();

        if (debugContext != null) {
            return create(debugContext);
        }

        throw new AssertionError("no option for " + statementContext);
    }

    @Nonnull
    public static Statement create(@Nonnull String input) {
        return create(LightJass.transform(input).statement());
    }

    public void write(@Nonnull StringWriter sw) {
        if (_isDebug) sw.write("debug ");
    }
}
