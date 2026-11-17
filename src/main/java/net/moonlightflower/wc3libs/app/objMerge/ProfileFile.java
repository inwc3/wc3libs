package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A profile ({@code .txt}) file read for merging: sections of
 * {@code key=value} lines whose values are kept exactly as written.
 * <p>
 * Keys match without regard to case, as the game reads them. A key may carry
 * a qualifier after a colon -- {@code Art:hd}, {@code Ubertip:melee,V0} --
 * which the game prefers over the plain key in the matching context;
 * {@link #baseKey} strips it.
 */
public final class ProfileFile {
    /** One section's entries, keyed by lower-cased key, in file order. */
    public static final class Section {
        private final String _id;
        private final Map<String, Entry> _entries = new LinkedHashMap<>();

        public Section(@Nonnull String id) {
            _id = id;
        }

        @Nonnull
        public String getId() {
            return _id;
        }

        @Nonnull
        public Map<String, Entry> getEntries() {
            return Collections.unmodifiableMap(_entries);
        }

        @Nullable
        public Entry get(@Nonnull String key) {
            return _entries.get(key.toLowerCase(Locale.ROOT));
        }

        /** Sets a key, keeping its position if present. */
        public void put(@Nonnull String key, @Nonnull String rawValue) {
            String lower = key.toLowerCase(Locale.ROOT);
            Entry existing = _entries.get(lower);

            _entries.put(lower, new Entry(existing == null ? key : existing.getKey(), rawValue));
        }

        public void remove(@Nonnull String key) {
            _entries.remove(key.toLowerCase(Locale.ROOT));
        }

        /** @return whether the section has {@code baseKey}, plain or qualified */
        public boolean hasBaseKey(@Nonnull String baseKey) {
            if (_entries.containsKey(baseKey.toLowerCase(Locale.ROOT))) return true;

            for (String key : _entries.keySet()) {
                if (isBaseKey(key, baseKey)) return true;
            }

            return false;
        }

        /** Removes every qualified form of {@code baseKey}, keeping the plain one. */
        public void removeQualified(@Nonnull String baseKey) {
            _entries.keySet().removeIf(key -> key.indexOf(':') >= 0 && isBaseKey(key, baseKey));
        }

        @Nonnull
        public Section copy(@Nonnull String id) {
            Section copy = new Section(id);

            copy._entries.putAll(_entries);

            return copy;
        }

        /** Merges {@code other}'s entries over this one's. */
        public void putAll(@Nonnull Section other) {
            // Both maps are keyed by the lower-cased key already.
            for (Map.Entry<String, Entry> entry : other._entries.entrySet()) {
                Entry existing = _entries.get(entry.getKey());

                _entries.put(entry.getKey(), existing == null ? entry.getValue()
                    : new Entry(existing.getKey(), entry.getValue().getRawValue()));
            }
        }

        @Nonnull
        public String toText(@Nonnull String lineSeparator) {
            StringBuilder sb = new StringBuilder();

            sb.append('[').append(_id).append(']').append(lineSeparator);

            for (Entry entry : _entries.values()) {
                sb.append(entry.getKey()).append('=').append(entry.getRawValue()).append(lineSeparator);
            }

            return sb.toString();
        }
    }

    public static final class Entry {
        private final String _key;
        private final String _rawValue;

        public Entry(@Nonnull String key, @Nonnull String rawValue) {
            _key = key;
            _rawValue = rawValue;
        }

        @Nonnull
        public String getKey() {
            return _key;
        }

        /** @return the value as written, quotes and all */
        @Nonnull
        public String getRawValue() {
            return _rawValue;
        }
    }

    private final Map<String, Section> _sections = new LinkedHashMap<>();
    private final String _lineSeparator;

    private ProfileFile(@Nonnull String lineSeparator) {
        _lineSeparator = lineSeparator;
    }

    @Nonnull
    public Map<String, Section> getSections() {
        return Collections.unmodifiableMap(_sections);
    }

    @Nullable
    public Section getSection(@Nonnull String id) {
        return _sections.get(id);
    }

    @Nonnull
    public String getLineSeparator() {
        return _lineSeparator;
    }

    /**
     * Lays {@code top} over this file the way the game stacks its data mods:
     * each key it sets replaces the one beneath, keys and sections it leaves
     * out keep theirs, and new sections are appended.
     */
    public void overlay(@Nonnull ProfileFile top) {
        for (Section section : top._sections.values()) {
            _sections.computeIfAbsent(section.getId(), Section::new).putAll(section);
        }
    }

    /** @return the sections as text, a blank line between them; comments are not kept */
    @Nonnull
    public byte[] toBytes() {
        StringBuilder sb = new StringBuilder();

        for (Section section : _sections.values()) {
            if (sb.length() > 0) sb.append(_lineSeparator);

            sb.append(section.toText(_lineSeparator));
        }

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Parses a profile file. Section ids are case-sensitive, as object ids
     * are; a section appearing twice continues where the first left off.
     * Only whole-line {@code //} comments are comments, since a value may
     * legitimately contain the sequence.
     */
    @Nonnull
    public static ProfileFile parse(@Nonnull byte[] bytes) {
        String text = new String(bytes, StandardCharsets.UTF_8);

        if (text.startsWith("﻿")) text = text.substring(1);

        ProfileFile file = new ProfileFile(text.contains("\r\n") ? "\r\n" : "\n");
        Section cur = null;
        int length = text.length();

        for (int start = 0; start < length; ) {
            int end = text.indexOf('\n', start);
            int next;

            if (end < 0) {
                end = length;
                next = length;
            } else {
                next = end + 1;

                if (end > start && text.charAt(end - 1) == '\r') end--;
            }

            String line = text.substring(start, end);

            start = next;

            String trimmed = line.trim();

            if (trimmed.isEmpty() || trimmed.startsWith("//")) continue;

            if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
                String id = trimmed.substring(1, trimmed.length() - 1).trim();

                cur = file._sections.computeIfAbsent(id, Section::new);

                continue;
            }

            int eq = line.indexOf('=');

            if (cur == null || eq <= 0) continue;

            String key = line.substring(0, eq).trim();

            if (key.isEmpty()) continue;

            cur.put(key, line.substring(eq + 1));
        }

        return file;
    }

    /** @return whether {@code key} without its {@code :qualifier} is {@code baseKey}, ignoring case */
    public static boolean isBaseKey(@Nonnull String key, @Nonnull String baseKey) {
        int colon = key.indexOf(':');
        int length = colon < 0 ? key.length() : colon;

        return length == baseKey.length() && key.regionMatches(true, 0, baseKey, 0, length);
    }

    /** @return {@code key} without a {@code :qualifier} */
    @Nonnull
    public static String baseKey(@Nonnull String key) {
        int colon = key.indexOf(':');

        return colon < 0 ? key : key.substring(0, colon);
    }

    /**
     * Splits a value into its comma-separated elements the way the game
     * does: double quotes group, and are not part of the element.
     */
    @Nonnull
    public static List<String> tokenize(@Nonnull String raw) {
        List<String> tokens = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean quoted = false;

        for (int i = 0; i < raw.length(); i++) {
            char c = raw.charAt(i);

            if (c == '"') {
                quoted = !quoted;
            } else if (c == ',' && !quoted) {
                tokens.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }

        tokens.add(cur.toString());

        return tokens;
    }

    /** Joins elements into a value, quoting those that hold a comma. */
    @Nonnull
    public static String join(@Nonnull List<String> tokens) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tokens.size(); i++) {
            if (i > 0) sb.append(',');

            String token = tokens.get(i);

            if (token.contains(",")) {
                sb.append('"').append(token).append('"');
            } else {
                sb.append(token);
            }
        }

        return sb.toString();
    }
}
