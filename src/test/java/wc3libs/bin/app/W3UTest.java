package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.objMod.W3H;
import net.moonlightflower.wc3libs.bin.app.objMod.W3U;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.IOException;

public class W3UTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3U.class, getFile("wc3data/W3U/war3map.w3u"));
        readWriteCycle(W3U.class, getFile("wc3data/W3U/war3mapReforged.w3u"));
        readWriteCycle(W3U.class, getFile("wc3data/W3U/war3mapBorked.w3u"));
	}
}