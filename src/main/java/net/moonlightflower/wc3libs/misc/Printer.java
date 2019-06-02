package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Printer {
    private final PrintStream _stream;

    public Printer(@Nonnull PrintStream stream) {
        _stream = stream;
    }

    private final Stack<Object> _groups = new Stack<>();

    public void beginGroup(@Nonnull Object name) {
        println("begin " + name + ":");

        _groups.push(name);
    }

    public void endGroup() {
        if (_groups.isEmpty()) return;

        println("end " + _groups.pop());
    }

    public void print(@Nullable Object s) {
        _stream.print(new String(new char[_groups.size()]).replace("\0", "\t") + s);
    }

    public void println(@Nullable Object s) {
        _stream.println(new String(new char[_groups.size()]).replace("\0", "\t") + s);
    }
}