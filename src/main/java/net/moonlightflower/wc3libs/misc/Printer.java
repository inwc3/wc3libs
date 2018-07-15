package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.PrintStream;

public class Printer {
    private PrintStream _stream;

    private int _nestDepth = 0;

    public void beginGroup(@Nonnull Object name) {
        _nestDepth++;

        _stream.print(new String(new char[_nestDepth]).replace("\0", "\t") + name);
    }

    public void endGroup() {
        _nestDepth--;

        if (_nestDepth < 0) _nestDepth = 0;
    }

    public void print(@Nullable Object s) {
        _stream.print(s);
    }

    public Printer(@Nonnull PrintStream stream) {
        _stream = stream;
    }
}
