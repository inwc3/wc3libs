package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
import net.moonlightflower.wc3libs.misc.ObjId;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

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
		URL url = Wc3LibTest.class.getResource("/wc3data/DOO_UNITS/war3mapUnits (2).doo");

		if (url == null) {
			throw new IOException("could not find resource /wc3data/DOO_UNITS/war3mapUnits (2).doo");
		}

		File file = new File(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8));

		readWriteCycle(DOO_UNITS.class, file);
	}

	@Test()
	public void readWriteCycle_subversion_9() throws IOException {
		URL url = Wc3LibTest.class.getResource("/wc3data/DOO_UNITS/war3mapUnits-subversion-9.doo");

		if (url == null) {
			throw new IOException("could not find resource /wc3data/DOO_UNITS/war3mapUnits-subversion-9.doo");
		}

		File file = new File(URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8));

		try (Wc3BinInputStream inStream = new Wc3BinInputStream(file)) {
			DOO_UNITS dooUnits = new DOO_UNITS(inStream);

			Assert.assertEquals(dooUnits.getObjs().size(), 2);
			Assert.assertEquals(dooUnits.getObjs().get(0).getTypeId(), ObjId.valueOf("hfoo"));
			Assert.assertEquals(dooUnits.getObjs().get(1).getTypeId(), ObjId.valueOf("sloc"));
		}
	}
}
