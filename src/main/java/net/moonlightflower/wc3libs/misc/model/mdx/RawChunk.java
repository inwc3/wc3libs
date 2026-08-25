package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * An MDX chunk whose payload is not understood by this version of wc3libs.
 * Keeping the complete chunk allows newer format extensions to survive a
 * parse/write cycle without being silently discarded.
 */
public final class RawChunk extends Chunk {
    private final Id _token;
    private byte[] _data;

    @Override
    public Id getToken() {
        return _token;
    }

    @Nonnull
    public byte[] getData() {
        return Arrays.copyOf(_data, _data.length);
    }

    public void setData(@Nonnull byte[] data) {
        _data = Arrays.copyOf(data, data.length);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeId(_token);
        stream.writeUInt32(_data.length);
        stream.writeBytes(_data);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public RawChunk(@Nonnull Id token, @Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        _token = token;

        long size = stream.readUInt32("header_size");
        if (size > Integer.MAX_VALUE) {
            throw new BinStream.StreamException(stream, "unknown chunk is too large: " + size);
        }

        _data = stream.readBytes((int) size, "unknownChunkData");
    }

    public RawChunk(@Nonnull Id token, @Nonnull byte[] data) {
        _token = token;
        _data = Arrays.copyOf(data, data.length);
    }
}
