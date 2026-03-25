package wc3libs.bin.app;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.dataTypes.app.UnitId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.bin.GameExeTest;
import wc3libs.misc.Wc3LibTest;
import wc3libs.util.MurmurHash;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class W3ITest extends Wc3LibTest {
    private static final Logger log = LoggerFactory.getLogger(GameExeTest.class.getName());

    @Test
    public void testRebuild() throws Exception {
        List<Path> w3iFile = getFiles("wc3data/Infos/");

        w3iFile.forEach((Path p) -> {
            try {
                log.info("Testing: " + p.getFileName());
                byte[] input = Files.readAllBytes(p);
                W3I w3I = new W3I(input);

                Path outPath = Paths.get("out.w3i");
                Files.deleteIfExists(outPath);
                Path temp = Files.createFile(outPath);
                w3I.write(temp.toFile());
                byte[] output = Files.readAllBytes(temp);

                log.info("Reading copy");

                W3I w3I2 = new W3I(output);

                assertEqualsW3I(w3I, w3I2);

                Assert.assertEquals(MurmurHash.hash64(input, input.length), MurmurHash.hash64(output, output.length));

                log.info("OK: " + p.getFileName());

                Files.delete(temp);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });

    }

    private static void assertEqualsW3I(W3I w3I, W3I w3I2) {
        Assert.assertEquals(w3I2.getHeight(), w3I.getHeight());
        Assert.assertEquals(w3I2.getWidth(), w3I.getWidth());
        Assert.assertEquals(w3I2.getMargins(), w3I.getMargins());
        Assert.assertEquals(Arrays.toString(w3I2.getPlayers().toArray()), Arrays.toString(w3I.getPlayers().toArray()));
        Assert.assertEquals(w3I2.getPrologueScreen().toString(), w3I.getPrologueScreen().toString());
        Assert.assertEquals(w3I2.getLoadingScreen().toString(), w3I.getLoadingScreen().toString());
        Assert.assertEquals(w3I2.getMapDescription(), w3I.getMapDescription());
        Assert.assertEquals(w3I2.getFlags().toString(), w3I.getFlags().toString());
        Assert.assertEquals(w3I2.getSavesAmount(), w3I.getSavesAmount());
        Assert.assertEquals(w3I2.getTileset().toString(), w3I.getTileset().toString());
        Assert.assertEquals(w3I2.getGameDataSet().toString(), w3I.getGameDataSet().toString());
        Assert.assertEquals(w3I2.getWaterColor().toString(), w3I.getWaterColor().toString());
        Assert.assertEquals(Arrays.toString(w3I2.getUpgradeMods().toArray()), Arrays.toString(w3I.getUpgradeMods().toArray()));
        Assert.assertEquals(Arrays.toString(w3I2.getTechMods().toArray()), Arrays.toString(w3I.getTechMods().toArray()));
        Assert.assertEquals(Arrays.toString(w3I2.getForces().toArray()), Arrays.toString(w3I.getForces().toArray()));
        Assert.assertEquals(Arrays.toString(w3I2.getItemTables().toArray()), Arrays.toString(w3I.getItemTables().toArray()));
        Assert.assertEquals(Arrays.toString(w3I2.getUnitTables().toArray()), Arrays.toString(w3I.getUnitTables().toArray()));
        Assert.assertEquals(w3I2.getPlayersRecommendedAmount(), w3I.getPlayersRecommendedAmount());
        Assert.assertEquals(w3I2.getWorldBounds().toString(), w3I.getWorldBounds().toString());
        Assert.assertEquals(w3I2.getCameraBounds1(), w3I.getCameraBounds1());
        Assert.assertEquals(w3I2.getCameraBounds2(), w3I.getCameraBounds2());
        Assert.assertEquals(w3I2.getCameraBounds3(), w3I.getCameraBounds3());
        Assert.assertEquals(w3I2.getCameraBounds4(), w3I.getCameraBounds4());
        Assert.assertEquals(w3I2.getTerrainFog(), w3I.getTerrainFog());
        Assert.assertEquals(w3I2.getGlobalWeatherId(), w3I.getGlobalWeatherId());
    }

    private static void assertEqualsItemTable(@Nonnull W3I.ItemTable a, @Nonnull W3I.ItemTable b) {
        Assert.assertEquals(a.getIndex(), b.getIndex());
        Assert.assertEquals(a.getName(), b.getName());
        Assert.assertEquals(a.getSets().size(), b.getSets().size());
        for (int i = 0; i < a.getSets().size(); i++) {
            assertEqualsItemTableSet(a.getSets().get(i), b.getSets().get(i));
        }
    }

    private static void assertEqualsItemTableSet(@Nonnull W3I.ItemTable.Set a, @Nonnull W3I.ItemTable.Set b) {
        Assert.assertEquals(a.getItems().size(), b.getItems().size());

        for (int i = 0; i < a.getItems().size(); i++) {
            assertEqualsItemTableSetItem(a.getItems().get(i), b.getItems().get(i));
        }
    }

    private static void assertEqualsItemTableSetItem(@Nonnull W3I.ItemTable.Set.Item a, @Nonnull W3I.ItemTable.Set.Item b) {
        Assert.assertEquals(a.getChance(), b.getChance());
        Assert.assertEquals(a.getTypeId(), b.getTypeId());
    }

    private static void assertEqualsUnitTable(@Nonnull W3I.UnitTable a, @Nonnull W3I.UnitTable b) {
        Assert.assertEquals(a.getIndex(), b.getIndex());
        Assert.assertEquals(a.getName(), b.getName());
        // omitted positions
        Assert.assertEquals(a.getSets().size(), b.getSets().size());
        for (int i = 0; i < a.getSets().size(); i++) {
            assertEqualsUnitTableSet(a.getSets().get(i), b.getSets().get(i));
        }
    }

    private static void assertEqualsUnitTableSet(@Nonnull W3I.UnitTable.Set a, @Nonnull W3I.UnitTable.Set b) {
        Assert.assertEquals(a.getChance(), b.getChance());
        // omitted typeIds
    }

    @Test()
    public void readWriteCycle() throws IOException {
        readWriteCycle(W3I.class, getFile("wc3data/W3I/war3map.w3i"));
        readWriteCycle(W3I.class, getFile("wc3data/W3I/war3map_default.w3i"));
        readWriteCycle(W3I.class, getFile("wc3data/W3I/war3map_latest_lua.w3i"));

        List<Path> w3iFile = getFiles("wc3data/Infos/");

        w3iFile.forEach((Path p) -> {
            try {
                log.info("w3i read write cycle: " + p.getFileName());
                readWriteCycle(W3I.class, p.toFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void testDefaultW3I() throws Exception {
        File file = getFile("wc3data/W3I/war3map_default.w3i");
        W3I w3i = new W3I(file);

        Assert.assertEquals(w3i.getFileVersion(), 33);
        Assert.assertEquals(w3i.getScriptLang(), W3I.ScriptLang.JASS);
        Assert.assertEquals(w3i.getGraphics(), W3I.Graphics.SD_AND_HD);
        Assert.assertEquals(w3i.getGameDataVersion(), W3I.GameDataVersion.TFT);
        Assert.assertEquals(w3i.getForceDefaultCameraZoom(), 1250);
        Assert.assertEquals(w3i.getForceMaxCameraZoom(), 1250);
        Assert.assertEquals(w3i.getForceMinCameraZoom(), 1250);

        // Verify these match the defaults of a freshly constructed W3I
        W3I fresh = new W3I();
        Assert.assertEquals(fresh.getFileVersion(), w3i.getFileVersion());
        Assert.assertEquals(fresh.getScriptLang(), w3i.getScriptLang());
        Assert.assertEquals(fresh.getGraphics(), w3i.getGraphics());
        Assert.assertEquals(fresh.getGameDataVersion(), w3i.getGameDataVersion());
        Assert.assertEquals(fresh.getForceDefaultCameraZoom(), w3i.getForceDefaultCameraZoom());
        Assert.assertEquals(fresh.getForceMaxCameraZoom(), w3i.getForceMaxCameraZoom());
        Assert.assertEquals(fresh.getForceMinCameraZoom(), w3i.getForceMinCameraZoom());
        Assert.assertEquals(fresh.getTerrainFog(), w3i.getTerrainFog());
        Assert.assertEquals(fresh.getWaterColor().toString(), w3i.getWaterColor().toString());

        readWriteCycle(W3I.class, file);
    }

    @Test
    public void testLatestLuaW3I() throws Exception {
        File file = getFile("wc3data/W3I/war3map_latest_lua.w3i");
        W3I w3i = new W3I(file);

        // Verify latest format version (0x21 = 33)
        Assert.assertEquals(w3i.getFileVersion(), 33);

        // Verify Lua scripting language
        Assert.assertEquals(w3i.getScriptLang(), W3I.ScriptLang.LUA);

        // Verify 0x1F+ fields are present
        Assert.assertNotNull(w3i.getGraphics());
        Assert.assertNotNull(w3i.getGameDataVersion());

        // Verify version-specific field values as stored by WC3 editor (even when not explicitly customized)
        Assert.assertEquals(w3i.getGraphics(), W3I.Graphics.SD_AND_HD);
        Assert.assertEquals(w3i.getGameDataVersion(), W3I.GameDataVersion.TFT);
        // Camera zoom: WC3 editor always writes concrete values (not 0); default/min=1250, max=2000
        Assert.assertEquals(w3i.getForceDefaultCameraZoom(), 1250);
        Assert.assertEquals(w3i.getForceMaxCameraZoom(), 2000);
        Assert.assertEquals(w3i.getForceMinCameraZoom(), 1250);

        // Verify read/write cycle produces byte-identical output
        readWriteCycle(W3I.class, file);

        // Verify round-trip field equality
        W3I w3i2 = new W3I(file);
        assertEqualsW3I(w3i, w3i2);
        Assert.assertEquals(w3i2.getFileVersion(), w3i.getFileVersion());
        Assert.assertEquals(w3i2.getScriptLang(), w3i.getScriptLang());
        Assert.assertEquals(w3i2.getGraphics(), w3i.getGraphics());
        Assert.assertEquals(w3i2.getGameDataVersion(), w3i.getGameDataVersion());
        Assert.assertEquals(w3i2.getForceDefaultCameraZoom(), w3i.getForceDefaultCameraZoom());
        Assert.assertEquals(w3i2.getForceMaxCameraZoom(), w3i.getForceMaxCameraZoom());
        Assert.assertEquals(w3i2.getForceMinCameraZoom(), w3i.getForceMinCameraZoom());
    }
}
