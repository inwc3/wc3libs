package net.moonlightflower.wc3libs.txt.app.jass.statement;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.misc.State;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class LoopStatement extends Statement {
    private final List<Statement> _stmts = new ArrayList<>();

    public LoopStatement(@Nonnull List<Statement> stmts) {
        _stmts.addAll(stmts);
    }

    public static LoopStatement create(@Nonnull LightJassParser.LoopContext loopContext) {
        List<Statement> stmts = new ArrayList<>();

        for (LightJassParser.Loop_body_lineContext loopBodyLineContext : loopContext.loop_body().loop_body_line()) {
            for (LightJassParser.StatementContext statementContext : loopBodyLineContext.statement_list().statement()) {
                stmts.add(Statement.create(statementContext));
            }
        }

        return new LoopStatement(stmts);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        super.write(sw);

        sw.write("loop");

        for (Statement stmt : _stmts) {
            sw.write("\n");

            stmt.write(sw);
        }

        sw.write("\nendloop");
    }
}
