package net.moonlightflower.wc3libs.port;

import dorkbox.peParser.PE;

import javax.annotation.Nonnull;
import java.io.File;

public class StdGameVersionFinder implements GameVersionFinder {
    public StdGameVersionFinder() {
    }

    private boolean _enteredGameExeFinder = false;

    @Nonnull
    public GameVersion get() throws NotFoundException {
        if (!_enteredGameExeFinder) {
            _enteredGameExeFinder = true;

            GameExeFinder gameExeFinder = getGameExeFinder();

            try {
                File gameExeFile = gameExeFinder.get();

                try {
                    return new GameVersion(PE.getVersion(gameExeFile.getAbsolutePath()));
                } catch (Exception e) {
                    throw new NotFoundException(e);
                }
            } catch (NotFoundException ignored) {
            }

            _enteredGameExeFinder = false;
        }

        return getTelemetryGameVersionFinder().get();
    }

    protected GameExeFinder getGameExeFinder() {
        return Context.getService(GameExeFinder.class);
    }

    protected GameVersionFinder getTelemetryGameVersionFinder() {
        return new TelemetryGameVersionFinder();
    }
}
