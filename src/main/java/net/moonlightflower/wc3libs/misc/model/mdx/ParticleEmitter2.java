package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private List<Chunk> _chunks = new ArrayList<>();

    public List<Chunk> getChunks() {
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
        public List<? extends Track> getTracks() {
            return _emissionRateTracks;
        }

        public static class EmissionRateTrack extends Track {
            private float _emissionRate;

            public float getEmissionRate() {
                return _emissionRate;
            }

            private float _inTan_emissionRate;

            public float getInTanEmissionRate() {
                return _inTan_emissionRate;
            }

            private float _outTan_emissionRate;

            public float getOutTanEmissionRate() {
                return _outTan_emissionRate;
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

        private List<EmissionRateTrack> _emissionRateTracks = new ArrayList<>();

        public List<EmissionRateTrack> getEmissionRateTracks() {
            return new ArrayList<>(_emissionRateTracks);
        }

        public void addEmissionRateTrack(@Nonnull EmissionRateTrack val) {
            if (!_emissionRateTracks.contains(val)) {
                _emissionRateTracks.add(val);
            }
        }

        public EmissionRateTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addEmissionRateTrack(new EmissionRateTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public EmissionRateTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<EmissionRateTrackChunk> _emissionRateTrackChunks = new ArrayList<>();

    public List<EmissionRateTrackChunk> getEmissionRateTrackChunks() {
        return new ArrayList<>(_emissionRateTrackChunks);
    }

    public void addEmissionRateTrackChunk(@Nonnull EmissionRateTrackChunk val) {
        addChunk(val);

        if (!_emissionRateTrackChunks.contains(val)) {
            _emissionRateTrackChunks.add(val);
        }
    }

    public static class GravityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2G");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _gravityTracks;
        }

        public static class GravityTrack extends Track {
            private float _gravity;

            public float getGravity() {
                return _gravity;
            }

            private float _inTan_gravity;

            public float getInTanGravity() {
                return _inTan_gravity;
            }

            private float _outTan_gravity;

            public float getOutTanGravity() {
                return _outTan_gravity;
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

        private List<GravityTrack> _gravityTracks = new ArrayList<>();

        public List<GravityTrack> getGravityTracks() {
            return new ArrayList<>(_gravityTracks);
        }

        public void addGravityTrack(@Nonnull GravityTrack val) {
            if (!_gravityTracks.contains(val)) {
                _gravityTracks.add(val);
            }
        }

        public GravityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addGravityTrack(new GravityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public GravityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<GravityTrackChunk> _gravityTrackChunks = new ArrayList<>();

    public List<GravityTrackChunk> getGravityTrackChunks() {
        return new ArrayList<>(_gravityTrackChunks);
    }

    public void addGravityTrackChunk(@Nonnull GravityTrackChunk val) {
        addChunk(val);

        if (!_gravityTrackChunks.contains(val)) {
            _gravityTrackChunks.add(val);
        }
    }

    public static class LatitudeTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2L");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _latitudeTracks;
        }

        public static class LatitudeTrack extends Track {
            private float _latitude;

            public float getLatitude() {
                return _latitude;
            }

            private float _inTan_latitude;

            public float getInTanLatitude() {
                return _inTan_latitude;
            }

            private float _outTan_latitude;

            public float getOutTanLatitude() {
                return _outTan_latitude;
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

        private List<LatitudeTrack> _latitudeTracks = new ArrayList<>();

        public List<LatitudeTrack> getLatitudeTracks() {
            return new ArrayList<>(_latitudeTracks);
        }

        public void addLatitudeTrack(@Nonnull LatitudeTrack val) {
            if (!_latitudeTracks.contains(val)) {
                _latitudeTracks.add(val);
            }
        }

        public LatitudeTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addLatitudeTrack(new LatitudeTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public LatitudeTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<LatitudeTrackChunk> _latitudeTrackChunks = new ArrayList<>();

    public List<LatitudeTrackChunk> getLatitudeTrackChunks() {
        return new ArrayList<>(_latitudeTrackChunks);
    }

    public void addLatitudeTrackChunk(@Nonnull LatitudeTrackChunk val) {
        addChunk(val);

        if (!_latitudeTrackChunks.contains(val)) {
            _latitudeTrackChunks.add(val);
        }
    }

    public static class SpeedTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2S");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _speedTracks;
        }

        public static class SpeedTrack extends Track {
            private float _speed;

            public float getSpeed() {
                return _speed;
            }

            private float _inTan_speed;

            public float getInTanSpeed() {
                return _inTan_speed;
            }

            private float _outTan_speed;

            public float getOutTanSpeed() {
                return _outTan_speed;
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

        private List<SpeedTrack> _speedTracks = new ArrayList<>();

        public List<SpeedTrack> getSpeedTracks() {
            return new ArrayList<>(_speedTracks);
        }

        public void addSpeedTrack(@Nonnull SpeedTrack val) {
            if (!_speedTracks.contains(val)) {
                _speedTracks.add(val);
            }
        }

        public SpeedTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addSpeedTrack(new SpeedTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public SpeedTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<SpeedTrackChunk> _speedTrackChunks = new ArrayList<>();

    public List<SpeedTrackChunk> getSpeedTrackChunks() {
        return new ArrayList<>(_speedTrackChunks);
    }

    public void addSpeedTrackChunk(@Nonnull SpeedTrackChunk val) {
        addChunk(val);

        if (!_speedTrackChunks.contains(val)) {
            _speedTrackChunks.add(val);
        }
    }

    public static class VisibilityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2V");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _visibilityTracks;
        }

        public static class VisibilityTrack extends Track {
            private float _visibility;

            public float getVisibility() {
                return _visibility;
            }

            private float _inTan_visibility;

            public float getInTanVisibility() {
                return _inTan_visibility;
            }

            private float _outTan_visibility;

            public float getOutTanVisibility() {
                return _outTan_visibility;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _visibility = stream.readFloat32("visibility");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_visibility = stream.readFloat32("inTan_visibility");
                    _outTan_visibility = stream.readFloat32("outTan_visibility");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_visibility);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_visibility);
                    stream.writeFloat32(_outTan_visibility);
                }
            }

            public VisibilityTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<VisibilityTrack> _visibilityTracks = new ArrayList<>();

        public List<VisibilityTrack> getVisibilityTracks() {
            return new ArrayList<>(_visibilityTracks);
        }

        public void addVisibilityTrack(@Nonnull VisibilityTrack val) {
            if (!_visibilityTracks.contains(val)) {
                _visibilityTracks.add(val);
            }
        }

        public VisibilityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addVisibilityTrack(new VisibilityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public VisibilityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<VisibilityTrackChunk> _visibilityTrackChunks = new ArrayList<>();

    public List<VisibilityTrackChunk> getVisibilityTrackChunks() {
        return new ArrayList<>(_visibilityTrackChunks);
    }

    public void addVisibilityTrackChunk(@Nonnull VisibilityTrackChunk val) {
        addChunk(val);

        if (!_visibilityTrackChunks.contains(val)) {
            _visibilityTrackChunks.add(val);
        }
    }

    public static class VariationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2R");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _variationTracks;
        }

        public static class VariationTrack extends Track {
            private float _variation;

            public float getVariation() {
                return _variation;
            }

            private float _inTan_variation;

            public float getInTanVariation() {
                return _inTan_variation;
            }

            private float _outTan_variation;

            public float getOutTanVariation() {
                return _outTan_variation;
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

        private List<VariationTrack> _variationTracks = new ArrayList<>();

        public List<VariationTrack> getVariationTracks() {
            return new ArrayList<>(_variationTracks);
        }

        public void addVariationTrack(@Nonnull VariationTrack val) {
            if (!_variationTracks.contains(val)) {
                _variationTracks.add(val);
            }
        }

        public VariationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addVariationTrack(new VariationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public VariationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<VariationTrackChunk> _variationTrackChunks = new ArrayList<>();

    public List<VariationTrackChunk> getVariationTrackChunks() {
        return new ArrayList<>(_variationTrackChunks);
    }

    public void addVariationTrackChunk(@Nonnull VariationTrackChunk val) {
        addChunk(val);

        if (!_variationTrackChunks.contains(val)) {
            _variationTrackChunks.add(val);
        }
    }

    public static class LengthTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2N");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _lengthTracks;
        }

        public static class LengthTrack extends Track {
            private float _length;

            public float getLength() {
                return _length;
            }

            private float _inTan_length;

            public float getInTanLength() {
                return _inTan_length;
            }

            private float _outTan_length;

            public float getOutTanLength() {
                return _outTan_length;
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

        private List<LengthTrack> _lengthTracks = new ArrayList<>();

        public List<LengthTrack> getLengthTracks() {
            return new ArrayList<>(_lengthTracks);
        }

        public void addLengthTrack(@Nonnull LengthTrack val) {
            if (!_lengthTracks.contains(val)) {
                _lengthTracks.add(val);
            }
        }

        public LengthTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addLengthTrack(new LengthTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public LengthTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<LengthTrackChunk> _lengthTrackChunks = new ArrayList<>();

    public List<LengthTrackChunk> getLengthTrackChunks() {
        return new ArrayList<>(_lengthTrackChunks);
    }

    public void addLengthTrackChunk(@Nonnull LengthTrackChunk val) {
        addChunk(val);

        if (!_lengthTrackChunks.contains(val)) {
            _lengthTrackChunks.add(val);
        }
    }

    public static class WidthTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KP2W");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _widthTracks;
        }

        public static class WidthTrack extends Track {
            private float _width;

            public float getWidth() {
                return _width;
            }

            private float _inTan_width;

            public float getInTanWidth() {
                return _inTan_width;
            }

            private float _outTan_width;

            public float getOutTanWidth() {
                return _outTan_width;
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

        private List<WidthTrack> _widthTracks = new ArrayList<>();

        public List<WidthTrack> getWidthTracks() {
            return new ArrayList<>(_widthTracks);
        }

        public void addWidthTrack(@Nonnull WidthTrack val) {
            if (!_widthTracks.contains(val)) {
                _widthTracks.add(val);
            }
        }

        public WidthTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addWidthTrack(new WidthTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public WidthTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<WidthTrackChunk> _widthTrackChunks = new ArrayList<>();

    public List<WidthTrackChunk> getWidthTrackChunks() {
        return new ArrayList<>(_widthTrackChunks);
    }

    public void addWidthTrackChunk(@Nonnull WidthTrackChunk val) {
        addChunk(val);

        if (!_widthTrackChunks.contains(val)) {
            _widthTrackChunks.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeUInt32(_inclusiveSize);
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

        stream.writeFloat32(_startColor.getBlue() / 255F);
        stream.writeFloat32(_startColor.getGreen() / 255F);
        stream.writeFloat32(_startColor.getRed() / 255F);

        stream.writeFloat32(_midColor.getBlue() / 255F);
        stream.writeFloat32(_midColor.getGreen() / 255F);
        stream.writeFloat32(_midColor.getRed() / 255F);

        stream.writeFloat32(_endColor.getBlue() / 255F);
        stream.writeFloat32(_endColor.getGreen() / 255F);
        stream.writeFloat32(_endColor.getRed() / 255F);

        stream.writeUInt8(_startColor.getAlpha());
        stream.writeUInt8(_midColor.getAlpha());
        stream.writeUInt8(_endColor.getAlpha());

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

        _startColor = Color.fromBGRA255(_startColor.getBlue(), _startColor.getGreen(), _startColor.getRed(), stream.readUInt8("startColorAlpha"));
        _midColor = Color.fromBGRA255(_midColor.getBlue(), _midColor.getGreen(), _midColor.getRed(), stream.readUInt8("midColorAlpha"));

        _endColor = Color.fromBGRA255(_endColor.getBlue(), _endColor.getGreen(), _endColor.getRed(), stream.readUInt8("endColorAlpha"));

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
}
