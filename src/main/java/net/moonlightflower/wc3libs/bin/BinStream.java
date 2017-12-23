package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinStream {
    public int getPos() {
        return _pos;
    }

    public byte get(int index) {
        return _bytes.get(index);
    }

    public int size() {
        return _bytes.size();
    }

    public boolean eof() {
        return (_pos >= _bytes.size());
    }

    public static class StreamException extends IOException {
        private BinStream _stream;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (String s : _stream.getLogLines()) {
                sb.append(System.lineSeparator());

                sb.append(s);
            }

            return sb.toString();
        }

        @Nonnull
        public BinStream getStream() {
            return _stream;
        }

        public StreamException(@Nonnull BinStream stream) {
            _stream = stream;
        }
    }

    public void log(String type, String label, Object val, String valFormat) {
        int startPos = _logStartPos;
        int endPos = _pos;

        int len = endPos - startPos;

        if (label == null) label = "?";

        StringBuilder bytesPart = new StringBuilder("");

        for (int i = startPos; i < startPos + len; i++) {
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

    protected int _pos = 0;
    protected List<Byte> _bytes = new ArrayList<>();

    protected List<String> _logLines = new ArrayList<>();

    @Nonnull
    public List<String> getLogLines() {
        return _logLines;
    }

    public void log(String s) {
        _logLines.add(s);
    }

    private int _logStartPos = 0;

    public void logBegin() {
        _logStartPos = _pos;
    }

    public void log(String type, String label, Object val) {
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
