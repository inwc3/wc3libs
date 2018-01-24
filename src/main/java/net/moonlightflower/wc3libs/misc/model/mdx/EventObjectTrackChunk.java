package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class EventObjectTrackChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("KEVT");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private long _globalSequenceId = 0;

    public long getGlobalSequenceId() {
        return _globalSequenceId;
    }

    public void setGlobaLSequenceId(long globaLSequenceId) {
        _globalSequenceId = globaLSequenceId;
    }

    private final LinkedHashSet<EventObjectTrack> _tracks = new ObservableLinkedHashSet<>();

    public LinkedHashSet<EventObjectTrack> getTracks() {
        return _tracks;
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        long tracksCount = stream.readUInt32("tracksCount");

        _globalSequenceId = stream.readUInt32("globalSequenceId");

        while (tracksCount > 0) {
            _tracks.add(new EventObjectTrack(stream));

            tracksCount--;
        }
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(getTracks().size());

        stream.writeUInt32(_globalSequenceId);

        for (EventObjectTrack track : getTracks()) {
            track.write(stream);
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

    public EventObjectTrackChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public EventObjectTrackChunk() {
    }
}
