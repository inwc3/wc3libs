package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3E;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.testng.Assert.assertEquals;

/**
 * A terrain tile is seven bytes: ground height, then a water level whose top bit
 * is the camera-bounds flag, then a byte holding four flags and the ground
 * texture, then the texture details, then the cliff texture and layer height.
 * <p>
 * The reader and writer used to assign the four flags in the opposite order to
 * the format's. Being wrong in both directions, a round trip never noticed, but
 * every one of the accessors returned a different tile property than its name
 * says.
 */
public class W3ETileTest {
	private static W3E.Tile read(byte[] bytes) throws Exception {
		try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(bytes))) {
			return new W3E.Tile(in);
		}
	}

	private static byte[] write(W3E.Tile tile) throws Exception {
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();

		try (Wc3BinOutputStream out = new Wc3BinOutputStream(outBytes)) {
			new W3E.Tile.Writer(out, W3E.EncodingFormat.W3E_0xB).exec(tile);
		}

		return outBytes.toByteArray();
	}

	/** Bit 0 of the flags byte is the ramp. */
	@Test
	public void readsRampFromBitZero() throws Exception {
		W3E.Tile tile = read(new byte[]{0x00, 0x20, 0x00, 0x00, 0x01, 0x00, 0x00});

		assertEquals(tile.getRamp(), 1);
		assertEquals(tile.getBlight(), 0);
		assertEquals(tile.getWater(), 0);
		assertEquals(tile.getBoundary2(), 0);
		assertEquals(tile.getTex(), 0);
	}

	@Test
	public void readsEachFlagFromItsOwnBit() throws Exception {
		assertEquals(read(new byte[]{0, 0x20, 0, 0, 0x02, 0, 0}).getBlight(), 1);
		assertEquals(read(new byte[]{0, 0x20, 0, 0, 0x04, 0, 0}).getWater(), 1);
		assertEquals(read(new byte[]{0, 0x20, 0, 0, 0x08, 0, 0}).getBoundary2(), 1);
		assertEquals(read(new byte[]{0, 0x20, 0, 0, (byte) 0x70, 0, 0}).getTex(), 7);
	}

	/**
	 * The water level's top bit is a flag. Read as a signed short it
	 * sign-extended, so a set flag reported as -1 rather than 1.
	 */
	@Test
	public void readsCameraBoundsFlagAsOne() throws Exception {
		W3E.Tile tile = read(new byte[]{0x00, 0x20, 0x34, (byte) 0x92, 0x00, 0x00, 0x00});

		assertEquals(tile.getBoundary(), 1);
		assertEquals(tile.getWaterLevel(), (short) 0x1234);
	}

	@Test
	public void readsCliffTextureAndLayer() throws Exception {
		W3E.Tile tile = read(new byte[]{0x00, 0x20, 0x00, 0x00, 0x00, 0x00, (byte) 0xA5});

		assertEquals(tile.getCliffTex(), 0xA);
		assertEquals(tile.getCliffLayer(), 0x5);
	}

	@Test
	public void writesBackTheSameBytes() throws Exception {
		byte[][] samples = {
			{0x00, 0x20, 0x34, (byte) 0x92, (byte) 0x7F, 0x11, (byte) 0xA5},
			{0x10, 0x20, 0x00, 0x10, 0x00, 0x00, 0x00},
			{(byte) 0xFF, 0x1F, (byte) 0xFF, 0x7F, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF},
		};

		for (byte[] sample : samples) {
			assertEquals(write(read(sample)), sample);
		}
	}

	/**
	 * A layer height of 15 used to be clamped to 14 on write, so a tile read
	 * from a file came back out different from how it went in.
	 */
	@Test
	public void keepsALayerHeightOfFifteen() throws Exception {
		byte[] sample = {0x00, 0x20, 0x00, 0x00, 0x00, 0x00, 0x0F};

		assertEquals(read(sample).getCliffLayer(), 15);
		assertEquals(write(read(sample)), sample);
	}
}
