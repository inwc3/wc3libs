package wc3libs.bin.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import net.moonlightflower.wc3libs.bin.BinState;
import net.moonlightflower.wc3libs.bin.BinStream.StreamException;
import net.moonlightflower.wc3libs.bin.Wc3BinStream;
import net.moonlightflower.wc3libs.bin.app.W3R;

public class W3RTest {
	@Test()
	public void writeTest() throws StreamException {
		W3R w3r = new W3R();
		
		w3r.addRect();
		w3r.addRect();
		
		Wc3BinStream stream = new Wc3BinStream();
		
		w3r.write_0x5(stream);
		
		stream.rewind();
		
		W3R newW3R = new W3R(stream);
		
		Assert.assertEquals(w3r.getRects().size(), newW3R.getRects().size());
		
		for (int i = 0; i < w3r.getRects().size(); i++) {
			W3R.Rect rect = w3r.getRects().get(i);
			W3R.Rect newRect = newW3R.getRects().get(i);
			
			for (BinState<?> state : rect.getVals().keySet()) {
				Assert.assertEquals(newRect.get(state), rect.get(state), state.toString());
			}
		}
	}
}