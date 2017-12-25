package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TexCoord {
    private Coords2DF _pos;

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeFloat8(_pos.getX());
        stream.writeFloat8(_pos.getY());
    }

    public TexCoord(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        float x = stream.readFloat8("x");
        float y = stream.readFloat8("y");

        _pos = new Coords2DF(x, y);
    }
}
