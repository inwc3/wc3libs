package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.*;

import javax.annotation.Nonnull;
import java.io.File;

public class WinGameVersionFinder extends GameVersionFinder {
    protected GameExeFinder getGameExeFinder() {
        return Context.getService(WinGameExeFinder.class);
    }

    protected GameVersionFinder getBuildInfoGameVersionFinder() {
        return new WinBuildInfoGameVersionFinder();
    }

    protected GameVersionFinder getTelemetryGameVersionFinder() {
        return new WinTelemetryGameVersionFinder();
    }

    @Nonnull
    @Override
    public GameVersion find() throws NotFoundException {
        // 1. The .build.info text file at the install root: most reliable for 1.30+/Reforged and unaffected by the
        //    install-root launcher stub. No executable parsing required.
        try {
            return getBuildInfoGameVersionFinder().get();
        } catch (NotFoundException ignored) {
        }

        // 2. The VS_VERSIONINFO resource of the resolved game executable (covers classic/legacy clients).
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

        // 3. Last resort: the version string embedded in the executable's telemetry block.
        try {
            return getTelemetryGameVersionFinder().get();
        } catch (NotFoundException ignored) {
        }

        throw new NotFoundException();
    }
}
