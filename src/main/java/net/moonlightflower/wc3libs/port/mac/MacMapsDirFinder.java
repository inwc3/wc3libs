package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.MapsDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;

public class MacMapsDirFinder implements MapsDirFinder {
    public static File LOCAL_MAPS_DIR = new File("~/Library/Application Support/Blizzard/Warcraft III/Maps/");
    public static File GAME_SUB_DIR = new File("Maps");

    protected GameDirFinder getMacGameDirFinder() {
        return new MacGameDirFinder();
    }

    public File get(@Nonnull GameVersion gameVersion) throws NotFoundException {
        if (gameVersion.compareTo(GameVersion.VERSION_1_29) > 0) {
            if (!(LOCAL_MAPS_DIR.exists())) {
                throw new NotFoundException(new Exception(LOCAL_MAPS_DIR + " does not exist"));
            }

            return LOCAL_MAPS_DIR;
        }

        GameDirFinder macGameDirFinder = getMacGameDirFinder();

        try {
            File gameDir = macGameDirFinder.get();

            File mapsDir = new File(gameDir, GAME_SUB_DIR.toString());

            if (!(mapsDir.exists())) {
                throw new NotFoundException(new Exception(mapsDir + " does not exist"));
            }

            return mapsDir;
        } catch (NotFoundException e) {
        }

        throw new NotFoundException();
    }

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        return null;
    }
}
