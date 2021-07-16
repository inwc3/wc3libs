package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.WCT;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class WCTTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(WCT.class, getFile("wc3data/WCT/war3map.wct"));
	}
}