package net.moonlightflower.wc3libs.txt.app.lua;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.List;

public class FuncDecl {
    private List<Param> _params;

    public FuncDecl(@Nonnull List<Param> params) {
        _params = params;
    }

    public void write(StringWriter sw) {
        sw.write("function (");

        for (Param param : _params) {
            param.write(sw);
        }

        sw.write(")");
    }
}
