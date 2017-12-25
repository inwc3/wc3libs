package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

public class Extent {
    private float _boundsRadius;
    private float _minX;
    private float _minY;
    private float _minZ;
    private float _maxX;
    private float _maxY;
    private float _maxZ;

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeFloat8(_boundsRadius);
        stream.writeFloat8(_minX);
        stream.writeFloat8(_minY);
        stream.writeFloat8(_minZ);
        stream.writeFloat8(_maxX);
        stream.writeFloat8(_maxY);
        stream.writeFloat8(_maxZ);
    }

    public Extent(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _boundsRadius = stream.readFloat8("boundsRadius");
        _minX = stream.readFloat8("minX");
        _minY = stream.readFloat8("minY");
        _minZ = stream.readFloat8("minZ");
        _maxX = stream.readFloat8("maxX");
        _maxY = stream.readFloat8("maxY");
        _maxZ = stream.readFloat8("maxZ");
    }
}
