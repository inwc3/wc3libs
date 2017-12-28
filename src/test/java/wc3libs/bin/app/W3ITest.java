package wc3libs.bin.app;
import com.esotericsoftware.minlog.Log;
import net.moonlightflower.wc3libs.bin.app.W3I;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.misc.model.MDX;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;
import wc3libs.util.MurmurHash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class W3ITest extends Wc3LibTest {
    @Test
    public void testRebuild() throws Exception {
        List<Path> w3iFile = getFiles("wc3data/Infos/");

        w3iFile.forEach((Path p) -> {
            try {
                Log.info("Testing: " + p.getFileName());
                byte[] input = Files.readAllBytes(p);
                W3I w3I = new W3I(input);

                Path outPath = Paths.get("out.w3i");
                Files.deleteIfExists(outPath);
                Path temp = Files.createFile(outPath);
                w3I.write(temp.toFile());
                byte[] output = Files.readAllBytes(temp);

                W3I w3I2 = new W3I(output);

                assertEqualsW3I(w3I, w3I2);

                Assert.assertEquals(MurmurHash.hash64(input, input.length), MurmurHash.hash64(output, output.length));
                Files.delete(temp);
            } catch (Exception e) {
                e.printStackTrace();
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
        Assert.assertEquals(w3I2.getUnitTables(), w3I.getUnitTables());
        Assert.assertEquals(w3I2.getPlayersRecommendedAmount(), w3I.getPlayersRecommendedAmount());
        Assert.assertEquals(w3I2.getWorldBounds().toString(), w3I.getWorldBounds().toString());
        Assert.assertEquals(w3I2.getCameraBounds1(), w3I.getCameraBounds1());
        Assert.assertEquals(w3I2.getCameraBounds2(), w3I.getCameraBounds2());
        Assert.assertEquals(w3I2.getCameraBounds3(), w3I.getCameraBounds3());
        Assert.assertEquals(w3I2.getCameraBounds4(), w3I.getCameraBounds4());
        Assert.assertEquals(w3I2.getTerrainFog(), w3I.getTerrainFog());
        Assert.assertEquals(w3I2.getGlobalWeatherId(), w3I.getGlobalWeatherId());
    }

    @Test()
    public void readWriteCycle() throws IOException {
        readWriteCycle(W3I.class, getFile("wc3data/W3I/war3map.w3i"));

        List<Path> w3iFile = getFiles("wc3data/Infos/");

        w3iFile.forEach((Path p) -> {
            try {
                readWriteCycle(W3I.class, p.toFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
