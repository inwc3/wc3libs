package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.objMod.W3T;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3TTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3T.class, getFile("wc3data/W3T/war3map.w3t"));
	}
}