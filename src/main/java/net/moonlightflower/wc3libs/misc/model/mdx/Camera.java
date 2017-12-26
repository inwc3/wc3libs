package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords4DF;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Camera {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private String _name;

    public String getName() {
        return _name;
    }

    private Coords3DF _pos;

    public Coords3DF getPos() {
        return _pos;
    }

    private float _fieldOfView;

    public float getFieldOfView() {
        return _fieldOfView;
    }

    private float _farClippingPlane;

    public float getFarClippingPlane() {
        return _farClippingPlane;
    }

    private float _nearClippingPlane;

    public float getNearClippingPlane() {
        return _nearClippingPlane;
    }

    private Coords3DF _targetPos;

    public Coords3DF getTargetPos() {
        return _targetPos;
    }

    private List<Chunk> _chunks = new ArrayList<>();

    public List<Chunk> getChunks() {
        return _chunks;
    }

    private void addChunk(@Nonnull Chunk val) {
        _chunks.add(val);
    }

    public static class TranslationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KCTR");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _translationTracks;
        }

        public static class TranslationTrack extends Track {
            private Coords3DF _translation;

            public Coords3DF getTranslation() {
                return _translation;
            }

            private Coords3DF _inTan_translation;

            public Coords3DF getInTanTranslation() {
                return _inTan_translation;
            }

            private Coords3DF _outTan_translation;

            public Coords3DF getOutTanTranslation() {
                return _outTan_translation;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _translation = new Coords3DF(stream.readFloat32("translationX"), stream.readFloat32("translationY"), stream.readFloat32("translationZ"));

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_translation = new Coords3DF(stream.readFloat32("inTan_translationX"), stream.readFloat32("inTan_translationY"), stream.readFloat32("inTan_translationZ"));
                    _outTan_translation = new Coords3DF(stream.readFloat32("outTan_translationX"), stream.readFloat32("outTan_translationY"), stream.readFloat32("outTan_translationZ"));
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_translation.getX());
                stream.writeFloat32(_translation.getY());
                stream.writeFloat32(_translation.getZ());

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_translation.getX());
                    stream.writeFloat32(_inTan_translation.getY());
                    stream.writeFloat32(_inTan_translation.getZ());

                    stream.writeFloat32(_outTan_translation.getX());
                    stream.writeFloat32(_outTan_translation.getY());
                    stream.writeFloat32(_outTan_translation.getZ());
                }
            }

            public TranslationTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<TranslationTrack> _translationTracks = new ArrayList<>();

        public List<TranslationTrack> getTranslationTracks() {
            return new ArrayList<>(_translationTracks);
        }

        public void addTranslationTrack(@Nonnull TranslationTrack val) {
            if (!_translationTracks.contains(val)) {
                _translationTracks.add(val);
            }
        }

        public TranslationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addTranslationTrack(new TranslationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public TranslationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<TranslationTrackChunk> _translationTrackChunks = new ArrayList<>();

    public List<TranslationTrackChunk> getTranslationTrackChunks() {
        return new ArrayList<>(_translationTrackChunks);
    }

    public void addTranslationTrackChunk(@Nonnull TranslationTrackChunk val) {
        addChunk(val);

        if (!_translationTrackChunks.contains(val)) {
            _translationTrackChunks.add(val);
        }
    }

    public static class RotationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KCRL");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _rotationTracks;
        }

        public static class RotationTrack extends Track {
            private long _rotation;

            public long getRotation() {
                return _rotation;
            }

            private long _inTan_rotation;

            public long getInTanRotation() {
                return _inTan_rotation;
            }

            private long _outTan_rotation;

            public long getOutTanRotation() {
                return _outTan_rotation;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _rotation = stream.readUInt32("rotation");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_rotation = stream.readUInt32("inTan_rotation");
                    _outTan_rotation = stream.readUInt32("outTan_rotation");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeUInt32(_rotation);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeUInt32(_inTan_rotation);

                    stream.writeUInt32(_outTan_rotation);
                }
            }

            public RotationTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<RotationTrack> _rotationTracks = new ArrayList<>();

        public List<RotationTrack> getTranslationTracks() {
            return new ArrayList<>(_rotationTracks);
        }

        public void addTranslationTrack(@Nonnull RotationTrack val) {
            if (!_rotationTracks.contains(val)) {
                _rotationTracks.add(val);
            }
        }

        public RotationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addTranslationTrack(new RotationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public RotationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<RotationTrackChunk> _rotationTrackChunks = new ArrayList<>();

    public List<RotationTrackChunk> getRotationTrackChunks() {
        return new ArrayList<>(_rotationTrackChunks);
    }

    public void addRotationTrackChunk(@Nonnull RotationTrackChunk val) {
        addChunk(val);

        if (!_rotationTrackChunks.contains(val)) {
            _rotationTrackChunks.add(val);
        }
    }

    public static class TargetTranslationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KTTR");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _targetTranslationTracks;
        }

        public static class TargetTranslationTrack extends Track {
            private Coords3DF _targetTranslation;

            public Coords3DF getTargetTranslation() {
                return _targetTranslation;
            }

            private Coords3DF _inTan_targetTranslation;

            public Coords3DF getInTanTargetTranslation() {
                return _inTan_targetTranslation;
            }

            private Coords3DF _outTan_targetTranslation;

            public Coords3DF getOutTanTargetTranslation() {
                return _outTan_targetTranslation;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _targetTranslation = new Coords3DF(stream.readFloat32("targetTranslationX"), stream.readFloat32("targetTranslationY"), stream.readFloat32("targetTranslationZ"));

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_targetTranslation = new Coords3DF(stream.readFloat32("inTan_targetTranslationX"), stream.readFloat32("inTan_targetTranslationY"), stream.readFloat32("inTan_targetTranslationZ"));
                    _outTan_targetTranslation = new Coords3DF(stream.readFloat32("outTan_targetTranslationX"), stream.readFloat32("outTan_targetTranslationY"), stream.readFloat32("outTan_targetTranslationZ"));
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_targetTranslation.getX());
                stream.writeFloat32(_targetTranslation.getY());
                stream.writeFloat32(_targetTranslation.getZ());

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_targetTranslation.getX());
                    stream.writeFloat32(_inTan_targetTranslation.getY());
                    stream.writeFloat32(_inTan_targetTranslation.getZ());

                    stream.writeFloat32(_outTan_targetTranslation.getX());
                    stream.writeFloat32(_outTan_targetTranslation.getY());
                    stream.writeFloat32(_outTan_targetTranslation.getZ());
                }
            }

            public TargetTranslationTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<TargetTranslationTrack> _targetTranslationTracks = new ArrayList<>();

        public List<TargetTranslationTrack> getTargetTranslationTracks() {
            return new ArrayList<>(_targetTranslationTracks);
        }

        public void addTargetTranslationTrack(@Nonnull TargetTranslationTrack val) {
            if (!_targetTranslationTracks.contains(val)) {
                _targetTranslationTracks.add(val);
            }
        }

        public TargetTranslationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addTargetTranslationTrack(new TargetTranslationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public TargetTranslationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<TargetTranslationTrackChunk> _targetTranslationTrackChunks = new ArrayList<>();

    public List<TargetTranslationTrackChunk> getTargetTranslationTrackChunks() {
        return new ArrayList<>(_targetTranslationTrackChunks);
    }

    public void addTargetTranslationTrackChunk(@Nonnull TargetTranslationTrackChunk val) {
        addChunk(val);

        if (!_targetTranslationTrackChunks.contains(val)) {
            _targetTranslationTrackChunks.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeUInt32(_inclusiveSize);
        stream.writeBytes(Arrays.copyOf(_name.getBytes(), 60));

        stream.writeFloat32(_pos.getX());
        stream.writeFloat32(_pos.getY());
        stream.writeFloat32(_pos.getZ());

        stream.writeFloat32(_fieldOfView);
        stream.writeFloat32(_farClippingPlane);
        stream.writeFloat32(_nearClippingPlane);

        stream.writeFloat32(_targetPos.getX());
        stream.writeFloat32(_targetPos.getY());
        stream.writeFloat32(_targetPos.getZ());

        for (Chunk chunk : getChunks()) {
            chunk.write(stream);
        }
        /*for (TranslationTrackChunk translationTrackChunk : getTranslationTrackChunks()) {
            translationTrackChunk.write(stream);
        }
        for (RotationTrackChunk rotationTrackChunk : getRotationTrackChunks()) {
            rotationTrackChunk.write(stream);
        }
        for (TargetTranslationTrackChunk targetTranslationTrackChunk : getTargetTranslationTrackChunks()) {
            targetTranslationTrackChunk.write(stream);
        }*/
    }

    public Camera(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _name = new String(stream.readBytes(60, "name"), StandardCharsets.US_ASCII);
        _pos = new Coords3DF(stream.readFloat32("x"), stream.readFloat32("y"), stream.readFloat32("z"));
        _fieldOfView = stream.readFloat32("fieldOfView");
        _farClippingPlane = stream.readFloat32("farClippingPlane");
        _nearClippingPlane = stream.readFloat32("nearClippingPlane");
        _targetPos = new Coords3DF(stream.readFloat32("targetX"), stream.readFloat32("targetY"), stream.readFloat32("targetZ"));

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(TranslationTrackChunk.TOKEN, () -> addTranslationTrackChunk(new TranslationTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(RotationTrackChunk.TOKEN, () -> addRotationTrackChunk(new RotationTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(TargetTranslationTrackChunk.TOKEN, () -> addTargetTranslationTrackChunk(new TargetTranslationTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));

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
