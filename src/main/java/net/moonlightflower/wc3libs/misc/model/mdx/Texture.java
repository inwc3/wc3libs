package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Texture {
    private long _replaceableId;

    public long getReplaceableId() {
        return _replaceableId;
    }

    private String _fileName;

    public String getFileName() {
        return _fileName;
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt32(_replaceableId);
        stream.writeBytes(Arrays.copyOf(_fileName.getBytes(StandardCharsets.US_ASCII), 260));
        stream.writeUInt32(_flags);
    }

    public Texture(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _replaceableId = stream.readUInt32("replaceableId");
        _fileName = new String(stream.readBytes(260, "fileName"), StandardCharsets.US_ASCII);
        _flags = stream.readUInt32("flags");
    }
}
