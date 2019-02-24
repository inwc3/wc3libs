package net.moonlightflower.wc3libs.txt.app.jass.statement;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.Condition;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class ExitwhenStatement extends Statement {
    private Condition _condition;

    public ExitwhenStatement(@Nonnull Condition condition) {
        _condition = condition;
    }

    public static ExitwhenStatement create(@Nonnull LightJassParser.ExitwhenContext exitwhenContext) {
        return new ExitwhenStatement(Condition.create(exitwhenContext.condition()));
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        super.write(sw);

        sw.write("exitwhen ");

        _condition.write(sw);
    }
}
