package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class FaceTypeGroup extends MDXObject {
    public enum Type {
        POINTS,
        LINES,
        LINE_LOOP,
        LINE_STRIP,
        TRIANGLES,
        TRIANGLE_STRIP,
        TRIANGLE_FAN,
        QUADS,
        QUAD_STRIP,
        POLYGONS
    }

    private Type _type = Type.POINTS;

    public Type getType() {
        return _type;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeUInt32(_type.ordinal());
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public FaceTypeGroup(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long typeL = stream.readUInt32("type");

        if (typeL >= Type.values().length) throw new IllegalArgumentException("out of bounds 0-" + Type.values().length + " (" + typeL + ")");

        _type = Type.values()[(int) typeL];
    }

    public FaceTypeGroup() {
    }
}
