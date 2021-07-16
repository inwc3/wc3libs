package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.ArmorSound;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArmorSoundTest {
    @Test
    public void test() {
        Assert.assertEquals(ArmorSound.valueOf("Ethereal"), ArmorSound.ETHEREAL);
        Assert.assertEquals(ArmorSound.valueOf("Flesh"), ArmorSound.FLESH);
        Assert.assertEquals(ArmorSound.valueOf("Metal"), ArmorSound.METAL);
        Assert.assertEquals(ArmorSound.valueOf("Stone"), ArmorSound.STONE);
        Assert.assertEquals(ArmorSound.valueOf("Wood"), ArmorSound.WOOD);
    }
}
