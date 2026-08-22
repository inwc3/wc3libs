package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
import net.moonlightflower.wc3libs.misc.ObjId;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import javax.annotation.Nonnull;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class DOO_UNITSTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(DOO_UNITS.class, getFile("wc3data/DOO_UNITS/war3mapUnits.doo"));
	}

	@Test()
	public void readWriteCycle_with_skin() throws IOException {
		readWriteCycle(DOO_UNITS.class, resource("war3mapUnits (2).doo"));
	}

	@Test()
	public void readWriteCycle_subversion_9() throws IOException {
		File file = resource("war3mapUnits-subversion-9.doo");

		try (Wc3BinInputStream inStream = new Wc3BinInputStream(file)) {
			DOO_UNITS dooUnits = new DOO_UNITS(inStream);

			Assert.assertEquals(dooUnits.getSubVersion(), 9);

			// This file declares sub-version 9 and still carries skin ids, each
			// holding the object's own type: the two are independent.
			Assert.assertEquals(dooUnits.getSkinIds(), DOO_UNITS.SkinIds.PRESENT);

			Assert.assertEquals(dooUnits.getObjs().size(), 2);
			Assert.assertEquals(dooUnits.getObjs().get(0).getTypeId(), ObjId.valueOf("hfoo"));
			Assert.assertEquals(dooUnits.getObjs().get(0).getSkinId(), ObjId.valueOf("hfoo"));
			Assert.assertEquals(dooUnits.getObjs().get(1).getTypeId(), ObjId.valueOf("sloc"));
			Assert.assertEquals(dooUnits.getObjs().get(1).getSkinId(), ObjId.valueOf("sloc"));
		}

		// Writing used to relabel this as sub-version 11 and add that
		// sub-version's item-table pointer and hero attributes, which is a file
		// nothing can read back.
		readWriteCycle(DOO_UNITS.class, file);
	}

	/**
	 * The sub-version says nothing about skin ids: these two files both declare
	 * 11 and only one has them, and the sub-version 9 file above has them too.
	 * That is why the layout is worked out from the file's own size.
	 */
	@Test()
	public void detectsSkinIdsIndependentlyOfSubVersion() throws IOException {
		try (Wc3BinInputStream inStream = new Wc3BinInputStream(resource("war3mapUnits.doo"))) {
			DOO_UNITS dooUnits = new DOO_UNITS(inStream);

			Assert.assertEquals(dooUnits.getSubVersion(), 11);
			Assert.assertEquals(dooUnits.getSkinIds(), DOO_UNITS.SkinIds.ABSENT);
			Assert.assertNull(dooUnits.getObjs().get(0).getSkinId());
		}

		try (Wc3BinInputStream inStream = new Wc3BinInputStream(resource("war3mapUnits (2).doo"))) {
			DOO_UNITS dooUnits = new DOO_UNITS(inStream);

			Assert.assertEquals(dooUnits.getSubVersion(), 11);
			Assert.assertEquals(dooUnits.getSkinIds(), DOO_UNITS.SkinIds.PRESENT);
			Assert.assertNotNull(dooUnits.getObjs().get(0).getSkinId());
		}
	}

	@Nonnull
	private static File resource(@Nonnull String name) throws IOException {
		URL url = Wc3LibTest.class.getResource("/wc3data/DOO_UNITS/" + name);

		if (url == null) {
			throw new IOException("could not find resource /wc3data/DOO_UNITS/" + name);
		}

		return new File(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8));
	}
}
