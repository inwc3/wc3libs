package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.DOO;
import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
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
}
