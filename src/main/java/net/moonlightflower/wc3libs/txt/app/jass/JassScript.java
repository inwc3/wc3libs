package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.statement.Statement;
import org.antlr.v4.runtime.Vocabulary;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JassScript {
    private List<TypeDecl> _typeDecls = new ArrayList<>();

    private List<VarDecl> _globalVars = new ArrayList<>();

    private List<FuncDecl> _nativeDecls = new ArrayList<>();

    private List<FuncImpl> _funcImpls = new ArrayList<>();

    public List<FuncImpl> getFuncImpls() {
        return _funcImpls;
    }

    public void removeFuncImpl(@Nonnull FuncImpl funcImpl) {
        _funcImpls.remove(funcImpl);
    }

    public void addFuncImpl(@Nonnull FuncImpl funcImpl) {
        _funcImpls.add(funcImpl);
    }

    @Nonnull
    public static String getPrimaryLiteral(int tokenType) {
        Vocabulary vocab = JassLexer.VOCABULARY;

        String lit = vocab.getLiteralName(tokenType);

        if (lit == null) {
            return "";
        }

        return lit.replace("'", "");
    }

    public JassScript() {

    }

    private static class GlobalsBlock implements Decl {
        private final List<VarDecl> _varDecls = new ArrayList<>();

        public GlobalsBlock(@Nonnull List<VarDecl> varDecls) {
            _varDecls.addAll(varDecls);
        }

        public static GlobalsBlock create(@Nonnull LightJassParser.Globals_blockContext globals_blockContext) {
            List<VarDecl> varDecls = new ArrayList<>();

            for (LightJassParser.Global_declContext global_declContext : globals_blockContext.global_decl()) {
                varDecls.add(GlobalVarDecl.create(global_declContext));
            }

            return new GlobalsBlock(varDecls);
        }

        @Override
        public void write(@Nonnull StringWriter sw) {
            sw.write(getPrimaryLiteral(JassLexer.GLOBALS_START));

            sw.write("\n");

            for (VarDecl varDecl : _varDecls) {
                varDecl.write(sw);

                sw.write("\n");
            }

            sw.write(getPrimaryLiteral(JassLexer.GLOBALS_END));
        }
    }

    public Decl createDecl(@Nonnull LightJassParser.Top_declContext top_declContext) {
        if (top_declContext.type_decl() != null) return TypeDecl.create(top_declContext.type_decl());
        if (top_declContext.native_decl() != null) return FuncDecl.create(top_declContext.native_decl());
        if (top_declContext.globals_block() != null) return GlobalsBlock.create(top_declContext.globals_block());
        if (top_declContext.func_impl() != null) return FuncImpl.create(top_declContext.func_impl());

        throw new AssertionError("no option for " + top_declContext.getText());
    }

    public void addDecl(@Nonnull Decl decl) {
        if (decl instanceof TypeDecl) {
            _typeDecls.add((TypeDecl) decl);

            return;
        }
        if (decl instanceof GlobalsBlock) {
            _globalVars.addAll(((GlobalsBlock) decl)._varDecls);

            return;
        }
        if (decl instanceof FuncDecl) {
            _nativeDecls.add((FuncDecl) decl);

            return;
        }
        if (decl instanceof FuncImpl) {
            _funcImpls.add((FuncImpl) decl);

            return;
        }

        throw new AssertionError("no option for " + decl);
    }

    /*public JassScript(@Nonnull JassParser.RootContext rootContext) {
        for (JassParser.Top_declContext top_declContext : rootContext.top_decl()) {
            Decl decl = createDecl(top_declContext);

            addDecl(decl);
        }
    }*/

    public JassScript(@Nonnull LightJassParser.RootContext rootContext) {
        for (LightJassParser.Top_declContext top_declContext : rootContext.top_decl()) {
            Decl decl = createDecl(top_declContext);

            addDecl(decl);
        }
    }

    public void write(@Nonnull StringWriter sw) {
        for (TypeDecl typeDecl : _typeDecls) {
            typeDecl.write(sw);

            sw.write("\n");
        }

        for (FuncDecl nativeDecl : _nativeDecls) {
            nativeDecl.write(sw);

            sw.write("\n");
        }

        sw.write(getPrimaryLiteral(JassLexer.GLOBALS_START));

        sw.write("\n");

        for (VarDecl varDecl : _globalVars) {
            varDecl.write(sw);

            sw.write("\n");
        }

        sw.write(getPrimaryLiteral(JassLexer.GLOBALS_END));

        sw.write("\n");

        for (FuncImpl funcImpl : _funcImpls) {
            funcImpl.write(sw);

            sw.write("\n");
        }
    }
}
