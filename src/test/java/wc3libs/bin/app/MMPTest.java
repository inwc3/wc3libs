package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.MMP;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class MMPTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(MMP.class, getFile("wc3data/MMP/war3map.mmp"));
	}
}