package wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Color;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DI;
import net.moonlightflower.wc3libs.dataTypes.app.Coords4DF;
import net.moonlightflower.wc3libs.dataTypes.app.Tileset;
import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.misc.Size;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * These types override {@code equals} and, until this was fixed, not
 * {@code hashCode}, so two instances standing for the same value hashed to
 * different buckets. Anything using them as a set member or a map key -- which
 * this library does throughout -- silently kept duplicates.
 */
public class ValueTypeHashingTest {
	private static void assertUsableAsHashKey(String what, Supplier<?> factory) {
		Object a = factory.get();
		Object b = factory.get();

		assertEquals(a, b, what + ": two equal values");
		assertEquals(a.hashCode(), b.hashCode(), what + ": equal values must hash alike");

		Set<Object> set = new HashSet<>();
		set.add(a);
		set.add(b);

		assertEquals(set.size(), 1, what + ": equal values must collapse in a set");
		assertTrue(set.contains(factory.get()), what + ": lookup by an equal value");
	}

	@Test
	public void valueTypesAreUsableAsHashKeys() {
		assertUsableAsHashKey("War3Int", () -> War3Int.valueOf(42));
		assertUsableAsHashKey("War3Real", () -> War3Real.valueOf(1.5f));
		assertUsableAsHashKey("Color", () -> Color.fromBGRA255(1, 2, 3, 4));
		assertUsableAsHashKey("Coords2DI", () -> new Coords2DI(3, 4));
		assertUsableAsHashKey("Coords3DI", () -> new Coords3DI(3, 4, 5));
		assertUsableAsHashKey("Coords3DF", () -> new Coords3DF(3F, 4F, 5F));
		assertUsableAsHashKey("Coords4DF", () -> new Coords4DF(3F, 4F, 5F, 6F));
		assertUsableAsHashKey("Size", () -> new Size(16, 32));
		assertUsableAsHashKey("Bounds", () -> Bounds.valueOf(-1, 1, -2, 2));
		assertUsableAsHashKey("Tileset", () -> Tileset.ASHENVALE);
	}

	/** A Size is a Coords2DI, so the two must agree on what they hash to. */
	@Test
	public void sizeHashesLikeTheCoordsItIs() {
		Size size = new Size(16, 32);
		Coords2DI coords = new Coords2DI(16, 32);

		assertEquals(size, coords);
		assertEquals(size.hashCode(), coords.hashCode());
	}
}
