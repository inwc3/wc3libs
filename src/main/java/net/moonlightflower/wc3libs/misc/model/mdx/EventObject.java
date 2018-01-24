package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class EventObject extends MDXObject {
    private Node _node;

    @Nonnull
    public Node getNode() {
        return _node;
    }

    public void setNode(@Nonnull Node node) {
        _node = node;
    }

    private EventObjectTrackChunk _trackChunk;

    @Nonnull
    public EventObjectTrackChunk getTrackChunk() {
        return _trackChunk;
    }

    public void setTrackChunk(@Nonnull EventObjectTrackChunk trackChunk) {
        _trackChunk = trackChunk;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        _node.write(stream);

        _trackChunk.write(stream, MDX.EncodingFormat.AUTO);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public EventObject(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _node = new Node(stream);

        _trackChunk = new EventObjectTrackChunk(stream, MDX.EncodingFormat.MDX_0x0);
    }

    public EventObject() {
        _node = new Node();
        _trackChunk = new EventObjectTrackChunk();
    }
}
