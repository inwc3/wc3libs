package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.W3S;
import net.moonlightflower.wc3libs.bin.app.WPM;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class WPMTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(WPM.class, getFile("wc3data/WPM/war3map.wpm"));
	}
}