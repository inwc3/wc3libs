package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class Layer extends MDXObject {
    private long _inclusiveSive = 0;

    public long getInclusiveSize() {
        return _inclusiveSive;
    }

    private long _filterMode = 0;

    public long getFilterModeL() {
        return _filterMode;
    }

    public void setFilterModeL(long filterModeL) {
        _filterMode = filterModeL;
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

    @Nullable
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

    public void setTextureId(long textureId) {
        _textureId = textureId;
    }

    private long _textureAnimId;

    public long getTextureAnimId() {
        return _textureAnimId;
    }

    public void setTextureAnimId() {
        _textureAnimId = _textureAnimId;
    }

    private long _coordId;

    public long getCoordId() {
        return _coordId;
    }

    public void setCoordId(long coordId) {
        _coordId = coordId;
    }

    private float _alpha;

    public float getAlpha() {
        return _alpha;
    }

    public void setAlpha(float alpha) {
        _alpha = alpha;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    public static class TextureTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KMTF");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _textureTracks;
        }

        public static class TextureTrack extends Track {
            private long _textureId;

            public long getTextureId() {
                return _textureId;
            }

            public void setTextureId(long textureId) {
                _textureId = textureId;
            }

            private long _inTan_textureId;

            public long getInTanTextureId() {
                return _inTan_textureId;
            }

            public void setInTanTextureId(long textureId) {
                _inTan_textureId = textureId;
            }

            private long _outTan_textureId;

            public long getOutTanTextureId() {
                return _outTan_textureId;
            }

            public void setOutTanTextureId(long textureId) {
                _outTan_textureId = textureId;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _textureId = stream.readUInt32("textureId");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_textureId = stream.readUInt32("inTan_textureId");
                    _outTan_textureId = stream.readUInt32("outTan_textureId");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeUInt32(_textureId);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeUInt32(_inTan_textureId);
                    stream.writeUInt32(_outTan_textureId);
                }
            }

            public TextureTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<TextureTrack> _textureTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<TextureTrack> getTextureTracks() {
            return _textureTracks;
        }

        public TextureTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _textureTracks.add(new TextureTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public TextureTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<TextureTrackChunk> _textureTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<TextureTrackChunk> getTextureTrackChunks() {
        return _textureTrackChunks;
    }

    public void addTextureTrackChunk(@Nonnull TextureTrackChunk val) {
        _chunks.add(val);

        _textureTrackChunks.add(val);
    }

    public static class AlphaTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KMTA");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _alphaTracks;
        }

        public static class AlphaTrack extends Track {
            private float _alpha;

            public float getAlpha() {
                return _alpha;
            }

            public void setAlpha(float alpha) {
                _alpha = alpha;
            }

            private float _inTan_alpha;

            public float getInTanAlpha() {
                return _inTan_alpha;
            }

            public void setInTanAlpha(float alpha) {
                _inTan_alpha = alpha;
            }

            private float _outTan_alpha;

            public float getOutTanAlpha() {
                return _outTan_alpha;
            }

            public void setOutTanAlpha(float alpha) {
                _outTan_alpha = alpha;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _alpha = stream.readFloat32("alpha");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_alpha = stream.readFloat32("inTan_alpha");
                    _outTan_alpha = stream.readFloat32("outTan_alpha");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_alpha);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_alpha);
                    stream.writeFloat32(_outTan_alpha);
                }
            }

            public AlphaTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<AlphaTrack> _alphaTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<AlphaTrack> getAlphaTracks() {
            return _alphaTracks;
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _alphaTracks.add(new AlphaTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<AlphaTrackChunk> _alphaTrackChunks = new ArrayList<>();

    public List<AlphaTrackChunk> getAlphaTrackChunks() {
        return new ArrayList<>(_alphaTrackChunks);
    }

    public void addAlphaTrackChunk(@Nonnull AlphaTrackChunk val) {
        _chunks.add(val);

        _alphaTrackChunks.add(val);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSive);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        stream.writeUInt32(_filterMode);
        stream.writeUInt32(_shadingFlags);
        stream.writeUInt32(_textureId);
        stream.writeUInt32(_textureAnimId);
        stream.writeUInt32(_coordId);
        stream.writeFloat32(_alpha);

        for (TextureTrackChunk textureTrackChunk : getTextureTrackChunks()) {
            textureTrackChunk.write(stream);
        }
        for (AlphaTrackChunk alphaTrackChunk : getAlphaTrackChunks()) {
            alphaTrackChunk.write(stream);
        }

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinOutputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Layer(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSive = stream.readUInt32("inclusiveSize");
        _filterMode = stream.readUInt32("filterMode");
        _shadingFlags = stream.readUInt32("shadingFlags");
        _textureId = stream.readUInt32("texId");
        _textureAnimId = stream.readUInt32("texAnimId");
        _coordId = stream.readUInt32("coordId");
        _alpha = stream.readFloat32("alpha");

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(TextureTrackChunk.TOKEN, () -> addTextureTrackChunk(new TextureTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(AlphaTrackChunk.TOKEN, () -> addAlphaTrackChunk(new AlphaTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));

        while (!stream.eof()) {
            Id trackToken = stream.readId("trackToken");

            stream.rewind(4);

            if (_tokenMap.containsKey(trackToken)) {
                MDX.TokenHandler handler = _tokenMap.get(trackToken);

                handler.run();
            } else {
                break;
            }
        }
    }

    public Layer() {
    }
}
