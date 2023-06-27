package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords4DF;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Camera extends MDXObject {
    private long _inclusiveSize = 0;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private String _name = "unset";

    @Nonnull
    public String getName() {
        return _name;
    }

    public void setName(@Nonnull String name) {
        _name = name;
    }

    private Coords3DF _pos;

    @Nonnull
    public Coords3DF getPos() {
        return _pos;
    }

    public void setPos(@Nonnull Coords3DF pos) {
        _pos = pos;
    }

    private float _fieldOfView = 0F;

    public float getFieldOfView() {
        return _fieldOfView;
    }

    public void setFieldOfView(float fieldOfView) {
        _fieldOfView = fieldOfView;
    }

    private float _farClippingPlane = 0F;

    public float getFarClippingPlane() {
        return _farClippingPlane;
    }

    public void setFarClippingPlane(float farClippingPlane) {
        _farClippingPlane = farClippingPlane;
    }

    private float _nearClippingPlane = 0F;

    public float getNearClippingPlane() {
        return _nearClippingPlane;
    }

    public void setNearClippingPlane(float nearClippingPlane) {
        _nearClippingPlane = nearClippingPlane;
    }

    private Coords3DF _targetPos;

    public Coords3DF getTargetPos() {
        return _targetPos;
    }

    public void setTargetPos(@Nonnull Coords3DF targetPos) {
        _targetPos = targetPos;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    public static class TranslationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KCTR");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _translationTracks;
        }

        public static class TranslationTrack extends Track {
            private Coords3DF _translation;

            @Nonnull
            public Coords3DF getTranslation() {
                return _translation;
            }

            public void setTranslation(@Nonnull Coords3DF translation) {
                _translation = translation;
            }

            private Coords3DF _inTan_translation;

            @Nonnull
            public Coords3DF getInTanTranslation() {
                return _inTan_translation;
            }

            public void setInTanTranslation(@Nonnull Coords3DF translation) {
                _inTan_translation = translation;
            }

            private Coords3DF _outTan_translation;

            @Nonnull
            public Coords3DF getOutTanTranslation() {
                return _outTan_translation;
            }

            public void setOutTanTranslation(@Nonnull Coords3DF translation) {
                _outTan_translation = translation;
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

        private LinkedHashSet<TranslationTrack> _translationTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<TranslationTrack> getTranslationTracks() {
            return _translationTracks;
        }

        public TranslationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _translationTracks.add(new TranslationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public TranslationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<TranslationTrackChunk> _translationTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<TranslationTrackChunk> getTranslationTrackChunks() {
        return _translationTrackChunks;
    }

    public void addTranslationTrackChunk(@Nonnull TranslationTrackChunk val) {
        _chunks.add(val);

        _translationTrackChunks.add(val);
    }

    public static class RotationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KCRL");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _rotationTracks;
        }

        public static class RotationTrack extends Track {
            private long _rotation;

            public long getRotation() {
                return _rotation;
            }

            public void setRotation(long rotation) {
                _rotation = rotation;
            }

            private long _inTan_rotation;

            public long getInTanRotation() {
                return _inTan_rotation;
            }

            public void setInTanRotation(long rotation) {
                _rotation = rotation;
            }

            private long _outTan_rotation;

            public long getOutTanRotation() {
                return _outTan_rotation;
            }

            public void setOutTanRotation(long rotation) {
                _outTan_rotation = rotation;
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

        private final LinkedHashSet<RotationTrack> _rotationTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<RotationTrack> getRotationTracks() {
            return _rotationTracks;
        }

        public RotationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _rotationTracks.add(new RotationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public RotationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<RotationTrackChunk> _rotationTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<RotationTrackChunk> getRotationTrackChunks() {
        return _rotationTrackChunks;
    }

    public void addRotationTrackChunk(@Nonnull RotationTrackChunk val) {
        _chunks.add(val);

        _rotationTrackChunks.add(val);
    }

    public static class TargetTranslationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KTTR");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _targetTranslationTracks;
        }

        public static class TargetTranslationTrack extends Track {
            private Coords3DF _targetTranslation;

            @Nonnull
            public Coords3DF getTargetTranslation() {
                return _targetTranslation;
            }

            public void setTargetTranslation(@Nonnull Coords3DF targetTranslation) {
                _targetTranslation = targetTranslation;
            }

            private Coords3DF _inTan_targetTranslation;

            @Nonnull
            public Coords3DF getInTanTargetTranslation() {
                return _inTan_targetTranslation;
            }

            public void setInTanTargetTranslation(@Nonnull Coords3DF targetTranslation) {
                _targetTranslation = targetTranslation;
            }

            private Coords3DF _outTan_targetTranslation;

            @Nonnull
            public Coords3DF getOutTanTargetTranslation() {
                return _outTan_targetTranslation;
            }

            public void setOutTanTargetTranslation(@Nonnull Coords3DF targetTranslation) {
                _outTan_targetTranslation = targetTranslation;
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

        private final LinkedHashSet<TargetTranslationTrack> _targetTranslationTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<TargetTranslationTrack> getTargetTranslationTracks() {
            return _targetTranslationTracks;
        }

        public TargetTranslationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _targetTranslationTracks.add(new TargetTranslationTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public TargetTranslationTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private LinkedHashSet<TargetTranslationTrackChunk> _targetTranslationTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<TargetTranslationTrackChunk> getTargetTranslationTrackChunks() {
        return _targetTranslationTrackChunks;
    }

    public void addTargetTranslationTrackChunk(@Nonnull TargetTranslationTrackChunk val) {
        _chunks.add(val);

        _targetTranslationTrackChunks.add(val);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        stream.writeBytes(Arrays.copyOf(_name.getBytes(), 80));

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

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Camera(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _name = new String(stream.readBytes(80, "name"), StandardCharsets.US_ASCII);
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

    public Camera() {
        _pos = new Coords3DF(0F, 0F, 0F);
        _targetPos = new Coords3DF(0F, 0F, 0F);
    }
}
