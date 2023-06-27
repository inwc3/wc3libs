package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.*;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameExeFinder;

import javax.annotation.Nonnull;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class WinGameExeFinder extends GameExeFinder {
    public final static Path WAR3_EXE_PATH = Paths.get("war3.exe");
    public final static Path WARCRAFT_III_EXE_PATH = Paths.get("Warcraft III.exe");
    public final static Path FROZEN_THRONE_EXE_PATH = Paths.get("Frozen Throne.exe");

    public final static Path X86_DIR = Paths.get("x86");
    public final static Path X64_DIR = Paths.get("x86_64");

    public final static Path RETAIL_DIR = Paths.get("_retail_");

    public final static Path RETAIL_X86_DIR = RETAIL_DIR.resolve("x86");
    public final static Path RETAIL_X64_DIR = RETAIL_DIR.resolve("x86_64");

    public final static Path X86_EXE_PATH_131 = X86_DIR.resolve(WARCRAFT_III_EXE_PATH);
    public final static Path X64_EXE_PATH_131 = X64_DIR.resolve(WARCRAFT_III_EXE_PATH);

    public final static Path X86_EXE_PATH_132 = RETAIL_X86_DIR.resolve(WARCRAFT_III_EXE_PATH);
    public final static Path X64_EXE_PATH_132 = RETAIL_X64_DIR.resolve(WARCRAFT_III_EXE_PATH);

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

        return Stream.of(WARCRAFT_III_EXE_PATH,
                FROZEN_THRONE_EXE_PATH,
                WAR3_EXE_PATH,
                X86_EXE_PATH_131,
                X64_EXE_PATH_131,
                X86_EXE_PATH_132,
                X64_EXE_PATH_132)
            .map(relativePath -> dir.toPath().resolve(relativePath))
            .filter(Files::exists)
            .findFirst()
            .orElseThrow(() -> new NotFoundException("tried all known wc3 sub-paths in " + dir.getAbsolutePath()))
            .toFile();
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
