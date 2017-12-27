package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sound extends MDXObject {
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

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeBytes(Arrays.copyOf(_fileName.getBytes(StandardCharsets.US_ASCII), 260));
        stream.writeFloat32(_volume);
        stream.writeFloat32(_pitch);
        stream.writeUInt32(_flags);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Sound(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _fileName = new String(stream.readBytes(260, "fileName"), StandardCharsets.US_ASCII);
        _volume = stream.readFloat32("volume");
        _pitch = stream.readFloat32("pitch");
        _flags = stream.readUInt32("flags");
    }
}
