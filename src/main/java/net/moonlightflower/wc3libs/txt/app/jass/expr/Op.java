package net.moonlightflower.wc3libs.txt.app.jass.expr;

import javax.annotation.Nonnull;
import java.io.StringWriter;

public interface Op {
    void write(@Nonnull StringWriter sw);
}
