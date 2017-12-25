package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;

public class GlobalSequence {
    private long _duration;

    public long getDuration() {
        return _duration;
    }

    public void setDuration(long val) {
        _duration = val;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt32(_duration);
    }

    public GlobalSequence(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _duration = stream.readUInt32("duration");
    }
}
