package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class GeosetAnim {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private float _alpha;

    public float getAlpha() {
        return _alpha;
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    private Color _color;

    public Color getColor() {
        return _color;
    }

    private long _geosetId;

    public long getGeosetId() {
        return _geosetId;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt32(_inclusiveSize);
        stream.writeFloat8(_alpha);
        stream.writeUInt32(_flags);

        stream.writeFloat8(_color.getBlue());
        stream.writeFloat8(_color.getGreen());
        stream.writeFloat8(_color.getRed());

        stream.writeUInt32(_geosetId);

        //TODO: KGAO, KGAC
    }

    public GeosetAnim(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _alpha = stream.readFloat8("alpha");
        _flags = stream.readUInt32("flags");

        float blue = stream.readFloat8("blue");
        float green = stream.readFloat8("green");
        float red = stream.readFloat8("red");

        _color = Color.fromBGR255((int) (blue * 255), (int) (green * 255), (int) (red * 255));

        _geosetId = stream.readInt32("geosetId");

        //TODO: KGAO, KGAC
    }
}
