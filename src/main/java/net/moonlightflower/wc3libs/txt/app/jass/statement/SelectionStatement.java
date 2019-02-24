package net.moonlightflower.wc3libs.txt.app.jass.statement;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.Condition;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class SelectionStatement extends Statement {
    public static class ConditionalBranch {
        private Condition _condition;
        private final List<Statement> _statements = new ArrayList<>();

        public ConditionalBranch(@Nonnull Condition condition, List<Statement> statements) {
            _condition = condition;
            _statements.clear();
            _statements.addAll(statements);
        }
    }

    private ConditionalBranch _thenBranch;
    private final List<ConditionalBranch> _elseifBranches = new ArrayList<>();
    private List<Statement> _elseStmts = new ArrayList<>();

    public SelectionStatement(@Nonnull ConditionalBranch thenBranch, @Nonnull List<ConditionalBranch> elseifBranches, @Nonnull List<Statement> elseStmts) {
        _thenBranch = thenBranch;
        _elseifBranches.addAll(elseifBranches);
        _elseStmts.addAll(elseStmts);
    }

    public static SelectionStatement create(@Nonnull LightJassParser.SelectionContext selectionContext) {
        Condition thenCondition = Condition.create(selectionContext.condition());
        List<Statement> thenStatements = new ArrayList<>();

        LightJassParser.Statement_listContext thenStatement_listContext = selectionContext.thenStatements;

        if (thenStatement_listContext != null) {
            for (LightJassParser.StatementContext statementContext : thenStatement_listContext.statement()) {
                thenStatements.add(Statement.create(statementContext));
            }
        }

        ConditionalBranch thenBranch = new ConditionalBranch(thenCondition, thenStatements);
        List<ConditionalBranch> elseifBranches = new ArrayList<>();

        for (LightJassParser.Selection_elseif_branchContext elseif_branchContext : selectionContext.elseif_branches) {
            Condition elseifCondition = Condition.create(elseif_branchContext.condition());

            List<Statement> elseifStmts = new ArrayList<>();

            LightJassParser.Statement_listContext statement_listContext = elseif_branchContext.statement_list();

            if (statement_listContext != null) {
                for (LightJassParser.StatementContext statementContext : statement_listContext.statement()) {
                    elseifStmts.add(Statement.create(statementContext));
                }
            }

            ConditionalBranch elseifBranch = new ConditionalBranch(elseifCondition, elseifStmts);

            elseifBranches.add(elseifBranch);
        }

        LightJassParser.Selection_else_branchContext else_branchContext = selectionContext.else_branch;
        List<Statement> elseStmts = new ArrayList<>();

        if (else_branchContext != null) {
            LightJassParser.Statement_listContext elseStatement_listContext = else_branchContext.statement_list();

            if (elseStatement_listContext != null) {
                for (LightJassParser.StatementContext elseStatementContext : elseStatement_listContext.statement()) {
                    elseStmts.add(Statement.create(elseStatementContext));
                }
            }
        }

        return new SelectionStatement(thenBranch, elseifBranches, elseStmts);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        super.write(sw);

        sw.write("if ");

        _thenBranch._condition.write(sw);

        sw.write(" then");

        if (!_thenBranch._statements.isEmpty()) {
            for (Statement stmt : _thenBranch._statements) {
                sw.write("\n");

                stmt.write(sw);
            }
        }

        for (ConditionalBranch elseifBranch : _elseifBranches) {
            sw.write("\nelseif ");

            elseifBranch._condition.write(sw);

            sw.write(" then");

            for (Statement stmt : elseifBranch._statements) {
                sw.write("\n");

                stmt.write(sw);
            }
        }

        if (!_elseStmts.isEmpty()) {
            sw.write("\nelse");

            for (Statement stmt : _elseStmts) {
                sw.write("\n");

                stmt.write(sw);
            }
        }

        sw.write("\nendif");
    }
}
