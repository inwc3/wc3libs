package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;

public abstract class VarDecl implements Decl {
    protected boolean _isConstant;
    protected String _type;
    protected boolean _isArray;
    protected String _name;
    protected Expr _val;

    public VarDecl(boolean isConstant, @Nonnull String type, boolean isArray, @Nonnull String name, @Nullable Expr val) {
        _isConstant = isConstant;
        _type = type;
        _isArray = isArray;
        _name = name;
        _val = val;
    }
}
