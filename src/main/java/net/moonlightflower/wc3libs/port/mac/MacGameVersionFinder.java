package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.GameVersionFinder;
import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;

public class MacGameVersionFinder extends GameVersionFinder {
    protected GameDirFinder getGameDirFinder() {
        return Context.getService(MacGameDirFinder.class);
    }

    @Nonnull
    @Override
    public GameVersion find() throws NotFoundException {
        try {
            File gameDir = getGameDirFinder().get();
            File infoPlist = MacGameDirFinder.findInfoPlist(gameDir);
            GameVersion gameVersion = PListGameVersionParser.get(infoPlist);

            return gameVersion;
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }
}
