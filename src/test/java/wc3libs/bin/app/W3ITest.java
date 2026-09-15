package wc3libs.bin.app;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.bin.app.MapFlag;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Controller;
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
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    @Test
    public void testVersion39W3I() throws Exception {
        byte[] input = Files.readAllBytes(getFile("wc3data/W3I/war3map_v3.w3i").toPath());
        W3I w3i = new W3I(input);

        Assert.assertEquals(39, w3i.getFileVersion());
        Assert.assertEquals("TRIGSTR_003", w3i.getMapName());
        Assert.assertEquals("TRIGSTR_006", w3i.getMapAuthor());
        Assert.assertEquals("TRIGSTR_005", w3i.getMapDescription());
        Assert.assertEquals("TRIGSTR_004", w3i.getPlayersRecommendedAmount());
        Assert.assertEquals(W3I.Graphics.SD_HD_AND_DE, w3i.getGraphics());
        Assert.assertEquals(W3I.GameDataVersion.FORSAKEN_KINGDOM, w3i.getGameDataVersion());
        Assert.assertTrue(w3i.getFlag(MapFlag.USE_WATER_OVERRIDE_COLOR));
        Assert.assertTrue(w3i.getFlag(MapFlag.USE_ALPHA_TILE_MINIMAP_COLOR));
        Assert.assertTrue(w3i.getFlag(MapFlag.USE_DYNAMIC_MINIMAP));
        Assert.assertEquals(w3i.getLoadingScreenCrestRace(), 8);
        Assert.assertEquals(0, w3i.getLoadingScreen().getCampaignBackgroundIndex());
        Assert.assertEquals("TRIGSTR_009", w3i.getLoadingScreen().getText());
        Assert.assertEquals("TRIGSTR_007", w3i.getLoadingScreen().getTitle());
        Assert.assertEquals("TRIGSTR_008", w3i.getLoadingScreen().getSubtitle());
        Assert.assertEquals(0, w3i.getTerrainFogStyle());
        Assert.assertFalse(w3i.getDrawTerrainFogOverSky());
        Assert.assertEquals(10000F, w3i.getTerrainFogLinearStart(), 0F);
        Assert.assertEquals(10000F, w3i.getTerrainFogLinearEnd(), 0F);
        Assert.assertEquals(1F, w3i.getTerrainFogMaxOpacity(), 0F);
        Assert.assertEquals(0F, w3i.getTerrainFogHeight(), 0F);
        Assert.assertEquals(w3i.getGlobalWeatherId().toString(), "VWbr");
        Assert.assertEquals("lake", w3i.getSoundEnv().toString());
        Assert.assertEquals('A', w3i.getTilesetLightEnv().getChar().getVal().charValue());
        Assert.assertEquals(1444, w3i.getForceDefaultCameraZoom());
        Assert.assertEquals(1555, w3i.getForceMaxCameraZoom());
        Assert.assertEquals(1333, w3i.getForceMinCameraZoom());
        Assert.assertEquals(20, w3i.getWaterMinOpacity());
        Assert.assertEquals(100, w3i.getWaterMaxOpacity());
        Assert.assertEquals(10, w3i.getWaterReflectivity());
        // The 3.0 editor displays 10 here but serializes 0 in this slot.
        Assert.assertEquals(0, w3i.getWaterEmissivity());
        Assert.assertEquals(50, w3i.getWaterEdgeSoftness());
        Assert.assertEquals(20, w3i.getWaterWavesVertexDisplacement());
        Assert.assertEquals(100, w3i.getWaterWavesNormalMapStrength());
        Assert.assertEquals(0, w3i.getWaterOverrideColor());
        Assert.assertEquals(100, w3i.getWaterEnvMapReflectivity());
        Assert.assertEquals(-1, w3i.getWaterUnknown());

        assertV3Player(w3i.getPlayers().get(0), 0, Controller.USER, W3I.Player.UnitRace.HUMAN, 2, 0, "TRIGSTR_010");
        assertV3Player(w3i.getPlayers().get(1), 1, Controller.USER, W3I.Player.UnitRace.ORC, 8, 1, "TRIGSTR_011");
        assertV3Player(w3i.getPlayers().get(2), 2, Controller.COMPUTER, W3I.Player.UnitRace.UNDEAD, 64, 0, "TRIGSTR_012");
        assertV3Player(w3i.getPlayers().get(3), 3, Controller.COMPUTER, W3I.Player.UnitRace.NIGHT_ELF, 64, 1, "TRIGSTR_013");
        assertV3Player(w3i.getPlayers().get(4), 4, Controller.NEUTRAL, W3I.Player.UnitRace.HUMAN, 64, 0, "TRIGSTR_014");
        assertV3Player(w3i.getPlayers().get(5), 5, Controller.RESCUABLE, W3I.Player.UnitRace.ORC, 64, 0, "TRIGSTR_015");

        Assert.assertEquals(w3i.getForces().size(), 2);
        Assert.assertEquals(w3i.getForces().get(0).getPlayerNums(w3i.getPlayers()), Set.of(0, 1, 2));
        Assert.assertEquals(w3i.getForces().get(0).getFlags().toInt(), 2);
        Assert.assertEquals(w3i.getForces().get(0).getName(), "TRIGSTR_016");
        Assert.assertEquals(w3i.getForces().get(1).getPlayerNums(w3i.getPlayers()), Set.of(3, 4, 5));
        Assert.assertEquals(w3i.getForces().get(1).getFlags().toInt(), 57);
        Assert.assertEquals(w3i.getForces().get(1).getName(), "TRIGSTR_017");

        StringWriter playerSlots = new StringWriter();
        w3i.makeInitCustomPlayerSlots(false).write(playerSlots, false);
        String compactPlayerSlots = playerSlots.toString().replaceAll("\\s+", "");
        Assert.assertTrue(compactPlayerSlots.contains("callSetPlayerRaceSkin(Player(0),ConvertRacePref(2))"));
        Assert.assertTrue(compactPlayerSlots.contains("callSetPlayerRaceSkin(Player(1),ConvertRacePref(8))"));
        Assert.assertTrue(compactPlayerSlots.contains("callSetPlayerRaceSkin(Player(2),ConvertRacePref(64))"));

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (Wc3BinOutputStream stream = new Wc3BinOutputStream(output)) {
            w3i.write(stream);
        }

        Assert.assertEquals(output.toByteArray(), input, "version 39 must round-trip byte-identically");
    }

    @Test
    public void version39StringMutationKeepsPlayerAndForceLayout() throws Exception {
        W3I w3i = new W3I(getFile("wc3data/W3I/war3map_v3.w3i"));
        long[][] originalForceMasks = w3i.getForces().stream()
            .map(force -> force.getPlayers().toLongArray())
            .toArray(long[][]::new);
        w3i.setMapName("inlined map name");
        w3i.setMapDescription("inlined description");
        w3i.getPlayers().get(0).setName("inlined player name");
        w3i.getForces().get(0).setName("inlined force name");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (Wc3BinOutputStream stream = new Wc3BinOutputStream(output)) {
            w3i.write(stream);
        }

        W3I reparsed = new W3I(output.toByteArray());
        Assert.assertEquals(reparsed.getFileVersion(), 39);
        Assert.assertEquals(reparsed.getMapName(), "inlined map name");
        Assert.assertEquals(reparsed.getMapDescription(), "inlined description");
        assertV3Player(reparsed.getPlayers().get(0), 0, Controller.USER, W3I.Player.UnitRace.HUMAN, 2, 0, "inlined player name");
        assertV3Player(reparsed.getPlayers().get(1), 1, Controller.USER, W3I.Player.UnitRace.ORC, 8, 1, "TRIGSTR_011");
        Assert.assertEquals(reparsed.getForces().get(0).getPlayers().toLongArray(), originalForceMasks[0]);
        Assert.assertEquals(reparsed.getForces().get(0).getFlags().toInt(), 2);
        Assert.assertEquals(reparsed.getForces().get(0).getName(), "inlined force name");
        Assert.assertEquals(reparsed.getForces().get(1).getPlayers().toLongArray(), originalForceMasks[1]);
        Assert.assertEquals(reparsed.getForces().get(1).getFlags().toInt(), 57);
    }

    private static void assertV3Player(W3I.Player player, int num, Controller controller,
                                       W3I.Player.UnitRace race, int hudSkin, int startPosFixed, String name) {
        Assert.assertEquals(player.getNum(), num);
        Assert.assertEquals(player.getType(), controller);
        Assert.assertEquals(player.getRace(), race);
        Assert.assertEquals(player.getHudSkin(), hudSkin);
        Assert.assertEquals(player.getStartPosFixed(), startPosFixed);
        Assert.assertEquals(player.getName(), name);
    }
}
