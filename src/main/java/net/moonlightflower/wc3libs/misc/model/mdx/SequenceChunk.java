package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

public class SequenceChunk {
    public static Id TOKEN = Id.valueOf("SEQS");

    private String _name;
    private long _intervalStart;
    private long _intervalEnd;
    private float _moveSpeed;
    private long _flags;

    public boolean isLooping() {
        return ((_flags & 0x1) > 0);
    }

    public void setLooping(boolean val) {
        if (val)
            _flags |= 0x1;
        else
            _flags &= ~0x1;
    }

    private Extent _extent;
    private float _rarity;
    private long _syncPt;

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        _name = new String(stream.readBytes(80, "name"), StandardCharsets.US_ASCII);
        _intervalStart = stream.readUInt32("intervalStart");
        _intervalEnd = stream.readUInt32("intervalEnd");
        _moveSpeed = stream.readFloat8("moveSpeed");
        _flags = stream.readUInt32("flags");
        _rarity = stream.readFloat8("rarity");
        _syncPt = stream.readUInt32("syncPoint");

        _extent = new Extent(stream);
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
        stream.writeBytes(_name.getBytes(StandardCharsets.US_ASCII));
        stream.writeUInt(_intervalStart);
        stream.writeUInt(_intervalEnd);
        stream.writeFloat(_moveSpeed);
        stream.writeUInt(_flags);
        stream.writeFloat(_rarity);
        stream.writeUInt(_syncPt);

        _extent.write(stream);
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

    public SequenceChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public SequenceChunk() {

    }
}
