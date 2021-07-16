package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.Set;

public abstract class TrackChunk<Type> extends Chunk {
    @Override
    public abstract Id getToken();

    private long _tracksCount = 0;

    public long getTracksCount() {
        return _tracksCount;
    }

    public abstract Set<? extends Track> getTracks();

    public enum InterpolationType {
        NONE,
        LINEAR,
        HERMITE,
        BEZIER
    }

    private InterpolationType _interpolationType = InterpolationType.NONE;

    @Nonnull
    public InterpolationType getInterpolationType() {
        return _interpolationType;
    }

    public void setInterpolationType(@Nonnull InterpolationType interpolationType) {
        _interpolationType = interpolationType;
    }

    private long _globalSequenceId = 0;

    public long getGlobalSequenceId() {
        return _globalSequenceId;
    }

    public void setGlobalSequenceId(long globalSequenceId) {
        _globalSequenceId = globalSequenceId;
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        _tracksCount = stream.readUInt32("tracksCount");

        long interpolationTypeL = stream.readUInt32("interpolationType");

        if (interpolationTypeL >= InterpolationType.values().length) throw new IllegalArgumentException("interpolationType out of bounds 0-" + (InterpolationType.values().length - 1) + " (" + interpolationTypeL + ")");

        _interpolationType = InterpolationType.values()[(int) interpolationTypeL];
        _globalSequenceId = stream.readUInt32("globalSequenceId");
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeId(getToken());

        stream.writeUInt32(getTracks().size());

        stream.writeUInt32(_interpolationType.ordinal());
        stream.writeUInt32(_globalSequenceId);

        for (Track track : getTracks()) {
            track.write(stream, _interpolationType, MDX.EncodingFormat.MDX_0x0);
        }
    }

    public void read(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        switch (format.toEnum()) {
            case MDX_0x0:
                read_0x0(stream);

                break;
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        switch (format.toEnum()) {
            case AUTO:
            case MDX_0x0:
                write_0x0(stream);

                break;
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public TrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public TrackChunk(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        this();

        read(stream, MDX.EncodingFormat.AUTO);
    }

    public TrackChunk() {
    }
}
