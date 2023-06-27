package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.NotFoundException;
import net.moonlightflower.wc3libs.port.Orient;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameExeFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertTrue;

public class GameExeTest {
    private static final Logger log = LoggerFactory.getLogger(GameExeTest.class.getName());
    @Test()
    public void extractGameVersionTest() throws IOException {
        if (!Orient.isWindowsSystem()) {
            log.info("not a windows system (" + Orient.getSystem() + "), skip setGet tests");

            return;
        }

        try {
            File gameExeFile = new WinRegistryGameExeFinder().get();

            GameVersion gameVersion = GameExe.getVersion(gameExeFile);
            String versionStr = gameVersion.toString();
            System.out.println(versionStr);

            Pattern majorMinorRegex = Pattern.compile("^\\d+\\.\\d+");

            // Pre-Reforged and Reforged exe: "1.29.1234"
            // Classic exe: "1.0.0.1"
            // ROC Beta exe: "200.0.0.3627"
            Matcher m = majorMinorRegex.matcher(versionStr);
            assertTrue(m.find(), "Could not match game exe version, got: '" + gameVersion + "'");
            log.info("Game version extracted: " + gameVersion);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
