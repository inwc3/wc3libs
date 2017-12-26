package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private String _name;

    public String getName() {
        return _name;
    }

    private long _objectId;

    private long getObjectId() {
        return _objectId;
    }

    private long _parentId;

    public long getParentId() {
        return _parentId;
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

    public static class TranslationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KMTA");

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
                _translation = new Coords3DF(stream.readFloat8("translationX"), stream.readFloat8("translationY"), stream.readFloat8("translationZ"));

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_translation = new Coords3DF(stream.readFloat8("inTan_translationX"), stream.readFloat8("inTan_translationY"), stream.readFloat8("inTan_translationZ"));
                    _outTan_translation = new Coords3DF(stream.readFloat8("outTan_translationX"), stream.readFloat8("outTan_translationY"), stream.readFloat8("outTan_translationZ"));
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
        if (!_translationTrackChunks.contains(val)) {
            _translationTrackChunks.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        stream.writeBytes(Arrays.copyOf(_name.getBytes(), 80));
        stream.writeUInt32(_objectId);
        stream.writeUInt32(_parentId);
        stream.writeUInt32(_flags);

        //TODO: KGTR, KGRT, KGSC
    }

    public Node(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _name = new String(stream.readBytes(80), StandardCharsets.US_ASCII);
        _objectId = stream.readUInt32("objectId");
        _parentId = stream.readUInt32("parentId");
        _flags = stream.readUInt32("flags");

        //TODO: KGTR, KGRT, KGSC
    }
}
