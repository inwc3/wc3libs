package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Attachment {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    private String _path;

    public String getPath() {
        return _path;
    }

    private long _attachmentId;

    public long getAttachmentId() {
        return _attachmentId;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        _node.write(stream);
        stream.writeBytes(Arrays.copyOf(_path.getBytes(), 260));
        stream.writeUInt32(_attachmentId);

        //TODO: KATV
    }

    public Attachment(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _path = new String(stream.readBytes(260), StandardCharsets.US_ASCII);
        _attachmentId = stream.readUInt32("attachmentId");

        //TODO: KATV
    }
}
