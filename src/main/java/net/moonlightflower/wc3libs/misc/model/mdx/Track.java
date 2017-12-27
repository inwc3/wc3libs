package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public abstract class Track {
    private int _frame;

    public int getFrame() {
        return _frame;
    }

    protected abstract void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType) throws BinStream.StreamException;
    protected abstract void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType) throws BinStream.StreamException;

    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeInt32(_frame);

        writeSpec(stream, interpolationType);
    }

    public Track(@Nonnull Wc3BinInputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        _frame = stream.readInt32("frame");

        readSpec(stream, interpolationType);
    }

    public Track(@Nonnull Wc3BinInputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType) throws BinStream.StreamException {
        this(stream, interpolationType, MDX.EncodingFormat.AUTO);
    }
}
