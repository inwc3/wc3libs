package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.Stringable;
import net.moonlightflower.wc3libs.dataTypes.app.Char;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class Wc3BinOutputStream extends BinOutputStream {
    public void writeUByte(int val) {
        writeByte((byte) val);
    }

    private final static byte[] _shortBytes = new byte[2];

    private final static ByteBuffer _shortBuf = ByteBuffer.wrap(_shortBytes);

    public void writeShort(short val) {
        _shortBuf.rewind();
        _shortBuf.order(ByteOrder.LITTLE_ENDIAN);

        _shortBuf.putShort(val);

        writeBytes(_shortBytes);
    }

    public void writeUShort(int val) {
        writeShort((short) val);
    }

    private final static byte[] _intBytes = new byte[4];

    private final static ByteBuffer _intBuf = ByteBuffer.wrap(_intBytes);

    public void writeInt(int val) {
        _intBuf.rewind();
        _intBuf.order(ByteOrder.LITTLE_ENDIAN);

        _intBuf.putInt(val);

        writeBytes(_intBytes);
    }

    public void writeInt(Int val) {
        writeInt(val.getVal());
    }

    public void writeUInt(long val) {
        writeInt((int) val);
    }

    public void writeFloat(float val) {
        ByteBuffer buf = ByteBuffer.allocate(4);

        buf.order(ByteOrder.LITTLE_ENDIAN);

        buf.putFloat(val);

        writeBytes(buf.array());
    }

    public void writeFloat(Real val) {
        writeFloat(val.toFloat());
    }

    public void writeReal(Real val) {
        writeFloat(val);
    }

    public void writeChar(char val) {
        writeByte((byte) val);
    }

    public void writeChar(Char val) {
        writeChar(val != null ? val.getVal() : '\0');
    }

    public byte[] stringToByteArray(String val) {
        return val.getBytes(StandardCharsets.UTF_8);
    }

    public void writeString(String val) {
        if (val == null) val = "";

        byte[] valBytes = stringToByteArray(val);

        writeBytes(valBytes);

        writeByte((byte) 0);
    }

    public void writeString(Stringable val) {
        if (val == null)
            writeString((String) null);
        else
            writeString(val.toString());
    }

    public void writeId(Id val) {
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
