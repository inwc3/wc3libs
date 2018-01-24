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

public class Light extends MDXObject {
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

    public enum Type {
        OMNI,
        DIRECTIONAL,
        AMBIENT
    }

    private Type _type = Type.OMNI;

    @Nonnull
    public Type getType() {
        return _type;
    }

    public void setType(@Nonnull Type type) {
        _type = type;
    }

    private float _attenuationStart = 0F;

    public float getAttenuationStart() {
        return _attenuationStart;
    }

    public void setAttenuationStart(float attenuationStart) {
        _attenuationStart = attenuationStart;
    }

    private float _attenuationEnd = 0F;

    public float getAttenuationEnd() {
        return _attenuationEnd;
    }

    public void setAttenuationEnd(float attenuationEnd) {
        _attenuationEnd = attenuationEnd;
    }

    private Color _color;

    @Nonnull
    public Color getColor() {
        return _color;
    }

    public void setColor(@Nonnull Color color) {
        _color = color;
    }

    private float _intensity = 0F;

    public float getIntensity() {
        return _intensity;
    }

    public void setIntensity(float intensity) {
        _intensity = intensity;
    }

    private Color _ambientColor;

    @Nonnull
    public Color getAmbientColor() {
        return _ambientColor;
    }

    public void setAmbientColor(@Nonnull Color ambientColor) {
        _ambientColor = ambientColor;
    }

    private float _ambientIntensity;

    public float getAmbientIntensity() {
        return _ambientIntensity;
    }

    public void setAmbientIntensity(float ambientIntensity) {
        _ambientIntensity = ambientIntensity;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    public static class AttenuationStartTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAS");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _attenuationStartTracks;
        }

        public static class AttenuationStartTrack extends Track {
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

            public AttenuationStartTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<AttenuationStartTrack> _attenuationStartTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<AttenuationStartTrack> getAttenuationStartTracks() {
            return _attenuationStartTracks;
        }

        public AttenuationStartTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _attenuationStartTracks.add(new AttenuationStartTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AttenuationStartTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<AttenuationStartTrackChunk> _attenuationStartTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<AttenuationStartTrackChunk> getAttenuationStartTrackChunks() {
        return _attenuationStartTrackChunks;
    }

    public void addAttenuationStartTrackChunk(@Nonnull AttenuationStartTrackChunk val) {
        _chunks.add(val);

        _attenuationStartTrackChunks.add(val);
    }

    public static class AttenuationEndTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAE");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _attenuationEndTracks;
        }

        public static class AttenuationEndTrack extends Track {
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

            public AttenuationEndTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<AttenuationEndTrack> _attenuationEndTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<AttenuationEndTrack> getAttenuationEndTracks() {
            return _attenuationEndTracks;
        }

        public AttenuationEndTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _attenuationEndTracks.add(new AttenuationEndTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AttenuationEndTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<AttenuationEndTrackChunk> _attenuationEndTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<AttenuationEndTrackChunk> getAttenuationEndTrackChunks() {
        return _attenuationEndTrackChunks;
    }

    public void addAttenuationEndTrackChunk(@Nonnull AttenuationEndTrackChunk val) {
        _chunks.add(val);

        _attenuationEndTrackChunks.add(val);
    }

    public static class ColorTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAC");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _colorTracks;
        }

        public static class ColorTrack extends Track {
            private Color _color;

            @Nonnull
            public Color getColor() {
                return _color;
            }

            public void setColor(@Nonnull Color color) {
                _color = color;
            }

            private Color _inTan_color;

            @Nonnull
            public Color getInTanColor() {
                return _inTan_color;
            }

            public void setInTanColor(@Nonnull Color color) {
                _inTan_color = color;
            }

            private Color _outTan_color;

            @Nonnull
            public Color getOutTanColor() {
                return _outTan_color;
            }

            public void setOutTanColor(@Nonnull Color color) {
                _outTan_color = color;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _color = Color.fromBGR255((int) (stream.readFloat32("colorBlue") * 255), (int) (stream.readFloat32("colorGreen") * 255), (int) (stream.readFloat32("colorRed") * 255));

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_color = Color.fromBGR255((int) (stream.readFloat32("inTan_colorBlue") * 255), (int) (stream.readFloat32("inTan_colorGreen") * 255), (int) (stream.readFloat32("inTan_colorRed") * 255));
                    _outTan_color = Color.fromBGR255((int) (stream.readFloat32("outTan_colorBlue") * 255), (int) (stream.readFloat32("outTan_colorGreen") * 255), (int) (stream.readFloat32("outTan_colorRed") * 255));
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_color.getBlue255() / 255F);
                stream.writeFloat32(_color.getGreen255() / 255F);
                stream.writeFloat32(_color.getRed255() / 255F);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_color.getBlue255() / 255F);
                    stream.writeFloat32(_inTan_color.getGreen255() / 255F);
                    stream.writeFloat32(_inTan_color.getRed255() / 255F);

                    stream.writeFloat32(_outTan_color.getBlue255() / 255F);
                    stream.writeFloat32(_outTan_color.getGreen255() / 255F);
                    stream.writeFloat32(_outTan_color.getRed255() / 255F);
                }
            }

            public ColorTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<ColorTrack> _colorTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<ColorTrackChunk.ColorTrack> getAlphaTracks() {
            return _colorTracks;
        }

        public ColorTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _colorTracks.add(new ColorTrackChunk.ColorTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public ColorTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<ColorTrackChunk> _colorTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<ColorTrackChunk> getColorTrackChunks() {
        return _colorTrackChunks;
    }

    public void addColorTrackChunk(@Nonnull ColorTrackChunk val) {
        _chunks.add(val);

        _colorTrackChunks.add(val);
    }

    public static class IntensityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAI");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _intensityTracks;
        }

        public static class IntensityTrack extends Track {
            private float _intensity;

            public float getIntensity() {
                return _intensity;
            }

            public void setIntensity(float intensity) {
                _intensity = intensity;
            }

            private float _inTan_intensity;

            public float getInTanIntensity() {
                return _inTan_intensity;
            }

            public void setInTanIntensity(float intensity) {
                _inTan_intensity = intensity;
            }

            private float _outTan_intensity;

            public float getOutTanIntensity() {
                return _outTan_intensity;
            }

            public void setOutTanIntensity(float intensity) {
                _outTan_intensity = intensity;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _intensity = stream.readFloat32("intensity");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_intensity = stream.readFloat32("inTan_intensity");
                    _outTan_intensity = stream.readFloat32("outTan_intensity");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_intensity);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_intensity);
                    stream.writeFloat32(_outTan_intensity);
                }
            }

            public IntensityTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<IntensityTrack> _intensityTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<IntensityTrack> getIntensityTracks() {
            return _intensityTracks;
        }

        public IntensityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _intensityTracks.add(new IntensityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public IntensityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<IntensityTrackChunk> _intensityTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<IntensityTrackChunk> getIntensityTrackChunks() {
        return _intensityTrackChunks;
    }

    public void addIntensityTrackChunk(@Nonnull IntensityTrackChunk val) {
        _chunks.add(val);

        _intensityTrackChunks.add(val);
    }

    public static class AmbientIntensityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLBI");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _ambientIntensityTracks;
        }

        public static class AmbientIntensityTrack extends Track {
            private float _ambientIntensity;

            public float getAmbientIntensity() {
                return _ambientIntensity;
            }

            public void setAmbientIntensity(float ambientIntensity) {
                _ambientIntensity = ambientIntensity;
            }

            private float _inTan_ambientIntensity;

            public float getInTanAmbientIntensity() {
                return _inTan_ambientIntensity;
            }

            public void setInTanAmbientIntensity(float ambientIntensity) {
                _inTan_ambientIntensity = ambientIntensity;
            }

            private float _outTan_ambientIntensity;

            public float getOutTanAmbientIntensity() {
                return _outTan_ambientIntensity;
            }

            public void setOutTanAmbientIntensity(float ambientIntensity) {
                _outTan_ambientIntensity = ambientIntensity;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _ambientIntensity = stream.readFloat32("ambientIntensity");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_ambientIntensity = stream.readFloat32("inTan_ambientIntensity");
                    _outTan_ambientIntensity = stream.readFloat32("outTan_ambientIntensity");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_ambientIntensity);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_ambientIntensity);
                    stream.writeFloat32(_outTan_ambientIntensity);
                }
            }

            public AmbientIntensityTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<AmbientIntensityTrack> _ambientIntensityTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<AmbientIntensityTrack> getAmbientIntensityTracks() {
            return _ambientIntensityTracks;
        }

        public AmbientIntensityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _ambientIntensityTracks.add(new AmbientIntensityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AmbientIntensityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<AmbientIntensityTrackChunk> _ambientIntensityTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<AmbientIntensityTrackChunk> getAmbientIntensityTrackChunks() {
        return _ambientIntensityTrackChunks;
    }

    public void addAmbientIntensityTrackChunk(@Nonnull AmbientIntensityTrackChunk val) {
        _chunks.add(val);

        _ambientIntensityTrackChunks.add(val);
    }

    public static class AmbientColorTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLBC");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _ambientColorTracks;
        }

        public static class AmbientColorTrack extends Track {
            private Color _color;

            @Nonnull
            public Color getColor() {
                return _color;
            }

            public void setColor(@Nonnull Color color) {
                _color = color;
            }

            private Color _inTan_color;

            @Nonnull
            public Color getInTanColor() {
                return _inTan_color;
            }

            public void setInTanColor(@Nonnull Color color) {
                _inTan_color = color;
            }

            private Color _outTan_color;

            @Nonnull
            public Color getOutTanColor() {
                return _outTan_color;
            }

            public void setOutTanColor(@Nonnull Color color) {
                _outTan_color = color;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _color = Color.fromBGR255((int) (stream.readFloat32("colorBlue") * 255), (int) (stream.readFloat32("colorGreen") * 255), (int) (stream.readFloat32("colorRed") * 255));

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_color = Color.fromBGR255((int) (stream.readFloat32("inTan_colorBlue") * 255), (int) (stream.readFloat32("inTan_colorGreen") * 255), (int) (stream.readFloat32("inTan_colorRed") * 255));
                    _outTan_color = Color.fromBGR255((int) (stream.readFloat32("outTan_colorBlue") * 255), (int) (stream.readFloat32("outTan_colorGreen") * 255), (int) (stream.readFloat32("outTan_colorRed") * 255));
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_color.getBlue255() / 255F);
                stream.writeFloat32(_color.getGreen255() / 255F);
                stream.writeFloat32(_color.getRed255() / 255F);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_color.getBlue255() / 255F);
                    stream.writeFloat32(_inTan_color.getGreen255() / 255F);
                    stream.writeFloat32(_inTan_color.getRed255() / 255F);

                    stream.writeFloat32(_outTan_color.getBlue255() / 255F);
                    stream.writeFloat32(_outTan_color.getGreen255() / 255F);
                    stream.writeFloat32(_outTan_color.getRed255() / 255F);
                }
            }

            public AmbientColorTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<AmbientColorTrack> _ambientColorTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<AmbientColorTrack> getAlphaTracks() {
            return _ambientColorTracks;
        }

        public AmbientColorTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _ambientColorTracks.add(new AmbientColorTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AmbientColorTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<AmbientColorTrackChunk> _ambientColorTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<AmbientColorTrackChunk> getAmbientColorTrackChunks() {
        return _ambientColorTrackChunks;
    }

    public void addAmbientColorTrackChunk(@Nonnull AmbientColorTrackChunk val) {
        _chunks.add(val);

        _ambientColorTrackChunks.add(val);
    }

    public static class VisibilityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAV");

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

        stream.writeUInt32(_type.ordinal());

        stream.writeFloat32(_attenuationStart);
        stream.writeFloat32(_attenuationEnd);

        stream.writeFloat32(_color.getBlue255());
        stream.writeFloat32(_color.getGreen255());
        stream.writeFloat32(_color.getRed255());

        stream.writeFloat32(_intensity);

        stream.writeFloat32(_ambientColor.getBlue255());
        stream.writeFloat32(_ambientColor.getGreen255());
        stream.writeFloat32(_ambientColor.getRed255());

        stream.writeFloat32(_ambientIntensity);

        for (Chunk chunk : getChunks()) {
            chunk.write(stream);
        }
        /*for (AttenuationStartTrackChunk attenuationStartTrackChunk : getAttenuationStartTrackChunks()) {
            attenuationStartTrackChunk.write(stream);
        }
        for (AttenuationEndTrackChunk attenuationEndTrackChunk : getAttenuationEndTrackChunks()) {
            attenuationEndTrackChunk.write(stream);
        }
        for (ColorTrackChunk colorTrackChunk : getColorTrackChunks()) {
            colorTrackChunk.write(stream);
        }
        for (IntensityTrackChunk intensityTrackChunk : getIntensityTrackChunks()) {
            intensityTrackChunk.write(stream);
        }
        for (AmbientIntensityTrackChunk ambientIntensityTrackChunk : getAmbientIntensityTrackChunks()) {
            ambientIntensityTrackChunk.write(stream);
        }
        for (AmbientColorTrackChunk ambientColorTrackChunk : getAmbientColorTrackChunks()) {
            ambientColorTrackChunk.write(stream);
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

    public Light(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);

        long typeL = stream.readUInt32("type");

        if (typeL >= Type.values().length) throw new IllegalArgumentException("type out of bounds 0-" + Type.values().length + " (" + typeL + ")");

        _type = Type.values()[(int) typeL];

        _attenuationStart = stream.readFloat32("attenuationStart");
        _attenuationEnd = stream.readFloat32("attenuationEnd");

        _color = Color.fromBGR255((int) (stream.readFloat32("color_blue") * 255), (int) (stream.readFloat32("color_green") * 255), (int) (stream.readFloat32("color_red") * 255));

        _intensity = stream.readFloat32("intensity");

        _ambientColor = Color.fromBGR255((int) (stream.readFloat32("ambientColor_blue") * 255), (int) (stream.readFloat32("ambientColor_green") * 255), (int) (stream.readFloat32("ambientColor_red") * 255));

        _ambientIntensity = stream.readFloat32("ambientIntensity");

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(AttenuationStartTrackChunk.TOKEN, () -> addAttenuationStartTrackChunk(new AttenuationStartTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(AttenuationEndTrackChunk.TOKEN, () -> addAttenuationEndTrackChunk(new AttenuationEndTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(ColorTrackChunk.TOKEN, () -> addColorTrackChunk(new ColorTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(IntensityTrackChunk.TOKEN, () -> addIntensityTrackChunk(new IntensityTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(AmbientIntensityTrackChunk.TOKEN, () -> addAmbientIntensityTrackChunk(new AmbientIntensityTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(AmbientColorTrackChunk.TOKEN, () -> addAmbientColorTrackChunk(new AmbientColorTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
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

    public Light() {
        _color = Color.fromBGRA255(0 ,0, 0, 0);
        _ambientColor = Color.fromBGRA255(0 ,0, 0, 0);
    }
}
