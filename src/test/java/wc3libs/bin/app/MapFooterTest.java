package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.app.MapFooter;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MapFooterTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		File inFile = getFile("wc3data/Map/Forest_Defense_0.18w_sign.w3x");

		MapFooter mapFooter = MapFooter.ofMapFile(inFile);

		File outFile = File.createTempFile("wc3libsTest", "mapFooter");

		Orient.copyFile(inFile, outFile, true);

		mapFooter.writeToMapFile(outFile);

		byte[] inBytes = Files.readAllBytes(inFile.toPath());
		byte[] outBytes = Files.readAllBytes(outFile.toPath());

		Assert.assertEquals(outBytes, inBytes);
	}
}