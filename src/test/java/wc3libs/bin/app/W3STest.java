package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.W3S;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3STest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3S.class, getFile("wc3data/W3S/war3map.w3s"));
	}
}