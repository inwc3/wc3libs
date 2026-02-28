package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.Stringable;
import net.moonlightflower.wc3libs.dataTypes.app.War3Char;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class Wc3BinOutputStream extends BinOutputStream {
    public void writeUByte(int val) {
        writeByte((byte) val);
    }

    public void writeInt8(short val) {
        writeByte((byte) (val & 0xFF));
    }

    public void writeUInt8(int val) {
        writeInt8((short) val);
    }

    public void writeInt16(short val) {
        writeByte((byte) (val & 0xFF));
        writeByte((byte) ((val >>> 8) & 0xFF));
    }

    public void writeUInt16(int val) {
        writeInt16((short) val);
    }

    public void writeInt32(int val) {
        writeByte((byte) (val & 0xFF));
        writeByte((byte) ((val >>> 8) & 0xFF));
        writeByte((byte) ((val >>> 16) & 0xFF));
        writeByte((byte) ((val >>> 24) & 0xFF));
    }

    public void writeInt32(@Nonnull War3Int val) {
        writeInt32(val.getVal());
    }

    public void writeUInt32(long val) {
        writeInt32((int) val);
    }

    public void writeFloat32(float val) {
        writeInt32(Float.floatToIntBits(val));
    }

    public void writeFloat32(@Nullable War3Real val) {
        writeFloat32(val != null ? val.toFloat() : 0F);
    }

    public void writeReal(@Nullable War3Real val) {
        writeFloat32(val);
    }

    public void writeChar(char val) {
        writeByte((byte) val);
    }

    public void writeChar(@Nullable War3Char val) {
        writeChar(val != null ? val.getVal() : '\0');
    }

    @Nullable
    public byte[] stringToByteArray(@Nullable String val) {
        if (val == null) return null;

        return val.getBytes(StandardCharsets.UTF_8);
    }

    public void writeString(@Nullable String val) {
        if (val == null) val = "";

        byte[] valBytes = stringToByteArray(val);

        writeBytes(valBytes);

        writeByte((byte) 0);
    }

    public void writeString(@Nullable Stringable val) {
        if (val == null)
            writeString((String) null);
        else
            writeString(val.toString());
    }

    public void writeId(@Nullable Id val) {
        if (val == null) val = Id.valueOf("\0\0\0\0");

        byte[] valBytes = val.toString().getBytes(StandardCharsets.US_ASCII);
        int len = Math.min(4, valBytes.length);

        for (int i = 0; i < len; i++) {
            writeByte(valBytes[i]);
        }
        for (int i = len; i < 4; i++) {
            writeByte((byte) 0);
        }
    }

    public Wc3BinOutputStream(@Nonnull OutputStream outStream) {
        super(outStream);
    }

    public Wc3BinOutputStream(@Nonnull File file) throws IOException {
        super(file);
    }
}
