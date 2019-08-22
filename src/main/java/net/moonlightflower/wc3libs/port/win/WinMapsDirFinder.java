package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;
import net.moonlightflower.wc3libs.port.*;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameDirFinder;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class WinMapsDirFinder extends MapsDirFinder {
    public static File DEFAULT_DOCUMENTS_DIR = new File("%USERPROFILE%/Documents");
    public static File LOCAL_SUB_DIR = new File("Warcraft III/Maps");
    public static File GAME_SUB_DIR = new File("Maps");

    protected GameDirFinder getWinGameDirFinder() {
        return new WinRegistryGameDirFinder();
    }

    protected WinRegistryHandler getWinRegistryHandler() {
        return new WinRegistryHandler();
    }

    protected GameVersionFinder getWinGameVersionFinder() {
        return new WinGameVersionFinder();
    }

    public File find(@Nonnull GameVersion gameVersion) throws NotFoundException {
        if (gameVersion.compareTo(GameVersion.VERSION_1_29) > 0) {
            WinRegistryHandler winRegistryHandler = getWinRegistryHandler();
            File documentsDir = DEFAULT_DOCUMENTS_DIR;

            try {
                documentsDir = new File(winRegistryHandler.get("HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\User Shell Folders", "Personal"));
            } catch (IOException e) {
            }

            File localMapsDir = new File(documentsDir, LOCAL_SUB_DIR.toString());

            if (!(localMapsDir.exists())) {
                throw new NotFoundException(new Exception(localMapsDir + " does not exist"));
            }

            return localMapsDir;
        }

        GameDirFinder winGameDirFinder = getWinGameDirFinder();

        try {
            File gameDir = winGameDirFinder.get();

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
        GameVersionFinder winGameVersionFinder = getWinGameVersionFinder();

        try {
            GameVersion gameVersion = winGameVersionFinder.get();

            return find(gameVersion);
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        }
    }
}
