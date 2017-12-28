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
}