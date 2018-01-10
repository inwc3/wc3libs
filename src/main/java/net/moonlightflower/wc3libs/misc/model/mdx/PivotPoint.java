package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;

import javax.annotation.Nonnull;

public class PivotPoint extends Vertex {
    public PivotPoint(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        super(stream);
    }
}
