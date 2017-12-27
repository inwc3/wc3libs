package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.W3E;
import net.moonlightflower.wc3libs.bin.app.WPM;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3ETest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3E.class, getFile("wc3data/W3E/war3map.w3e"));
	}
}