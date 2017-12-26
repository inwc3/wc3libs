package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public abstract class Chunk {
    public abstract Id getToken();

    public class Header {
        private long _size = 0L;

        public long getSize() {
            return _size;
        }

        private long _sizePos = 0L;
        private long _afterPos = 0L;
        private Wc3BinOutputStream _stream;

        public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
            _stream = stream;

            _stream.writeId(getToken());

            _sizePos = _stream.getPos();

            _stream.writeUInt32(_size);

            _afterPos = _stream.getPos();
        }

        public void rewrite() {
            long endPos = _stream.getPos();

            _stream.setPos(_sizePos);

            _stream.writeUInt32(endPos - _afterPos);

            _stream.setPos(endPos);
        }

        public void write(@Nonnull Wc3BinOutputStream stream) {
            write_0x0(stream);
        }

        public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            Id header_token = stream.readId("header_token");

            if (!header_token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + header_token + ")");

            _size = stream.readUInt32("header_size");
        }

        public Header(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            this();

            read_0x0(stream);
        }

        public Header() {

        }
    }

    public abstract void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException;
    public abstract void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException;
}
