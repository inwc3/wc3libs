package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameDirFinder;

import javax.annotation.Nonnull;
import java.io.File;

public class WinGameDirFinder extends GameDirFinder {
    protected GameDirFinder getRegistryGameDirFinder() {
        return new WinRegistryGameDirFinder();
    }

    protected GameDirFinder getDefaultGameDirFinder() {
        return new WinDefaultGameDirFinder();
    }

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        GameDirFinder registryGameDirFinder = getRegistryGameDirFinder();

        try {
            return registryGameDirFinder.get();
        } catch (NotFoundException e) {
        }

        GameDirFinder defaultGameDirFinder = getDefaultGameDirFinder();

        try {
            return defaultGameDirFinder.get();
        } catch (NotFoundException e) {
        }

        throw new NotFoundException();
    }
}
