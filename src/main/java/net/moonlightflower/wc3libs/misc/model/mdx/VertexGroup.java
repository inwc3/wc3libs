package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

public class VertexGroup {
    private int _index;

    public int getIndex() {
        return _index;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt8(_index);
    }

    public VertexGroup(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _index = stream.readUInt8("index");
    }
}
