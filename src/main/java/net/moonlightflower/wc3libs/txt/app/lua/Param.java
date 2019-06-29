package net.moonlightflower.wc3libs.txt.app.lua;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public class Param {
    private String _name;

    public Param(@Nonnull String name) {
        _name = name;
    }

    public void write(StringWriter sw) {
        sw.write(_name);
    }
}
