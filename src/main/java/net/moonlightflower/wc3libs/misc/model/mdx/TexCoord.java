package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;

import javax.annotation.Nonnull;

public class TexCoord {
    private Coords2DF _pos;

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeFloat32(_pos.getX());
        stream.writeFloat32(_pos.getY());
    }

    public TexCoord(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        float x = stream.readFloat32("x");
        float y = stream.readFloat32("y");

        _pos = new Coords2DF(x, y);
    }
}
