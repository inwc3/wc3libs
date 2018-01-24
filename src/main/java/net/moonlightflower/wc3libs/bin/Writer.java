package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;

public abstract class Writer<EncodingFormat extends net.moonlightflower.wc3libs.bin.Format> {
    public abstract EncodingFormat getAutoFormat();

    private EncodingFormat _format = getAutoFormat();

    @Nonnull
    public EncodingFormat getFormat() {
        return _format;
    }

    public void setFormat(@Nonnull EncodingFormat val) {
        _format = val;
    }

    private final Wc3BinOutputStream _stream;

    @Nonnull
    public Wc3BinOutputStream getStream() {
        return _stream;
    }

    public Writer(@Nonnull Wc3BinOutputStream stream) {
        _stream = stream;
    }

    public Writer(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
        _stream = stream;
        _format = format;
    }
}
