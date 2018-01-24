package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sequence extends MDXObject {
    private String _name = "unset";

    @Nonnull
    public String getName() {
        return _name;
    }

    public void setName(@Nonnull String name) {
        _name = name;
    }

    private long _intervalStart = 0;

    public long getIntervalStart() {
        return _intervalStart;
    }

    public void setIntervalStart(long intervalStart) {
        _intervalStart = intervalStart;
    }

    private long _intervalEnd = 0;

    public long getIntervalEnd() {
        return _intervalEnd;
    }

    public void setIntervalEnd(long intervalEnd) {
        _intervalEnd = intervalEnd;
    }

    private float _moveSpeed = 0;

    public float getMoveSpeed() {
        return _moveSpeed;
    }

    public void setMoveSpeed(float moveSpeed) {
        _moveSpeed = moveSpeed;
    }

    private long _flags = 0;

    public long getFlags() {
        return _flags;
    }

    public void setFlags(long flags) {
        _flags = flags;
    }

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

    public Extent getExtent() {
        return _extent;
    }

    public void setExtent(@Nonnull Extent extent) {
        _extent = extent;
    }

    private float _rarity = 0F;

    public float getRarity() {
        return _rarity;
    }

    public void setRarity(float rarity) {
        _rarity = rarity;
    }

    private long _syncPt = 0;

    public long getSyncPt() {
        return _syncPt;
    }

    public void setSyncPt(long syncPt) {
        _syncPt = syncPt;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeBytes(Arrays.copyOf(_name.getBytes(StandardCharsets.US_ASCII), 80));
        stream.writeUInt32(_intervalStart);
        stream.writeUInt32(_intervalEnd);
        stream.writeFloat32(_moveSpeed);
        stream.writeUInt32(_flags);
        stream.writeFloat32(_rarity);
        stream.writeUInt32(_syncPt);

        _extent.write(stream);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
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

    public Sequence() {
        _extent = new Extent();
    }
}
