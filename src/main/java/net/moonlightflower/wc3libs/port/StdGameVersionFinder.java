package net.moonlightflower.wc3libs.port;

import dorkbox.peParser.PE;
import net.moonlightflower.wc3libs.bin.GameExe;

import javax.annotation.Nonnull;
import java.io.File;

public class StdGameVersionFinder implements GameVersionFinder {
    public StdGameVersionFinder() {
    }

    private boolean _entered = false;

    @Nonnull
    public GameVersion get() throws NotFoundException {
        if (_entered) throw new NotFoundException(new Exception("already tried"));

        _entered = true;

        try {
            GameExeFinder gameExeFinder = getGameExeFinder();

            try {
                File gameExeFile = gameExeFinder.get();

                try {
                    return GameExe.getVersion(gameExeFile);
                } catch (Exception e) {
                    throw new NotFoundException(e);
                }
            } catch (NotFoundException ignored) {
            }

            return getTelemetryGameVersionFinder().get();
        } finally {
            _entered = false;
        }
    }

    protected GameExeFinder getGameExeFinder() {
        return Context.getService(GameExeFinder.class);
    }

    protected GameVersionFinder getTelemetryGameVersionFinder() {
        return new TelemetryGameVersionFinder();
    }
}
