package wc3libs.misc;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

public class IdTest {
	/**
	 * An unset id is four NUL bytes on disk and the empty string once read, so a
	 * literal written the padded way has to mean the same id.
	 */
	@Test
	public void nulPaddedIdEqualsItsTrimmedForm() {
		Id padded = Id.valueOf("\0\0\0\0");
		Id empty = Id.valueOf("");

		assertEquals(padded, empty);
		assertEquals(padded.hashCode(), empty.hashCode());
		assertTrue(padded.isEmpty());

		assertEquals(Id.valueOf("hfoo\0"), Id.valueOf("hfoo"));
	}

	@Test
	public void distinctIdsAreDistinct() {
		assertNotEquals(Id.valueOf("hfoo"), Id.valueOf("hpea"));
		assertNotEquals(Id.valueOf("hfoo"), Id.valueOf("HFOO"));
		assertNotEquals(Id.valueOf(""), Id.valueOf("hfoo"));
	}

	/**
	 * Equality used to compare hash codes, so any two ids whose hashes collided
	 * were the same id. These two three-character strings collide.
	 */
	@Test
	public void collidingStringsAreNotTheSameId() {
		String a = "Aa";
		String b = "BB";

		assertEquals(a.hashCode(), b.hashCode(), "precondition: these strings collide");
		assertNotEquals(Id.valueOf(a), Id.valueOf(b));
		assertNotEquals(War3String.valueOf(a), War3String.valueOf(b));
	}

	@Test
	public void worksAsAHashKey() {
		Set<Id> ids = new HashSet<>();

		ids.add(Id.valueOf("hfoo"));
		ids.add(ObjId.valueOf("hfoo"));
		ids.add(Id.valueOf("\0\0\0\0"));
		ids.add(Id.valueOf(""));

		assertEquals(ids.size(), 2, ids.toString());
		assertTrue(ids.contains(Id.valueOf("hfoo")));
	}

	/**
	 * An id whose bytes are not printable ASCII is what a reader gets when it
	 * speculatively reads four bytes to see whether they spell a token. It has to
	 * be constructible and comparable, not throw.
	 */
	@Test
	public void nonAsciiIdIsUsableForLookAhead() throws Exception {
		byte[] bytes = {(byte) 0x9A, (byte) 0xBC, 0x01, 0x02};

		try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(bytes))) {
			Id id = in.readId();

			assertFalse(new HashSet<>(Set.of(Id.valueOf("MDLX"))).contains(id));
			assertEquals(id, id);
		}
	}

	@Test
	public void idRoundTripsThroughTheStream() throws Exception {
		for (String spelling : new String[]{"hfoo", "", "\0\0\0\0"}) {
			ByteArrayOutputStream outBytes = new ByteArrayOutputStream();

			try (Wc3BinOutputStream out = new Wc3BinOutputStream(outBytes)) {
				out.writeId(Id.valueOf(spelling));
			}

			assertEquals(outBytes.toByteArray().length, 4);

			try (Wc3BinInputStream in = new Wc3BinInputStream(new ByteArrayInputStream(outBytes.toByteArray()))) {
				assertEquals(in.readId(), Id.valueOf(spelling));
			}
		}
	}
}
