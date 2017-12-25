package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AttachmentChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("ATCH");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<Attachment> _attachments = new ArrayList<>();

    public List<Attachment> getAttachments() {
        return new ArrayList<>(_attachments);
    }

    public void addAttachment(@Nonnull Helper val) {
        if (!_attachments.contains(val)) {
            _attachments.add(val);
        }
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Header header = new Header(stream);

        long endPos = stream.getPos() + header.getSize();

        while (stream.getPos() < endPos) {
            addAttachment(new Helper(stream));
        }
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        stream.writeId(TOKEN);

        long sizePos = stream.getPos();

        stream.writeUInt32(0L);

        long startPos = stream.getPos();

        for (Attachment attachment : getAttachments()) {
            attachment.write(stream);
        }

        long endPos = stream.getPos();

        stream.setPos(sizePos);

        stream.writeUInt32(endPos - startPos);

        stream.setPos(endPos);
    }

    public void read(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        switch (format.toEnum()) {
            case MDX_0x0:
                read_0x0(stream);

                break;
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        switch (format.toEnum()) {
            case AUTO:
            case MDX_0x0:
                write_0x0(stream);

                break;
        }
    }

    public AttachmentChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public AttachmentChunk() {

    }
}