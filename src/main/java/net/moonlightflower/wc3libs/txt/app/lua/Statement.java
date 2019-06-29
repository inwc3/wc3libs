package net.moonlightflower.wc3libs.txt.app.lua;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class Statement {
    private String _stmt;

    private Statement(@Nonnull String input) {
        _stmt = input;
    }

    public static Statement create(@Nonnull String input) {
        return new Statement(input);
    }

    public void write(StringWriter sw) {
        sw.write(_stmt);
    }
}
