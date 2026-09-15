package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinState;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3R;
import net.moonlightflower.wc3libs.bin.app.W3S;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class W3STest extends Wc3LibTest {
	@Test()
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3S.class, getFile("wc3data/W3S/war3map.w3s"));
	}

	@Test
	public void defaultFileWriterFlushesTheBufferedData() throws IOException {
		W3S source = new W3S(getFile("wc3data/W3S/war3map.w3s"));
		Path output = Files.createTempFile("wc3libs-w3s-", ".w3s");
		try {
			source.write(output.toFile());
			Assert.assertEquals(Files.readAllBytes(output), Files.readAllBytes(getFile("wc3data/W3S/war3map.w3s").toPath()));
		} finally {
			Files.deleteIfExists(output);
		}
	}
}
