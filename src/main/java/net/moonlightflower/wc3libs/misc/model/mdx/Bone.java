package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class Bone extends MDXObject {
    private Node _node;

    @Nonnull
    public Node getNode() {
        return _node;
    }

    public void setNode(@Nonnull Node node) {
        _node = node;
    }

    private long _geosetId = 0;

    public long getGeosetId() {
        return _geosetId;
    }

    public void setGeosetId(long geosetId) {
        _geosetId = geosetId;
    }

    private long _geosetAnimId = 0;

    public long getGeosetAnimId() {
        return _geosetAnimId;
    }

    public void setGeosetAnimId(long geosetAnimId) {
        _geosetAnimId = geosetAnimId;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        _node.write(stream);
        stream.writeUInt32(_geosetId);
        stream.writeUInt32(_geosetAnimId);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Bone(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _node = new Node(stream);
        _geosetId = stream.readUInt32("geosetId");
        _geosetAnimId = stream.readUInt32("geosetAnimId");
    }

    public Bone() {
        _node = new Node();
    }
}
