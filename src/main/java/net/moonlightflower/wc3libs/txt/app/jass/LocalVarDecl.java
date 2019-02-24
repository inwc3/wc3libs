package net.moonlightflower.wc3libs.txt.app.jass;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.txt.app.jass.expr.Expr;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.StringWriter;

public class LocalVarDecl extends VarDecl {
    public LocalVarDecl(boolean isConstant, @Nonnull String type, boolean isArray, @Nonnull String name, @Nullable Expr val) {
        super(isConstant, type, isArray, name, val);
    }

    public static VarDecl create(@Nonnull LightJassParser.Local_var_declContext local_var_declContext) {
        Expr val = local_var_declContext.expr() != null ? Expr.create(local_var_declContext.expr()) : null;

        return new LocalVarDecl(false, local_var_declContext.type_ref().getText(), local_var_declContext.ARRAY_DECL() != null, local_var_declContext.var_name().getText(), val);
    }

    public void write(@Nonnull StringWriter sw) {
        sw.write("local ");

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
