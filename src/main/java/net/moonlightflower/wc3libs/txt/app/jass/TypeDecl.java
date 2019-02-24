package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class TypeDecl implements Decl {
    private String _name;
    private String _parent;

    public TypeDecl(@Nonnull String name, @Nonnull String parent) {
        _name = name;
        _parent = parent;
    }

    public static TypeDecl create(@Nonnull LightJassParser.Type_declContext type_declContext) {
        return new TypeDecl(type_declContext.type_name().getText(), type_declContext.parent.getText());
    }

    public void write(@Nonnull StringWriter sw) {
        sw.write(JassScript.getPrimaryLiteral(JassLexer.TYPE_DECL));

        sw.write(" ");

        sw.write(_name);

        sw.write(" ");

        sw.write(JassScript.getPrimaryLiteral(JassLexer.TYPE_EXTENDS));

        sw.write(_parent);
    }
}
