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

public class RibbonEmitter extends MDXObject {
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

    private float _heightAbove = 0F;

    public float getHeightAbove() {
        return _heightAbove;
    }

    public void setHeightAbove(float heightAbove) {
        _heightAbove = heightAbove;
    }

    private float _heightBelow = 0F;

    public float getHeightBelow() {
        return _heightBelow;
    }

    public void setHeightBelow(float heightBelow) {
        _heightBelow = heightBelow;
    }

    private Color _color;

    @Nonnull
    public Color getColor() {
        return _color;
    }

    public void setColor(@Nonnull Color color) {
        _color = color;
    }

    private float _lifespan = 0F;

    public float getLifespan() {
        return _lifespan;
    }

    public void setLifespan(float lifespan) {
        _lifespan = lifespan;
    }

    private long _textureSlot = 0;

    public long getTextureSlot() {
        return _textureSlot;
    }

    public void setTextureSlot(long textureSlot) {
        _textureSlot = textureSlot;
    }

    private long _emissionRate = 0;

    public long getEmissionRate() {
        return _emissionRate;
    }

    public void setEmissionRate(long emissionRate) {
        _emissionRate = emissionRate;
    }

    private long _rows;

    public long getRows() {
        return _rows;
    }

    public void setRows(long rows) {
        _rows = rows;
    }

    private long _columns;

    public long getColumns() {
        return _columns;
    }

    public void setColumns(long columns) {
        _columns = columns;
    }

    private long _materialId;

    public long getMaterialId() {
        return _materialId;
    }

    public void setMaterialId(long materialId) {
        _materialId = materialId;
    }

    private float _gravity;

    public float getGravity() {
        return _gravity;
    }

    public void setGravity(float gravity) {
        _gravity = gravity;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
        return _chunks;
    }

    public static class VisibilityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRVS");

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

    public static class HeightAboveTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRHA");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _heightAboveTracks;
        }

        public static class HeightAboveTrack extends Track {
            private float _heightAbove;

            public float getHeightAbove() {
                return _heightAbove;
            }

            public void setHeightAbove(float heightAbove) {
                _heightAbove = heightAbove;
            }

            private float _inTan_heightAbove;

            public float getInTanHeightAbove() {
                return _inTan_heightAbove;
            }

            public void setInTanHeightAbove(float heightAbove) {
                _inTan_heightAbove = heightAbove;
            }

            private float _outTan_heightAbove;

            public float getOutTanHeightAbove() {
                return _outTan_heightAbove;
            }

            public void setOutTanHeightAbove(float heightAbove) {
                _outTan_heightAbove = heightAbove;
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

        private final LinkedHashSet<HeightAboveTrack> _heightAboveTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<HeightAboveTrack> getHeightAboveTracks() {
            return _heightAboveTracks;
        }

        public HeightAboveTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _heightAboveTracks.add(new HeightAboveTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public HeightAboveTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<HeightAboveTrackChunk> _heightAboveTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<HeightAboveTrackChunk> getHeightAboveTrackChunks() {
        return _heightAboveTrackChunks;
    }

    public void addHeightAboveTrackChunk(@Nonnull HeightAboveTrackChunk val) {
        _chunks.add(val);

        _heightAboveTrackChunks.add(val);
    }

    public static class HeightBelowTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRHB");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _heightBelowTracks;
        }

        public static class HeightBelowTrack extends Track {
            private float _heightBelow;

            public float getHeightBelow() {
                return _heightBelow;
            }

            public void setHeightBelow(float heightBelow) {
                _heightBelow = heightBelow;
            }

            private float _inTan_heightBelow;

            public float getInTanHeightBelow() {
                return _inTan_heightBelow;
            }

            public void setInTanHeightBelow(float heightBelow) {
                _inTan_heightBelow = heightBelow;
            }

            private float _outTan_heightBelow;

            public float getOutTanHeightBelow() {
                return _outTan_heightBelow;
            }

            public void setOutTanHeightBelow(float heightBelow) {
                _outTan_heightBelow = heightBelow;
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

        private final LinkedHashSet<HeightBelowTrack> _heightBelowTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<HeightBelowTrack> getHeightBelowTracks() {
            return _heightBelowTracks;
        }

        public HeightBelowTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _heightBelowTracks.add(new HeightBelowTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public HeightBelowTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<HeightBelowTrackChunk> _heightBelowTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<HeightBelowTrackChunk> getHeightBelowTrackChunks() {
        return _heightBelowTrackChunks;
    }

    public void addHeightBelowTrackChunk(@Nonnull HeightBelowTrackChunk val) {
        _chunks.add(val);

        _heightBelowTrackChunks.add(val);
    }

    public static class AlphaTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRAL");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _alphaTracks;
        }

        public static class AlphaTrack extends Track {
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

            public AlphaTrack(@Nonnull Wc3BinInputStream stream, @Nonnull InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
                super(stream, interpolationType, format);
            }
        }

        private final LinkedHashSet<AlphaTrack> _alphaTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<AlphaTrack> getAlphaTracks() {
            return _alphaTracks;
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _alphaTracks.add(new AlphaTrack(stream, getInterpolationType(), format));

                tracksCount--;
            }
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this(stream, MDX.EncodingFormat.AUTO);
        }
    }

    private final LinkedHashSet<AlphaTrackChunk> _alphaTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<AlphaTrackChunk> getAlphaTrackChunks() {
        return _alphaTrackChunks;
    }

    public void addAlphaTrackChunk(@Nonnull AlphaTrackChunk val) {
        _chunks.add(val);

        _alphaTrackChunks.add(val);
    }

    public static class ColorTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRCO");

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

        public LinkedHashSet<ColorTrack> getAlphaTracks() {
            return _colorTracks;
        }

        public void addColorTrack(@Nonnull ColorTrack val) {
            _colorTracks.add(val);
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

    private final LinkedHashSet<ColorTrackChunk> _colorTrackChunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<ColorTrackChunk> getColorTrackChunks() {
        return _colorTrackChunks;
    }

    public void addColorTrackChunk(@Nonnull ColorTrackChunk val) {
        _chunks.add(val);

        _colorTrackChunks.add(val);
    }

    public static class TextureSlotTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KRTX");

        @Override
        public Id getToken() {
            return TOKEN;
        }

        @Override
        public Set<? extends Track> getTracks() {
            return _textureSlotTracks;
        }

        public static class TextureSlotTrack extends Track {
            private long _textureSlot;

            public long getTextureSlot() {
                return _textureSlot;
            }

            public void setTextureSlot(long textureSlot) {
                _textureSlot = textureSlot;
            }

            private long _inTan_textureSlot;

            public long getInTanTextureSlot() {
                return _inTan_textureSlot;
            }

            public void setInTanTextureSlot(long textureSlot) {
                _inTan_textureSlot = textureSlot;
            }

            private long _outTan_textureSlot;

            public long getOutTanTextureSlot() {
                return _outTan_textureSlot;
            }

            public void setOutTanTextureSlot(long textureSlot) {
                _outTan_textureSlot = textureSlot;
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

        private final LinkedHashSet<TextureSlotTrack> _textureSlotTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<TextureSlotTrack> getTextureSlotTracks() {
            return _textureSlotTracks;
        }

        public TextureSlotTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _textureSlotTracks.add(new TextureSlotTrack(stream, getInterpolationType(), format));

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
        _chunks.add(val);

        _textureSlotTrackChunks.add(val);
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

    public RibbonEmitter() {
        _node = new Node();
        _color = Color.fromBGRA255(0, 0, 0 , 0);
    }
}
