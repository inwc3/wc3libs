package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Attachment {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    private String _path;

    public String getPath() {
        return _path;
    }

    private long _attachmentId;

    public long getAttachmentId() {
        return _attachmentId;
    }

    public static class VisibilityTrackChunk extends TrackChunk {
        public final static Id TOKEN = Id.valueOf("KATV");

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
        if (!_visibilityTrackChunks.contains(val)) {
            _visibilityTrackChunks.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeUInt32(_inclusiveSize);
        _node.write(stream);
        stream.writeBytes(Arrays.copyOf(_path.getBytes(), 260));
        stream.writeUInt32(_attachmentId);

        for (VisibilityTrackChunk visibilityTrackChunk : getVisibilityTrackChunks()) {
            visibilityTrackChunk.write(stream);
        }
    }

    public Attachment(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _path = new String(stream.readBytes(260), StandardCharsets.US_ASCII);
        _attachmentId = stream.readUInt32("attachmentId");

        Map<Id, MDX.TokenHandler> _tokenMap = new LinkedHashMap<>();

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
