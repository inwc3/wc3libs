package wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3E;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.Size;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class W3ETest extends Wc3LibTest {
	@Test
	public void readWriteCycle() throws IOException {
		readWriteCycle(W3E.class, getFile("wc3data/W3E/war3map.w3e"));
	}

	@Test
	public void readWriteCycle_0xC() throws IOException {
		W3E source = build_0xC_fixture();
		byte[] serialized = write(source, W3E.EncodingFormat.W3E_0xC);
		W3E.Reader reader = new W3E.Reader(new Wc3BinInputStream(new ByteArrayInputStream(serialized)));
		reader.setFormat(W3E.EncodingFormat.W3E_0xC);
		W3E parsed = new W3E(reader);
		Assert.assertEquals(parsed.get(new Coords2DI(1, 0)).getBoundary(), 1);
		Assert.assertEquals(parsed.get(new Coords2DI(1, 1)).getCliffLayer(), 15);
		byte[] roundTrip = write(parsed, W3E.EncodingFormat.W3E_0xC);

		Assert.assertEquals(roundTrip, serialized);
	}

	private byte[] write(W3E w3e, W3E.EncodingFormat format) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try (Wc3BinOutputStream outStream = new Wc3BinOutputStream(out)) {
			W3E.Writer writer = w3e.new Writer(outStream);
			writer.setFormat(format);
			w3e.write(writer);
		}

		return out.toByteArray();
	}

	private W3E build_0xC_fixture() {
		W3E w3e = new W3E(new Bounds(new Size(2, 2), new Coords2DF(0, 0)));

		w3e.setTileset('L');
		w3e.setCustomTilesetFlag(1);

		w3e.setGroundTile(0, Id.valueOf("Ldrt"));
		w3e.setGroundTile(1, Id.valueOf("Lgrs"));
		w3e.setCliffTile(0, Id.valueOf("CLdi"));

		for (int x = 0; x < w3e.getWidth(); x++) {
			for (int y = 0; y < w3e.getHeight(); y++) {
				W3E.Tile tile = new W3E.Tile();

				tile.setGroundHeight((short) (W3E.Tile.GROUND_ZERO + (x + y) * 12));
				tile.setWaterLevel((short) (W3E.Tile.GROUND_ZERO - 3 * x + 2 * y));
				tile.setBoundary((x + y) % 2);
				tile.setBoundary2((x + y) % 2);
				tile.setWater((x % 2));
				tile.setBlight((y % 2));
				tile.setRamp((x + y) % 2);
				tile.setTex((x << 1) | y);
				tile.setCliffTex(2);
				tile.setCliffLayer(x == 1 && y == 1 ? 15 : 2 + x + y);
				tile.setTexDetails(0x1F);

				w3e.set(new Coords2DI(x, y), tile);
			}
		}

		return w3e;
	}
}
