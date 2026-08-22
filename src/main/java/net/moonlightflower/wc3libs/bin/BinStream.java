package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static net.moonlightflower.wc3libs.port.LogConfig.LOG_ENABLED;

public class BinStream {
    public long getPos() {
        return _pos;
    }

    public byte get(long index) {
        return _bytes.get(index);
    }

    public long size() {
        return _bytes.size();
    }

    public boolean eof() {
        return (_pos >= _bytes.size());
    }

    public static class StreamException extends IOException {
        private BinStream _stream;

        @Nonnull
        public BinStream getStream() {
            return _stream;
        }

        @Override
        public String getMessage() {
            StringBuilder sb = new StringBuilder();

            for (String s : _stream.getLogLines()) {
                sb.append(System.lineSeparator());

                sb.append(s);
            }

            return sb.toString();
        }

        @Override
        public String toString() {
            return getMessage();
        }

        public StreamException(@Nonnull BinStream stream, @Nonnull String msg) {
            this(stream);

            _stream.log(msg);
        }

        public StreamException(@Nonnull BinStream stream) {
            _stream = stream;
        }
    }

    protected long _pos = 0;

    public void setPos(long pos) {
        _pos = pos;
    }

    /**
     * The bytes of the file being read or written.
     * <p>
     * This used to be an {@code ArrayList<Byte>}, which boxes every single byte:
     * a megabyte of terrain or model data became a million {@code Byte} objects
     * plus a million references to them, so the buffer cost something like
     * twenty times the file it held and every read paid an unboxing indirection.
     * A {@code byte[]} that grows geometrically costs the file's own size.
     * <p>
     * Growing also zero-fills, which closes a hole in the writer: seeking past
     * the end and writing used to pad with {@code null} entries, and turning one
     * of those back into a {@code byte} threw.
     */
    protected static final class ByteList {
        private final static int MIN_CAPACITY = 64;

        private byte[] _data;
        private int _size;

        public long size() {
            return _size;
        }

        public byte get(long index) {
            checkIndex(index);

            return _data[(int) index];
        }

        public void set(long index, byte val) {
            if (index < 0) throw new IndexOutOfBoundsException("index out of bounds " + index);
            if (index >= Integer.MAX_VALUE) throw new UnsupportedOperationException("index out of bounds " + index);

            ensureSize(index + 1);

            _data[(int) index] = val;
        }

        public void add(byte val) {
            set(_size, val);
        }

        public void addAll(byte[] vals, int len) {
            long start = _size;

            ensureSize(start + len);

            System.arraycopy(vals, 0, _data, (int) start, len);
        }

        /**
         * Copies {@code len} bytes starting at {@code srcPos} into {@code dst}.
         *
         * @throws IndexOutOfBoundsException if the range is not all present.
         */
        public void copyInto(long srcPos, byte[] dst, int dstPos, int len) {
            if (srcPos < 0 || len < 0 || srcPos + len > _size) {
                throw new IndexOutOfBoundsException("range out of bounds " + srcPos + ".." + (srcPos + len));
            }

            System.arraycopy(_data, (int) srcPos, dst, dstPos, len);
        }

        /**
         * @return the index of the first zero byte at or after {@code from}, or
         *         {@link #size()} if there is none.
         */
        public long indexOfZero(long from) {
            for (int i = (int) Math.max(0, from); i < _size; i++) {
                if (_data[i] == 0) return i;
            }

            // Never before `from`, so a position already past the end yields an
            // empty range rather than a negative one.
            return Math.max(from, _size);
        }

        @Nonnull
        public byte[] toArray() {
            return Arrays.copyOf(_data, _size);
        }

        public void writeTo(@Nonnull OutputStream outStream) throws IOException {
            outStream.write(_data, 0, _size);
        }

        private void checkIndex(long index) {
            if (index < 0 || index >= _size) throw new IndexOutOfBoundsException("index out of bounds " + index);
        }

        private void ensureSize(long size) {
            if (size > Integer.MAX_VALUE) throw new UnsupportedOperationException("size out of bounds " + size);

            if (size > _data.length) {
                int capacity = Math.max(MIN_CAPACITY, _data.length);

                while (capacity < size) {
                    capacity = (capacity > Integer.MAX_VALUE / 2) ? Integer.MAX_VALUE : capacity * 2;
                }

                _data = Arrays.copyOf(_data, capacity);
            }

            if (size > _size) _size = (int) size;
        }

        public ByteList() {
            _data = new byte[MIN_CAPACITY];
        }
    }

    protected ByteList _bytes = new ByteList();
    protected List<String> _logLines = new ArrayList<>();

    @Nonnull
    public List<String> getLogLines() {
        return _logLines;
    }

    public void log(@Nullable String s) {
        _logLines.add(s);
    }

    public void log(@Nonnull String type, @Nullable String label, Object val, String valFormat) {
        if (!LOG_ENABLED) return;

        long startPos = _logStartPos;
        long endPos = _pos;

        long len = endPos - startPos;

        if (label == null) label = "?";

        StringBuilder bytesPart = new StringBuilder("");

        for (long i = startPos; i < startPos + len; i++) {
            if (bytesPart.length() > 0) bytesPart.append(" ");

            if (i < _bytes.size()) {
                bytesPart.append(String.format("%02X", _bytes.get(i)));
            } else {
                bytesPart.append("?");
            }
        }

        String line = String.format("%s [%s]: %s [%s]", label, type, String.format(valFormat, val), bytesPart);

        StringBuilder sb = new StringBuilder();

        for (String group : _curGroupStack) {
            sb.append(group);
            sb.append("_");
        }

        sb.append(line);

        _logLines.add(sb.toString());
    }

    private long _logStartPos = 0;

    public void logBegin() {
        _logStartPos = _pos;
    }

    public void log(@Nonnull String type, @Nullable String label, Object val) {
        log(type, label, val, "%s");
    }

    private Stack<String> _curGroupStack = new Stack<>();

    public void beginGroup(@Nonnull String label) {
        _curGroupStack.push(label);
    }

    public void endGroup() {
        _curGroupStack.pop();
    }

    public void printLog(@Nonnull OutputStream outStream) throws IOException {
        for (String line : getLogLines()) {
            outStream.write(line.getBytes(StandardCharsets.UTF_8));
            outStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
        }
    }
}
