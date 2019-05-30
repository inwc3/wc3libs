package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.misc.Registry;

import javax.annotation.Nonnull;
import java.io.File;

public class StdGameDirFinder implements GameDirFinder {
    public StdGameDirFinder() {

    }

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        String envWar3Dir = System.getenv("war3dir");

        if (envWar3Dir != null) return new File(envWar3Dir);

        GameDirFinder registryGameDirFinder = new RegistryGameDirFinder();

        try {
            return registryGameDirFinder.get();
        } catch (NotFoundException e) {
        }

        GameDirFinder defaultGameDirFinder = new RegistryGameDirFinder();

        try {
            return defaultGameDirFinder.get();
        } catch (NotFoundException e) {
        }

        throw new NotFoundException();
    }
}
