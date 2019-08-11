package net.moonlightflower.wc3libs.port.win.registry;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.GameExeFinder;
import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.GameVersionFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class WinRegistryGameVersionFinder implements GameVersionFinder {
    public WinRegistryGameVersionFinder() {
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
        return new WinRegistryGameExeFinder();
    }
}
