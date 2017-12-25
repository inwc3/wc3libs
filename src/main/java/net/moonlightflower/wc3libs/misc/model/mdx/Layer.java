package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.*;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Layer {
    private long _inclusiveSive;

    public long getInclusiveSize() {
        return _inclusiveSive;
    }

    private long _filterMode;

    public long getFilterModeL() {
        return _filterMode;
    }

    public enum FilterMode {
        NONE(0),
        TRANSPARENT(1),
        BLEND(2),
        ADDITIVE(3),
        ADD_ALPHA(4),
        MODULATE(5),
        MODULATE_2X(6);

        FilterMode(int index) {

        }
    }

    public FilterMode getFilterMode() {
        if (_filterMode != (int) _filterMode) return null;

        return FilterMode.values()[(int) _filterMode];
    }

    public void setFilterMode(@Nonnull FilterMode val) {
        _filterMode = val.ordinal();
    }

    private long _shadingFlags;

    public long getShadingFlags() {
        return _shadingFlags;
    }

    public enum ShadingFlag {
        UNSHADED,
        SPHERE_ENV_MAP,
        UNKNOWN_A,
        UNKNOWN_B,
        TWO_SIDED,
        UNFOGGED,
        NO_DEPTH_TEST,
        NO_DEPTH_SET
    }

    public void setShadingFlags(long val) {
        _shadingFlags = val;
    }

    private long _textureId;

    public long getTextureId() {
        return _textureId;
    }

    private long _textureAnimId;

    public long getTextureAnimId() {
        return _textureAnimId;
    }

    private long _coordId;

    public long getCoordId() {
        return _coordId;
    }

    private float _alpha;

    public float getAlpha() {
        return _alpha;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinOutputStream.StreamException {
        stream.writeUInt32(_inclusiveSive);
        stream.writeUInt32(_filterMode);
        stream.writeUInt32(_shadingFlags);
        stream.writeUInt32(_textureId);
        stream.writeUInt32(_textureAnimId);
        stream.writeUInt32(_coordId);
        stream.writeFloat8(_alpha);

        //TODO: KMTF, KMTA
    }

    public Layer(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSive = stream.readUInt32("inclusiveSize");
        _filterMode = stream.readUInt32("filterMode");
        _shadingFlags = stream.readUInt32("shadingFlags");
        _textureId = stream.readUInt32("texId");
        _textureAnimId = stream.readUInt32("texAnimId");
        _coordId = stream.readUInt32("coordId");
        _alpha = stream.readFloat8("alpha");

        //TODO: KMTF, KMTA
    }
}
