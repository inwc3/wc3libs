package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class BinOutputStream extends BinStream implements AutoCloseable {
    private void ensureCap(long size) {
        long toAdd = size - _bytes.size();

        for (long i = 1; i <= toAdd; i++) {
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
        ByteList bytes = _bytes;

        long size = size();

        while (size > 0) {
            int sizeI = (int) size;

            byte[] buf = new byte[sizeI];

            for (int i = 0; i < bytes.size(); i++) {
                buf[i] = bytes.get(i);
            }

            outStream.write(buf);

            size -= sizeI;
        }

        outStream.flush();
    }

    private final OutputStream _outStream;

    @Override
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
