package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;
import java.io.File;

public class StdGameDirFinder implements GameDirFinder {
    public StdGameDirFinder() {

    }

    protected GameDirFinder getRegistryGameDirFinder() {
        return new RegistryGameDirFinder();
    }

    protected GameDirFinder getDefaultGameDirFinder() {
        return new DefaultGameDirFinder();
    }

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        String envWar3Dir = System.getenv("war3dir");

        if (envWar3Dir != null) return new File(envWar3Dir);

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
