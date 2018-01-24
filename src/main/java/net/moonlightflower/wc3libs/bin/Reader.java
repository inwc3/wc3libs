package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;

public abstract class Reader<EncodingFormat extends Format> {
    public abstract EncodingFormat getAutoFormat();

    private EncodingFormat _format = getAutoFormat();

    @Nonnull
    public EncodingFormat getFormat() {
        return _format;
    }

    public void setFormat(@Nonnull EncodingFormat val) {
        _format = val;
    }

    private final Wc3BinInputStream _stream;

    @Nonnull
    public Wc3BinInputStream getStream() {
        return _stream;
    }

    public Reader(@Nonnull Wc3BinInputStream stream) {
        _stream = stream;
    }

    public Reader(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) {
        _stream = stream;
        _format = format;
    }
}
