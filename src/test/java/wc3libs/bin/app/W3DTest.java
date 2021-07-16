package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.objMod.W3D;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3DTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3D.class, getFile("wc3data/W3D/war3map.w3d"));
	}
}