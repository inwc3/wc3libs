package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class Extent extends MDXObject {
    private float _boundsRadius = 0F;
    private float _minX = 0F;
    private float _minY = 0F;
    private float _minZ = 0F;
    private float _maxX = 0F;
    private float _maxY = 0F;
    private float _maxZ = 0F;

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

    public Extent() {
    }
}
