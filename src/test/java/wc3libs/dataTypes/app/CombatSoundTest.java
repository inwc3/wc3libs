package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.CombatSound;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CombatSoundTest {
    @Test
    public void test() {
        Assert.assertEquals(CombatSound.valueOf("AxeMediumChop"), CombatSound.AXE_MEDIUM_CHOP);
        Assert.assertEquals(CombatSound.valueOf("MetalHeavyBash"), CombatSound.METAL_HEAVY_BASH);
        Assert.assertEquals(CombatSound.valueOf("MetalHeavyChop"), CombatSound.METAL_HEAVY_CHOP);
        Assert.assertEquals(CombatSound.valueOf("MetalHeavySlice"), CombatSound.METAL_HEAVY_SLICE);
        Assert.assertEquals(CombatSound.valueOf("MetalLightChop"), CombatSound.METAL_LIGHT_CHOP);
        Assert.assertEquals(CombatSound.valueOf("MetalLightSlice"), CombatSound.METAL_LIGHT_SLICE);
        Assert.assertEquals(CombatSound.valueOf("MetalMediumBash"), CombatSound.METAL_MEDIUM_BASH);
        Assert.assertEquals(CombatSound.valueOf("MetalMediumChop"), CombatSound.METAL_MEDIUM_CHOP);
        Assert.assertEquals(CombatSound.valueOf("MetalMediumSlice"), CombatSound.METAL_MEDIUM_SLICE);
        Assert.assertEquals(CombatSound.valueOf("RockHeavyBash"), CombatSound.ROCK_HEAVY_BASH);
        Assert.assertEquals(CombatSound.valueOf("WoodHeavyBash"), CombatSound.WOOD_HEAVY_BASH);
        Assert.assertEquals(CombatSound.valueOf("WoodLightBash"), CombatSound.WOOD_LIGHT_BASH);
        Assert.assertEquals(CombatSound.valueOf("WoodMediumBash"), CombatSound.WOOD_MEDIUM_BASH);
    }
}
