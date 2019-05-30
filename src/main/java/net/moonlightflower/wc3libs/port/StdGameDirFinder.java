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
        GameDirFinder registryGameDirFinder = Context.getService(RegistryGameDirFinder.class);

        try {
            registryGameDirFinder.get();
        } catch (NotFoundException e) {
        }

        throw new NotFoundException();
    }
}
