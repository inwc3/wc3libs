package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.app.DOO;
import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
import net.moonlightflower.wc3libs.bin.app.MMP;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class DOOTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(DOO.class, getFile("wc3data/DOO/war3map.doo"));
	}
	
	@Test()
	public void readWriteCycle_with_skin() throws IOException {
		readWriteCycle(DOO.class, getFile("wc3data/DOO/war3map_with_skin.doo"));
	}

	/**
	 * Both of these declare sub-version 11 and only one has skin ids, so the
	 * layout has to come from the file's own size: the two candidate layouts
	 * differ by four bytes per doodad, and only one of them can leave the
	 * special-doodad section that follows intact.
	 */
	@Test()
	public void detectsSkinIdsIndependentlyOfSubVersion() throws Exception {
		try (Wc3BinInputStream inStream = new Wc3BinInputStream(getFile("wc3data/DOO/war3map.doo"))) {
			DOO doo = new DOO(inStream);

			Assert.assertEquals(doo.getSubVersion(), 11);
			Assert.assertEquals(doo.getSkinIds(), DOO.SkinIds.ABSENT);
			Assert.assertNull(doo.getDoods().get(0).getSkinId());
		}

		try (Wc3BinInputStream inStream = new Wc3BinInputStream(getFile("wc3data/DOO/war3map_with_skin.doo"))) {
			DOO doo = new DOO(inStream);

			Assert.assertEquals(doo.getSubVersion(), 11);
			Assert.assertEquals(doo.getSkinIds(), DOO.SkinIds.PRESENT);
			Assert.assertNotNull(doo.getDoods().get(0).getSkinId());
		}
	}
}
