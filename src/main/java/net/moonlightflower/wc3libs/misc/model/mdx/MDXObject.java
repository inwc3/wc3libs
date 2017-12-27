package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public abstract class MDXObject {
    protected static class SizeWriter {
        private long _size = 0L;

        public long getSize() {
            return _size;
        }

        private long _sizePos = 0L;
        private Wc3BinOutputStream _stream;

        public void rewrite() {
            long endPos = _stream.getPos();

            _stream.setPos(_sizePos);

            _stream.writeUInt32(endPos - _sizePos);

            _stream.setPos(endPos);
        }

        public void write_0x0(@Nonnull Wc3BinOutputStream stream) {
            _stream = stream;

            _sizePos = _stream.getPos();

            _stream.writeUInt32(_size);
        }

        public void write(@Nonnull Wc3BinOutputStream stream) {
            write_0x0(stream);
        }

        public SizeWriter() {

        }
    }

    public abstract void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException;
    public abstract void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException;
}
