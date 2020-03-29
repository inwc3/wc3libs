package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    protected class ByteList {
        protected List<Byte> _bytes = new ArrayList<>();

        public long size() {
            return _bytes.size();
        }

        public Byte get(long index) {
            if (index > Integer.MAX_VALUE) throw new UnsupportedOperationException("index out of bounds " + index);

            return _bytes.get((int) index);
        }

        public void set(long index, Byte val) {
            if (index > Integer.MAX_VALUE) throw new UnsupportedOperationException("index out of bounds " + index);

            _bytes.set((int) index, val);
        }

        public void add(Byte val) {
            _bytes.add(val);
        }

        public ByteList() {

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
