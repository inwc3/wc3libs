package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Opaque wrapper for the {@code war3map.w3grp} file introduced by the 3.0
 * World Editor. Its record semantics are not yet known, so preserving every
 * byte is safer than assigning names to the three zero dwords in an empty map.
 */
public class W3GRP {
    public final static File GAME_PATH = new File("war3map.w3grp");

    private byte[] _data = new byte[0];

    @Nonnull
    public byte[] getData() {
        return Arrays.copyOf(_data, _data.length);
    }

    public void setData(@Nonnull byte[] data) {
        _data = Arrays.copyOf(data, data.length);
    }

    public void read(@Nonnull Wc3BinInputStream stream) throws IOException {
        setData(stream.readBytes(Math.toIntExact(stream.size() - stream.getPos()), "w3grpData"));
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeBytes(_data);
    }

    public W3GRP(@Nonnull Wc3BinInputStream stream) throws IOException {
        read(stream);
    }

    public W3GRP(@Nonnull File file) throws IOException {
        try (Wc3BinInputStream stream = new Wc3BinInputStream(file)) {
            read(stream);
        }
    }

    public W3GRP() {
    }
}
