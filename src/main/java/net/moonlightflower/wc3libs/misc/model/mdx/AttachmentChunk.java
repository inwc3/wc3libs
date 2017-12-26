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

    public void addAttachment(@Nonnull Attachment val) {
        if (!_attachments.contains(val)) {
            _attachments.add(val);
        }
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Header header = new Header(stream);

        long endPos = stream.getPos() + header.getSize();

        while (stream.getPos() < endPos) {
            addAttachment(new Attachment(stream));
        }
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        Header header = new Header();

        header.write(stream);

        for (Attachment attachment : getAttachments()) {
            attachment.write(stream);
        }

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
        write(stream);
    }

    public AttachmentChunk(@Nonnull Wc3BinInputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        this();

        read(stream, format);
    }

    public AttachmentChunk() {

    }
}
