package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.objMod.W3B;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3BTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3B.class, getFile("wc3data/W3B/war3map.w3b"));
	}
}