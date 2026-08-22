package wc3libs.txt;

import net.moonlightflower.wc3libs.txt.Profile;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Every profile class names one game file, and the name is the class's own. Four
 * of them pointed at a sibling's file instead -- {@code CommandStrings} read
 * {@code CommandFunc.txt}, and so on -- which is the sort of thing that survives
 * indefinitely because each class is a single line nobody rereads. Checking the
 * rule is cheaper than checking twenty-odd constants.
 */
public class ProfileGamePathTest {
	private final static String PACKAGE = "net.moonlightflower.wc3libs.txt.app.profile";

	@Test
	public void eachProfileNamesItsOwnGameFile() throws Exception {
		List<Class<?>> classes = profileClasses();

		assertTrue(classes.size() > 20, "expected the profile package to be found, got " + classes.size());

		for (Class<?> type : classes) {
			Field field = type.getDeclaredField("GAME_PATH");
			File path = (File) field.get(null);

			assertEquals(fileName(path), type.getSimpleName() + ".txt",
				type.getName() + " points at " + path);
		}
	}

	/**
	 * These paths are spelled the way the game spells them, with backslashes,
	 * and are held in a {@link File}. On Windows a backslash is a separator and
	 * {@code getName()} gives the last segment; everywhere else it is an
	 * ordinary character and {@code getName()} gives the whole string. So the
	 * last segment has to be taken without asking the platform.
	 */
	private static String fileName(File path) {
		String s = path.toString();
		int cut = Math.max(s.lastIndexOf('\\'), s.lastIndexOf('/'));

		return s.substring(cut + 1);
	}

	private static List<Class<?>> profileClasses() throws Exception {
		URL url = ProfileGamePathTest.class.getClassLoader().getResource(PACKAGE.replace('.', '/'));

		if (url == null) throw new IllegalStateException("cannot locate " + PACKAGE);

		Path dir = Path.of(URLDecoder.decode(url.getPath().replaceFirst("^/(.:/)", "$1"), StandardCharsets.UTF_8));

		List<Class<?>> classes = new ArrayList<>();

		try (Stream<Path> files = Files.list(dir)) {
			for (Path file : files.toList()) {
				String name = file.getFileName().toString();

				if (!name.endsWith(".class") || name.contains("$")) continue;

				Class<?> type = Class.forName(PACKAGE + "." + name.substring(0, name.length() - ".class".length()));

				if (!Profile.class.isAssignableFrom(type)) continue;

				classes.add(type);
			}
		}

		return classes;
	}
}
