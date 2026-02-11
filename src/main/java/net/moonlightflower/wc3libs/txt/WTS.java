package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
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

    private static final Pattern KEY_PATTERN = Pattern.compile(
        "(?is)\\bSTRING\\s+(\\d+)\\s*(?:\\r?\\n)+\\{\\s*(.*?)\\s*(?:\\r?\\n)?\\}"
    );

    private static final Pattern COMMENT_PATTERN = Pattern.compile("(?m)^//.*$");

    private final Map<Integer, String> _vals = new LinkedHashMap<>();

    // Preserve style from input. Default for newly-created WTS.
    private String _lineEnding = "\r\n";

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
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            int i = 0;
            int size = _vals.size();

            for (Map.Entry<Integer, String> entry : _vals.entrySet()) {
                int key = entry.getKey();
                String val = entry.getValue() == null ? "" : entry.getValue();

                writer.write("STRING ");
                writer.write(Integer.toString(key));
                writer.write(_lineEnding);
                writer.write("{");
                writer.write(_lineEnding);
                writer.write(val);
                writer.write(_lineEnding);
                writer.write("}");
                writer.write(_lineEnding);

                // Keep a blank line between entries for canonical compatibility
                if (++i < size) {
                    writer.write(_lineEnding);
                }
            }
        }
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
        String input = new String(raw, StandardCharsets.UTF_8);

        Matcher commentMatcher = COMMENT_PATTERN.matcher(input);
        input = commentMatcher.replaceAll("");

        Matcher matcher = KEY_PATTERN.matcher(input);

        while (matcher.find()) {
            int key = Integer.parseInt(matcher.group(1));
            String val = matcher.group(2); // do not trim
            addEntry(key, val);
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
