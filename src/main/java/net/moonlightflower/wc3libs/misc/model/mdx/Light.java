package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;

import javax.annotation.Nonnull;

public class Light {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    public enum Type {
        OMNI,
        DIRECTIONAL,
        AMBIENT
    }

    private Type _type;

    public Type getType() {
        return _type;
    }

    private float _attenuationStart;

    public float getAttenuationStart() {
        return _attenuationStart;
    }

    private float _attenuationEnd;

    public float getAttenuationEnd() {
        return _attenuationEnd;
    }

    private Color _color;

    public Color getColor() {
        return _color;
    }

    private float _intensity;

    public float getIntensity() {
        return _intensity;
    }

    private Color _ambientColor;

    public Color getAmbientColor() {
        return _ambientColor;
    }

    private float _ambientIntensity;

    public float getAmbientIntensity() {
        return _ambientIntensity;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        _node.write(stream);

        stream.writeUInt32(_type.ordinal());

        stream.writeFloat8(_attenuationStart);
        stream.writeFloat8(_attenuationEnd);

        stream.writeFloat8(_color.getBlue());
        stream.writeFloat8(_color.getGreen());
        stream.writeFloat8(_color.getRed());

        stream.writeFloat8(_intensity);

        stream.writeFloat8(_ambientColor.getBlue());
        stream.writeFloat8(_ambientColor.getGreen());
        stream.writeFloat8(_ambientColor.getRed());

        stream.writeFloat8(_ambientIntensity);

        //TODO: KLAS, KLAE, KLAC, KLAI, KLBI, KLBC, KLAV
    }

    public Light(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);

        long typeL = stream.readUInt32("type");

        if (typeL >= Type.values().length) throw new IllegalArgumentException("type out of bounds 0-" + Type.values().length + " (" + typeL + ")");

        _type = Type.values()[(int) typeL];

        _attenuationStart = stream.readFloat8("attenuationStart");
        _attenuationEnd = stream.readFloat8("attenuationEnd");

        _color = Color.fromBGR255((int) (stream.readFloat8("color_blue") * 255), (int) (stream.readFloat8("color_green") * 255), (int) (stream.readFloat8("color_red") * 255));

        _intensity = stream.readFloat8("intensity");

        _ambientColor = Color.fromBGR255((int) (stream.readFloat8("ambientColor_blue") * 255), (int) (stream.readFloat8("ambientColor_green") * 255), (int) (stream.readFloat8("ambientColor_red") * 255));

        _ambientIntensity = stream.readFloat8("ambientIntensity");

        //TODO: KLAS, KLAE, KLAC, KLAI, KLBI, KLBC, KLAV
    }
}
