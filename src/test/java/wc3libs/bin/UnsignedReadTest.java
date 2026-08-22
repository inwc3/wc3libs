package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.W3I;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.BitSet;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class UnsignedReadTest {
	private static Wc3BinInputStream stream(int... bytes) throws Exception {
		byte[] raw = new byte[bytes.length];

		for (int i = 0; i < bytes.length; i++) {
			raw[i] = (byte) bytes[i];
		}

		return new Wc3BinInputStream(new ByteArrayInputStream(raw));
	}

	/**
	 * The mask was an int literal, so the result stayed an int and sign-extended
	 * into the long: anything with its top bit set came back negative from a
	 * method whose whole purpose is that it does not.
	 */
	@Test
	public void readsTheFullUnsignedRange() throws Exception {
		try (Wc3BinInputStream in = stream(0xFF, 0xFF, 0xFF, 0xFF)) {
			assertEquals(in.readUInt32(), 0xFFFFFFFFL);
		}

		try (Wc3BinInputStream in = stream(0x00, 0x00, 0x00, 0x80)) {
			assertEquals(in.readUInt32(), 0x80000000L);
		}

		try (Wc3BinInputStream in = stream(0x34, 0x12, 0x00, 0x00)) {
			assertEquals(in.readUInt32(), 0x1234L);
		}
	}

	/**
	 * A force's members are a 32-bit mask. Read as a negative long it became a
	 * BitSet with every bit from 31 to 63 set, so a force holding the last
	 * player claimed to hold thirty-two more that do not exist.
	 */
	@Test
	public void forceWithTheLastPlayerDoesNotGainPhantomMembers() throws Exception {
		W3I w3i = new W3I();

		W3I.Force force = new W3I.Force();
		force.addPlayerNums(0, 31);
		w3i.addForce(force);

		W3I.Force roundTripped = roundTrip(w3i).getForces().get(0);
		BitSet players = roundTripped.getPlayers();

		assertTrue(players.get(0));
		assertTrue(players.get(31));
		assertFalse(players.get(32), "player 32 does not exist");
		assertEquals(players.cardinality(), 2, players.toString());
	}

	private static W3I roundTrip(W3I w3i) throws Exception {
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();

		try (Wc3BinOutputStream out = new Wc3BinOutputStream(outBytes)) {
			w3i.write(out);
		}

		try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(outBytes.toByteArray()))) {
			return new W3I(in);
		}
	}
}
