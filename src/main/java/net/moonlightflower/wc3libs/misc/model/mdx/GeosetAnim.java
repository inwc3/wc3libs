package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.*;

public class GeosetAnim extends MDXObject {
    private long _inclusiveSize = 0;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private float _alpha;

    public float getAlpha() {
        return _alpha;
    }

    public void setAlpha(float alpha) {
        _alpha = alpha;
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    public void setFlags(long flags) {
        _flags = flags;
    }

    private Color _color;

    @Nonnull
    public Color getColor() {
        return _color;
    }

    public void setColor(@Nonnull Color color) {
        _color = color;
    }

    private long _geosetId = 0;

    public long getGeosetId() {
        return _geosetId;
    }

    private final LinkedHashSet<Chunk> _chunks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Chunk> getChunks() {
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
        public Set<? extends Track> getTracks() {
            return _alphaTracks;
        }

        public static class AlphaTrack extends Track {
            private float _alpha = 0F;

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

            public void setOutTanAlpha(float alpha) {
                _outTan_alpha = alpha;
            }

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

        private final LinkedHashSet<AlphaTrack> _alphaTracks = new ObservableLinkedHashSet<>();

        public LinkedHashSet<AlphaTrackChunk.AlphaTrack> getAlphaTracks() {
            return _alphaTracks;
        }

        public AlphaTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _alphaTracks.add(new AlphaTrackChunk.AlphaTrack(stream, getInterpolationType(), format));

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
        public final static Id TOKEN = Id.valueOf("KGAC");

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

        public ColorTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
            super(stream, format);

            long tracksCount = getTracksCount();

            while (tracksCount > 0) {
                _colorTracks.add(new ColorTrack(stream, getInterpolationType(), format));

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

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        stream.writeFloat32(_alpha);
        stream.writeUInt32(_flags);

        stream.writeFloat32(_color.getBlue255() / 255F);
        stream.writeFloat32(_color.getGreen255() / 255F);
        stream.writeFloat32(_color.getRed255() / 255F);

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

    public GeosetAnim() {
        _color = Color.fromBGRA255(0, 0, 0, 0);
    }
}
