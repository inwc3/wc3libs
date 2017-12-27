package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
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

public class GeosetAnim extends MDXObject {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private float _alpha;

    public float getAlpha() {
        return _alpha;
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    private Color _color;

    public Color getColor() {
        return _color;
    }

    private long _geosetId;

    public long getGeosetId() {
        return _geosetId;
    }

    private List<Chunk> _chunks = new ArrayList<>();

    public List<Chunk> getChunks() {
        return _chunks;
    }

    private void addChunk(@Nonnull Chunk val) {
        _chunks.add(val);
    }

    public static class AlphaTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KGAO");

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

        private List<AlphaTrackChunk.AlphaTrack> _alphaTracks = new ArrayList<>();

        public List<AlphaTrackChunk.AlphaTrack> getAlphaTracks() {
            return new ArrayList<>(_alphaTracks);
        }

        public void addAlphaTrack(@Nonnull AlphaTrackChunk.AlphaTrack val) {
            if (!_alphaTracks.contains(val)) {
                _alphaTracks.add(val);
            }
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                addAlphaTrack(new AlphaTrackChunk.AlphaTrack(stream, getInterpolationType(), format));

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
        public final static Id TOKEN = Id.valueOf("KGAC");

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

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        stream.writeFloat32(_alpha);
        stream.writeUInt32(_flags);

        stream.writeFloat32(_color.getBlue() / 255F);
        stream.writeFloat32(_color.getGreen() / 255F);
        stream.writeFloat32(_color.getRed() / 255F);

        stream.writeUInt32(_geosetId);

        for (Chunk chunk : getChunks()) {
            chunk.write(stream);
        }
        /*for (AlphaTrackChunk alphaTrackChunk : getAlphaTrackChunks()) {
            alphaTrackChunk.write(stream);
        }
        for (ColorTrackChunk colorTrackChunk : getColorTrackChunks()) {
            colorTrackChunk.write(stream);
        }*/

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public GeosetAnim(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");

        _alpha = stream.readFloat32("alpha");
        _flags = stream.readUInt32("flags");

        float blue = stream.readFloat32("blue");
        float green = stream.readFloat32("green");
        float red = stream.readFloat32("red");

        _color = Color.fromBGR255((int) (blue * 255), (int) (green * 255), (int) (red * 255));

        _geosetId = stream.readInt32("geosetId");

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

        _tokenMap.put(AlphaTrackChunk.TOKEN, () -> addAlphaTrackChunk(new AlphaTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));
        _tokenMap.put(ColorTrackChunk.TOKEN, () -> addColorTrackChunk(new ColorTrackChunk(stream, MDX.EncodingFormat.MDX_0x0)));

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
