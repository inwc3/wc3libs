package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
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

public class TexAnim {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private List<Chunk> _chunks = new ArrayList<>();

    public List<Chunk> getChunks() {
        return _chunks;
    }

    private void addChunk(@Nonnull Chunk val) {
        _chunks.add(val);
    }

    public static class TranslationTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KTAT");

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

        private List<TranslationTrackChunk.TranslationTrack> _translationTracks = new ArrayList<>();

        public List<TranslationTrackChunk.TranslationTrack> getTranslationTracks() {
            return new ArrayList<>(_translationTracks);
        }

        public void addTranslationTrack(@Nonnull TranslationTrackChunk.TranslationTrack val) {
            if (!_translationTracks.contains(val)) {
                _translationTracks.add(val);
            }
        }

        public TranslationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addTranslationTrack(new TranslationTrackChunk.TranslationTrack(stream, getInterpolationType(), format));

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
        public final static Id TOKEN = Id.valueOf("KTAR");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _rotationTracks;
        }

        public static class RotationTrack extends Track {
            private Coords4DF _rotation;

            public Coords4DF getRotation() {
                return _rotation;
            }

            private Coords4DF _inTan_rotation;

            public Coords4DF getInTanRotation() {
                return _inTan_rotation;
            }

            private Coords4DF _outTan_rotation;

            public Coords4DF getOutTanRotation() {
                return _outTan_rotation;
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

        private List<RotationTrackChunk.RotationTrack> _rotationTracks = new ArrayList<>();

        public List<RotationTrackChunk.RotationTrack> getTranslationTracks() {
            return new ArrayList<>(_rotationTracks);
        }

        public void addTranslationTrack(@Nonnull RotationTrackChunk.RotationTrack val) {
            if (!_rotationTracks.contains(val)) {
                _rotationTracks.add(val);
            }
        }

        public RotationTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addTranslationTrack(new RotationTrackChunk.RotationTrack(stream, getInterpolationType(), format));

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

    public static class ScalingTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KTAS");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _scalingTracks;
        }

        public static class ScalingTrack extends Track {
            private Coords3DF _scaling;

            public Coords3DF getScaling() {
                return _scaling;
            }

            private Coords3DF _inTan_scaling;

            public Coords3DF getInTanScaling() {
                return _inTan_scaling;
            }

            private Coords3DF _outTan_scaling;

            public Coords3DF getOutTanScaling() {
                return _outTan_scaling;
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

        private List<ScalingTrackChunk.ScalingTrack> _scalingTracks = new ArrayList<>();

        public List<ScalingTrackChunk.ScalingTrack> getScalingTracks() {
            return new ArrayList<>(_scalingTracks);
        }

        public void addScalingTrack(@Nonnull ScalingTrackChunk.ScalingTrack val) {
            if (!_scalingTracks.contains(val)) {
                _scalingTracks.add(val);
            }
        }

        public ScalingTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addScalingTrack(new ScalingTrackChunk.ScalingTrack(stream, getInterpolationType(), format));

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
        addChunk(val);

        if (!_scalingTrackChunks.contains(val)) {
            _scalingTrackChunks.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt32(_inclusiveSize);

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
    }

    public TexAnim(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");

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
}
