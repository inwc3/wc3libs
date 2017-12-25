package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RibbonEmitter {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    private float _heightAbove;

    public float getHeightAbove() {
        return _heightAbove;
    }

    private float _heightBelow;

    public float getHeightBelow() {
        return _heightBelow;
    }

    private Color _color;

    public Color getColor() {
        return _color;
    }

    private float _lifespan;

    public float getLifespan() {
        return _lifespan;
    }

    private long _textureSlot;

    public long getTextureSlot() {
        return _textureSlot;
    }

    private long _emissionRate;

    public long getEmissionRate() {
        return _emissionRate;
    }

    private long _rows;

    public long getRows() {
        return _rows;
    }

    private long _columns;

    public long getColumns() {
        return _columns;
    }

    private long _materialId;

    public long getMaterialId() {
        return _materialId;
    }

    private float _gravity;

    public float getGravity() {
        return _gravity;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        _node.write(stream);
        stream.writeFloat8(_heightAbove);
        stream.writeFloat8(_heightBelow);

        stream.writeFloat8(_color.getAlpha() / 255F);
        stream.writeFloat8(_color.getBlue() / 255F);
        stream.writeFloat8(_color.getGreen() / 255F);
        stream.writeFloat8(_color.getRed() / 255F);

        stream.writeFloat8(_lifespan);

        stream.writeUInt32(_textureSlot);
        stream.writeUInt32(_emissionRate);

        stream.writeUInt32(_rows);
        stream.writeUInt32(_columns);

        stream.writeUInt32(_materialId);
        stream.writeFloat8(_gravity);

        //TODO: KRHA, KRHB, KRAL, KRCO, KRTX, KRVS
    }

    public RibbonEmitter(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _heightAbove = stream.readFloat8("heightAbove");
        _heightBelow = stream.readFloat8("heightBelow");

        int alpha = (int) (stream.readFloat8("alpha") * 255);

        _color = Color.fromBGRA255((int) (stream.readFloat8("blue") * 255), (int) (stream.readFloat8("green") * 255), (int) (stream.readFloat8("red") * 255), alpha);

        _lifespan = stream.readFloat8("lifespan");

        _textureSlot = stream.readUInt32("textureSlot");
        _emissionRate = stream.readUInt32("emissionRate");

        _rows = stream.readUInt32("rows");
        _columns = stream.readUInt32("columns");

        _materialId = stream.readUInt32("materialId");
        _gravity = stream.readFloat8("gravity");

        //TODO: KRHA, KRHB, KRAL, KRCO, KRTX, KRVS
    }
}
