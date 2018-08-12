package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.IMP;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class IMPTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(IMP.class, getFile("wc3data/war3map.imp"));
	}
}