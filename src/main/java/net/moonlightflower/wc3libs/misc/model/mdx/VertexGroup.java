package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class VertexGroup extends MDXObject {
    private int _index = 0;

    public int getIndex() {
        return _index;
    }

    public void setIndex(int index) {
        _index = index;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeUInt8(_index);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public VertexGroup(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _index = stream.readUInt8("index");
    }

    public VertexGroup() {
    }
}
