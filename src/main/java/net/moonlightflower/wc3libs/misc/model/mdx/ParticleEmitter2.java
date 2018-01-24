package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.*;

public class ParticleEmitter2 extends MDXObject {
    private long _inclusiveSize = 0;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    @Nonnull
    public Node getNode() {
        return _node;
    }

    public void setNode(@Nonnull Node node) {
        _node = node;
    }

    private float _speed = 0F;

    public float getSpeed() {
        return _speed;
    }

    public void setSpeed(float speed) {
        _speed = speed;
    }

    private float _variation = 0F;

    public float getVariation() {
        return _variation;
    }

    public void setVariation(float variation) {
        _variation = variation;
    }

    private float _latitude = 0F;

    public float getLatitude() {
        return _latitude;
    }

    public void setLatitude(float latitude) {
        _latitude = latitude;
    }

    private float _gravity = 0F;

    public float getGravity() {
        return _gravity;
    }

    public void setGravity(float gravity) {
        _gravity = gravity;
    }

    private float _lifespan = 0F;

    public float getLifespan() {
        return _lifespan;
    }

    public void setLifespan(float lifespan) {
        _lifespan = lifespan;
    }

    private float _emissionRate = 0F;

    public float getEmissionRate() {
        return _emissionRate;
    }

    public void setEmissionRate(float emissionRate) {
        _emissionRate = emissionRate;
    }

    private float _width = 0F;

    public float getWidth() {
        return _width;
    }

    public void setWidth(float width) {
        _width = width;
    }

    private float _length = 0F;

    public float getLength() {
        return _length;
    }

    public void setLength(float length) {
        _length = length;
    }

    public enum FilterMode {
        BLEND,
        ADDITIVE,
        MODULATE,
        MODULATE_2X,
        ALPHA_KEY
    }

    private FilterMode _filterMode = FilterMode.BLEND;

    @Nonnull
    public FilterMode getFilterMode() {
        return _filterMode;
    }

    public void setFilterMode(@Nonnull FilterMode filterMode) {
        _filterMode = filterMode;
    }

    private long _rows = 0;

    public long getRows() {
        return _rows;
    }

    public void setRows(long rows) {
        _rows = rows;
    }

    private long _columns = 0;

    public long getColumns() {
        return _columns;
    }

    public void setColumns(long columns) {
        _columns = columns;
    }

    public enum HeadTailFlag {
        HEAD,
        TAIL,
        BOTH
    }

    private HeadTailFlag _headTailFlag = HeadTailFlag.HEAD;

    @Nonnull
    public HeadTailFlag getHeadTailFlag() {
        return _headTailFlag;
    }

    public void setHeadTailFlag(@Nonnull HeadTailFlag headTailFlag) {
        _headTailFlag = headTailFlag;
    }

    private float _tailLength = 0F;

    public float getTailLength() {
        return _tailLength;
    }

    public void setTailLength(float tailLength) {
        _tailLength = tailLength;
    }

    private float _time = 0F;

    public float getTime() {
        return _time;
    }

    public void setTime(float time) {
        _time = time;
    }

    private Color _startColor;

    @Nonnull
    public Color getStartColor() {
        return _startColor;
    }

    public void setStartColor(@Nonnull Color startColor) {
        _startColor = startColor;
    }

    private Color _midColor;

    @Nonnull
    public Color getMidColor() {
        return _midColor;
    }

    public void setMidColor(@Nonnull Color midColor) {
        _midColor = midColor;
    }

    private Color _endColor;

    @Nonnull
    public Color getEndColor() {
        return _endColor;
    }

    public void setEndColor(@Nonnull Color endColor) {
        _endColor = endColor;
    }

    private float _startScaling = 0F;

    public float getStartScaling() {
        return _startScaling;
    }

    public void setStartScaling(float startScaling) {
        _startScaling = startScaling;
    }

    private float _midScaling = 0F;

    public float getMidScaling() {
        return _midScaling;
    }

    public void setMidScaling(float midScaling) {
        _midScaling = midScaling;
    }

    private float _endScaling = 0F;

    public float getEndScaling() {
        return _endScaling;
    }

    public void setEndScaling(float endScaling) {
        _endScaling = endScaling;
    }

    private long _startHeadInterval = 0;

    public long getStartHeadInterval() {
        return _startHeadInterval;
    }

    public void setStartHeadInterval(long startHeadInterval) {
        _startHeadInterval = startHeadInterval;
    }

    private long _midHeadInterval = 0;

    public long getMidHeadInterval() {
        return _midHeadInterval;
    }

    public void setMidHeadInterval(long midHeadInterval) {
        _midHeadInterval = midHeadInterval;
    }

    private long _endHeadInterval = 0;

    public long getEndHeadInterval() {
        return _endHeadInterval;
    }

    public void setEndHeadInterval(long endHeadInterval) {
        _endHeadInterval = endHeadInterval;
    }

    private long _startHeadDecayInterval = 0;

    public long getStartHeadDecayInterval() {
        return _startHeadDecayInterval;
    }

    public void setStartHeadDecayInterval(long startHeadDecayInterval) {
        _startHeadDecayInterval = startHeadDecayInterval;
    }

    private long _midHeadDecayInterval = 0;

    public long getMidHeadDecayInterval() {
        return _midHeadDecayInterval;
    }

    public void setMidHeadDecayInterval(long midHeadDecayInterval) {
        _midHeadDecayInterval = midHeadDecayInterval;
    }

    private long _endHeadDecayInterval = 0;

    public long getEndHeadDecayInterval() {
        return _endHeadDecayInterval;
    }

    public void setEndHeadDecayInterval(long endHeadDecayInterval) {
        _endHeadDecayInterval = endHeadDecayInterval;
    }

    private long _startTailInterval = 0;

    public long getStartTailInterval() {
        return _startTailInterval;
    }

    public void setStartTailInterval(long startTailInterval) {
        _startTailInterval = startTailInterval;
    }

    private long _midTailInterval = 0;

    public long getMidTailInterval() {
        return _midTailInterval;
    }

    public void setMidTailInterval(long midTailInterval) {
        _midTailInterval = midTailInterval;
    }

    private long _endTailInterval = 0;

    public long getEndTailInterval() {
        return _endTailInterval;
    }

    public void setEndTailInterval(long endTailInterval) {
        _endTailInterval = endTailInterval;
    }

    private long _startTailDecayInterval = 0;

    public long getStartTailDecayInterval() {
        return _startTailDecayInterval;
    }

    public void setStartTailDecayInterval(long startTailDecayInterval) {
        _startTailDecayInterval = startTailDecayInterval;
    }

    private long _midTailDecayInterval = 0;

    public long getMidTailDecayInterval() {
        return _midTailDecayInterval;
    }

    public void setMidTailDecayInterval(long midTailDecayInterval) {
        _midTailDecayInterval = midTailDecayInterval;
    }

    private long _endTailDecayInterval = 0;

    public long getEndTailDecayInterval() {
        return _endTailDecayInterval;
    }

    public void setEndTailDecayInterval(long endTailDecayInterval) {
        _endTailDecayInterval = endTailDecayInterval;
    }

    private long _textureId = 0;

    public long getTextureId() {
        return _textureId;
    }

    public void setTextureId(long textureId) {
        _textureId = textureId;
    }

    private long _squirt = 0;

    public long getSquirt() {
        return _squirt;
    }

    public void setSquirt(long squirt) {
        _squirt = squirt;
    }

    private long _priorityPlane = 0;

    public long getPriorityPlane() {
        return _priorityPlane;
    }

    public void setPriorityPlane(long priorityPlane) {
        _priorityPlane = priorityPlane;
    }

    private long _replaceableId = 0;

    public long getReplaceableId() {
        return _replaceableId;
    }

    public void setReplaceableId(long replaceableId) {
        _replaceableId = replaceableId;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    private void addChunk(@Nonnull Chunk val) {
        _chunks.add(val);
    }

    public static class EmissionRateTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2E");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _emissionRateTracks;
        }

        public static class EmissionRateTrack extends Track {
            private float _emissionRate;

            public float getEmissionRate() {
                return _emissionRate;
            }

            public void setEmissionRate(float emissionRate) {
                _emissionRate = emissionRate;
            }

            private float _inTan_emissionRate;

            public float getInTanEmissionRate() {
                return _inTan_emissionRate;
            }

            public void setInTanEmissionRate(float emissionRate) {
                _inTan_emissionRate = emissionRate;
            }

            private float _outTan_emissionRate;

            public float getOutTanEmissionRate() {
                return _outTan_emissionRate;
            }

            public void setOutTanEmissionRate(float emissionRate) {
                _outTan_emissionRate = emissionRate;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _emissionRate = stream.readFloat32("emissionRate");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_emissionRate = stream.readFloat32("inTan_emissionRate");
                    _outTan_emissionRate = stream.readFloat32("outTan_emissionRate");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_emissionRate);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_emissionRate);
                    stream.writeFloat32(_outTan_emissionRate);
                }
            }

            public EmissionRateTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<EmissionRateTrack> _emissionRateTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<EmissionRateTrack> getEmissionRateTracks() {
            return _emissionRateTracks;
        }

        public EmissionRateTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _emissionRateTracks.add(new EmissionRateTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public EmissionRateTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<EmissionRateTrackChunk> _emissionRateTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<EmissionRateTrackChunk> getEmissionRateTrackChunks() {
        return _emissionRateTrackChunks;
    }

    public void addEmissionRateTrackChunk(@Nonnull EmissionRateTrackChunk val) {
        _chunks.add(val);

        _emissionRateTrackChunks.add(val);
    }

    public static class GravityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2G");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _gravityTracks;
        }

        public static class GravityTrack extends Track {
            private float _gravity;

            public float getGravity() {
                return _gravity;
            }

            public void setGravity(float gravity) {
                _gravity = gravity;
            }

            private float _inTan_gravity;

            public float getInTanGravity() {
                return _inTan_gravity;
            }

            public void setInTanGravity(float gravity) {
                _inTan_gravity = gravity;
            }

            private float _outTan_gravity;

            public float getOutTanGravity() {
                return _outTan_gravity;
            }

            public void setOutTanGravity(float gravity) {
                _outTan_gravity = gravity;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _gravity = stream.readFloat32("gravity");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_gravity = stream.readFloat32("inTan_gravity");
                    _outTan_gravity = stream.readFloat32("outTan_gravity");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_gravity);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_gravity);
                    stream.writeFloat32(_outTan_gravity);
                }
            }

            public GravityTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<GravityTrack> _gravityTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<GravityTrack> getGravityTracks() {
            return _gravityTracks;
        }

        public GravityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _gravityTracks.add(new GravityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public GravityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<GravityTrackChunk> _gravityTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<GravityTrackChunk> getGravityTrackChunks() {
        return _gravityTrackChunks;
    }

    public void addGravityTrackChunk(@Nonnull GravityTrackChunk val) {
        _chunks.add(val);

        _gravityTrackChunks.add(val);
    }

    public static class LatitudeTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2L");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _latitudeTracks;
        }

        public static class LatitudeTrack extends Track {
            private float _latitude;

            public float getLatitude() {
                return _latitude;
            }

            public void setLatitude(float latitude) {
                _latitude = latitude;
            }

            private float _inTan_latitude;

            public float getInTanLatitude() {
                return _inTan_latitude;
            }

            public void setInTanLatitude(float latitude) {
                _inTan_latitude = latitude;
            }

            private float _outTan_latitude;

            public float getOutTanLatitude() {
                return _outTan_latitude;
            }

            public void setOutTanLatitude(float latitude) {
                _outTan_latitude = latitude;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _latitude = stream.readFloat32("latitude");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_latitude = stream.readFloat32("inTan_latitude");
                    _outTan_latitude = stream.readFloat32("outTan_latitude");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_latitude);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_latitude);
                    stream.writeFloat32(_outTan_latitude);
                }
            }

            public LatitudeTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<LatitudeTrack> _latitudeTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<LatitudeTrack> getLatitudeTracks() {
            return _latitudeTracks;
        }

        public LatitudeTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _latitudeTracks.add(new LatitudeTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public LatitudeTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<LatitudeTrackChunk> _latitudeTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<LatitudeTrackChunk> getLatitudeTrackChunks() {
        return _latitudeTrackChunks;
    }

    public void addLatitudeTrackChunk(@Nonnull LatitudeTrackChunk val) {
        _chunks.add(val);

        _latitudeTrackChunks.add(val);
    }

    public static class SpeedTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2S");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _speedTracks;
        }

        public static class SpeedTrack extends Track {
            private float _speed;

            public float getSpeed() {
                return _speed;
            }

            public void setSpeed(float speed) {
                _speed = speed;
            }

            private float _inTan_speed;

            public float getInTanSpeed() {
                return _inTan_speed;
            }

            public void setInTanSpeed(float speed) {
                _inTan_speed = speed;
            }

            private float _outTan_speed;

            public float getOutTanSpeed() {
                return _outTan_speed;
            }

            public void setOutTanSpeed(float speed) {
                _outTan_speed = speed;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _speed = stream.readFloat32("speed");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_speed = stream.readFloat32("inTan_speed");
                    _outTan_speed = stream.readFloat32("outTan_speed");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_speed);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_speed);
                    stream.writeFloat32(_outTan_speed);
                }
            }

            public SpeedTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<SpeedTrack> _speedTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<SpeedTrack> getSpeedTracks() {
            return _speedTracks;
        }

        public SpeedTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _speedTracks.add(new SpeedTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public SpeedTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<SpeedTrackChunk> _speedTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<SpeedTrackChunk> getSpeedTrackChunks() {
        return _speedTrackChunks;
    }

    public void addSpeedTrackChunk(@Nonnull SpeedTrackChunk val) {
        _chunks.add(val);

        _speedTrackChunks.add(val);
    }

    public static class VisibilityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2V");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _visibilityTracks;
        }

        private final LinkedHashSet<VisibilityTrack> _visibilityTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<VisibilityTrack> getVisibilityTracks() {
            return _visibilityTracks;
        }

        public VisibilityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _visibilityTracks.add(new VisibilityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public VisibilityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<VisibilityTrackChunk> _visibilityTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<VisibilityTrackChunk> getVisibilityTrackChunks() {
        return _visibilityTrackChunks;
    }

    public void addVisibilityTrackChunk(@Nonnull VisibilityTrackChunk val) {
        _chunks.add(val);

        _visibilityTrackChunks.add(val);
    }

    public static class VariationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2R");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _variationTracks;
        }

        public static class VariationTrack extends Track {
            private float _variation;

            public float getVariation() {
                return _variation;
            }

            public void setVariation(float variation) {
                _variation = variation;
            }

            private float _inTan_variation;

            public float getInTanVariation() {
                return _inTan_variation;
            }

            public void setInTanVariation(float variation) {
                _inTan_variation = variation;
            }

            private float _outTan_variation;

            public float getOutTanVariation() {
                return _outTan_variation;
            }

            public void setOutTanVariation(float variation) {
                _outTan_variation = variation;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _variation = stream.readFloat32("variation");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_variation = stream.readFloat32("inTan_variation");
                    _outTan_variation = stream.readFloat32("outTan_variation");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_variation);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_variation);
                    stream.writeFloat32(_outTan_variation);
                }
            }

            public VariationTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<VariationTrack> _variationTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<VariationTrack> getVariationTracks() {
            return _variationTracks;
        }

        public VariationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _variationTracks.add(new VariationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public VariationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<VariationTrackChunk> _variationTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<VariationTrackChunk> getVariationTrackChunks() {
        return _variationTrackChunks;
    }

    public void addVariationTrackChunk(@Nonnull VariationTrackChunk val) {
        _chunks.add(val);

        _variationTrackChunks.add(val);
    }

    public static class LengthTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2N");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _lengthTracks;
        }

        public static class LengthTrack extends Track {
            private float _length;

            public float getLength() {
                return _length;
            }

            public void setLength(float length) {
                _length = length;
            }

            private float _inTan_length;

            public float getInTanLength() {
                return _inTan_length;
            }

            public void setInTanLength(float length) {
                _inTan_length = length;
            }

            private float _outTan_length;

            public float getOutTanLength() {
                return _outTan_length;
            }

            public void setOutTanLength(float length) {
                _outTan_length = length;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _length = stream.readFloat32("length");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_length = stream.readFloat32("inTan_length");
                    _outTan_length = stream.readFloat32("outTan_length");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_length);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_length);
                    stream.writeFloat32(_outTan_length);
                }
            }

            public LengthTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<LengthTrack> _lengthTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<LengthTrack> getLengthTracks() {
            return _lengthTracks;
        }

        public LengthTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _lengthTracks.add(new LengthTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public LengthTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<LengthTrackChunk> _lengthTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<LengthTrackChunk> getLengthTrackChunks() {
        return _lengthTrackChunks;
    }

    public void addLengthTrackChunk(@Nonnull LengthTrackChunk val) {
        _chunks.add(val);

        _lengthTrackChunks.add(val);
    }

    public static class WidthTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2W");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _widthTracks;
        }

        public static class WidthTrack extends Track {
            private float _width;

            public float getWidth() {
                return _width;
            }

            public void setWidth(float width) {
                _width = width;
            }

            private float _inTan_width;

            public float getInTanWidth() {
                return _inTan_width;
            }

            public void setInTanWidth(float width) {
                _inTan_width = width;
            }

            private float _outTan_width;

            public float getOutTanWidth() {
                return _outTan_width;
            }

            public void setOutTanWidth(float width) {
                _outTan_width = width;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _width = stream.readFloat32("width");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_width = stream.readFloat32("inTan_width");
                    _outTan_width = stream.readFloat32("outTan_width");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_width);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_width);
                    stream.writeFloat32(_outTan_width);
                }
            }

            public WidthTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<WidthTrack> _widthTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<WidthTrack> getWidthTracks() {
            return _widthTracks;
        }

        public WidthTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _widthTracks.add(new WidthTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public WidthTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<WidthTrackChunk> _widthTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<WidthTrackChunk> getWidthTrackChunks() {
        return _widthTrackChunks;
    }

    public void addWidthTrackChunk(@Nonnull WidthTrackChunk val) {
        _chunks.add(val);

        _widthTrackChunks.add(val);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        _node.write(stream);
        stream.writeFloat32(_speed);
        stream.writeFloat32(_variation);
        stream.writeFloat32(_latitude);
        stream.writeFloat32(_gravity);
        stream.writeFloat32(_lifespan);
        stream.writeFloat32(_emissionRate);
        stream.writeFloat32(_length);
        stream.writeFloat32(_width);

        stream.writeUInt32(_filterMode.ordinal());

        stream.writeUInt32(_rows);
        stream.writeUInt32(_columns);

        stream.writeUInt32(_headTailFlag.ordinal());

        stream.writeFloat32(_tailLength);
        stream.writeFloat32(_time);

        stream.writeFloat32(_startColor.getBlue255() / 255F);
        stream.writeFloat32(_startColor.getGreen255() / 255F);
        stream.writeFloat32(_startColor.getRed255() / 255F);

        stream.writeFloat32(_midColor.getBlue255() / 255F);
        stream.writeFloat32(_midColor.getGreen255() / 255F);
        stream.writeFloat32(_midColor.getRed255() / 255F);

        stream.writeFloat32(_endColor.getBlue255() / 255F);
        stream.writeFloat32(_endColor.getGreen255() / 255F);
        stream.writeFloat32(_endColor.getRed255() / 255F);

        stream.writeUInt8(_startColor.getAlpha255());
        stream.writeUInt8(_midColor.getAlpha255());
        stream.writeUInt8(_endColor.getAlpha255());

        stream.writeFloat32(_startScaling);
        stream.writeFloat32(_midScaling);
        stream.writeFloat32(_endScaling);

        stream.writeUInt32(_startHeadInterval);
        stream.writeUInt32(_midHeadInterval);
        stream.writeUInt32(_endHeadInterval);

        stream.writeUInt32(_startHeadDecayInterval);
        stream.writeUInt32(_midHeadDecayInterval);
        stream.writeUInt32(_endHeadDecayInterval);

        stream.writeUInt32(_startTailInterval);
        stream.writeUInt32(_midTailInterval);
        stream.writeUInt32(_endTailInterval);

        stream.writeUInt32(_startTailDecayInterval);
        stream.writeUInt32(_midTailDecayInterval);
        stream.writeUInt32(_endTailDecayInterval);

        stream.writeUInt32(_textureId);
        stream.writeUInt32(_squirt);
        stream.writeUInt32(_priorityPlane);
        stream.writeUInt32(_replaceableId);

        for (Chunk chunk : getChunks()) {
            chunk.write(stream);
        }
        /*for (EmissionRateTrackChunk emissionRateTrackChunk : getEmissionRateTrackChunks()) {
            emissionRateTrackChunk.write(stream);
        }
        for (GravityTrackChunk gravityTrackChunk : getGravityTrackChunks()) {
            gravityTrackChunk.write(stream);
        }
        for (LatitudeTrackChunk latitudeTrackChunk : getLatitudeTrackChunks()) {
            latitudeTrackChunk.write(stream);
        }
        for (SpeedTrackChunk speedTrackChunk : getSpeedTrackChunks()) {
            speedTrackChunk.write(stream);
        }
        for (VisibilityTrackChunk visibilityTrackChunk : getVisibilityTrackChunks()) {
            visibilityTrackChunk.write(stream);
        }
        for (VariationTrackChunk variationTrackChunk : getVariationTrackChunks()) {
            variationTrackChunk.write(stream);
        }
        for (LengthTrackChunk lengthTrackChunk : getLengthTrackChunks()) {
            lengthTrackChunk.write(stream);
        }
        for (WidthTrackChunk widthTrackChunk : getWidthTrackChunks()) {
            widthTrackChunk.write(stream);
        }*/

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public ParticleEmitter2(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _speed = stream.readFloat32("speed");
        _variation = stream.readFloat32("variation");
        _latitude = stream.readFloat32("latitude");
        _gravity = stream.readFloat32("gravity");
        _lifespan = stream.readFloat32("lifespan");
        _emissionRate = stream.readFloat32("emissionRate");
        _length = stream.readFloat32("length");
        _width = stream.readFloat32("width");

        long filterModeL = stream.readUInt32("filterMode");

        if (filterModeL >= FilterMode.values().length) throw new IllegalArgumentException("filterMode out of bounds 0-" + FilterMode.values().length + " (" + filterModeL + ")");

        _filterMode = FilterMode.values()[(int) filterModeL];

        _rows = stream.readUInt32("rows");
        _columns = stream.readUInt32("columns");

        long headTailFlagL = stream.readUInt32("headTailFlag");

        if (headTailFlagL >= HeadTailFlag.values().length) throw new IllegalArgumentException("headTailFlags out of bounds 0-" + HeadTailFlag.values().length + " (" + headTailFlagL + ")");

        _headTailFlag = HeadTailFlag.values()[(int) headTailFlagL];

        _tailLength = stream.readFloat32("tailLength");
        _time = stream.readFloat32("time");

        _startColor = Color.fromBGR255((int) (stream.readFloat32("startColorBlue") * 255), (int) (stream.readFloat32("startColorGreen") * 255), (int) (stream.readFloat32("startColorRed") * 255));
        _midColor = Color.fromBGR255((int) (stream.readFloat32("midColorBlue") * 255), (int) (stream.readFloat32("midColorGreen") * 255), (int) (stream.readFloat32("midColorRed") * 255));
        _endColor = Color.fromBGR255((int) (stream.readFloat32("endColorBlue") * 255), (int) (stream.readFloat32("endColorGreen") * 255), (int) (stream.readFloat32("endColorRed") * 255));

        _startColor = Color.fromBGRA255(_startColor.getBlue255(), _startColor.getGreen255(), _startColor.getRed255(), stream.readUInt8("startColorAlpha"));
        _midColor = Color.fromBGRA255(_midColor.getBlue255(), _midColor.getGreen255(), _midColor.getRed255(), stream.readUInt8("midColorAlpha"));

        _endColor = Color.fromBGRA255(_endColor.getBlue255(), _endColor.getGreen255(), _endColor.getRed255(), stream.readUInt8("endColorAlpha"));

        _startScaling = stream.readFloat32("startScaling");
        _midScaling = stream.readFloat32("midScaling");
        _endScaling = stream.readFloat32("endScaling");

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

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(EmissionRateTrackChunk.TOKEN, () -> addEmissionRateTrackChunk(new EmissionRateTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(GravityTrackChunk.TOKEN, () -> addGravityTrackChunk(new GravityTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(LatitudeTrackChunk.TOKEN, () -> addLatitudeTrackChunk(new LatitudeTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(SpeedTrackChunk.TOKEN, () -> addSpeedTrackChunk(new SpeedTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(VisibilityTrackChunk.TOKEN, () -> addVisibilityTrackChunk(new VisibilityTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(VariationTrackChunk.TOKEN, () -> addVariationTrackChunk(new VariationTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(LengthTrackChunk.TOKEN, () -> addLengthTrackChunk(new LengthTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(WidthTrackChunk.TOKEN, () -> addWidthTrackChunk(new WidthTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));

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

    public ParticleEmitter2() {
        _node = new Node();
        _startColor = Color.fromBGRA255(0, 0, 0, 0);
        _midColor = Color.fromBGRA255(0, 0, 0, 0);
        _endColor = Color.fromBGRA255(0, 0, 0, 0);
    }
}
