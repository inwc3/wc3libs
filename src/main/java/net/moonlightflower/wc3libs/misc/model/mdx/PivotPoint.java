package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;

import javax.annotation.Nonnull;

public class PivotPoint {
    private Coords3DF _pos;

    public Coords3DF getPos() {
        return _pos;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeFloat32(_pos.getX());
        stream.writeFloat32(_pos.getY());
        stream.writeFloat32(_pos.getZ());
    }

    public PivotPoint(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        float x = stream.readFloat32("x");
        float y = stream.readFloat32("y");
        float z = stream.readFloat32("z");

        _pos = new Coords3DF(x, y, z);
    }
}
