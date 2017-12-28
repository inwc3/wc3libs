package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.WPM;
import net.moonlightflower.wc3libs.bin.app.objMod.W3A;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3ATest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3A.class, getFile("wc3data/W3A/war3map.w3a"));
	}
}