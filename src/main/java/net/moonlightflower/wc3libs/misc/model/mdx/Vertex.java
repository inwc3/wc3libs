package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Vertex {
    private Coords3DF _pos;

    public Coords3DF getPos() {
        return _pos;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeFloat8(_pos.getX());
        stream.writeFloat8(_pos.getY());
        stream.writeFloat8(_pos.getZ());
    }

    public Vertex(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        float x = stream.readFloat8("x");
        float y = stream.readFloat8("y");
        float z = stream.readFloat8("z");

        _pos = new Coords3DF(x, y, z);
    }
}
