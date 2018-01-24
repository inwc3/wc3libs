package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ModelInfoChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("MODL");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private String _name = "unset";

    @Nonnull
    public String getName() {
        return _name;
    }

    public void setName(@Nonnull String name) {
        _name = name;
    }

    private String _animFileName = "unset";

    @Nonnull
    public String getAnimFileName() {
        return _animFileName;
    }

    public void setAnimFileName(@Nonnull String animFileName) {
        _animFileName = animFileName;
    }

    private Extent _extent;

    @Nonnull
    public Extent getExtent() {
        return _extent;
    }

    public void setExtent(@Nonnull Extent extent) {
        _extent = extent;
    }

    private long _blendTime = 0;

    public long getBlendTime() {
        return _blendTime;
    }

    public void setBlendTime(long blendTime) {
        _blendTime = blendTime;
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Header header = new Header(stream);

        _name = new String(stream.readBytes(80), StandardCharsets.US_ASCII);
        _animFileName = new String(stream.readBytes(260), StandardCharsets.US_ASCII);

        _extent = new Extent(stream);

        _blendTime = stream.readUInt32();
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        Header header = new Header();

        header.write(stream);

        stream.writeBytes(Arrays.copyOf(_name.getBytes(StandardCharsets.US_ASCII), 80));
        stream.writeBytes(Arrays.copyOf(_animFileName.getBytes(StandardCharsets.US_ASCII), 260));

        _extent.write(stream);

        stream.writeUInt32(_blendTime);

        header.rewrite();
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

    public ModelInfoChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public ModelInfoChunk() {
        _extent = new Extent();
    }
}
