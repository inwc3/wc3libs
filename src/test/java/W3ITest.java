import net.moonlightflower.wc3libs.bin.app.W3I;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;

public class W3ITest extends Wc3LibTest {

    @Test
    public void testRebuild() throws Exception {
        W3I w3I = new W3I(getFile("war3map.w3i"));

        File temp = new File("out.w3i");
        w3I.write(temp);

        W3I w3I2 = new W3I(temp);

        temp.delete();

        Assert.assertEquals(w3I.getHeight(), w3I2.getHeight());
        Assert.assertEquals(w3I.getWidth(), w3I2.getWidth());
        Assert.assertEquals(w3I.getMarginBottom(), w3I2.getMarginBottom());
        Assert.assertEquals(w3I.getMarginTop(), w3I2.getMarginTop());
        Assert.assertEquals(w3I.getMarginLeft(), w3I2.getMarginLeft());
        Assert.assertEquals(w3I.getMarginRight(), w3I2.getMarginRight());
        Assert.assertEquals(Arrays.toString(w3I.getPlayers().toArray()), Arrays.toString(w3I2.getPlayers().toArray()));
        Assert.assertEquals(w3I.getPrologueScreen().toString(), w3I2.getPrologueScreen().toString());
        Assert.assertEquals(w3I.getLoadingScreen().toString(), w3I2.getLoadingScreen().toString());
        Assert.assertEquals(w3I.getMapDescription(), w3I2.getMapDescription());
        Assert.assertEquals(w3I.getFlags().toString(), w3I2.getFlags().toString());
        Assert.assertEquals(w3I.getSavesAmount(), w3I2.getSavesAmount());
        Assert.assertEquals(w3I.getTileset().toString(), w3I2.getTileset().toString());
        Assert.assertEquals(w3I.getGameDataSet().toString(), w3I2.getGameDataSet().toString());
        Assert.assertEquals(w3I.getWaterColor().toString(), w3I2.getWaterColor().toString());
    }
}
