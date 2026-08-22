package wc3libs.port;

import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.War3MapFiles;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import javax.annotation.Nonnull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

public class JMpqPortTest extends Wc3LibTest {
	/** A map that carries a usable {@code (listfile)}. */
	private final static String LISTED_MAP = "wc3data/Map/Forest_Defense_0.18w.w3x";

	/** A map whose {@code (listfile)} cannot be decoded, as protected maps go. */
	private final static String UNLISTED_MAP = "in.w3x";

	@Nonnull
	private static Path copyToTemp(String resource, String name) throws Exception {
		Path dir = Files.createTempDirectory("wc3libs-jmpqport");
		Path target = dir.resolve(name);

		Files.copy(getFile(resource).toPath(), target);

		target.toFile().deleteOnExit();
		dir.toFile().deleteOnExit();

		return target;
	}

	@Nonnull
	private static List<String> listNames(Path map) throws Exception {
		return new JMpqPort().listFiles(map.toFile()).stream()
			.map(File::toString)
			.collect(Collectors.toList());
	}

	@Test
	public void listsFilesOfAListedMap() throws Exception {
		List<String> names = listNames(copyToTemp(LISTED_MAP, "listed.w3x"));

		assertFalse(names.isEmpty(), "a map with a listfile should enumerate");
		assertTrue(names.stream().anyMatch(name -> name.equalsIgnoreCase("war3map.w3i")), names.toString());
	}

	/**
	 * The archive cannot name its own contents, so enumeration has to fall back
	 * to asking for the paths the game asks for.
	 */
	@Test
	public void recoversStandardNamesOfAnUnlistedMap() throws Exception {
		List<String> names = listNames(copyToTemp(UNLISTED_MAP, "unlisted.w3x"));

		assertTrue(names.stream().anyMatch(name -> name.equalsIgnoreCase("war3map.w3i")), names.toString());
		assertTrue(names.stream().anyMatch(name -> name.equalsIgnoreCase("war3map.w3e")), names.toString());
	}

	@Test
	public void importedFileIsReadableAndLeavesTheRestAlone() throws Exception {
		Path map = copyToTemp(LISTED_MAP, "import.w3x");

		List<String> before = listNames(map);

		Path payload = map.resolveSibling("payload.txt");
		Files.writeString(payload, "injected by wc3libs");

		JMpqPort.importFile(map.toFile(), payload.toFile(), new File("war3mapMisc.txt"));

		List<String> after = listNames(map);

		assertTrue(after.stream().anyMatch(name -> name.equalsIgnoreCase("war3mapMisc.txt")), after.toString());

		for (String name : before) {
			assertTrue(after.stream().anyMatch(name::equalsIgnoreCase), "lost " + name + " on rebuild");
		}

		MpqPort.Out out = new JMpqPort.Out();
		out.add(new File("war3mapMisc.txt"));

		MpqPort.Out.Result result = out.commit(map.toFile());

		try (var stream = result.getInputStream(new File("war3mapMisc.txt"))) {
			assertEquals(new String(stream.readAllBytes(), StandardCharsets.UTF_8), "injected by wc3libs");
		}
	}

	/**
	 * Rebuilding a map whose list file is unusable used to keep only what the
	 * archive could name, which for a protected map is nothing at all: the
	 * terrain, objects and script went with it.
	 */
	@Test
	public void rebuildKeepsStandardFilesOfAnUnlistedMap() throws Exception {
		Path map = copyToTemp(UNLISTED_MAP, "unlisted-rebuild.w3x");

		List<String> before = listNames(map);

		Path payload = map.resolveSibling("payload.txt");
		Files.writeString(payload, "x");

		JMpqPort.importFile(map.toFile(), payload.toFile(), new File("war3mapSkin.txt"));

		List<String> after = listNames(map);

		assertTrue(after.size() >= before.size(), before + " -> " + after);

		for (String name : War3MapFiles.paths()) {
			if (before.stream().noneMatch(name::equalsIgnoreCase)) continue;

			assertTrue(after.stream().anyMatch(name::equalsIgnoreCase), "lost " + name + " on rebuild");
		}
	}

	/**
	 * The stream belongs to the caller, who may be writing several exports into
	 * one destination, so the port must not close it.
	 */
	@Test
	public void doesNotCloseACallerSuppliedStream() throws Exception {
		Path map = copyToTemp(LISTED_MAP, "streams.w3x");

		class CountingStream extends ByteArrayOutputStream {
			int closes;

			@Override
			public void close() {
				closes++;
			}
		}

		CountingStream first = new CountingStream();
		CountingStream second = new CountingStream();

		MpqPort.Out out = new JMpqPort.Out();
		out.add(new File("war3map.w3i"), first);
		out.add(new File("war3map.w3e"), second);

		out.commit(map.toFile());

		assertEquals(first.closes, 0, "the port closed the caller's stream");
		assertEquals(second.closes, 0, "the port closed the caller's stream");
		assertTrue(first.size() > 0, "nothing was written to the first stream");
		assertTrue(second.size() > 0, "nothing was written to the second stream");
	}

	/** One destination taking several exports must receive all of them. */
	@Test
	public void writesSeveralExportsIntoOneStream() throws Exception {
		Path map = copyToTemp(LISTED_MAP, "shared-stream.w3x");

		ByteArrayOutputStream shared = new ByteArrayOutputStream();

		MpqPort.Out out = new JMpqPort.Out();
		out.add(new File("war3map.w3i"), shared);
		out.add(new File("war3map.w3e"), shared);

		MpqPort.Out.Result result = out.commit(map.toFile());

		int w3iSize = result.getInputStream(new File("war3map.w3i")).readAllBytes().length;
		int w3eSize = result.getInputStream(new File("war3map.w3e")).readAllBytes().length;

		assertEquals(shared.size(), w3iSize + w3eSize,
			"a closed stream would have swallowed everything after the first export");
	}

	/**
	 * A destination that cannot be written to is not the archive's fault.
	 * Treating it as "this volume does not have the file" hid disk-full and
	 * permission errors, moved on to the next volume, and could append the same
	 * bytes twice after a partial write.
	 */
	@Test
	public void destinationFailurePropagatesInsteadOfLookingLikeAMissingEntry() throws Exception {
		Path map = copyToTemp(LISTED_MAP, "failing-destination.w3x");

		OutputStream broken = new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				throw new IOException("disk full");
			}

			@Override
			public void write(byte[] b, int off, int len) throws IOException {
				throw new IOException("disk full");
			}
		};

		MpqPort.Out out = new JMpqPort.Out();
		out.add(new File("war3map.w3i"), broken);

		assertThrows(IOException.class, () -> out.commit(map.toFile()));
	}

	@Test
	public void createsAnArchiveThatDoesNotExistYet() throws Exception {
		Path dir = Files.createTempDirectory("wc3libs-jmpqport-new");
		Path map = dir.resolve("fresh.w3x");

		Path payload = dir.resolve("payload.txt");
		Files.writeString(payload, "fresh");

		Vector<File> targets = new Vector<>();
		targets.add(map.toFile());

		MpqPort.In in = new JMpqPort.In();
		in.add(payload.toFile(), new File("war3map.j"));
		in.commit(targets);

		assertTrue(Files.exists(map), "archive should have been created");
		assertTrue(listNames(map).stream().anyMatch(name -> name.equalsIgnoreCase("war3map.j")));

		map.toFile().deleteOnExit();
		payload.toFile().deleteOnExit();
		dir.toFile().deleteOnExit();
	}
}
