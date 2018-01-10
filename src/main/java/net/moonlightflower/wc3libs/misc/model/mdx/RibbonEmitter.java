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

public class RibbonEmitter extends MDXObject {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    private float _heightAbove;

    public float getHeightAbove() {
        return _heightAbove;
    }

    private float _heightBelow;

    public float getHeightBelow() {
        return _heightBelow;
    }

    private Color _color;

    public Color getColor() {
        return _color;
    }

    private float _lifespan;

    public float getLifespan() {
        return _lifespan;
    }

    private long _textureSlot;

    public long getTextureSlot() {
        return _textureSlot;
    }

    private long _emissionRate;

    public long getEmissionRate() {
        return _emissionRate;
    }

    private long _rows;

    public long getRows() {
        return _rows;
    }

    private long _columns;

    public long getColumns() {
        return _columns;
    }

    private long _materialId;

    public long getMaterialId() {
        return _materialId;
    }

    private float _gravity;

    public float getGravity() {
        return _gravity;
    }

    private List<Chunk> _chunks = new ArrayList<>();

    public List<Chunk> getChunks() {
        return _chunks;
    }

    private void addChunk(@Nonnull Chunk val) {
        _chunks.add(val);
    }

    public static class VisibilityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRVS");

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

    public static class HeightAboveTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRHA");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _heightAboveTracks;
        }

        public static class HeightAboveTrack extends Track {
            private float _heightAbove;

            public float getHeightAbove() {
                return _heightAbove;
            }

            private float _inTan_heightAbove;

            public float getInTanHeightAbove() {
                return _inTan_heightAbove;
            }

            private float _outTan_heightAbove;

            public float getOutTanHeightAbove() {
                return _outTan_heightAbove;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _heightAbove = stream.readFloat32("heightAbove");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_heightAbove = stream.readFloat32("inTan_heightAbove");
                    _outTan_heightAbove = stream.readFloat32("outTan_heightAbove");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_heightAbove);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_heightAbove);
                    stream.writeFloat32(_outTan_heightAbove);
                }
            }

            public HeightAboveTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<HeightAboveTrack> _heightAboveTracks = new ArrayList<>();

        public List<HeightAboveTrack> getHeightAboveTracks() {
            return new ArrayList<>(_heightAboveTracks);
        }

        public void addHeightAboveTrack(@Nonnull HeightAboveTrack val) {
            if (!_heightAboveTracks.contains(val)) {
                _heightAboveTracks.add(val);
            }
        }

        public HeightAboveTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addHeightAboveTrack(new HeightAboveTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public HeightAboveTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<HeightAboveTrackChunk> _heightAboveTrackChunks = new ArrayList<>();

    public List<HeightAboveTrackChunk> getHeightAboveTrackChunks() {
        return new ArrayList<>(_heightAboveTrackChunks);
    }

    public void addHeightAboveTrackChunk(@Nonnull HeightAboveTrackChunk val) {
        addChunk(val);

        if (!_heightAboveTrackChunks.contains(val)) {
            _heightAboveTrackChunks.add(val);
        }
    }

    public static class HeightBelowTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRHB");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _heightBelowTracks;
        }

        public static class HeightBelowTrack extends Track {
            private float _heightBelow;

            public float getHeightBelow() {
                return _heightBelow;
            }

            private float _inTan_heightBelow;

            public float getInTanHeightBelow() {
                return _inTan_heightBelow;
            }

            private float _outTan_heightBelow;

            public float getOutTanHeightBelow() {
                return _outTan_heightBelow;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _heightBelow = stream.readFloat32("heightBelow");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_heightBelow = stream.readFloat32("inTan_heightBelow");
                    _outTan_heightBelow = stream.readFloat32("outTan_heightBelow");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeFloat32(_heightBelow);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeFloat32(_inTan_heightBelow);
                    stream.writeFloat32(_outTan_heightBelow);
                }
            }

            public HeightBelowTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<HeightBelowTrack> _heightBelowTracks = new ArrayList<>();

        public List<HeightBelowTrack> getHeightBelowTracks() {
            return new ArrayList<>(_heightBelowTracks);
        }

        public void addHeightBelowTrack(@Nonnull HeightBelowTrack val) {
            if (!_heightBelowTracks.contains(val)) {
                _heightBelowTracks.add(val);
            }
        }

        public HeightBelowTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addHeightBelowTrack(new HeightBelowTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public HeightBelowTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<HeightBelowTrackChunk> _heightBelowTrackChunks = new ArrayList<>();

    public List<HeightBelowTrackChunk> getHeightBelowTrackChunks() {
        return new ArrayList<>(_heightBelowTrackChunks);
    }

    public void addHeightBelowTrackChunk(@Nonnull HeightBelowTrackChunk val) {
        addChunk(val);

        if (!_heightBelowTrackChunks.contains(val)) {
            _heightBelowTrackChunks.add(val);
        }
    }

    public static class AlphaTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRAL");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _alphaTracks;
        }

        public static class AlphaTrack extends Track {
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

            public AlphaTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<AlphaTrack> _alphaTracks = new ArrayList<>();

        public List<AlphaTrack> getAlphaTracks() {
            return new ArrayList<>(_alphaTracks);
        }

        public void addAlphaTrack(@Nonnull AlphaTrack val) {
            if (!_alphaTracks.contains(val)) {
                _alphaTracks.add(val);
            }
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addAlphaTrack(new AlphaTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<AlphaTrackChunk> _alphaTrackChunks = new ArrayList<>();

    public List<AlphaTrackChunk> getAlphaTrackChunks() {
        return new ArrayList<>(_alphaTrackChunks);
    }

    public void addAlphaTrackChunk(@Nonnull AlphaTrackChunk val) {
        addChunk(val);

        if (!_alphaTrackChunks.contains(val)) {
            _alphaTrackChunks.add(val);
        }
    }

    public static class ColorTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRCO");

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

        private List<ColorTrack> _colorTracks = new ArrayList<>();

        public List<ColorTrack> getAlphaTracks() {
            return new ArrayList<>(_colorTracks);
        }

        public void addColorTrack(@Nonnull ColorTrack val) {
            if (!_colorTracks.contains(val)) {
                _colorTracks.add(val);
            }
        }

        public ColorTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addColorTrack(new ColorTrack(stream, getInterpolationType(), format));

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

    public static class TextureSlotTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRTX");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public List<? extends Track> getTracks() {
            return _textureSlotTracks;
        }

        public static class TextureSlotTrack extends Track {
            private long _textureSlot;

            public long getTextureSlot() {
                return _textureSlot;
            }

            private long _inTan_textureSlot;

            public long getInTanTextureSlot() {
                return _inTan_textureSlot;
            }

            private long _outTan_textureSlot;

            public long getOutTanTextureSlot() {
                return _outTan_textureSlot;
            }

            @Override
            protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                _textureSlot = stream.readUInt32("textureSlot");

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    _inTan_textureSlot = stream.readUInt32("inTan_textureSlot");
                    _outTan_textureSlot = stream.readUInt32("outTan_textureSlot");
                }
            }

            @Override
            protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull InterpolationType interpolationType) throws BinStream.StreamException {
                stream.writeUInt32(_textureSlot);

                if (interpolationType.equals(InterpolationType.HERMITE) || interpolationType.equals(InterpolationType.BEZIER)) {
                    stream.writeUInt32(_inTan_textureSlot);
                    stream.writeUInt32(_outTan_textureSlot);
                }
            }

            public TextureSlotTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private List<TextureSlotTrack> _textureSlotTracks = new ArrayList<>();

        public List<TextureSlotTrack> getTextureSlotTracks() {
            return new ArrayList<>(_textureSlotTracks);
        }

        public void addTextureSlotTrack(@Nonnull TextureSlotTrack val) {
            if (!_textureSlotTracks.contains(val)) {
                _textureSlotTracks.add(val);
            }
        }

        public TextureSlotTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addTextureSlotTrack(new TextureSlotTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public TextureSlotTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private List<TextureSlotTrackChunk> _textureSlotTrackChunks = new ArrayList<>();

    public List<TextureSlotTrackChunk> getTextureSlotTrackChunks() {
        return new ArrayList<>(_textureSlotTrackChunks);
    }

    public void addTextureSlotTrackChunk(@Nonnull TextureSlotTrackChunk val) {
        addChunk(val);

        if (!_textureSlotTrackChunks.contains(val)) {
            _textureSlotTrackChunks.add(val);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        _node.write(stream);
        stream.writeFloat32(_heightAbove);
        stream.writeFloat32(_heightBelow);

        stream.writeFloat32(_color.getAlpha255() / 255F);
        stream.writeFloat32(_color.getBlue255() / 255F);
        stream.writeFloat32(_color.getGreen255() / 255F);
        stream.writeFloat32(_color.getRed255() / 255F);

        stream.writeFloat32(_lifespan);

        stream.writeUInt32(_textureSlot);
        stream.writeUInt32(_emissionRate);

        stream.writeUInt32(_rows);
        stream.writeUInt32(_columns);

        stream.writeUInt32(_materialId);
        stream.writeFloat32(_gravity);

        for (Chunk chunk : getChunks()) {
            chunk.write(stream);
        }
        /*for (VisibilityTrackChunk visibilityTrackChunk : getVisibilityTrackChunks()) {
            visibilityTrackChunk.write(stream);
        }
        for (HeightAboveTrackChunk heightAboveTrackChunk : getHeightAboveTrackChunks()) {
            heightAboveTrackChunk.write(stream);
        }
        for (HeightBelowTrackChunk heightBelowTrackChunk : getHeightBelowTrackChunks()) {
            heightBelowTrackChunk.write(stream);
        }
        for (AlphaTrackChunk alphaTrackChunk : getAlphaTrackChunks()) {
            alphaTrackChunk.write(stream);
        }
        for (ColorTrackChunk colorTrackChunk : getColorTrackChunks()) {
            colorTrackChunk.write(stream);
        }
        for (TextureSlotTrackChunk textureSlotTrackChunk : getTextureSlotTrackChunks()) {
            textureSlotTrackChunk.write(stream);
        }*/

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public RibbonEmitter(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _heightAbove = stream.readFloat32("heightAbove");
        _heightBelow = stream.readFloat32("heightBelow");

        int alpha = (int) (stream.readFloat32("alpha") * 255);

        _color = Color.fromBGRA255((int) (stream.readFloat32("blue") * 255), (int) (stream.readFloat32("green") * 255), (int) (stream.readFloat32("red") * 255), alpha);

        _lifespan = stream.readFloat32("lifespan");

        _textureSlot = stream.readUInt32("textureSlot");
        _emissionRate = stream.readUInt32("emissionRate");

        _rows = stream.readUInt32("rows");
        _columns = stream.readUInt32("columns");

        _materialId = stream.readUInt32("materialId");
        _gravity = stream.readFloat32("gravity");

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(VisibilityTrackChunk.TOKEN, () -> addVisibilityTrackChunk(new VisibilityTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(HeightAboveTrackChunk.TOKEN, () -> addHeightAboveTrackChunk(new HeightAboveTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(HeightBelowTrackChunk.TOKEN, () -> addHeightBelowTrackChunk(new HeightBelowTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(AlphaTrackChunk.TOKEN, () -> addAlphaTrackChunk(new AlphaTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(ColorTrackChunk.TOKEN, () -> addColorTrackChunk(new ColorTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(TextureSlotTrackChunk.TOKEN, () -> addTextureSlotTrackChunk(new TextureSlotTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));

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
