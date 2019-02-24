package net.moonlightflower.wc3libs.txt.app.jass.expr;

import net.moonlightflower.wc3libs.antlr.JassParser;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class VarRef implements AnyTypeExpr {
    private String _varRef;

    public VarRef(@Nonnull String varRef) {
        _varRef = varRef;
    }

    public static VarRef create(@Nonnull JassParser.Var_refContext var_refContext) {
        return new VarRef(var_refContext.getText());
    }

    @Override
    public void write(@Nonnull StringWriter sw) {
        sw.write(_varRef);
    }
}
