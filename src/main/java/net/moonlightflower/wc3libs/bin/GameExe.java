package net.moonlightflower.wc3libs.bin;

import dorkbox.peParser.PE;
import net.moonlightflower.wc3libs.port.GameVersion;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class GameExe {
    @Nonnull
    public static String getVersionString(@Nonnull File file) throws IOException {
        try {
            return PE.getVersion(file.getAbsolutePath());
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Nonnull
    public static GameVersion getVersion(@Nonnull File file) throws IOException {
        return new GameVersion(getVersionString(file));
    }
}