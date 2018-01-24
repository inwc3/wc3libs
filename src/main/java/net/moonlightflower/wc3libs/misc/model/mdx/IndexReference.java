package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class IndexReference extends MDXObject {
    private long _index = 0;

    public long getIndex() {
        return _index;
    }

    public void setIndex(long index) {
        _index = index;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeUInt32(_index);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public IndexReference(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _index = stream.readUInt32("index");
    }

    public IndexReference() {
    }
}
