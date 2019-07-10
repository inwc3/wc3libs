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
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class Wc3BinOutputStream extends BinOutputStream {
    public void writeUByte(int val) {
        writeByte((byte) val);
    }

    private final static byte[] _shortBytes = new byte[2];

    private final static ByteBuffer _shortBuf = ByteBuffer.wrap(_shortBytes);

    public void writeInt8(short val) {
        writeByte((byte) (val & 0xFF));
    }

    public void writeUInt8(int val) {
        writeInt8((short) val);
    }

    public void writeInt16(short val) {
        ((Buffer) _shortBuf).rewind();
        _shortBuf.order(ByteOrder.LITTLE_ENDIAN);

        _shortBuf.putShort(val);

        writeBytes(_shortBytes);
    }

    public void writeUInt16(int val) {
        writeInt16((short) val);
    }

    private final static byte[] _intBytes = new byte[4];

    private final static ByteBuffer _intBuf = ByteBuffer.wrap(_intBytes);

    public void writeInt32(int val) {
        ((Buffer) _intBuf).rewind();
        _intBuf.order(ByteOrder.LITTLE_ENDIAN);

        _intBuf.putInt(val);

        writeBytes(_intBytes);
    }

    public void writeInt32(@Nonnull War3Int val) {
        writeInt32(val.getVal());
    }

    public void writeUInt32(long val) {
        writeInt32((int) val);
    }

    public void writeFloat32(float val) {
        ByteBuffer buf = ByteBuffer.allocate(4);

        buf.order(ByteOrder.LITTLE_ENDIAN);

        buf.putFloat(val);

        writeBytes(buf.array());
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

        byte[] sub = val.toString().getBytes(StandardCharsets.US_ASCII);

        writeBytes(sub);
    }

    public Wc3BinOutputStream(@Nonnull OutputStream outStream) {
        super(outStream);
    }

    public Wc3BinOutputStream(@Nonnull File file) throws IOException {
        super(file);
    }
}
