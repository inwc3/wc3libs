package wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.app.objMerge.BundledGameData;
import net.moonlightflower.wc3libs.app.objMerge.DirGameData;
import net.moonlightflower.wc3libs.app.objMerge.GameData;
import net.moonlightflower.wc3libs.app.objMerge.LayeredGameData;
import net.moonlightflower.wc3libs.app.objMerge.MemoryGameData;
import net.moonlightflower.wc3libs.app.objMerge.ProfileFile;
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
     * The balance layers replace base files: in 3.0.1, custom_v1 and melee_v0
     * ship their own campaignunitfunc.txt, which differ from each other.
     */
    @Test
    public void dataSetsSelectTheirBalanceLayer() throws Exception {
        String path = "Units\\CampaignUnitFunc.txt";

        byte[] custom = BundledGameData.custom().read(path);
        byte[] melee = BundledGameData.melee().read(path);

        Assert.assertEquals(custom, BundledGameData.layer(BundledGameData.LAYER_CUSTOM).read(path));
        Assert.assertEquals(melee, BundledGameData.layer(BundledGameData.LAYER_MELEE).read(path));
        Assert.assertNotEquals(custom, melee);

        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.STANDARD, false).read(path), custom);
        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.STANDARD, true).read(path), melee);
        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.MELEE_V1, false).read(path), melee);
        Assert.assertEquals(BundledGameData.forDataSet(W3I.GameDataSet.CUSTOM_V1, true).read(path), custom);
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
