package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;

public abstract class Chunk {
    public abstract Id getToken();

    public class Header {
        private long _size;

        public long getSize() {
            return _size;
        }

        public void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            Id header_token = stream.readId("header_token");

            if (!header_token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + header_token + ")");

            _size = stream.readUInt32("header_size");
        }

        public Header(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
            read_0x0(stream);
        }
    }
}
