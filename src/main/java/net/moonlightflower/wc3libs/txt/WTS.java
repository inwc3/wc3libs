package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.misc.LosslessUTF8;
import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Trigger String file is a plain text file containing strings key-value pairs that can be referenced
 * from other files such as the mapscript.
 * Pairs are delimited by single lines of curly open/close brackets.
 * See wc3data/WTS for an example.
 */
public class WTS {
    public final static File GAME_PATH = new File("war3map.WTS");
    public final static File CAMPAIGN_PATH = new File("war3campaign.WTS");

    private static final Pattern ENTRY_HEADER_PATTERN = Pattern.compile(
        "(?im)^[\\t ]*STRING[\\t ]+(\\d+)[\\t ]*\\r?\\n" +
            "(?:(?:[\\t ]*//[^\\r\\n]*|[\\t ]*)\\r?\\n)*" +
            "[\\t ]*\\{[\\t ]*\\r?\\n"
    );

    private static final Pattern ENTRY_END_PATTERN = Pattern.compile(
        "(?m)^[\\t ]*\\}[\\t ]*(?:\\r?\\n|\\z)"
    );

    private static final class EntrySpan {
        private final int key;
        private final int valueStart;
        private final int valueEnd;

        private EntrySpan(int key, int valueStart, int valueEnd) {
            this.key = key;
            this.valueStart = valueStart;
            this.valueEnd = valueEnd;
        }
    }

    private final Map<Integer, String> _vals = new LinkedHashMap<>();

    // Preserve style from input. Default for newly-created WTS.
    private String _lineEnding = "\r\n";
    private boolean _utf8Bom;
    private String _sourceText;
    private final List<EntrySpan> _sourceSpans = new ArrayList<>();
    private final Map<Integer, String> _sourceVals = new LinkedHashMap<>();
    private boolean _sourcePatchable = true;

    @Nonnull
    public Map<Integer, String> getKeyedEntries() {
        return _vals;
    }

    @Nonnull
    public Map<String, String> getNamedEntries() {
        Map<String, String> res = new LinkedHashMap<>();

        for (Map.Entry<Integer, String> valEntry : _vals.entrySet()) {
            int key = valEntry.getKey();
            String val = valEntry.getValue();

            res.put(String.format("TRIGSTR_%03d", key), val);
        }

        return res;
    }

    @Nonnull
    public TXT toTXT() {
        TXT txt = new TXT();

        for (Map.Entry<String, String> entry : getNamedEntries().entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();

            txt.set(key, val);
        }

        return txt;
    }

    @Nonnull
    public String getEntry(int key) {
        return _vals.getOrDefault(key, "");
    }

    public void addEntry(int key, @Nullable String val) {
        _vals.put(key, val);
    }

    public void removeEntry(int key) {
        _vals.remove(key);
    }

    public void removeEntry(String object) {
        _vals.values().remove(object);
    }

    public void write(@Nonnull File file) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(fos);
        }
    }

    public void write(@Nonnull OutputStream outputStream) throws IOException {
        String text = renderPreservingSource();
        if (text == null) text = renderCanonical();
        if (_utf8Bom) text = '\uFEFF' + text;

        outputStream.write(LosslessUTF8.encode(text));
        outputStream.flush();
    }

    @Nullable
    private String renderPreservingSource() {
        if (_sourceText == null) return null;
        if (_sourceVals.equals(_vals)) return _sourceText;
        if (!_sourcePatchable || !_sourceVals.keySet().equals(_vals.keySet())) return null;

        StringBuilder result = new StringBuilder(_sourceText.length());
        int cursor = 0;

        for (EntrySpan span : _sourceSpans) {
            result.append(_sourceText, cursor, span.valueStart);

            String current = Objects.toString(_vals.get(span.key), "");
            String original = _sourceVals.get(span.key);
            result.append(Objects.equals(current, original) ? current : normalizeLineEndings(current));
            cursor = span.valueEnd;
        }

        result.append(_sourceText, cursor, _sourceText.length());
        return result.toString();
    }

    @Nonnull
    private String renderCanonical() {
        StringBuilder writer = new StringBuilder();
        int i = 0;
        int size = _vals.size();

        for (Map.Entry<Integer, String> entry : _vals.entrySet()) {
            int key = entry.getKey();
            String val = entry.getValue() == null ? "" : entry.getValue();

            writer.append("STRING ");
            writer.append(key);
            writer.append(_lineEnding);
            writer.append("{");
            writer.append(_lineEnding);
            writer.append(normalizeLineEndings(val));
            writer.append(_lineEnding);
            writer.append("}");
            writer.append(_lineEnding);

            // Keep a blank line between entries for canonical compatibility
            if (++i < size) {
                writer.append(_lineEnding);
            }
        }

        return writer.toString();
    }

    @Nonnull
    private String normalizeLineEndings(@Nonnull String value) {
        return value.replace("\r\n", "\n").replace('\r', '\n').replace("\n", _lineEnding);
    }

    private void read(@Nonnull InputStream inStream) throws IOException {
        byte[] raw = inStream.readAllBytes();

        // Detect original newline style from raw bytes (robust against reader normalization)
        boolean hasCRLF = false;
        for (int i = 0; i < raw.length - 1; i++) {
            if (raw[i] == '\r' && raw[i + 1] == '\n') {
                hasCRLF = true;
                break;
            }
        }
        _lineEnding = hasCRLF ? "\r\n" : "\n";

        // Decode directly; avoid UTF8 helper if it normalizes newlines
        String input = LosslessUTF8.decode(raw);
        _utf8Bom = input.startsWith("\uFEFF");
        if (_utf8Bom) input = input.substring(1);
        _sourceText = input;

        Matcher headerMatcher = ENTRY_HEADER_PATTERN.matcher(input);

        while (headerMatcher.find()) {
            Matcher endMatcher = ENTRY_END_PATTERN.matcher(input);
            endMatcher.region(headerMatcher.end(), input.length());
            if (!endMatcher.find()) {
                throw new IOException("unterminated WTS entry " + headerMatcher.group(1));
            }

            int key = Integer.parseInt(headerMatcher.group(1));
            String val = input.substring(headerMatcher.end(), endMatcher.start());
            int valueEnd = endMatcher.start();

            // The line ending directly before the closing brace separates the
            // value from the delimiter. Any additional ending is value data.
            if (val.endsWith("\r\n")) {
                val = val.substring(0, val.length() - 2);
                valueEnd -= 2;
            } else if (val.endsWith("\n") || val.endsWith("\r")) {
                val = val.substring(0, val.length() - 1);
                valueEnd--;
            }

            if (_sourceVals.containsKey(key)) _sourcePatchable = false;
            _sourceVals.put(key, val);
            _sourceSpans.add(new EntrySpan(key, headerMatcher.end(), valueEnd));
            addEntry(key, val);

            headerMatcher.region(endMatcher.end(), input.length());
        }
    }

    public WTS() {
    }

    public WTS(@Nonnull InputStream inStream) throws IOException {
        read(inStream);
    }

    public WTS(@Nonnull File file) throws IOException {
        try (InputStream inStream = new FileInputStream(file)) {
            read(inStream);
        }
    }

    @Nonnull
    public static WTS ofMapFile(@Nonnull File mapFile) throws Exception {
        MpqPort.Out portOut = Context.getService(MpqPort.class).createOut();

        portOut.add(WTS.GAME_PATH);

        MpqPort.Out.Result portResult = portOut.commit(mapFile);

        if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract WTS file");

        byte[] bytes = portResult.getExports().get(GAME_PATH).getOutBytes();

        try (InputStream inStream = new ByteArrayInputStream(bytes)) {
            return new WTS(inStream);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WTS wts = (WTS) o;
        return Objects.equals(_vals, wts._vals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_vals);
    }
}
