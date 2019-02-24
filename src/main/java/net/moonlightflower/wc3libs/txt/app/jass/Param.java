package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class Param {
    private String _type;
    private String _name;

    public Param(@Nonnull String type, @Nonnull String name) {
        _type = type;
        _name = name;
    }

    public static Param create(@Nonnull LightJassParser.Func_paramContext func_paramContext) {
        return new Param(func_paramContext.type_ref().getText(), func_paramContext.var_name().getText());
    }

    public void write(@Nonnull StringWriter sw) {
        sw.write(_type);

        sw.write(" ");

        sw.write(_name);
    }
}
