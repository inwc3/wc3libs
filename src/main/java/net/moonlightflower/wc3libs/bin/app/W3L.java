package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Light Editor settings introduced by the 3.0 World Editor.
 * <p>
 * The two model paths and three option dwords are structurally established by
 * both empty and populated fixtures. Their finer UI meanings still need
 * one-change differential saves, so the public names remain positional.
 */
public class W3L {
    public final static File GAME_PATH = new File("war3map.w3l");
    private final static byte[] MAGIC = "W3L!".getBytes(StandardCharsets.US_ASCII);

    private int _version = 3;
    private final String[] _modelPaths = { "", "" };
    private final int[] _options = new int[3];
    private byte[] _trailingData = new byte[0];

    public int getVersion() {
        return _version;
    }

    public void setVersion(int version) {
        _version = version;
    }

    @Nonnull
    public String getModelPath(int index) {
        return _modelPaths[index];
    }

    public void setModelPath(int index, @Nonnull String path) {
        _modelPaths[index] = path;
    }

    public int getOption(int index) {
        return _options[index];
    }

    public void setOption(int index, int val) {
        _options[index] = val;
    }

    @Nonnull
    public byte[] getTrailingData() {
        return Arrays.copyOf(_trailingData, _trailingData.length);
    }

    public void read(@Nonnull Wc3BinInputStream stream) throws IOException {
        byte[] magic = stream.readBytes(MAGIC.length, "magic");
        if (!Arrays.equals(magic, MAGIC)) throw new IOException("invalid W3L signature");

        setVersion(stream.readInt32("version"));
        for (int i = 0; i < _modelPaths.length; i++) setModelPath(i, stream.readString("modelPath" + i));
        for (int i = 0; i < _options.length; i++) setOption(i, stream.readInt32("option" + i));
        _trailingData = stream.readBytes(Math.toIntExact(stream.size() - stream.getPos()), "trailingData");
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeBytes(MAGIC);
        stream.writeInt32(getVersion());
        for (String path : _modelPaths) stream.writeString(path);
        for (int option : _options) stream.writeInt32(option);
        stream.writeBytes(_trailingData);
    }

    public W3L(@Nonnull Wc3BinInputStream stream) throws IOException {
        read(stream);
    }

    public W3L(@Nonnull File file) throws IOException {
        try (Wc3BinInputStream stream = new Wc3BinInputStream(file)) {
            read(stream);
        }
    }

    public W3L() {
    }
}
