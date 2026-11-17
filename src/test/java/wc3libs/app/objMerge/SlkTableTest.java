package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.objMerge.BundledGameData;
import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.ObjType;
import net.moonlightflower.wc3libs.app.objMerge.SlkTable;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SlkTableTest {
    @DataProvider
    public static Object[][] bundledTables() throws Exception {
        List<Object[]> cases = new ArrayList<>();

        for (String layer : Arrays.asList(BundledGameData.LAYER_BASE, BundledGameData.LAYER_CUSTOM, BundledGameData.LAYER_MELEE)) {
            GameData data = BundledGameData.layer(layer);

            for (ObjType type : ObjType.values()) {
                List<String> paths = new ArrayList<>(type.getTables());

                paths.add(type.getMetaPath());

                for (String path : paths) {
                    if (data.read(path) != null) cases.add(new Object[]{layer, path});
                }
            }
        }

        return cases.toArray(new Object[0][]);
    }

    /** Every cell of every bundled table survives a rewrite exactly as it was spelled. */
    @Test(dataProvider = "bundledTables")
    public void rewriteKeepsEveryCell(String layer, String path) throws Exception {
        byte[] original = BundledGameData.layer(layer).read(path);
        Map<String, String> cells = cells(original);

        SlkTable table = SlkTable.parse(original);
        byte[] rewritten = table.toBytes();

        Assert.assertEquals(cells(rewritten), cells, "cells of " + layer + "/" + path);
        Assert.assertEquals(SlkTable.parse(rewritten).toBytes(), rewritten, "rewriting is not stable for " + path);
    }

    /**
     * Reads a SYLK file into "row id/column index -> raw K text", independently
     * of {@link SlkTable}, so the comparison does not share its parser.
     */
    private static Map<String, String> cells(byte[] bytes) {
        Map<Integer, Map<Integer, String>> grid = new LinkedHashMap<>();
        int x = 0;
        int y = 0;

        for (String line : new String(bytes, StandardCharsets.UTF_8).split("\r?\n")) {
            if (!line.startsWith("C;")) continue;

            int k = line.indexOf(";K");
            String head = k < 0 ? line : line.substring(0, k);

            for (String part : head.split(";")) {
                if (part.startsWith("X")) x = Integer.parseInt(part.substring(1));
                if (part.startsWith("Y")) y = Integer.parseInt(part.substring(1));
            }

            if (k >= 0) grid.computeIfAbsent(y, key -> new LinkedHashMap<>()).put(x, line.substring(k + 2));
        }

        Map<String, String> cells = new LinkedHashMap<>();
        boolean header = true;
        int idLess = 0;

        for (Map<Integer, String> row : grid.values()) {
            String rowKey = header ? "<header>" : row.containsKey(1) ? row.get(1) : "<no id " + idLess++ + ">";

            row.forEach((col, raw) -> cells.put(rowKey + "/" + col, raw));

            header = false;
        }

        return cells;
    }

    @Test
    public void emptyHeaderCellsKeepTheirColumn() {
        SlkTable table = SlkTable.parse(slk(
            "C;X1;Y1;K\"id\"", "C;X3;K\"after\"",
            "C;X1;Y2;K\"a\"", "C;X3;K7"));

        Assert.assertEquals(table.getColumns(), Arrays.asList("id", null, "after"));
        Assert.assertEquals(table.getColumnIndex("AFTER"), 2);
        Assert.assertEquals(table.getRow("a").get("after"), "7");

        SlkTable reparsed = SlkTable.parse(table.toBytes());

        Assert.assertEquals(reparsed.getColumns(), table.getColumns());
        Assert.assertEquals(reparsed.getRow("a").getRaw(2), "7");
    }

    @Test
    public void cellsKeepTheirSpelling() {
        SlkTable table = SlkTable.parse(slk(
            "C;X1;Y1;K\"id\"", "C;X2;K\"num\"", "C;X3;K\"str\"",
            "C;X1;Y2;K\"a\"", "C;X2;K0.50", "C;X3;K\"1\""));

        SlkTable.Row row = SlkTable.parse(table.toBytes()).getRow("a");

        Assert.assertEquals(row.getRaw(1), "0.50");
        Assert.assertEquals(row.getRaw(2), "\"1\"");
        Assert.assertEquals(row.get("str"), "1");
    }

    @Test
    public void positionsCarryOverWhenLeftOut() {
        SlkTable table = SlkTable.parse(slk(
            "C;Y1;X1;K\"id\"", "C;X2;K\"v\"",
            "C;Y2;X1;K\"a\"", "C;X2;K1",
            "C;Y3;X1;K\"b\"", "C;X2;K2"));

        Assert.assertEquals(table.getRow("b").get("v"), "2");
        Assert.assertEquals(table.getRows().size(), 2);
    }

    @Test
    public void copiedRowsAreIndependent() {
        SlkTable table = SlkTable.parse(slk(
            "C;X1;Y1;K\"id\"", "C;X2;K\"v\"", "C;X3;K\"w\"",
            "C;X1;Y2;K\"a\"", "C;X2;K1", "C;X3;K\"x\""));

        SlkTable.Row copy = table.copyRow(table.getRow("a"), "b");

        copy.setRaw(1, "2");

        Assert.assertEquals(table.getRow("a").get("v"), "1");
        Assert.assertEquals(table.getRow("b").get("v"), "2");
        Assert.assertEquals(table.getRow("b").get("w"), "x");
        Assert.assertEquals(table.getRow("b").getId(), "b");

        Assert.expectThrows(IllegalArgumentException.class, () -> table.copyRow(table.getRow("a"), "b"));
        Assert.assertSame(table.addRow("a"), table.getRow("a"));

        SlkTable.Row added = table.addRow("c");

        added.setRaw(5, "9");

        SlkTable reparsed = SlkTable.parse(table.toBytes());

        Assert.assertEquals(reparsed.getRows().size(), 3);
        Assert.assertEquals(reparsed.getRow("c").getRaw(5), "9");
        Assert.assertNull(reparsed.getRow("c").getRaw(1));
    }

    @Test
    public void quotedSemicolonsDoNotSplitRecords() {
        SlkTable table = SlkTable.parse(slk("C;X1;Y1;K\"id\"", "C;X2;K\"v\"", "C;X1;Y2;K\"a\"", "C;X2;K\"x;y\""));

        Assert.assertEquals(table.getRow("a").get("v"), "x;y");
    }

    private static byte[] slk(String... cells) {
        StringBuilder sb = new StringBuilder("ID;PWXL;N;E\r\n");

        for (String cell : cells) sb.append(cell).append("\r\n");

        sb.append("E\r\n");

        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
