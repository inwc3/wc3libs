package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.DOO_UNITS;
import net.moonlightflower.wc3libs.bin.app.WTG;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.txt.TXT;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class WTGTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws Exception {
		WTG wtg = new WTG();

		wtg.addTriggerData(new TXT(getFile("wc3data/UI/TriggerData.txt")));

		readWriteCycle(wtg, getFile("wc3data/WTG/war3map.wtg"));
	}

	@Test
	public void readWriteCycleKeepsVersion4() throws Exception {
		ByteArrayOutputStream inputBytes = new ByteArrayOutputStream();
		try (Wc3BinOutputStream stream = new Wc3BinOutputStream(inputBytes)) {
			stream.writeId(net.moonlightflower.wc3libs.misc.Id.valueOf("WTG!"));
			stream.writeInt32(4);
			stream.writeInt32(0); // categories
			stream.writeInt32(123); // legacy unknown value
			stream.writeInt32(0); // variables
			stream.writeInt32(0); // triggers
		}

		byte[] expected = inputBytes.toByteArray();
		WTG wtg = new WTG(new Wc3BinInputStream(new ByteArrayInputStream(expected)));
		Assert.assertSame(wtg.getFormat(), WTG.EncodingFormat.WTG_0x4);

		ByteArrayOutputStream outputBytes = new ByteArrayOutputStream();
		try (Wc3BinOutputStream stream = new Wc3BinOutputStream(outputBytes)) {
			wtg.write(stream);
		}
		Assert.assertEquals(outputBytes.toByteArray(), expected);
	}

	@Test
	public void readWriteCycleV3() throws Exception {
		WTG wtg = new WTG();

		wtg.addTriggerData(new TXT(getFile("wc3data/UI/TriggerData.txt")));

		readWriteCycle(wtg, getFile("wc3data/WTG/war3map_v3.wtg"));
		Assert.assertTrue(wtg.isOpaque(), "3.0 triggers must not be decoded using the legacy WTG layout");
	}

	@Test
	public void readWriteCycleV3Filled() throws Exception {
		WTG wtg = new WTG();
		wtg.addTriggerData(new TXT(getFile("wc3data/UI/TriggerData.txt")));

		readWriteCycle(wtg, getFile("wc3data/WTG/war3map_v3_filled.wtg"));
		Assert.assertTrue(wtg.isOpaque());
	}
}
