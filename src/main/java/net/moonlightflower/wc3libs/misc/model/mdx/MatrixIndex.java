package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

public class MatrixIndex {
    private long _index;

    public long getIndex() {
        return _index;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt32(_index);
    }

    public MatrixIndex(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _index = stream.readUInt32("index");
    }
}
