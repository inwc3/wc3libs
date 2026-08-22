package wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.mac.MacGameDirFinder;
import net.moonlightflower.wc3libs.port.mac.MacGameExeFinder;
import net.moonlightflower.wc3libs.port.mac.PListGameVersionParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import wc3libs.misc.Wc3LibTest;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class MacGameVersionFinderTest extends Wc3LibTest {
    @Test
    public void test() throws Exception {
        GameVersion gameVersion = PListGameVersionParser.get(getFile("wc3data/context/Info.plist"));

        Assert.assertEquals(gameVersion, new GameVersion("1.31.1.12164"));
    }

    @Test
    public void readsCFBundleVersion() throws Exception {
        Path tempDir = Files.createTempDirectory("wc3libs-mac-plist-");
        File infoPlist = writeInfoPlist(tempDir, "2.0.4.23745");

        Assert.assertEquals(PListGameVersionParser.get(infoPlist), new GameVersion("2.0.4.23745"));
    }

    @Test
    public void resolvesRetailLayoutFromGameDir() throws Exception {
        Path gameDir = Files.createTempDirectory("wc3libs-mac-game-dir-");
        Path appContentsDir = gameDir
            .resolve("_retail_")
            .resolve("x86_64")
            .resolve("Warcraft III.app")
            .resolve("Contents");
        Files.createDirectories(appContentsDir.resolve("MacOS"));
        writeInfoPlist(appContentsDir, "2.0.4.23745");
        Path exePath = appContentsDir.resolve("MacOS").resolve("Warcraft III");
        Files.write(exePath, new byte[0]);

        Assert.assertEquals(MacGameDirFinder.findInfoPlist(gameDir.toFile()), appContentsDir.resolve("Info.plist").toFile());
        Assert.assertEquals(MacGameDirFinder.fromDir(gameDir.toFile()), gameDir.toFile());
        Assert.assertEquals(MacGameExeFinder.fromDir(gameDir.toFile()), exePath.toFile());
    }

    @Test
    public void findsWarcraftInfoPlistInUnknownArchDir() throws Exception {
        Path gameDir = Files.createTempDirectory("wc3libs-mac-game-dir-");
        Path appContentsDir = gameDir
            .resolve("_retail_")
            .resolve("arm64")
            .resolve("Warcraft III.app")
            .resolve("Contents");
        File infoPlist = writeInfoPlist(appContentsDir, "2.0.4.23745");

        Assert.assertEquals(MacGameDirFinder.findInfoPlist(gameDir.toFile()), infoPlist);
    }

    private File writeInfoPlist(Path dir, String version) throws IOException {
        Files.createDirectories(dir);

        Path infoPlist = dir.resolve("Info.plist");
        String content = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<plist version=\"1.0\">\n"
            + "<dict>\n"
            + " <key>CFBundleIdentifier</key>\n"
            + " <string>com.blizzard.WarcraftIII</string>\n"
            + " <key>CFBundleVersion</key>\n"
            + " <string>" + version + "</string>\n"
            + "</dict>\n"
            + "</plist>\n";

        Files.write(infoPlist, content.getBytes(StandardCharsets.UTF_8));

        return infoPlist.toFile();
    }
}
