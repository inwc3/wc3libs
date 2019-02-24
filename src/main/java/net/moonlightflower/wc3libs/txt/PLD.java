package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.txt.app.jass.FuncImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;
import java.util.Set;

public class PLD {
    public FuncImpl toJassFunc() {
        StringBuilder sb = new StringBuilder();

        sb.append("function PreloadFiles takes nothing returns nothing");

        sb.append("\n");

        for (String path : _preloads) {
            sb.append("\t");
            sb.append("call Preload(");
            sb.append("\"");
            sb.append(path.replaceAll("\\\\", "\\\\\\\\"));
            sb.append("\"");
            sb.append(")");
            sb.append("\n");
        }

        sb.append("endfunction");

        CharStream antlrStream = CharStreams.fromString(sb.toString());

        Lexer lexer = new JassLexer(antlrStream);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        JassParser parser = new JassParser(tokenStream);

        return null;
        //TODO: fix
        //return FuncImpl.create(parser.func_impl());
    }

    private Set<String> _preloads = new LinkedHashSet<>();

    @Nonnull
    public Set<String> getPreloads() {
        return new LinkedHashSet<>(_preloads);
    }

    public void addPreload(@Nonnull String path) {
        _preloads.add(path);
    }

    public PLD() {
    }
}
