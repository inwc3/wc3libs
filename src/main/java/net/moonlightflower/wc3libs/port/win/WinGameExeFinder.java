package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.*;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameExeFinder;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class WinGameExeFinder extends GameExeFinder {
    public final static File WAR3_EXE_PATH = new File("war3.exe");
    public final static File WARCRAFT_III_EXE_PATH = new File("Warcraft III.exe");
    public final static File FROZEN_THRONE_EXE_PATH = new File("Frozen Throne.exe");

    public final static File X86_DIR = new File("x86");
    public final static File X64_DIR = new File("x86_64");

    public final static File X86_EXE_PATH_131 = new File(X86_DIR, WARCRAFT_III_EXE_PATH.toString());
    public final static File X64_EXE_PATH_131 = new File(X64_DIR, WARCRAFT_III_EXE_PATH.toString());

    protected GameExeFinder getRegistryGameExeFinder() {
        return new WinRegistryGameExeFinder();
    }

    protected GameDirFinder getGameDirFinder() {
        return Context.getService(WinGameDirFinder.class);
    }

    protected GameVersionFinder getGameVersionFinder() {
        return Context.getService(WinGameVersionFinder.class);
    }

    @Nonnull
    public static File fromDir(
            @Nonnull File dir, @Nonnull GameVersion version, @Nonnull Orient.WinArch arch) throws NotFoundException {
        if (version.compareTo(GameVersion.VERSION_1_31) >= 0) {
            switch (arch) {
                case X86:
                    File inDirPathX86 = new File(dir, X86_EXE_PATH_131.toString());

                    if (inDirPathX86.exists()) {
                        return inDirPathX86;
                    }
                case X64:
                    File inDirPathX64 = new File(dir, X64_EXE_PATH_131.toString());

                    if (inDirPathX64.exists()) {
                        return inDirPathX64;
                    }

                    return inDirPathX64;
            }
        } else if (version.compareTo(GameVersion.VERSION_1_29) >= 0) {
            File inDirPath = new File(dir, WARCRAFT_III_EXE_PATH.toString());

            if (inDirPath.exists()) {
                return inDirPath;
            }
        }

        File inDirPath = new File(dir, WAR3_EXE_PATH.toString());

        if (inDirPath.exists()) {
            return inDirPath;
        }

        throw new NotFoundException();
    }

    @Nonnull
    public static File fromDirIgnoreVersion(@Nonnull File dir, @Nonnull Orient.WinArch arch) throws NotFoundException {
        switch (arch) {
            case X86:
                File inDirPathX86 = new File(dir, X86_EXE_PATH_131.toString());

                if (inDirPathX86.exists()) return inDirPathX86;

                break;
            case X64:
                File inDirPathX64 = new File(dir, X64_EXE_PATH_131.toString());

                if (inDirPathX64.exists()) return inDirPathX64;

                break;
        }

        List<File> relativeSearchPaths = Arrays.asList(WARCRAFT_III_EXE_PATH,
                FROZEN_THRONE_EXE_PATH,
                WAR3_EXE_PATH,
                X86_EXE_PATH_131,
                X64_EXE_PATH_131
        );

        for (File relativeSearchPath : relativeSearchPaths) {
            File inDirPath = new File(dir, relativeSearchPath.toString());

            if (inDirPath.exists()) return inDirPath;
        }

        throw new NotFoundException(new Exception("tried: " + relativeSearchPaths.toString()));
    }

    @Nonnull
    public static File fromDirIgnoreVersion(@Nonnull File dir) throws NotFoundException {
        return fromDirIgnoreVersion(dir, Orient.getWinArch());
    }

    @Nonnull
    private File getGameExeInDir(@Nonnull File dir) throws NotFoundException {
        GameVersionFinder gameVersionFinder = getGameVersionFinder();

        try {
            GameVersion gameVersion = gameVersionFinder.get();

            return fromDir(dir, gameVersion, Orient.getWinArch());
        } catch (NotFoundException ignored) {
            return fromDirIgnoreVersion(dir);
        }
    }

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        GameExeFinder winRegistryGameExeFinder = getRegistryGameExeFinder();

        try {
            return winRegistryGameExeFinder.get();
        } catch (NotFoundException e) {
        }

        GameDirFinder gameDirFinder = getGameDirFinder();

        try {
            File gameDir = gameDirFinder.get();

            return getGameExeInDir(gameDir);
        } catch (NotFoundException e) {
        }

        throw new NotFoundException();
    }
}
