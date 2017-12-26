package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sequence {
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

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeBytes(Arrays.copyOf(_name.getBytes(StandardCharsets.US_ASCII), 80));
        stream.writeUInt32(_intervalStart);
        stream.writeUInt32(_intervalEnd);
        stream.writeFloat32(_moveSpeed);
        stream.writeUInt32(_flags);
        stream.writeFloat32(_rarity);
        stream.writeUInt32(_syncPt);

        _extent.write(stream);
    }

    public Sequence(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _name = new String(stream.readBytes(80, "name"), StandardCharsets.US_ASCII);
        _intervalStart = stream.readUInt32("intervalStart");
        _intervalEnd = stream.readUInt32("intervalEnd");
        _moveSpeed = stream.readFloat32("moveSpeed");
        _flags = stream.readUInt32("flags");
        _rarity = stream.readFloat32("rarity");
        _syncPt = stream.readUInt32("syncPoint");

        _extent = new Extent(stream);
    }
}
