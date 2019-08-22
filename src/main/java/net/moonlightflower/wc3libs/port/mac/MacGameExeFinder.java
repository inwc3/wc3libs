package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameExeFinder;
import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;

public class MacGameExeFinder extends GameExeFinder {
    public static File X32_FILE = new File("/Applications/Warcraft III/Warcraft III.app/Contents/MacOS/Warcraft III");
    public static File X64_FILE = new File("/Applications/Warcraft III/x86_64/Warcraft III.app/Contents/MacOS/Warcraft III");

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        if (X64_FILE.exists()) {
            return X64_FILE;
        } else if (X32_FILE.exists()) {
            return X32_FILE;
        }

        throw new NotFoundException(new Exception("tried " + X64_FILE + " and " + X32_FILE));
    }
}
