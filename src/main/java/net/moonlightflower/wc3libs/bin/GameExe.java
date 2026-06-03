package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.misc.exeversion.ExeVersionPe;
import net.moonlightflower.wc3libs.port.GameVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class GameExe {
    private static final Logger log = LoggerFactory.getLogger(GameExe.class.getName());

    @Nonnull
    public static String getVersionString(@Nonnull File file) throws IOException {
        String exePath = file.getAbsolutePath();
        try {
            log.info("Querying exe file '{}' for version via VS_VERSIONINFO resource", exePath);
            String version = ExeVersionPe.getVersion(exePath);
            if (!version.isEmpty()) {
                return version;
            }
        } catch (Exception e) {
            // Keep the user-facing log short; the full parser trace is only useful with debug logging enabled.
            log.warn("Could not read version resource of '{}': {}", exePath, e.getMessage());
            log.debug("VS_VERSIONINFO extraction failed for '{}'", exePath, e);
            throw new IOException("Could not read version resource of " + exePath, e);
        }

        throw new IOException("No version resource found in " + exePath);
    }

    @Nonnull
    public static GameVersion getVersion(@Nonnull File file) throws IOException {
        return new GameVersion(getVersionString(file));
    }
}
