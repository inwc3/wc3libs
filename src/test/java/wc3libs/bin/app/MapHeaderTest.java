package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.MapHeader;
import net.moonlightflower.wc3libs.bin.app.WPM;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	/**
	 * A .w3x is a bare MPQ archive when it carries no header, which the writer
	 * already knows about. Reading one used to hand back a header made of
	 * whatever the archive's first bytes happened to say.
	 */
	@Test()
	public void rejectsAFileWithoutTheHeaderToken() throws IOException {
		byte[] bareArchive = new byte[600];

		bareArchive[0] = 'M';
		bareArchive[1] = 'P';
		bareArchive[2] = 'Q';
		bareArchive[3] = 0x1A;

		try (Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bareArchive))) {
			Assert.assertThrows(BinInputStream.StreamException.class, () -> new MapHeader.Reader(stream).exec());
		}
	}

	/**
	 * The padding length subtracted the start position twice instead of using it
	 * to measure what had been written, so a header written anywhere but the
	 * start of a stream came out short by exactly that offset.
	 */
	@Test()
	public void padsToFiveHundredAndTwelveBytesWhateverTheStartPosition() throws IOException {
		MapHeader mapHeader = new MapHeader();
		mapHeader.setMapName("a map");
		mapHeader.setMaxPlayersCount(4);

		int prefix = 16;

		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();

		try (Wc3BinOutputStream out = new Wc3BinOutputStream(outBytes)) {
			out.writeBytes(new byte[prefix]);

			new MapHeader.Writer(out).exec(mapHeader);
		}

		Assert.assertEquals(outBytes.toByteArray().length, prefix + (int) MapHeader.HEADER_BYTES_SIZE);
	}
}
