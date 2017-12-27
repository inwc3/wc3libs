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

public class Light extends MDXObject {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    public enum Type {
        OMNI,
        DIRECTIONAL,
        AMBIENT
    }

    private Type _type;

    public Type getType() {
        return _type;
    }

    private float _attenuationStart;

    public float getAttenuationStart() {
        return _attenuationStart;
    }

    private float _attenuationEnd;

    public float getAttenuationEnd() {
        return _attenuationEnd;
    }

    private Color _color;

    public Color getColor() {
        return _color;
    }

    private float _intensity;

    public float getIntensity() {
        return _intensity;
    }

    private Color _ambientColor;

    public Color getAmbientColor() {
        return _ambientColor;
    }

    private float _ambientIntensity;

    public float getAmbientIntensity() {
        return _ambientIntensity;
    }

    private List<Chunk> _chunks = new ArrayList<>();

    public List<Chunk> getChunks() {
        return _chunks;
    }

    private void addChunk(@Nonnull Chunk val) {
        _chunks.add(val);
    }

    public static class AttenuationStartTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAS");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _attenuationStartTracks;
        }

        public static class AttenuationStartTrack extends Track {
            private float _alpha;

            public float getAlpha() {
                return _alpha;
            }

            private float _inTan_alpha;

            public float getInTanAlpha() {
                return _inTan_alpha;
            }

            private float _outTan_alpha;

            public float getOutTanAlpha() {
                return _outTan_alpha;
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

        private List<AttenuationStartTrack> _attenuationStartTracks = new ArrayList<>();

        public List<AttenuationStartTrack> getAttenuationStartTracks() {
            return new ArrayList<>(_attenuationStartTracks);
        }

        public void addAttenuationStartTrack(@Nonnull AttenuationStartTrack val) {
            if (!_attenuationStartTracks.contains(val)) {
                _attenuationStartTracks.add(val);
            }
        }

        public AttenuationStartTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addAttenuationStartTrack(new AttenuationStartTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AttenuationStartTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<AttenuationStartTrackChunk> _attenuationStartTrackChunks = new ArrayList<>();

    public List<AttenuationStartTrackChunk> getAttenuationStartTrackChunks() {
        return new ArrayList<>(_attenuationStartTrackChunks);
    }

    public void addAttenuationStartTrackChunk(@Nonnull AttenuationStartTrackChunk val) {
        addChunk(val);

        if (!_attenuationStartTrackChunks.contains(val)) {
            _attenuationStartTrackChunks.add(val);
        }
    }

    public static class AttenuationEndTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAE");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _attenuationEndTracks;
        }

        public static class AttenuationEndTrack extends Track {
            private float _alpha;

            public float getAlpha() {
                return _alpha;
            }

            private float _inTan_alpha;

            public float getInTanAlpha() {
                return _inTan_alpha;
            }

            private float _outTan_alpha;

            public float getOutTanAlpha() {
                return _outTan_alpha;
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

        private List<AttenuationEndTrack> _attenuationEndTracks = new ArrayList<>();

        public List<AttenuationEndTrack> getAttenuationEndTracks() {
            return new ArrayList<>(_attenuationEndTracks);
        }

        public void addAttenuationEndTrack(@Nonnull AttenuationEndTrack val) {
            if (!_attenuationEndTracks.contains(val)) {
                _attenuationEndTracks.add(val);
            }
        }

        public AttenuationEndTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addAttenuationEndTrack(new AttenuationEndTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AttenuationEndTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<AttenuationEndTrackChunk> _attenuationEndTrackChunks = new ArrayList<>();

    public List<AttenuationEndTrackChunk> getAttenuationEndTrackChunks() {
        return new ArrayList<>(_attenuationEndTrackChunks);
    }

    public void addAttenuationEndTrackChunk(@Nonnull AttenuationEndTrackChunk val) {
        addChunk(val);

        if (!_attenuationEndTrackChunks.contains(val)) {
            _attenuationEndTrackChunks.add(val);
        }
    }

    public static class ColorTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAC");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _colorTracks;
        }

        public static class ColorTrack extends Track {
            private Color _color;

            public Color getColor() {
                return _color;
            }

            private Color _inTan_color;

            public Color getInTanColor() {
                return _inTan_color;
            }

            private Color _outTan_color;

            public Color getOutTanColor() {
                return _outTan_color;
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
                stream.writeFloat32(_color.getBlue() / 255F);
                stream.writeFloat32(_color.getGreen() / 255F);
                stream.writeFloat32(_color.getRed() / 255F);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_color.getBlue() / 255F);
                    stream.writeFloat32(_inTan_color.getGreen() / 255F);
                    stream.writeFloat32(_inTan_color.getRed() / 255F);

                    stream.writeFloat32(_outTan_color.getBlue() / 255F);
                    stream.writeFloat32(_outTan_color.getGreen() / 255F);
                    stream.writeFloat32(_outTan_color.getRed() / 255F);
                }
            }

            public ColorTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<ColorTrackChunk.ColorTrack> _colorTracks = new ArrayList<>();

        public List<ColorTrackChunk.ColorTrack> getAlphaTracks() {
            return new ArrayList<>(_colorTracks);
        }

        public void addColorTrack(@Nonnull ColorTrackChunk.ColorTrack val) {
            if (!_colorTracks.contains(val)) {
                _colorTracks.add(val);
            }
        }

        public ColorTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addColorTrack(new ColorTrackChunk.ColorTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public ColorTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<ColorTrackChunk> _colorTrackChunks = new ArrayList<>();

    public List<ColorTrackChunk> getColorTrackChunks() {
        return new ArrayList<>(_colorTrackChunks);
    }

    public void addColorTrackChunk(@Nonnull ColorTrackChunk val) {
        addChunk(val);

        if (!_colorTrackChunks.contains(val)) {
            _colorTrackChunks.add(val);
        }
    }

    public static class IntensityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAI");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _intensityTracks;
        }

        public static class IntensityTrack extends Track {
            private float _intensity;

            public float getIntensity() {
                return _intensity;
            }

            private float _inTan_intensity;

            public float getInTanIntensity() {
                return _inTan_intensity;
            }

            private float _outTan_intensity;

            public float getOutTanIntensity() {
                return _outTan_intensity;
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

        private List<IntensityTrack> _intensityTracks = new ArrayList<>();

        public List<IntensityTrack> getIntensityTracks() {
            return new ArrayList<>(_intensityTracks);
        }

        public void addIntensityTrack(@Nonnull IntensityTrack val) {
            if (!_intensityTracks.contains(val)) {
                _intensityTracks.add(val);
            }
        }

        public IntensityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addIntensityTrack(new IntensityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public IntensityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<IntensityTrackChunk> _intensityTrackChunks = new ArrayList<>();

    public List<IntensityTrackChunk> getIntensityTrackChunks() {
        return new ArrayList<>(_intensityTrackChunks);
    }

    public void addIntensityTrackChunk(@Nonnull IntensityTrackChunk val) {
        addChunk(val);

        if (!_intensityTrackChunks.contains(val)) {
            _intensityTrackChunks.add(val);
        }
    }

    public static class AmbientIntensityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLBI");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _ambientIntensityTracks;
        }

        public static class AmbientIntensityTrack extends Track {
            private float _ambientIntensity;

            public float getAmbientIntensity() {
                return _ambientIntensity;
            }

            private float _inTan_ambientIntensity;

            public float getInTanAmbientIntensity() {
                return _inTan_ambientIntensity;
            }

            private float _outTan_ambientIntensity;

            public float getOutTanAmbientIntensity() {
                return _outTan_ambientIntensity;
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

        private List<AmbientIntensityTrack> _ambientIntensityTracks = new ArrayList<>();

        public List<AmbientIntensityTrack> getAmbientIntensityTracks() {
            return new ArrayList<>(_ambientIntensityTracks);
        }

        public void addAmbientIntensityTrack(@Nonnull AmbientIntensityTrack val) {
            if (!_ambientIntensityTracks.contains(val)) {
                _ambientIntensityTracks.add(val);
            }
        }

        public AmbientIntensityTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addAmbientIntensityTrack(new AmbientIntensityTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AmbientIntensityTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<AmbientIntensityTrackChunk> _ambientIntensityTrackChunks = new ArrayList<>();

    public List<AmbientIntensityTrackChunk> getAmbientIntensityTrackChunks() {
        return new ArrayList<>(_ambientIntensityTrackChunks);
    }

    public void addAmbientIntensityTrackChunk(@Nonnull AmbientIntensityTrackChunk val) {
        addChunk(val);

        if (!_ambientIntensityTrackChunks.contains(val)) {
            _ambientIntensityTrackChunks.add(val);
        }
    }

    public static class AmbientColorTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLBC");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _ambientColorTracks;
        }

        public static class AmbientColorTrack extends Track {
            private Color _color;

            public Color getColor() {
                return _color;
            }

            private Color _inTan_color;

            public Color getInTanColor() {
                return _inTan_color;
            }

            private Color _outTan_color;

            public Color getOutTanColor() {
                return _outTan_color;
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
                stream.writeFloat32(_color.getBlue() / 255F);
                stream.writeFloat32(_color.getGreen() / 255F);
                stream.writeFloat32(_color.getRed() / 255F);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_color.getBlue() / 255F);
                    stream.writeFloat32(_inTan_color.getGreen() / 255F);
                    stream.writeFloat32(_inTan_color.getRed() / 255F);

                    stream.writeFloat32(_outTan_color.getBlue() / 255F);
                    stream.writeFloat32(_outTan_color.getGreen() / 255F);
                    stream.writeFloat32(_outTan_color.getRed() / 255F);
                }
            }

            public AmbientColorTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<AmbientColorTrack> _ambientColorTracks = new ArrayList<>();

        public List<AmbientColorTrack> getAlphaTracks() {
            return new ArrayList<>(_ambientColorTracks);
        }

        public void addAmbientColorTrack(@Nonnull AmbientColorTrack val) {
            if (!_ambientColorTracks.contains(val)) {
                _ambientColorTracks.add(val);
            }
        }

        public AmbientColorTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addAmbientColorTrack(new AmbientColorTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AmbientColorTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<AmbientColorTrackChunk> _ambientColorTrackChunks = new ArrayList<>();

    public List<AmbientColorTrackChunk> getAmbientColorTrackChunks() {
        return new ArrayList<>(_ambientColorTrackChunks);
    }

    public void addAmbientColorTrackChunk(@Nonnull AmbientColorTrackChunk val) {
        addChunk(val);

        if (!_ambientColorTrackChunks.contains(val)) {
            _ambientColorTrackChunks.add(val);
        }
    }

    public static class VisibilityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KLAV");

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

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        _node.write(stream);

        stream.writeUInt32(_type.ordinal());

        stream.writeFloat32(_attenuationStart);
        stream.writeFloat32(_attenuationEnd);

        stream.writeFloat32(_color.getBlue());
        stream.writeFloat32(_color.getGreen());
        stream.writeFloat32(_color.getRed());

        stream.writeFloat32(_intensity);

        stream.writeFloat32(_ambientColor.getBlue());
        stream.writeFloat32(_ambientColor.getGreen());
        stream.writeFloat32(_ambientColor.getRed());

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
}
