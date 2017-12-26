package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;

import javax.annotation.Nonnull;

public class ParticleEmitter2 {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    private float _speed;

    public float getSpeed() {
        return _speed;
    }

    private float _variation;

    public float getVariation() {
        return _variation;
    }

    private float _latitude;

    public float getLatitude() {
        return _latitude;
    }

    private float _gravity;

    public float getGravity() {
        return _gravity;
    }

    private float _lifespan;

    public float getLifespan() {
        return _lifespan;
    }

    private float _emissionRate;

    public float getEmissionRate() {
        return _emissionRate;
    }

    private float _width;

    public float getWidth() {
        return _width;
    }

    private float _length;

    public float getLength() {
        return _length;
    }

    public enum FilterMode {
        BLEND,
        ADDITIVE,
        MODULATE,
        MODULATE_2X,
        ALPHA_KEY
    }

    private FilterMode _filterMode;

    public FilterMode getFilterMode() {
        return _filterMode;
    }

    private long _rows;

    public long getRows() {
        return _rows;
    }

    private long _columns;

    public long getColumns() {
        return _columns;
    }

    public enum HeadTailFlag {
        HEAD,
        TAIL,
        BOTH
    }

    private HeadTailFlag _headTailFlag;

    public HeadTailFlag getHeadTailFlag() {
        return _headTailFlag;
    }

    private float _tailLength;

    public float getTailLength() {
        return _tailLength;
    }

    private float _time;

    public float getTime() {
        return _time;
    }

    private Color _startColor;

    public Color getStartColor() {
        return _startColor;
    }

    private Color _midColor;

    public Color getMidColor() {
        return _midColor;
    }

    private Color _endColor;

    public Color getEndColor() {
        return _endColor;
    }

    private float _startScaling;

    public float getStartScaling() {
        return _startScaling;
    }

    private float _midScaling;

    public float getMidScaling() {
        return _midScaling;
    }

    private float _endScaling;

    public float getEndScaling() {
        return _endScaling;
    }

    private long _startHeadInterval;

    public long getStartHeadInterval() {
        return _startHeadInterval;
    }

    private long _midHeadInterval;

    public long getMidHeadInterval() {
        return _midHeadInterval;
    }

    private long _endHeadInterval;

    public long getEndHeadInterval() {
        return _endHeadInterval;
    }

    private long _startHeadDecayInterval;

    public long getStartHeadDecayInterval() {
        return _startHeadDecayInterval;
    }

    private long _midHeadDecayInterval;

    public long getMidHeadDecayInterval() {
        return _midHeadDecayInterval;
    }

    private long _endHeadDecayInterval;

    public long getEndHeadDecayInterval() {
        return _endHeadDecayInterval;
    }

    private long _startTailInterval;

    public long getStartTailInterval() {
        return _startTailInterval;
    }

    private long _midTailInterval;

    public long getMidTailInterval() {
        return _midTailInterval;
    }

    private long _endTailInterval;

    public long getEndTailInterval() {
        return _endTailInterval;
    }

    private long _startTailDecayInterval;

    public long getStartTailDecayInterval() {
        return _startTailDecayInterval;
    }

    private long _midTailDecayInterval;

    public long getMidTailDecayInterval() {
        return _midTailDecayInterval;
    }

    private long _endTailDecayInterval;

    public long getEndTailDecayInterval() {
        return _endTailDecayInterval;
    }

    private long _textureId;

    public long getTextureId() {
        return _textureId;
    }

    private long _squirt;

    public long getSquirt() {
        return _squirt;
    }

    private long _priorityPlane;

    public long getPriorityPlane() {
        return _priorityPlane;
    }

    private long _replaceableId;

    public long getReplaceableId() {
        return _replaceableId;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        _node.write(stream);
        stream.writeFloat32(_speed);
        stream.writeFloat32(_variation);
        stream.writeFloat32(_latitude);
        stream.writeFloat32(_gravity);
        stream.writeFloat32(_lifespan);
        stream.writeFloat32(_emissionRate);
        stream.writeFloat32(_width);
        stream.writeFloat32(_length);

        stream.writeUInt32(_filterMode.ordinal());

        stream.writeUInt32(_rows);
        stream.writeUInt32(_columns);

        stream.writeUInt32(_headTailFlag.ordinal());

        stream.writeFloat32(_tailLength);
        stream.writeFloat32(_time);

        stream.writeFloat32(_startColor.getBlue() / 255F);
        stream.writeFloat32(_startColor.getGreen() / 255F);
        stream.writeFloat32(_startColor.getRed() / 255F);

        stream.writeFloat32(_midColor.getBlue() / 255F);
        stream.writeFloat32(_midColor.getGreen() / 255F);
        stream.writeFloat32(_midColor.getRed() / 255F);

        stream.writeFloat32(_endColor.getBlue() / 255F);
        stream.writeFloat32(_endColor.getGreen() / 255F);
        stream.writeFloat32(_endColor.getRed() / 255F);

        stream.writeFloat32(_startColor.getAlpha() / 255F);
        stream.writeFloat32(_midColor.getAlpha() / 255F);
        stream.writeFloat32(_endColor.getAlpha() / 255F);

        stream.writeFloat32(_startScaling);
        stream.writeFloat32(_midScaling);
        stream.writeFloat32(_endScaling);

        stream.writeFloat32(_startHeadInterval);
        stream.writeFloat32(_midHeadInterval);
        stream.writeFloat32(_endHeadInterval);

        stream.writeFloat32(_startHeadDecayInterval);
        stream.writeFloat32(_midHeadDecayInterval);
        stream.writeFloat32(_endHeadDecayInterval);

        stream.writeFloat32(_startTailInterval);
        stream.writeFloat32(_midTailInterval);
        stream.writeFloat32(_endTailInterval);

        stream.writeFloat32(_startTailDecayInterval);
        stream.writeFloat32(_midTailDecayInterval);
        stream.writeFloat32(_endTailDecayInterval);

        stream.writeUInt32(_textureId);
        stream.writeUInt32(_squirt);
        stream.writeUInt32(_priorityPlane);
        stream.writeUInt32(_replaceableId);

        //TODO: KP2S, KP2R, KP2L, KP2G, KP2E, KP2N, KP2W, KP2V
    }

    public ParticleEmitter2(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _speed = stream.readFloat8("speed");
        _variation = stream.readFloat8("variation");
        _latitude = stream.readFloat8("latitude");
        _gravity = stream.readFloat8("gravity");
        _lifespan = stream.readFloat8("lifespan");
        _emissionRate = stream.readFloat8("emissionRate");
        _width = stream.readFloat8("width");
        _length = stream.readFloat8("length");

        long filterModeL = stream.readUInt32("filterMode");

        if (filterModeL >= FilterMode.values().length) throw new IllegalArgumentException("filterMode out of bounds 0-" + FilterMode.values().length + " (" + filterModeL + ")");

        _filterMode = FilterMode.values()[(int) filterModeL];

        _rows = stream.readUInt32("rows");
        _columns = stream.readUInt32("columns");

        long headTailFlagL = stream.readUInt32("headTailFlag");

        if (headTailFlagL >= HeadTailFlag.values().length) throw new IllegalArgumentException("headTailFlags out of bounds 0-" + HeadTailFlag.values().length + " (" + headTailFlagL + ")");

        _headTailFlag = HeadTailFlag.values()[(int) headTailFlagL];

        _tailLength = stream.readFloat8("tailLength");
        _time = stream.readFloat8("time");

        _startColor = Color.fromBGR255((int) (stream.readFloat8("startColorBlue") * 255), (int) (stream.readFloat8("startColorGreen") * 255), (int) (stream.readFloat8("startColorRed") * 255));
        _midColor = Color.fromBGR255((int) (stream.readFloat8("midColorBlue") * 255), (int) (stream.readFloat8("midColorGreen") * 255), (int) (stream.readFloat8("midColorRed") * 255));
        _endColor = Color.fromBGR255((int) (stream.readFloat8("endColorBlue") * 255), (int) (stream.readFloat8("endColorGreen") * 255), (int) (stream.readFloat8("endColorRed") * 255));

        _startColor = Color.fromBGRA255(_startColor.getBlue(), _startColor.getGreen(), _startColor.getRed(), (int) (stream.readFloat8("startColorAlpha") * 255));
        _midColor = Color.fromBGRA255(_midColor.getBlue(), _midColor.getGreen(), _midColor.getRed(), (int) (stream.readFloat8("midColorAlpha") * 255));
        _endColor = Color.fromBGRA255(_endColor.getBlue(), _endColor.getGreen(), _endColor.getRed(), (int) (stream.readFloat8("endColorAlpha") * 255));

        _startScaling = stream.readFloat8("startScaling");
        _midScaling = stream.readFloat8("midScaling");
        _endScaling = stream.readFloat8("endScaling");

        _startHeadInterval = stream.readUInt32("startHeadInterval");
        _midHeadInterval = stream.readUInt32("midHeadInterval");
        _endHeadInterval = stream.readUInt32("endHeadInterval");

        _startHeadDecayInterval = stream.readUInt32("startHeadDecayInterval");
        _midHeadDecayInterval = stream.readUInt32("midHeadDecayInterval");
        _endHeadDecayInterval = stream.readUInt32("endHeadDecayInterval");

        _startTailInterval = stream.readUInt32("startTailInterval");
        _midTailInterval = stream.readUInt32("midTailInterval");
        _endTailInterval = stream.readUInt32("endTailInterval");

        _startTailDecayInterval = stream.readUInt32("startTailDecayInterval");
        _midTailDecayInterval = stream.readUInt32("midTailDecayInterval");
        _endTailDecayInterval = stream.readUInt32("endTailDecayInterval");

        _textureId = stream.readUInt32("textureId");
        _squirt = stream.readUInt32("squirt");
        _priorityPlane = stream.readUInt32("priorityPlane");
        _replaceableId = stream.readUInt32("replaceableId");

        //TODO: KP2S, KP2R, KP2L, KP2G, KP2E, KP2N, KP2W, KP2V
    }
}
