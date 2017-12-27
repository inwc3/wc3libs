package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class Extent extends MDXObject {
    private float _boundsRadius;
    private float _minX;
    private float _minY;
    private float _minZ;
    private float _maxX;
    private float _maxY;
    private float _maxZ;

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeFloat32(_boundsRadius);
        stream.writeFloat32(_minX);
        stream.writeFloat32(_minY);
        stream.writeFloat32(_minZ);
        stream.writeFloat32(_maxX);
        stream.writeFloat32(_maxY);
        stream.writeFloat32(_maxZ);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Extent(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _boundsRadius = stream.readFloat32("boundsRadius");
        _minX = stream.readFloat32("minX");
        _minY = stream.readFloat32("minY");
        _minZ = stream.readFloat32("minZ");
        _maxX = stream.readFloat32("maxX");
        _maxY = stream.readFloat32("maxY");
        _maxZ = stream.readFloat32("maxZ");
    }
}
