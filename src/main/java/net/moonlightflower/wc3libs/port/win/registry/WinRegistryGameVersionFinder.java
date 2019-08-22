package net.moonlightflower.wc3libs.port.win.registry;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.*;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class WinRegistryGameVersionFinder extends GameVersionFinder {
    public WinRegistryGameVersionFinder() {
    }

    @Nonnull
    public GameVersion find() throws NotFoundException {
        GameExeFinder gameExeFinder = getRegistryGameExeFinder();

        try {
            File gameExeFile = gameExeFinder.get();

            return GameExe.getVersion(gameExeFile);
        } catch (IOException | AlreadyTriedException e) {
            throw new NotFoundException(e);
        }
    }

    protected GameExeFinder getRegistryGameExeFinder() {
        return new WinRegistryGameExeFinder();
    }
}
