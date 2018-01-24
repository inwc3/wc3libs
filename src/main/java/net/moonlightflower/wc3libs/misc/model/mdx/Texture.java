package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Texture extends MDXObject {
    private long _replaceableId = 0;

    public long getReplaceableId() {
        return _replaceableId;
    }

    public void setReplaceableId(long replaceableId) {
        _replaceableId = replaceableId;
    }

    private String _fileName = "unset";

    @Nonnull
    public String getFileName() {
        return _fileName;
    }

    public void setFileName(@Nonnull String fileName) {
        _fileName = fileName;
    }

    private long _flags = 0;

    public long getFlags() {
        return _flags;
    }

    public void setFlags(long flags) {
        _flags = flags;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeUInt32(_replaceableId);
        stream.writeBytes(Arrays.copyOf(_fileName.getBytes(StandardCharsets.US_ASCII), 260));
        stream.writeUInt32(_flags);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Texture(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _replaceableId = stream.readUInt32("replaceableId");
        _fileName = new String(stream.readBytes(260, "fileName"), StandardCharsets.US_ASCII);
        _flags = stream.readUInt32("flags");
    }

    public Texture() {
    }
}
