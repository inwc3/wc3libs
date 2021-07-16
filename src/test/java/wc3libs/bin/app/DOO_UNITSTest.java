package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class DOO_UNITSTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(DOO_UNITS.class, getFile("wc3data/DOO_UNITS/war3mapUnits.doo"));
	}
}