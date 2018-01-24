package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ParticleEmitter extends MDXObject {
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

    private float _emissionRate = 0F;

    public float getEmissionRate() {
        return _emissionRate;
    }

    public void setEmissionRate(float emissionRate) {
        _emissionRate = emissionRate;
    }

    private float _gravity = 0F;

    public float getGravity() {
        return _gravity;
    }

    public void setGravity(float gravity) {
        _gravity = gravity;
    }

    private float _longitude = 0F;

    public float getLongitude() {
        return _longitude;
    }

    public void setLongitude(float longitude) {
        _longitude = longitude;
    }

    private float _latitude = 0F;

    public float getLatitude() {
        return _latitude;
    }

    public void setLatitude(float latitude) {
        _latitude = latitude;
    }

    private String _fileName = "unset";

    public String getFileName() {
        return _fileName;
    }

    private float _lifespan = 0F;

    public float getLifespan() {
        return _lifespan;
    }

    public void setLifespan(float lifespan) {
        _lifespan = lifespan;
    }

    private float _initialSpeed = 0F;

    public float getInitialSpeed() {
        return _initialSpeed;
    }

    public void setInitialSpeed(float initialSpeed) {
        _initialSpeed = initialSpeed;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    public static class EmissionRateTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPEE");

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
        public final static Id TOKEN = Id.valueOf("KPEG");

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

    public static class LongitudeTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPLN");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _longitudeTracks;
        }

        public static class LongitudeTrack extends Track {
            private float _longitude;

            public float getLongitude() {
                return _longitude;
            }

            public void setLongitude(float longitude) {
                _longitude = longitude;
            }

            private float _inTan_longitude;

            public float getInTanLongitude() {
                return _inTan_longitude;
            }

            public void setInTanLongitude(float longitude) {
                _inTan_longitude = longitude;
            }

            private float _outTan_longitude;

            public float getOutTanLongitude() {
                return _outTan_longitude;
            }

            public void setOutTanLongitude(float longitude) {
                _outTan_longitude = longitude;
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

        private final LinkedHashSet<LongitudeTrack> _longitudeTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<LongitudeTrack> getLongitudeTracks() {
            return _longitudeTracks;
        }

        public LongitudeTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _longitudeTracks.add(new LongitudeTrack(stream, getInterpolationType(), format));

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
        _chunks.add(val);

        _longitudeTrackChunks.add(val);
    }

    public static class LatitudeTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPLT");

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

    public static class LifespanTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPEL");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _lifespanTracks;
        }

        public static class LifespanTrack extends Track {
            private float _lifespan;

            public float getLifespan() {
                return _lifespan;
            }

            public void setLifespan(float lifespan) {
                _lifespan = lifespan;
            }

            private float _inTan_lifespan;

            public float getInTanLifespan() {
                return _inTan_lifespan;
            }

            public void setInTanLifespan(float lifespan) {
                _inTan_lifespan = lifespan;
            }

            private float _outTan_lifespan;

            public float getOutTanLifespan() {
                return _outTan_lifespan;
            }

            public void setOutTanLifespan(float lifespan) {
                _outTan_lifespan = lifespan;
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

        private final LinkedHashSet<LifespanTrack> _lifespanTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<LifespanTrack> getLifespanTracks() {
            return _lifespanTracks;
        }

        public LifespanTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _lifespanTracks.add(new LifespanTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public LifespanTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<LifespanTrackChunk> _lifespanTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<LifespanTrackChunk> getLifespanTrackChunks() {
        return _lifespanTrackChunks;
    }

    public void addLifespanTrackChunk(@Nonnull LifespanTrackChunk val) {
        _chunks.add(val);

        _lifespanTrackChunks.add(val);
    }

    public static class SpeedTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KPES");

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
        public final static Id TOKEN = Id.valueOf("KPEV");

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

    public ParticleEmitter() {
        _node = new Node();
    }
}
