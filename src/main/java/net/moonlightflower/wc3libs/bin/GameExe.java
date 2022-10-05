package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.misc.exeversion.ExeVersionPe;
import net.moonlightflower.wc3libs.misc.exeversion.ExeVersionWmic;
import net.moonlightflower.wc3libs.misc.exeversion.VersionExtractionException;
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
            log.info("Querying exe file '{}' for version with dorkbox PE", exePath);
            String version = ExeVersionPe.getVersion(exePath);
            if (!version.isEmpty()) {
                return version;
            }
        } catch (Exception e) {
            log.warn("Falling back to WMIC due to {}", e.getMessage());
            e.printStackTrace();
        }

        String version;
        try {
            version = ExeVersionWmic.getVersion(exePath);
        } catch (VersionExtractionException e) {
            log.error("WMIC extraction of file version failed due to {}", e.getMessage());

            throw new IOException("WMIC extraction of file version failed", e);
        }

        if (!version.isEmpty()) {
            return version;
        } else {
            throw new IOException(
                "Version string returned by WMIC is empty. Does this file have a version? " +
                exePath
            );
        }
    }

    @Nonnull
    public static GameVersion getVersion(@Nonnull File file) throws IOException {
        return new GameVersion(getVersionString(file));
    }
}