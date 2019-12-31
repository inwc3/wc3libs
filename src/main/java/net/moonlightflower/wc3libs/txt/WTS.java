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

    private static final Pattern KEY_PATTERN = Pattern.compile("STRING ([\\d]+)[\\n\\s]*\\{([^\\}]*)[\\n]*\\}");
    private static final Pattern COMMENT_PATTERN = Pattern.compile("(?m)^//.*");

    private final Map<Integer, String> _vals = new LinkedHashMap<>();

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
        write(new FileOutputStream(file));
    }

    public void write(@Nonnull OutputStream outputStream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

        for (Map.Entry<Integer, String> entry : _vals.entrySet()) {
            int key = entry.getKey();
            String val = entry.getValue();

            writer.write(String.format("STRING %s\r\n{\r\n%s\r\n}\r\n", key, val));
        }

        writer.close();
    }

    private void read(@Nonnull InputStream inStream) throws IOException {
        UTF8 reader = new UTF8(inStream);

        String input = reader.readAll();

        Matcher commentMatcher = COMMENT_PATTERN.matcher(input);

        input = commentMatcher.replaceAll("");

        Matcher matcher = KEY_PATTERN.matcher(input);

        while (matcher.find()) {
            int key = Integer.parseInt(matcher.group(1));
            String val = matcher.group(2).trim();

            addEntry(key, val);
        }
    }

    public WTS() {
    }

    public WTS(@Nonnull InputStream inStream) throws IOException {
        read(inStream);
    }

    public WTS(@Nonnull File file) throws IOException {
        InputStream inStream = new FileInputStream(file);

        read(inStream);

        inStream.close();
    }

    @Nonnull
    public static WTS ofMapFile(@Nonnull File mapFile) throws Exception {
        MpqPort.Out portOut = Context.getService(MpqPort.class).createOut();

        portOut.add(WTS.GAME_PATH);

        MpqPort.Out.Result portResult = portOut.commit(mapFile);

        if (!portResult.getExports().containsKey(GAME_PATH)) throw new IOException("could not extract WTS file");

        byte[] bytes = portResult.getExports().get(GAME_PATH).getOutBytes();

        InputStream inStream = new ByteArrayInputStream(bytes);

        WTS wts = new WTS(inStream);

        inStream.close();

        return wts;
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
