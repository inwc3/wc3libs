package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class VersionChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("VERS");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private long _version;

    public long getVersion() {
        return _version;
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Header header = new Header(stream);

        _version = stream.readUInt32("version");
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
        stream.writeId(TOKEN);
        stream.writeUInt32(4);

        stream.writeUInt32(_version);
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

    public VersionChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public VersionChunk() {

    }
}
