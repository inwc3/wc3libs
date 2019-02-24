package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class FuncImpl implements Decl {
    private FuncDecl _decl;

    public FuncDecl getDecl() {
        return _decl;
    }

    public static class Body {
        private List<VarDecl> _localVars;
        private List<Statement> _stmts;

        public void write(@Nonnull StringWriter sw) {
            boolean first = true;

            for (VarDecl var : _localVars) {
                if (first) {
                    first = false;
                } else {
                    sw.write("\n");
                }

                var.write(sw);
            }

            for (Statement stmt : _stmts) {
                if (first) {
                    first = false;
                } else {
                    sw.write("\n");
                }

                stmt.write(sw);
            }
        }

        public Body(@Nonnull List<VarDecl> localVars, @Nonnull List<Statement> stmts) {
            _localVars = localVars;
            _stmts = stmts;
        }

        public static Body create(@Nullable LightJassParser.Func_bodyContext func_bodyContext) {
            List<VarDecl> localVars = new ArrayList<>();

            if (func_bodyContext != null) {
                LightJassParser.Local_var_decl_listContext localVarDecl_listContext = func_bodyContext.local_var_decl_list();

                if (localVarDecl_listContext != null) {
                    List<LightJassParser.Local_var_declContext> localVarDeclContext = localVarDecl_listContext.local_var_decl();

                    if (localVarDeclContext != null) {
                        for (LightJassParser.Local_var_declContext localVarDecContext : localVarDeclContext) {
                            localVars.add(LocalVarDecl.create(localVarDecContext));
                        }
                    }
                }
            }

            List<Statement> stmts = new ArrayList<>();

            if (func_bodyContext != null) {
                LightJassParser.Statement_listContext statement_listContext = func_bodyContext.statement_list();

                if (statement_listContext != null) {
                    for (LightJassParser.StatementContext stmtContext : statement_listContext.statement()) {
                        stmts.add(Statement.create(stmtContext));
                    }
                }
            }

            return new Body(localVars, stmts);
        }
    }

    private Body _body;

    public FuncImpl(@Nonnull FuncDecl decl, @Nonnull Body body) {
        _decl = decl;
        _body = body;
    }

    public static FuncImpl create(@Nonnull LightJassParser.Func_implContext func_implContext) {
        return new FuncImpl(FuncDecl.create(func_implContext.func_decl()), Body.create(func_implContext.func_body()));
    }

    public void write(@Nonnull StringWriter sw) {
        _decl.write(sw);

        sw.write("\n");

        _body.write(sw);

        sw.write("\n");

        sw.write("endfunction");
    }
}
