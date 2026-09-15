package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.WCT;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class WCTTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(WCT.class, getFile("wc3data/WCT/war3map.wct"));
	}

	@Test
	public void triggerTextPreservesMalformedLocaleBytes() throws IOException {
		byte[] original = new byte[] { 2, 0, 0, 0, (byte) 0xE4, 'x' };
		WCT.Trig trigger = new WCT.Trig();
		try (Wc3BinInputStream input = new Wc3BinInputStream(new ByteArrayInputStream(original))) {
			trigger.read_0x0(input);
		}

		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		try (Wc3BinOutputStream output = new Wc3BinOutputStream(bytes)) {
			trigger.write_0x0(output);
		}

		Assert.assertEquals(bytes.toByteArray(), original);
	}
}
