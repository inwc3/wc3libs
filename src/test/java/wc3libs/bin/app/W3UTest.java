package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import net.moonlightflower.wc3libs.port.War3MapFiles;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

public class W3UTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws Exception {
		File legacyFile = getFile("wc3data/W3U/war3map.w3u");
		readWriteCycle(W3U.class, legacyFile);
		W3U legacy = new W3U(legacyFile);
		Assert.assertSame(legacy.getFormat(), ObjMod.EncodingFormat.OBJ_0x2);
		assertDefaultWriteIsExact(legacy, legacyFile);
		Assert.assertSame(legacy.copy().getFormat(), ObjMod.EncodingFormat.OBJ_0x2);

        readWriteCycle(W3U.class, getFile("wc3data/W3U/war3mapReforged.w3u"));
        readWriteCycle(W3U.class, getFile("wc3data/W3U/war3mapBorked.w3u"));
	}

	@Test
	public void readWriteCycleV3UnitAndSkinData() throws Exception {
		File dataFile = getFile("wc3data/W3U/war3map_v3_lumber_bounty.w3u");
		W3U data = new W3U(dataFile);
		Assert.assertSame(data.getFormat(), ObjMod.EncodingFormat.OBJ_0x3);
		Assert.assertEquals(data.getOrigObjs().size(), 1);
		Assert.assertEquals(data.getCustomObjs().size(), 1);

		for (W3U.Unit unit : data.getObjsList()) {
			Assert.assertEquals(unit.get(W3U.State.DATA_BOUNTY_LUMBER_BASE).toInt(), 1);
			Assert.assertEquals(unit.get(W3U.State.DATA_BOUNTY_LUMBER_DICE_COUNT).toInt(), 2);
			Assert.assertEquals(unit.get(W3U.State.DATA_BOUNTY_LUMBER_DICE_SIDES).toInt(), 3);
		}
		readWriteCycle(W3U.class, dataFile);
		assertDefaultWriteIsExact(data, dataFile);
		Assert.assertSame(data.copy().getFormat(), ObjMod.EncodingFormat.OBJ_0x3);

		File skinFile = getFile("wc3data/W3U/war3mapSkin_v3_lumber_bounty.w3u");
		W3U skin = new W3U(skinFile);
		Assert.assertSame(skin.getFormat(), ObjMod.EncodingFormat.OBJ_0x3);
		Assert.assertEquals(skin.getOrigObjs().size(), 1);
		Assert.assertEquals(skin.getCustomObjs().size(), 1);
		Assert.assertEquals(skin.getCustomObjs().get(0).get(W3U.State.TEXT_NAME).toString(), "TRIGSTR_003");
		readWriteCycle(W3U.class, skinFile);
		assertDefaultWriteIsExact(skin, skinFile);
	}

	@Test
	public void allSkinObjectModsAreDiscoverable() {
		File[] paths = { W3A.SKIN_PATH, W3B.SKIN_PATH, W3D.SKIN_PATH, W3H.SKIN_PATH,
				W3Q.SKIN_PATH, W3T.SKIN_PATH, W3U.SKIN_PATH };
		Class<?>[] types = { W3A.class, W3B.class, W3D.class, W3H.class,
				W3Q.class, W3T.class, W3U.class };

		for (int i = 0; i < paths.length; i++) {
			Assert.assertTrue(War3MapFiles.files().contains(paths[i]), "archive recovery must include " + paths[i]);
			Assert.assertTrue(ObjMod.getMapFiles().contains(paths[i]), "object merger must include " + paths[i]);
			Assert.assertTrue(types[i].isInstance(ObjMod.createFromInFile(paths[i])), "wrong parser for " + paths[i]);
		}
	}

	private void assertDefaultWriteIsExact(W3U data, File expectedFile) throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try (Wc3BinOutputStream stream = new Wc3BinOutputStream(output)) {
			data.write(stream);
		}
		Assert.assertEquals(output.toByteArray(), Files.readAllBytes(expectedFile.toPath()));
	}
}
