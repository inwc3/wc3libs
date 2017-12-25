package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sound {
    private String _fileName;

    public String getFileName() {
        return _fileName;
    }

    private float _volume;

    public float getVolume() {
        return _volume;
    }

    private float _pitch;

    public float getPitch() {
        return _pitch;
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeBytes(Arrays.copyOf(_fileName.getBytes(StandardCharsets.US_ASCII), 260));
        stream.writeFloat8(_volume);
        stream.writeFloat8(_pitch);
        stream.writeUInt32(_flags);
    }

    public Sound(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _fileName = new String(stream.readBytes(260, "fileName"), StandardCharsets.US_ASCII);
        _volume = stream.readFloat8("volume");
        _pitch = stream.readFloat8("pitch");
        _flags = stream.readUInt32("flags");
    }
}
