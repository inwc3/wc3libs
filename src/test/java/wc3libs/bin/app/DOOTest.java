package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.DOO;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class DOOTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(DOO.class, getFile("wc3data/DOO/war3map.doo"));
	}
}