package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.*;

import javax.annotation.Nonnull;
import java.io.File;

public class MacMapsDirFinder extends MapsDirFinder {
    public static File LOCAL_MAPS_DIR = new File("~/Library/Application Support/Blizzard/Warcraft III/Maps/");
    public static File GAME_SUB_DIR = new File("Maps");

    protected GameDirFinder getMacGameDirFinder() {
        return new MacGameDirFinder();
    }

    protected GameVersionFinder getMacGameVersionFinder() {
        return new MacGameVersionFinder();
    }

    public File find(@Nonnull GameVersion gameVersion) throws NotFoundException {
        if (gameVersion.compareTo(GameVersion.VERSION_1_29) > 0) {
            if (!(LOCAL_MAPS_DIR.exists())) {
                throw new NotFoundException(new Exception(LOCAL_MAPS_DIR + " does not exist"));
            }

            return LOCAL_MAPS_DIR;
        }

        GameDirFinder macGameDirFinder = getMacGameDirFinder();

        try {
            File gameDir = macGameDirFinder.find();

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
    public File find() throws NotFoundException {
        GameVersionFinder macGameVersionFinder = getMacGameVersionFinder();

        try {
            GameVersion gameVersion = macGameVersionFinder.get();

            return find(gameVersion);
        } catch (AlreadyTriedException e) {
            throw new NotFoundException(e);
        }
    }
}
