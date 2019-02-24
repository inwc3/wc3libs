package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;

public class GlobalVarDecl extends VarDecl {
    public GlobalVarDecl(boolean isConstant, @Nonnull String type, boolean isArray, @Nonnull String name, @Nullable Expr val) {
        super(isConstant, type, isArray, name, val);
    }

    public static VarDecl create(@Nonnull LightJassParser.Global_declContext global_declContext) {
        Expr val = global_declContext.val != null ? Expr.create(global_declContext.val) : null;

        return new GlobalVarDecl(global_declContext.CONST_DECL() != null, global_declContext.type_ref().getText(), global_declContext.ARRAY_DECL() != null, global_declContext.var_name().getText(), val);
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        if (_isConstant) {
            sw.write(JassScript.getPrimaryLiteral(JassLexer.CONST_DECL));

            sw.write(" ");
        }

        sw.write(_type);

        sw.write(" ");

        if (_isArray) {
            sw.write(JassScript.getPrimaryLiteral(JassLexer.ARRAY_DECL));

            sw.write(" ");
        }

        sw.write(_name);

        sw.write(" ");

        if (_val != null) {
            sw.write(JassScript.getPrimaryLiteral(JassLexer.ASSIGN_OP));

            _val.write(sw);
        }
    }
}
