package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.WTG;
import net.moonlightflower.wc3libs.txt.TXT;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

public class WTGTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws Exception {
		WTG wtg = new WTG();

		wtg.addTriggerData(new TXT(getFile("wc3data/UI/TriggerData.txt")));

		readWriteCycle(wtg, getFile("wc3data/WTG/war3map.wtg"));
	}
}