package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ModelInfoChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("MODL");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private String _name;
    private String _animFileName;

    private Extent _extent;
    private long _blendTime;

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Header header = new Header(stream);

        _name = new String(stream.readBytes(80), StandardCharsets.US_ASCII);
        _animFileName = new String(stream.readBytes(260), StandardCharsets.US_ASCII);

        _extent = new Extent(stream);

        _blendTime = stream.readUInt32();
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
        stream.writeBytes(Arrays.copyOf(_name.getBytes(StandardCharsets.US_ASCII), 80));
        stream.writeBytes(Arrays.copyOf(_animFileName.getBytes(StandardCharsets.US_ASCII), 260));
        _extent.write(stream);
        stream.writeUInt32(_blendTime);
    }

    public void read(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        switch (format.toEnum()) {
            case MDX_0x0:
                read_0x0(stream);

                break;
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) {
        switch (format.toEnum()) {
            case AUTO:
            case MDX_0x0:
                write_0x0(stream);

                break;
        }
    }

    public ModelInfoChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public ModelInfoChunk() {

    }
}
