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

public class Node extends MDXObject {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private String _name = "unset";

    @Nonnull
    public String getName() {
        return _name;
    }

    public void SetName(@Nonnull String name) {
        _name = name;
    }

    private long _objectId;

    private long getObjectId() {
        return _objectId;
    }

    public void setObjectId(long objectId) {
        _objectId = objectId;
    }

    private long _parentId;

    public long getParentId() {
        return _parentId;
    }

    public void setParentId(long parentId) {
        _parentId = parentId;
    }

    public enum Flag {
        HELPER(0x0),
        DONT_INHERIT_TRANSLATION(0x1),
        DONT_INHERIT_ROTATION(0x2),
        DONT_INHERIT_SCALING(0x4),
        BILLBOARDED(0x8),
        BILLBOARDED_LOCK_X(0x10),
        BILLBOARDED_LOCK_Y(0x20),
        BILLBOARDED_LOCK_Z(0x40),
        CAMERA_ANCHORED(0x80),
        BONE(0x100),
        LIGHT(0x200),
        EVENT_OBJECT(0x400),
        ATTACHMENT(0x800),
        PARTICLE_EMITTER(0x1000),
        COLLISION_SHAPE(0x2000),
        RIBBON_EMITTER(0x4000),
        EMITTER_USES_MDL_OR_UNSHADED(0x8000),
        EMITTER_USES_TGA_OR_SORT_PRIMITIVES_FAR_Z(0x10000),
        LINE_EMITTER(0x20000),
        UNFOGGED(0x40000),
        MODEL_SPACE(0x80000),
        XY_QUAD(0x100000);

        private int _index;

        public int getIndex() {
            return _index;
        }

        Flag(int index) {
            _index = index;
        }
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    public void setFlags(long flags) {
        _flags = flags;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    public static class TranslationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KGTR");

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

        private final LinkedHashSet<TranslationTrack> _translationTracks = new ObservableLinkedHashSet<>();

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
        public final static Id TOKEN = Id.valueOf("KGRT");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _rotationTracks;
        }

        public static class RotationTrack extends Track {
            private Coords4DF _rotation;

            @Nonnull
            public Coords4DF getRotation() {
                return _rotation;
            }

            public void setRotation(@Nonnull Coords4DF rotation) {
                _rotation = rotation;
            }

            private Coords4DF _inTan_rotation;

            @Nonnull
            public Coords4DF getInTanRotation() {
                return _inTan_rotation;
            }

            public void setInTanRotation(@Nonnull Coords4DF rotation) {
                _inTan_rotation = rotation;
            }

            private Coords4DF _outTan_rotation;

            @Nonnull
            public Coords4DF getOutTanRotation() {
                return _outTan_rotation;
            }

            public void setOutTanRotation(@Nonnull Coords4DF rotation) {
                _outTan_rotation = rotation;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _rotation = new Coords4DF(stream.readFloat32("rotationX"), stream.readFloat32("rotationY"), stream.readFloat32("rotationZ"), stream.readFloat32("rotationAlpha"));

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_rotation = new Coords4DF(stream.readFloat32("inTan_rotationX"), stream.readFloat32("inTan_rotationY"), stream.readFloat32("inTan_rotationZ"), stream.readFloat32("inTan_rotationAlpha"));
                    _outTan_rotation = new Coords4DF(stream.readFloat32("outTan_rotationX"), stream.readFloat32("outTan_rotationY"), stream.readFloat32("outTan_rotationZ"), stream.readFloat32("outTan_rotationAlpha"));
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_rotation.getX());
                stream.writeFloat32(_rotation.getY());
                stream.writeFloat32(_rotation.getZ());
                stream.writeFloat32(_rotation.getA());

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_rotation.getX());
                    stream.writeFloat32(_inTan_rotation.getY());
                    stream.writeFloat32(_inTan_rotation.getZ());
                    stream.writeFloat32(_inTan_rotation.getA());

                    stream.writeFloat32(_outTan_rotation.getX());
                    stream.writeFloat32(_outTan_rotation.getY());
                    stream.writeFloat32(_outTan_rotation.getZ());
                    stream.writeFloat32(_outTan_rotation.getA());
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

    public static class ScalingTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KGSC");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _scalingTracks;
        }

        public static class ScalingTrack extends Track {
            private Coords3DF _scaling;

            @Nonnull
            public Coords3DF getScaling() {
                return _scaling;
            }

            public void setScaling(@Nonnull Coords3DF scaling) {
                _scaling = scaling;
            }

            private Coords3DF _inTan_scaling;

            @Nonnull
            public Coords3DF getInTanScaling() {
                return _inTan_scaling;
            }

            public void setInTanScaling(@Nonnull Coords3DF scaling) {
                _inTan_scaling = scaling;
            }

            private Coords3DF _outTan_scaling;

            @Nonnull
            public Coords3DF getOutTanScaling() {
                return _outTan_scaling;
            }

            public void setOutTanScaling(@Nonnull Coords3DF scaling) {
                _outTan_scaling = scaling;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _scaling = new Coords3DF(stream.readFloat32("scalingX"), stream.readFloat32("scalingY"), stream.readFloat32("scalingZ"));

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_scaling = new Coords3DF(stream.readFloat32("inTan_scalingX"), stream.readFloat32("inTan_scalingY"), stream.readFloat32("inTan_scalingZ"));
                    _outTan_scaling = new Coords3DF(stream.readFloat32("outTan_scalingX"), stream.readFloat32("outTan_scalingY"), stream.readFloat32("outTan_scalingZ"));
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_scaling.getX());
                stream.writeFloat32(_scaling.getY());
                stream.writeFloat32(_scaling.getZ());

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_scaling.getX());
                    stream.writeFloat32(_inTan_scaling.getY());
                    stream.writeFloat32(_inTan_scaling.getZ());

                    stream.writeFloat32(_outTan_scaling.getX());
                    stream.writeFloat32(_outTan_scaling.getY());
                    stream.writeFloat32(_outTan_scaling.getZ());
                }
            }

            public ScalingTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<ScalingTrack> _scalingTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<ScalingTrack> getScalingTracks() {
            return _scalingTracks;
        }

        public ScalingTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _scalingTracks.add(new ScalingTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public ScalingTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<ScalingTrackChunk> _scalingTrackChunks = new ArrayList<>();

    public List<ScalingTrackChunk> getScalingTrackChunks() {
        return new ArrayList<>(_scalingTrackChunks);
    }

    public void addScalingTrackChunk(@Nonnull ScalingTrackChunk val) {
        _chunks.add(val);

        _scalingTrackChunks.add(val);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        stream.writeBytes(Arrays.copyOf(_name.getBytes(), 80));
        stream.writeUInt32(_objectId);
        stream.writeUInt32(_parentId);
        stream.writeUInt32(_flags);

        for (Chunk chunk : getChunks()) {
            chunk.write(stream);
        }
        /*for (TranslationTrackChunk translationTrackChunk : getTranslationTrackChunks()) {
            translationTrackChunk.write(stream);
        }
        for (RotationTrackChunk rotationTrackChunk : getRotationTrackChunks()) {
            rotationTrackChunk.write(stream);
        }
        for (ScalingTrackChunk scalingTrackChunk : getScalingTrackChunks()) {
            scalingTrackChunk.write(stream);
        }*/

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Node(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _name = new String(stream.readBytes(80), StandardCharsets.US_ASCII);
        _objectId = stream.readUInt32("objectId");
        _parentId = stream.readUInt32("parentId");
        _flags = stream.readUInt32("flags");

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(TranslationTrackChunk.TOKEN, () -> addTranslationTrackChunk(new TranslationTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(RotationTrackChunk.TOKEN, () -> addRotationTrackChunk(new RotationTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(ScalingTrackChunk.TOKEN, () -> addScalingTrackChunk(new ScalingTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));

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

    public Node() {
    }
}
