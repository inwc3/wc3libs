package wc3libs.port;

import net.moonlightflower.wc3libs.port.GameVersion;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GameVersionTest {
	/**
	 * Comparing two versions of different length that never actually differ used
	 * to loop forever: the loop ended only when the index had passed the end of
	 * both lists, which for {@code 1.29} against {@code 1.29.0} it never did.
	 * A version string with a trailing zero component is ordinary, so this hung
	 * on real input.
	 */
	@Test(timeOut = 5000)
	public void trailingZeroComponentsCompareEqual() {
		GameVersion shorter = new GameVersion("1.29");
		GameVersion longer = new GameVersion("1.29.0");

		assertEquals(shorter.compareTo(longer), 0);
		assertEquals(longer.compareTo(shorter), 0);
		assertEquals(shorter, longer);
		assertEquals(shorter.hashCode(), longer.hashCode());
	}

	@Test(timeOut = 5000)
	public void ordersByComponent() {
		assertTrue(new GameVersion("1.29").compareTo(new GameVersion("1.31")) < 0);
		assertTrue(new GameVersion("1.32.10").compareTo(new GameVersion("1.32.9")) > 0);
		assertTrue(new GameVersion("1.30").compareTo(new GameVersion("1.30.1")) < 0);
		assertEquals(new GameVersion("1.29").compareTo(GameVersion.VERSION_1_29), 0);
	}

	@Test(timeOut = 5000)
	public void worksAsAHashKey() {
		Set<GameVersion> versions = new HashSet<>();

		versions.add(new GameVersion("1.29"));
		versions.add(new GameVersion("1.29.0"));
		versions.add(new GameVersion(List.of(1, 29, 0, 0)));
		versions.add(new GameVersion("1.31"));

		assertEquals(versions.size(), 2, versions.toString());
	}
}
