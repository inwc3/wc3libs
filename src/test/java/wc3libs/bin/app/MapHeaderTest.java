package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.app.MapHeader;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MapHeaderTest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		File inFile = getFile("wc3data/Map/Forest_Defense_0.18w.w3x");

		Wc3BinInputStream stream = new Wc3BinInputStream(inFile);

		MapHeader.Reader reader = new MapHeader.Reader(stream);

		MapHeader mapHeader = reader.exec();

		stream.close();

		File outFile = File.createTempFile("wc3libsTest", "mapHeader");

		Orient.copyFile(inFile, outFile, true);

		mapHeader.writeToMapFile(outFile);

		byte[] inBytes = Files.readAllBytes(inFile.toPath());
		byte[] outBytes = Files.readAllBytes(outFile.toPath());

		Assert.assertEquals(outBytes, inBytes);
	}
}