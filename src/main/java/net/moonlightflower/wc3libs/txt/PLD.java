package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.antlr.LightJassLexer;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.FuncImpl;
import org.antlr.v4.runtime.*;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.Set;

public class PLD {
    public PLD() {
    }

    public FuncImpl toJassFunc() {
        StringBuilder sb = new StringBuilder();

        sb.append("function PreloadFiles takes nothing returns nothing");

        sb.append("\n");

        for (String path : _preloads) {
            sb.append("\t");
            sb.append("call Preload(");
            sb.append("\"");
            sb.append(path.replace("\\", "\\\\").replace("\"", "\\\""));
            sb.append("\"");
            sb.append(")");
            sb.append("\n");
        }

        sb.append("endfunction");

        CharStream antlrStream = getAntlrStream(sb.toString());

        LightJassLexer lexer = getJassLexer(antlrStream);

        CommonTokenStream tokenStream = getCommonTokenStream(lexer);

        LightJassParser parser = getJassParser(tokenStream);
        LightJassParser.Func_implContext funcImplContext = parser.func_impl();

        return FuncImpl.create(funcImplContext);
    }

    protected CharStream getAntlrStream(@Nonnull String s) {
        return CharStreams.fromString(s);
    }

    protected LightJassLexer getJassLexer(@Nonnull CharStream antlrStream) {
        return new LightJassLexer(antlrStream);
    }

    protected CommonTokenStream getCommonTokenStream(@Nonnull LightJassLexer lexer) {
        return new CommonTokenStream(lexer);
    }

    protected LightJassParser getJassParser(@Nonnull CommonTokenStream tokenStream) {
        return new LightJassParser(tokenStream);
    }

    private final Set<String> _preloads = new LinkedHashSet<>();

    @Nonnull
    public Set<String> getPreloads() {
        return new LinkedHashSet<>(_preloads);
    }

    public void addPreload(@Nonnull String path) {
        _preloads.add(path);
    }
}
