package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.SHD;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class SHDTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(SHD.class, getFile("wc3data/SHD/war3map.shd"));
	}
}