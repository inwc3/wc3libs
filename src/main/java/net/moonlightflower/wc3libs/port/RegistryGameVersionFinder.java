package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.bin.GameExe;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class RegistryGameVersionFinder implements GameVersionFinder {
    public RegistryGameVersionFinder() {
    }

    @Nonnull
    public GameVersion get() throws NotFoundException {
        GameExeFinder gameExeFinder = getRegistryGameExeFinder();

        File gameExeFile = gameExeFinder.get();

        try {
            return GameExe.getVersion(gameExeFile);
        } catch (IOException e) {
            throw new NotFoundException(e);
        }
    }

    protected GameExeFinder getRegistryGameExeFinder() {
        return new RegistryGameExeFinder();
    }
}
