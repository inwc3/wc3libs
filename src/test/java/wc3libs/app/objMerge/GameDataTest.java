package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.objMerge.BundledGameData;
import net.moonlightflower.wc3libs.app.objMerge.DirGameData;
import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.LayeredGameData;
import net.moonlightflower.wc3libs.app.objMerge.MemoryGameData;
import net.moonlightflower.wc3libs.app.objMerge.MergedGameData;
import net.moonlightflower.wc3libs.app.objMerge.ProfileFile;
import net.moonlightflower.wc3libs.app.objMerge.SlkTable;
import net.moonlightflower.wc3libs.app.ObjMerger;
import net.moonlightflower.wc3libs.bin.app.W3I;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GameDataTest {
    @Test
    public void pathsIgnoreCaseAndSlashDirection() {
        Assert.assertEquals(GameData.normalize("\\Units\\UnitData.slk"), "units/unitdata.slk");

        MemoryGameData data = new MemoryGameData();

        data.put("Units\\UnitData.slk", new byte[]{1});

        Assert.assertEquals(data.read("units/UNITDATA.slk"), new byte[]{1});
        Assert.assertNull(data.read("Units\\UnitUI.slk"));
    }

    @Test
    public void higherLayersShadowWholeFiles() throws Exception {
        MemoryGameData top = new MemoryGameData();
        MemoryGameData bottom = new MemoryGameData();

        top.put("a.txt", new byte[]{1});
        bottom.put("A.TXT", new byte[]{2});
        bottom.put("b.txt", new byte[]{3});

        GameData layered = new LayeredGameData(top, bottom);

        Assert.assertEquals(layered.read("a.txt"), new byte[]{1});
        Assert.assertEquals(layered.read("b.txt"), new byte[]{3});
        Assert.assertNull(layered.read("c.txt"));
    }

    @Test
    public void directoriesAreReadByGamePath() throws Exception {
        Path dir = Files.createTempDirectory("wc3libs-gamedata");
        Path file = dir.resolve("units").resolve("unitdata.slk");

        Files.createDirectories(file.getParent());
        Files.write(file, new byte[]{4});

        GameData data = new DirGameData(dir);

        Assert.assertEquals(data.read("Units\\UnitData.slk"), new byte[]{4});
        Assert.assertNull(data.read("Units\\UnitUI.slk"));
    }

    @Test
    public void snapshotIsTheExpectedBuild() throws Exception {
        Assert.assertEquals(BundledGameData.getVersion(), "3.0.1.24371");
    }

    @Test
    public void snapshotHoldsEveryFileMergingNeeds() throws Exception {
        GameData custom = BundledGameData.custom();

        for (String path : ObjMerger.gameDataPaths()) {
            Assert.assertNotNull(custom.read(path), path + " is missing from the snapshot");
        }
    }

    /**
     * The balance layers ship only what they change: in 3.0.1, custom_v1's
     * campaignunitfunc.txt lacks 63 of the base file's sections. The game
     * reads them merged, and so does the stack: every base section stays,
     * and a key the balance layer sets wins.
     */
    @Test
    public void dataSetsLayTheirBalanceLayerOverTheBase() throws Exception {
        String path = "Units\\CampaignUnitFunc.txt";

        ProfileFile base = ProfileFile.parse(BundledGameData.layer(BundledGameData.LAYER_BASE).read(path));
        ProfileFile balance = ProfileFile.parse(BundledGameData.layer(BundledGameData.LAYER_CUSTOM).read(path));
        ProfileFile custom = ProfileFile.parse(BundledGameData.custom().read(path));

        Assert.assertTrue(balance.getSections().size() < base.getSections().size(), "precondition: the balance layer is partial");
        Assert.assertTrue(custom.getSections().keySet().containsAll(base.getSections().keySet()));
        Assert.assertTrue(custom.getSections().keySet().containsAll(balance.getSections().keySet()));

        for (ProfileFile.Section section : balance.getSections().values()) {
            for (ProfileFile.Entry entry : section.getEntries().values()) {
                Assert.assertEquals(custom.getSection(section.getId()).get(entry.getKey()).getRawValue(), entry.getRawValue(),
                    section.getId() + "." + entry.getKey());
            }
        }

        byte[] customBytes = BundledGameData.custom().read(path);
        byte[] meleeBytes = BundledGameData.melee().read(path);

        Assert.assertNotEquals(customBytes, meleeBytes);
        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.STANDARD, false).read(path), customBytes);
        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.STANDARD, true).read(path), meleeBytes);
        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.MELEE_V1, false).read(path), meleeBytes);
        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.CUSTOM_V1, true).read(path), customBytes);
    }

    /**
     * A balance table replaces the rows it holds whole: custom_v1's
     * AbilityData.slk has about half the rows and columns of the base one, so
     * a row it changes loses the base cells it leaves out, and rows it lacks
     * stay. The game reads it so: custom_v1's UnitUI has no showAirToGround
     * column, and flyers read 0 where the base row says 1.
     */
    @Test
    public void balanceTablesReplaceRowsWhole() throws Exception {
        String path = "Units\\AbilityData.slk";

        SlkTable base = SlkTable.parse(BundledGameData.layer(BundledGameData.LAYER_BASE).read(path));
        SlkTable balance = SlkTable.parse(BundledGameData.layer(BundledGameData.LAYER_CUSTOM).read(path));
        SlkTable custom = SlkTable.parse(BundledGameData.custom().read(path));

        Assert.assertTrue(balance.getColumns().size() < base.getColumns().size(), "precondition: the balance layer is partial");

        for (SlkTable.Row row : base.getRows()) {
            if (row.getId() != null && !row.getId().isEmpty()) Assert.assertTrue(custom.containsRow(row.getId()), row.getId());
        }

        // AUan is in both; custom_v1 leaves EfctID1 out, so the merged row has none.
        Assert.assertNull(balance.getRow("AUan").get("EfctID1"));
        Assert.assertNotNull(base.getRow("AUan").get("EfctID1"));
        Assert.assertNull(custom.getRow("AUan").get("EfctID1"));
    }

    @Test
    public void mergedLayersCombineTablesAndProfilesButTakeOtherFilesWhole() throws Exception {
        MemoryGameData top = new MemoryGameData();
        MemoryGameData bottom = new MemoryGameData();

        bottom.put("t.slk", lines("ID;PWXL;N;E", "C;X1;Y1;K\"id\"", "C;X2;K\"a\"", "C;X3;K\"b\"",
            "C;X1;Y2;K\"r1\"", "C;X2;K1", "C;X3;K2", "C;X1;Y3;K\"r2\"", "C;X2;K3", "E"));
        // The top layer renames nothing, adds column c and row r3, and pads with a column without a header
        // and a row without an id, as Blizzard's tables do.
        top.put("t.slk", lines("ID;PWXL;N;E", "C;X1;Y1;K\"id\"", "C;X2;K\"b\"", "C;X3;K\"c\"",
            "C;X1;Y2;K\"r1\"", "C;X2;K9", "C;X3;K8", "C;X4;K6", "C;X1;Y3;K\"r3\"", "C;X3;K7", "C;X2;Y4;K5", "E"));
        bottom.put("p.txt", lines("[x]", "A=1", "B=2"));
        top.put("p.txt", lines("[x]", "b=3", "[y]", "C=4"));
        bottom.put("m.mdx", new byte[]{1});
        top.put("m.mdx", new byte[]{2});

        GameData merged = new MergedGameData(top, bottom);
        SlkTable table = SlkTable.parse(merged.read("t.slk"));

        Assert.assertEquals(table.getColumns(), java.util.Arrays.asList("id", "a", "b", "c"));
        Assert.assertEquals(table.getRows().size(), 3, "the row without an id addresses nothing");
        Assert.assertNull(table.getRow("r1").get("a"), "r1 is replaced whole");
        Assert.assertEquals(table.getRow("r1").get("b"), "9");
        Assert.assertEquals(table.getRow("r1").get("c"), "8");
        Assert.assertEquals(table.getRow("r2").get("a"), "3");
        Assert.assertEquals(table.getRow("r3").get("c"), "7");
        Assert.assertNull(table.getRow("r3").get("a"));

        ProfileFile profile = ProfileFile.parse(merged.read("p.txt"));

        Assert.assertEquals(profile.getSection("x").get("A").getRawValue(), "1");
        Assert.assertEquals(profile.getSection("x").get("B").getKey(), "B");
        Assert.assertEquals(profile.getSection("x").get("B").getRawValue(), "3");
        Assert.assertEquals(profile.getSection("y").get("C").getRawValue(), "4");

        Assert.assertEquals(merged.read("m.mdx"), new byte[]{2});
        Assert.assertNull(merged.read("none.txt"));
    }

    private static byte[] lines(String... lines) {
        return (String.join("\r\n", lines) + "\r\n").getBytes(StandardCharsets.UTF_8);
    }

    /** Display strings come from the locale layer, which the base layer does not have. */
    @Test
    public void stringsComeFromTheLocaleLayer() throws Exception {
        String path = "Units\\HumanUnitStrings.txt";

        Assert.assertNull(BundledGameData.layer(BundledGameData.LAYER_BASE).read(path));

        ProfileFile strings = ProfileFile.parse(BundledGameData.custom().read(path));

        Assert.assertEquals(strings.getSection("hfoo").get("Name").getRawValue(), "Footman");
        Assert.assertTrue(new String(BundledGameData.custom().read("Units\\UnitData.slk"), StandardCharsets.UTF_8).contains("\"hfoo\""));
    }
}
