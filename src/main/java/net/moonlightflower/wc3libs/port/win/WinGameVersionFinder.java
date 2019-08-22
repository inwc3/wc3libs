package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.*;

import javax.annotation.Nonnull;
import java.io.File;

public class WinGameVersionFinder extends GameVersionFinder {
    protected GameExeFinder getGameExeFinder() {
        return Context.getService(WinGameExeFinder.class);
    }

    protected GameVersionFinder getTelemetryGameVersionFinder() {
        return new WinTelemetryGameVersionFinder();
    }

    @Nonnull
    @Override
    public GameVersion find() throws NotFoundException {
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

        try {
            return getTelemetryGameVersionFinder().get();
        } catch (NotFoundException ignored) {
        }

        throw new NotFoundException();
    }
}
