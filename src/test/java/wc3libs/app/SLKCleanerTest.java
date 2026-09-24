package wc3libs.app;

import net.moonlightflower.wc3libs.app.SLKCleaner;
import net.moonlightflower.wc3libs.dataTypes.app.*;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;
import net.moonlightflower.wc3libs.slk.SLK;
import net.moonlightflower.wc3libs.slk.app.doodads.DoodSLK;
import net.moonlightflower.wc3libs.slk.app.splats.SplatSLK;
import net.moonlightflower.wc3libs.slk.app.splats.UberSplatSLK;
import net.moonlightflower.wc3libs.slk.app.terrainArts.TerrainSLK;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SLKCleanerTest {
    @Test
    public void removesEditorCommentColumnsAcrossMoreSlkTypes() {
        DoodSLK doodads = new DoodSLK();
        DoodSLK.Obj dood = doodads.addObj(DoodId.valueOf(ObjId.valueOf("hfoo")));
        dood.set(DoodSLK.State.COMMENT, War3String.valueOf("doodad editor comment"));
        assertCommentColumnRemoved(doodads, DoodSLK.State.COMMENT.getFieldId());

        TerrainSLK terrain = new TerrainSLK();
        TerrainSLK.Obj tile = terrain.addObj(TileId.valueOf(ObjId.valueOf("Ldrt")));
        tile.set(TerrainSLK.State.EDITOR_COMMENT, War3String.valueOf("terrain editor comment"));
        assertCommentColumnRemoved(terrain, TerrainSLK.State.EDITOR_COMMENT.getFieldId());

        SplatSLK splats = new SplatSLK();
        SplatSLK.Obj splat = splats.addObj(SplatId.valueOf(ObjId.valueOf("Splat")));
        splat.set(SplatSLK.State.EDITOR_COMMENT, War3String.valueOf("splat editor comment"));
        assertCommentColumnRemoved(splats, SplatSLK.State.EDITOR_COMMENT.getFieldId());

        UberSplatSLK uberSplats = new UberSplatSLK();
        UberSplatSLK.Obj uberSplat = uberSplats.addObj(UberSplatId.valueOf(ObjId.valueOf("USpl")));
        uberSplat.set(UberSplatSLK.State.EDITOR_COMMENT, War3String.valueOf("uber splat editor comment"));
        assertCommentColumnRemoved(uberSplats, UberSplatSLK.State.EDITOR_COMMENT.getFieldId());
    }

    private static void assertCommentColumnRemoved(SLK slk, FieldId commentField) {
        Assert.assertTrue(slk.containsField(commentField));

        SLKCleaner.clean(slk);

        Assert.assertFalse(slk.containsField(commentField));
    }
}
