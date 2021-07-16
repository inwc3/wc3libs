package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.moonlightflower.wc3libs.bin.BinState;
import net.moonlightflower.wc3libs.bin.app.W3R;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class W3RTest extends Wc3LibTest {
	@Test()
	public void writeTest() throws IOException {
		W3R w3r = new W3R();
		
		w3r.addRect();
		w3r.addRect();

		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

		Wc3BinOutputStream stream = new Wc3BinOutputStream(byteStream);
		
		w3r.write_0x5(stream);

		stream.close();
		
		W3R newW3R = new W3R();

		newW3R.read_0x5(new Wc3BinInputStream(new ByteArrayInputStream(byteStream.toByteArray())));
		
		Assert.assertEquals(newW3R.getRects().size(), w3r.getRects().size());
		
		for (int i = 0; i < w3r.getRects().size(); i++) {
			W3R.Rect rect = w3r.getRects().get(i);
			W3R.Rect newRect = newW3R.getRects().get(i);
			
			for (BinState<?> state : rect.getVals().keySet()) {
				Assert.assertEquals(newRect.get(state), rect.get(state), state.toString());
			}
		}
	}

	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3R.class, getFile("wc3data/W3R/war3map.w3r"));
	}
}