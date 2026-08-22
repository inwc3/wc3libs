package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class BinOutputStream extends BinStream implements AutoCloseable {
    public byte[] getBytes() {
        return _bytes.toArray();
    }

    public void writeByte(byte val) {
        _bytes.set(_pos++, val);
    }

    public void writeBytes(byte[] vals) {
        if (_pos == _bytes.size()) {
            // The common case: appending at the end, in one copy.
            _bytes.addAll(vals, vals.length);

            _pos += vals.length;

            return;
        }

        for (byte val : vals) {
            _bytes.set(_pos++, val);
        }
    }

    private void write(@Nonnull OutputStream outStream) throws IOException {
        _bytes.writeTo(outStream);

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
