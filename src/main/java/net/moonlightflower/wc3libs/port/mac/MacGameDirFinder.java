package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;

public class MacGameDirFinder extends GameDirFinder {
    public static File DIR = new File("/Applications/Warcraft III");

    public static final Path RETAIL_DIR = Paths.get("_retail_");
    public static final Path X86_DIR = Paths.get("x86");
    public static final Path X64_DIR = Paths.get("x86_64");
    public static final Path APP_DIR = Paths.get("Warcraft III.app");
    public static final Path INFO_PLIST_PATH = Paths.get("Contents", "Info.plist");
    public static final Path LOWERCASE_INFO_PLIST_PATH = Paths.get("Contents", "info.plist");

    private static final List<Path> INFO_PLIST_RELATIVE_PATHS = Arrays.asList(
        RETAIL_DIR.resolve(X64_DIR).resolve(APP_DIR).resolve(INFO_PLIST_PATH),
        RETAIL_DIR.resolve(X64_DIR).resolve(APP_DIR).resolve(LOWERCASE_INFO_PLIST_PATH),
        RETAIL_DIR.resolve(X86_DIR).resolve(APP_DIR).resolve(INFO_PLIST_PATH),
        RETAIL_DIR.resolve(X86_DIR).resolve(APP_DIR).resolve(LOWERCASE_INFO_PLIST_PATH),
        X64_DIR.resolve(APP_DIR).resolve(INFO_PLIST_PATH),
        X64_DIR.resolve(APP_DIR).resolve(LOWERCASE_INFO_PLIST_PATH),
        X86_DIR.resolve(APP_DIR).resolve(INFO_PLIST_PATH),
        X86_DIR.resolve(APP_DIR).resolve(LOWERCASE_INFO_PLIST_PATH),
        APP_DIR.resolve(INFO_PLIST_PATH),
        APP_DIR.resolve(LOWERCASE_INFO_PLIST_PATH),
        INFO_PLIST_PATH,
        LOWERCASE_INFO_PLIST_PATH
    );

    private static boolean isInfoPlist(@Nonnull Path path) {
        return path.getFileName() != null && path.getFileName().toString().equalsIgnoreCase("Info.plist");
    }

    private static String getPlistValue(@Nonnull File file, @Nonnull String keyName) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

            doc.normalize();

            Node dict = doc.getDocumentElement().getElementsByTagName("dict").item(0);
            NodeList keyList = ((Element) dict).getElementsByTagName("key");

            for (int i = 0; i < keyList.getLength(); i++) {
                Node key = keyList.item(i);

                if (key.getTextContent().equals(keyName)) {
                    Node val = key.getNextSibling();

                    while (val != null && val.getNodeType() != Node.ELEMENT_NODE) {
                        val = val.getNextSibling();
                    }

                    if (val != null) {
                        return val.getTextContent();
                    }
                }
            }
        } catch (Exception ignored) {
        }

        return null;
    }

    private static boolean isWarcraftInfoPlist(@Nonnull File file) {
        String bundleIdentifier = getPlistValue(file, "CFBundleIdentifier");

        if ("com.blizzard.WarcraftIII".equals(bundleIdentifier)) {
            return true;
        }

        String executable = getPlistValue(file, "CFBundleExecutable");

        return "Warcraft III".equals(executable);
    }

    private static File findInfoPlistInTree(@Nonnull File dir) throws IOException {
        if (!dir.exists()) {
            return null;
        }

        try (Stream<Path> paths = Files.find(dir.toPath(), 10, (path, attrs) -> attrs.isRegularFile() && isInfoPlist(path))) {
            return paths
                .map(Path::toFile)
                .filter(MacGameDirFinder::isWarcraftInfoPlist)
                .findFirst()
                .orElse(null);
        }
    }

    public static File findInfoPlist(@Nonnull File dir) throws NotFoundException {
        if (dir.isFile() && dir.getName().equalsIgnoreCase("Info.plist")) {
            return dir;
        }

        for (Path relativePath : INFO_PLIST_RELATIVE_PATHS) {
            File file = dir.toPath().resolve(relativePath).toFile();

            if (file.exists()) {
                return file;
            }
        }

        try {
            File file = findInfoPlistInTree(dir);

            if (file != null) {
                return file;
            }
        } catch (IOException e) {
            throw new NotFoundException(e);
        }

        throw new NotFoundException("tried all known mac Info.plist sub-paths in " + dir.getAbsolutePath());
    }

    public static File fromDir(@Nonnull File dir) throws NotFoundException {
        findInfoPlist(dir);

        if (dir.getName().equals("_retail_")) {
            File parent = dir.getParentFile();

            if (parent != null) {
                return parent;
            }
        }

        if (dir.getName().equals("Warcraft III.app")) {
            File archDir = dir.getParentFile();
            File retailDir = (archDir != null) ? archDir.getParentFile() : null;

            if (retailDir != null && retailDir.getName().equals("_retail_")) {
                File gameDir = retailDir.getParentFile();

                if (gameDir != null) {
                    return gameDir;
                }
            }
        }

        return dir;
    }

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        return fromDir(DIR);
    }
}
