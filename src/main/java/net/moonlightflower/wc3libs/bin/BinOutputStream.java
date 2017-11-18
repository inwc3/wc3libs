package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.List;

public class BinOutputStream extends BinStream {
    private void ensureCap(int size) {
        int toAdd = size - _bytes.size();

        for (int i = 1; i <= toAdd; i++) {
            _bytes.add(null);
        }
    }

    public void writeByte(byte val) {
        ensureCap(_pos + 1);

        _bytes.set(_pos++, val);
    }

    public void writeBytes(byte[] vals) {
        ensureCap(_pos + vals.length);

        for (byte val : vals) {
            _bytes.set(_pos++, val);
        }
    }

    private void write(@Nonnull OutputStream outStream) throws IOException {
        List<Byte> bytes = _bytes;

        byte[] buf = new byte[bytes.size()];

        for (int i = 0; i < bytes.size(); i++) {
            buf[i] = bytes.get(i);
        }

        outStream.write(buf);

        outStream.flush();
    }

    private OutputStream _outStream = null;

    public void close() throws IOException {
        write(_outStream);

        _outStream.close();
    }

    public BinOutputStream(@Nonnull OutputStream outStream) {
        _outStream = outStream;
    }

    public BinOutputStream(@Nonnull File file) throws IOException {
        _outStream = Orient.createFileOutputStream(file);
    }
}
