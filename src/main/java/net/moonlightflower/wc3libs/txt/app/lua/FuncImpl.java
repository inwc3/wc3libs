package net.moonlightflower.wc3libs.txt.app.lua;

import javax.annotation.Nonnull;
import java.io.StringWriter;
import java.util.List;

public class FuncImpl {
    public void write(StringWriter sw) {
        _decl.write(sw);

        sw.write("\n");

        _body.write(sw);

        sw.write("\n");

        sw.write("end");
    }

    public static class Body {
        private List<Statement> _stmts;

        public Body(@Nonnull List<Statement> stmts) {
            _stmts = stmts;
        }

        public void write(StringWriter sw) {
            boolean first = true;

            for (Statement stmt : _stmts) {
                if (first) {
                    first = false;
                } else {
                    sw.write("\n");
                }

                stmt.write(sw);
            }
        }
    }

    private FuncDecl _decl;
    private Body _body;

    public FuncImpl(@Nonnull FuncDecl decl, @Nonnull Body body) {
        _decl = decl;
        _body = body;
    }
}
