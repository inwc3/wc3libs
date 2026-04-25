package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.GameExeFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MacGameExeFinder extends GameExeFinder {
    public static File X32_FILE = new File("/Applications/Warcraft III/Warcraft III.app/Contents/MacOS/Warcraft III");
    public static File X64_FILE = new File("/Applications/Warcraft III/_retail_/x86_64/Warcraft III.app/Contents/MacOS/Warcraft III");

    public static final Path GAME_EXE_PATH = Paths.get("Contents", "MacOS", "Warcraft III");

    private static final List<Path> GAME_EXE_RELATIVE_PATHS = Arrays.asList(
        MacGameDirFinder.RETAIL_DIR.resolve(MacGameDirFinder.X64_DIR).resolve(MacGameDirFinder.APP_DIR).resolve(GAME_EXE_PATH),
        MacGameDirFinder.RETAIL_DIR.resolve(MacGameDirFinder.X86_DIR).resolve(MacGameDirFinder.APP_DIR).resolve(GAME_EXE_PATH),
        MacGameDirFinder.X64_DIR.resolve(MacGameDirFinder.APP_DIR).resolve(GAME_EXE_PATH),
        MacGameDirFinder.X86_DIR.resolve(MacGameDirFinder.APP_DIR).resolve(GAME_EXE_PATH),
        MacGameDirFinder.APP_DIR.resolve(GAME_EXE_PATH),
        GAME_EXE_PATH
    );

    protected GameDirFinder getGameDirFinder() {
        return Context.getService(MacGameDirFinder.class);
    }

    @Nonnull
    public static File fromDir(@Nonnull File dir) throws NotFoundException {
        if (dir.isFile() && dir.getName().equals("Warcraft III")) {
            return dir;
        }

        for (Path relativePath : GAME_EXE_RELATIVE_PATHS) {
            File file = dir.toPath().resolve(relativePath).toFile();

            if (file.exists()) {
                return file;
            }
        }

        throw new NotFoundException("tried all known mac executable sub-paths in " + dir.getAbsolutePath());
    }

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        try {
            File gameDir = getGameDirFinder().get();

            return fromDir(gameDir);
        } catch (NotFoundException ignored) {
        }

        throw new NotFoundException(new Exception("tried " + X64_FILE + " and " + X32_FILE));
    }
}
