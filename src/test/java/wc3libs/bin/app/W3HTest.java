package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3HTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3H.class, getFile("wc3data/W3H/war3map.w3h"));
	}
}