package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.*;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameExeFinder;

import javax.annotation.Nonnull;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        List<Path> candidates = new ArrayList<>();
        if (version.compareTo(GameVersion.VERSION_1_31) >= 0) {
            // 1.31+: split per-architecture binaries. 1.32+ (Reforged) moves them under _retail_. Prefer the
            // current architecture, then the 1.32 layout over the 1.31 layout, with the root exe as a last resort.
            if (arch == Orient.WinArch.X86) {
                candidates.add(X86_EXE_PATH_132);
                candidates.add(X86_EXE_PATH_131);
                candidates.add(X64_EXE_PATH_132);
                candidates.add(X64_EXE_PATH_131);
            } else {
                candidates.add(X64_EXE_PATH_132);
                candidates.add(X64_EXE_PATH_131);
                candidates.add(X86_EXE_PATH_132);
                candidates.add(X86_EXE_PATH_131);
            }
            candidates.add(WARCRAFT_III_EXE_PATH);
        } else if (version.compareTo(GameVersion.VERSION_1_29) >= 0) {
            // 1.29 / 1.30: a single "Warcraft III.exe" at the install root.
            candidates.add(WARCRAFT_III_EXE_PATH);
        }
        // Classic / legacy clients.
        candidates.add(WAR3_EXE_PATH);
        candidates.add(FROZEN_THRONE_EXE_PATH);

        return candidates.stream()
            .map(relativePath -> dir.toPath().resolve(relativePath))
            .filter(Files::exists)
            .findFirst()
            .orElseThrow(() -> new NotFoundException(
                "no Warcraft III executable for version " + version + " in " + dir.getAbsolutePath()))
            .toFile();
    }

    @Nonnull
    public static File fromDirIgnoreVersion(@Nonnull File dir, @Nonnull Orient.WinArch arch) throws NotFoundException {
        // Prefer the real, launchable game binary over the install-root "Warcraft III.exe": on Reforged that root
        // file is only a launcher stub which has no clean version resource and cannot be started directly
        // (CreateProcess error 216). Within the real binaries, prefer the current architecture, then 1.32 (_retail_)
        // over the 1.31 layout. Classic/legacy install-root executables come last as a fallback.
        List<Path> candidates = new ArrayList<>();
        if (arch == Orient.WinArch.X86) {
            candidates.add(X86_EXE_PATH_132);
            candidates.add(X86_EXE_PATH_131);
            candidates.add(X64_EXE_PATH_132);
            candidates.add(X64_EXE_PATH_131);
        } else {
            candidates.add(X64_EXE_PATH_132);
            candidates.add(X64_EXE_PATH_131);
            candidates.add(X86_EXE_PATH_132);
            candidates.add(X86_EXE_PATH_131);
        }
        candidates.add(WARCRAFT_III_EXE_PATH);
        candidates.add(FROZEN_THRONE_EXE_PATH);
        candidates.add(WAR3_EXE_PATH);

        return candidates.stream()
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
