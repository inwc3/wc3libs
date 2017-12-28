package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3Q;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3QTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3Q.class, getFile("wc3data/W3Q/war3map.w3q"));
	}
}