package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A SYLK table that keeps every cell exactly as written.
 * <p>
 * Merging rewrites whole game tables, so any drift in how a cell is spelled
 * -- a quoted {@code "1"} turning into a number, {@code 0.50} into
 * {@code 0.5}, an empty header cell vanishing and shifting the columns after
 * it -- would change thousands of base objects the map never touched. Cells
 * are therefore held as their raw {@code K} text, quotes included, and only
 * the cells the merge sets are spelled anew.
 * <p>
 * The first row is the header and the first column the object id. Rows
 * without an id are carried along untouched.
 */
public final class SlkTable {
    private final List<String> _columns = new ArrayList<>();
    private final Map<String, Integer> _columnIndex = new LinkedHashMap<>();
    private final List<Row> _rows = new ArrayList<>();
    private final Map<String, Row> _rowsById = new LinkedHashMap<>();
    private String _lineSeparator = "\r\n";

    public final class Row {
        private String[] _cells;

        private Row(@Nonnull String[] cells) {
            _cells = cells;
        }

        /** @return the object id, or {@code null} for a row without one */
        @Nullable
        public String getId() {
            return unquote(_cells.length > 0 ? _cells[0] : null);
        }

        /** @return the raw cell text, quotes included, or {@code null} if empty */
        @Nullable
        public String getRaw(int column) {
            return column < _cells.length ? _cells[column] : null;
        }

        /** @return the cell's value with quotes removed, or {@code null} if empty */
        @Nullable
        public String get(@Nonnull String column) {
            int index = getColumnIndex(column);

            return index < 0 ? null : unquote(getRaw(index));
        }

        /** @param raw the raw cell text, quotes included; {@code null} empties the cell */
        public void setRaw(int column, @Nullable String raw) {
            if (column == 0) throw new IllegalArgumentException("the id column is set through the row");

            if (column >= _cells.length) _cells = Arrays.copyOf(_cells, column + 1);

            _cells[column] = raw;
        }
    }

    public SlkTable(@Nonnull List<String> columns) {
        for (String column : columns) addColumn(column);
    }

    private SlkTable() {
    }

    private void addColumn(@Nullable String name) {
        _columns.add(name);

        if (name != null) _columnIndex.putIfAbsent(name.toLowerCase(Locale.ROOT), _columns.size() - 1);
    }

    /** @return the header, with {@code null} for an empty header cell */
    @Nonnull
    public List<String> getColumns() {
        return Collections.unmodifiableList(_columns);
    }

    /** @return the index of the column, found without regard to case, or -1 */
    public int getColumnIndex(@Nonnull String name) {
        Integer index = _columnIndex.get(name.toLowerCase(Locale.ROOT));

        return index == null ? -1 : index;
    }

    @Nonnull
    public List<Row> getRows() {
        return Collections.unmodifiableList(_rows);
    }

    @Nullable
    public Row getRow(@Nonnull String id) {
        return _rowsById.get(id);
    }

    public boolean containsRow(@Nonnull String id) {
        return _rowsById.containsKey(id);
    }

    /** Appends an empty row for {@code id}, or returns the existing one. */
    @Nonnull
    public Row addRow(@Nonnull String id) {
        Row row = _rowsById.get(id);

        if (row != null) return row;

        String[] cells = new String[_columns.size()];

        cells[0] = quote(id);

        return append(cells);
    }

    /** Appends a copy of {@code source} under {@code id}; the id must be new. */
    @Nonnull
    public Row copyRow(@Nonnull Row source, @Nonnull String id) {
        if (_rowsById.containsKey(id)) throw new IllegalArgumentException("row " + id + " already exists");

        String[] cells = Arrays.copyOf(source._cells, Math.max(source._cells.length, _columns.size()));

        cells[0] = quote(id);

        return append(cells);
    }

    @Nonnull
    private Row append(@Nonnull String[] cells) {
        Row row = new Row(cells);

        _rows.add(row);

        String id = row.getId();

        if (id != null) _rowsById.putIfAbsent(id, row);

        return row;
    }

    @Nonnull
    public static String quote(@Nonnull String val) {
        return "\"" + val + "\"";
    }

    @Nullable
    public static String unquote(@Nullable String raw) {
        if (raw == null) return null;

        if (raw.length() >= 2 && raw.startsWith("\"") && raw.endsWith("\"")) return raw.substring(1, raw.length() - 1);

        return raw;
    }

    /**
     * Parses a SYLK file. Only {@code C} records carry data; their position
     * carries over from the previous record when {@code X} or {@code Y} is
     * left out, as the format allows.
     */
    @Nonnull
    public static SlkTable parse(@Nonnull byte[] bytes) {
        String text = new String(bytes, StandardCharsets.UTF_8);

        if (text.startsWith("﻿")) text = text.substring(1);

        SlkTable table = new SlkTable();

        if (!text.contains("\r\n")) table._lineSeparator = "\n";

        Map<Integer, Map<Integer, String>> grid = new java.util.TreeMap<>();
        int x = 1;
        int y = 1;

        for (String line : text.split("\r?\n")) {
            if (!line.startsWith("C;")) continue;

            String value = null;

            for (String part : splitRecord(line)) {
                if (part.isEmpty()) continue;

                switch (part.charAt(0)) {
                    case 'X': x = Integer.parseInt(part.substring(1)); break;
                    case 'Y': y = Integer.parseInt(part.substring(1)); break;
                    case 'K': value = part.substring(1); break;
                    default: break;
                }
            }

            if (value != null) grid.computeIfAbsent(y, k -> new java.util.TreeMap<>()).put(x, value);
        }

        if (grid.isEmpty()) throw new IllegalArgumentException("SYLK file has no cells");

        int width = 0;

        for (Map<Integer, String> cells : grid.values()) {
            for (int cellX : cells.keySet()) width = Math.max(width, cellX);
        }

        boolean header = true;

        for (Map<Integer, String> cells : grid.values()) {
            String[] row = new String[width];

            cells.forEach((cellX, raw) -> row[cellX - 1] = raw);

            if (header) {
                for (String raw : row) table.addColumn(unquote(raw));

                header = false;
            } else {
                table.append(row);
            }
        }

        return table;
    }

    /** Splits a record at {@code ;}, except inside a quoted value. */
    @Nonnull
    private static List<String> splitRecord(@Nonnull String line) {
        List<String> parts = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        boolean quoted = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') quoted = !quoted;

            if (c == ';' && !quoted) {
                parts.add(cur.toString());
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }

        parts.add(cur.toString());

        return parts;
    }

    @Nonnull
    public byte[] toBytes() {
        String nl = _lineSeparator;
        int width = _columns.size();

        for (Row row : _rows) width = Math.max(width, row._cells.length);

        StringBuilder sb = new StringBuilder();

        sb.append("ID;PWXL;N;E").append(nl);
        sb.append("B;X").append(width).append(";Y").append(_rows.size() + 1).append(";D0").append(nl);

        String[] header = new String[_columns.size()];

        for (int i = 0; i < header.length; i++) header[i] = _columns.get(i) == null ? null : quote(_columns.get(i));

        writeRow(sb, 1, header, nl);

        int y = 2;

        for (Row row : _rows) writeRow(sb, y++, row._cells, nl);

        sb.append("E").append(nl);

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }

    private static void writeRow(@Nonnull StringBuilder sb, int y, @Nonnull String[] cells, @Nonnull String nl) {
        boolean first = true;

        for (int x = 0; x < cells.length; x++) {
            if (cells[x] == null) continue;

            sb.append("C;X").append(x + 1);

            if (first) {
                sb.append(";Y").append(y);
                first = false;
            }

            sb.append(";K").append(cells[x]).append(nl);
        }

        // A row with no cells still has to occupy its line, or the rows
        // after it would move up; an empty id cell keeps it in place.
        if (first) sb.append("C;X1;Y").append(y).append(";K\"\"").append(nl);
    }
}
