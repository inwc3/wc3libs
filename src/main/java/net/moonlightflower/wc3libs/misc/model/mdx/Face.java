package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

public class Face {
    private int _val;

    public int getVal() {
        return _val;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt32(_val);
    }

    public Face(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _val = stream.readUInt16("val");
    }
}
