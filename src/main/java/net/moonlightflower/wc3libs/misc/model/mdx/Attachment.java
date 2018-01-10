package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Attachment extends MDXObject {
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

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        _node.write(stream);
        stream.writeBytes(Arrays.copyOf(_path.getBytes(), 260));
        stream.writeUInt32(_attachmentId);

        for (VisibilityTrackChunk visibilityTrackChunk : getVisibilityTrackChunks()) {
            visibilityTrackChunk.write(stream);
        }

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
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
