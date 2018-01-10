package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ParticleEmitter extends MDXObject {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    private float _emissionRate;

    public float getEmissionRate() {
        return _emissionRate;
    }

    private float _gravity;

    public float getGravity() {
        return _gravity;
    }

    private float _longitude;

    public float getLongitude() {
        return _longitude;
    }

    private float _latitude;

    public float getLatitude() {
        return _latitude;
    }

    private String _fileName;

    public String getFileName() {
        return _fileName;
    }

    private float _lifespan;

    public float getLifespan() {
        return _lifespan;
    }

    private float _initialSpeed;

    public float getInitialSpeed() {
        return _initialSpeed;
    }

    private List<Chunk> _chunks = new ArrayList<>();

    public List<Chunk> getChunks() {
        return _chunks;
    }

    private void addChunk(@Nonnull Chunk val) {
        _chunks.add(val);
    }

    public static class EmissionRateTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPEE");

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
        public final static Id TOKEN = Id.valueOf("KPEG");

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

    public static class LongitudeTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPLN");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _longitudeTracks;
        }

        public static class LongitudeTrack extends Track {
            private float _longitude;

            public float getLongitude() {
                return _longitude;
            }

            private float _inTan_longitude;

            public float getInTanLongitude() {
                return _inTan_longitude;
            }

            private float _outTan_longitude;

            public float getOutTanLongitude() {
                return _outTan_longitude;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _longitude = stream.readFloat32("longitude");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_longitude = stream.readFloat32("inTan_longitude");
                    _outTan_longitude = stream.readFloat32("outTan_longitude");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_longitude);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_longitude);
                    stream.writeFloat32(_outTan_longitude);
                }
            }

            public LongitudeTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<LongitudeTrack> _longitudeTracks = new ArrayList<>();

        public List<LongitudeTrack> getLongitudeTracks() {
            return new ArrayList<>(_longitudeTracks);
        }

        public void addLongitudeTrack(@Nonnull LongitudeTrack val) {
            if (!_longitudeTracks.contains(val)) {
                _longitudeTracks.add(val);
            }
        }

        public LongitudeTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addLongitudeTrack(new LongitudeTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public LongitudeTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<LongitudeTrackChunk> _longitudeTrackChunks = new ArrayList<>();

    public List<LongitudeTrackChunk> getLongitudeTrackChunks() {
        return new ArrayList<>(_longitudeTrackChunks);
    }

    public void addLongitudeTrackChunk(@Nonnull LongitudeTrackChunk val) {
        addChunk(val);

        if (!_longitudeTrackChunks.contains(val)) {
            _longitudeTrackChunks.add(val);
        }
    }

    public static class LatitudeTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPLT");

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

    public static class LifespanTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPEL");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _lifespanTracks;
        }

        public static class LifespanTrack extends Track {
            private float _lifespan;

            public float getLifespan() {
                return _lifespan;
            }

            private float _inTan_lifespan;

            public float getInTanLifespan() {
                return _inTan_lifespan;
            }

            private float _outTan_lifespan;

            public float getOutTanLifespan() {
                return _outTan_lifespan;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _lifespan = stream.readFloat32("lifespan");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_lifespan = stream.readFloat32("inTan_lifespan");
                    _outTan_lifespan = stream.readFloat32("outTan_lifespan");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_lifespan);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_lifespan);
                    stream.writeFloat32(_outTan_lifespan);
                }
            }

            public LifespanTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<LifespanTrack> _lifespanTracks = new ArrayList<>();

        public List<LifespanTrack> getLifespanTracks() {
            return new ArrayList<>(_lifespanTracks);
        }

        public void addLifespanTrack(@Nonnull LifespanTrack val) {
            if (!_lifespanTracks.contains(val)) {
                _lifespanTracks.add(val);
            }
        }

        public LifespanTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addLifespanTrack(new LifespanTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public LifespanTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<LifespanTrackChunk> _lifespanTrackChunks = new ArrayList<>();

    public List<LifespanTrackChunk> getLifespanTrackChunks() {
        return new ArrayList<>(_lifespanTrackChunks);
    }

    public void addLifespanTrackChunk(@Nonnull LifespanTrackChunk val) {
        addChunk(val);

        if (!_lifespanTrackChunks.contains(val)) {
            _lifespanTrackChunks.add(val);
        }
    }

    public static class SpeedTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPES");

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
        public final static Id TOKEN = Id.valueOf("KPEV");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _visibilityTracks;
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

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        _node.write(stream);
        stream.writeFloat32(_emissionRate);
        stream.writeFloat32(_gravity);
        stream.writeFloat32(_longitude);
        stream.writeFloat32(_latitude);
        stream.writeBytes(Arrays.copyOf(_fileName.getBytes(), 260));
        stream.writeFloat32(_lifespan);
        stream.writeFloat32(_initialSpeed);

        for (Chunk chunk : getChunks()) {
            chunk.write(stream);
        }
        /*for (EmissionRateTrackChunk emissionRateTrackChunk : getEmissionRateTrackChunks()) {
            emissionRateTrackChunk.write(stream);
        }
        for (GravityTrackChunk gravityTrackChunk : getGravityTrackChunks()) {
            gravityTrackChunk.write(stream);
        }
        for (LongitudeTrackChunk longitudeTrackChunk : getLongitudeTrackChunks()) {
            longitudeTrackChunk.write(stream);
        }
        for (LatitudeTrackChunk latitudeTrackChunk : getLatitudeTrackChunks()) {
            latitudeTrackChunk.write(stream);
        }
        for (LifespanTrackChunk lifespanTrackChunk : getLifespanTrackChunks()) {
            lifespanTrackChunk.write(stream);
        }
        for (SpeedTrackChunk speedTrackChunk : getSpeedTrackChunks()) {
            speedTrackChunk.write(stream);
        }
        for (VisibilityTrackChunk visibilityTrackChunk : getVisibilityTrackChunks()) {
            visibilityTrackChunk.write(stream);
        }*/

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public ParticleEmitter(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _emissionRate = stream.readFloat32("emissionRate");
        _gravity = stream.readFloat32("gravity");
        _longitude = stream.readFloat32("longitude");
        _latitude = stream.readFloat32("latitude");
        _fileName = new String(stream.readBytes(260, "spawnModeLFileName"), StandardCharsets.US_ASCII);
        _lifespan = stream.readFloat32("lifespan");
        _initialSpeed = stream.readFloat32("initialVelocity");

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(EmissionRateTrackChunk.TOKEN, () -> addEmissionRateTrackChunk(new EmissionRateTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(GravityTrackChunk.TOKEN, () -> addGravityTrackChunk(new GravityTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(LongitudeTrackChunk.TOKEN, () -> addLongitudeTrackChunk(new LongitudeTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(LatitudeTrackChunk.TOKEN, () -> addLatitudeTrackChunk(new LatitudeTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(LifespanTrackChunk.TOKEN, () -> addLifespanTrackChunk(new LifespanTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(SpeedTrackChunk.TOKEN, () -> addSpeedTrackChunk(new SpeedTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(VisibilityTrackChunk.TOKEN, () -> addVisibilityTrackChunk(new VisibilityTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));

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
