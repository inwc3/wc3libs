package net.moonlightflower.wc3libs.port.mac;

import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;

public class MacGameDirFinder implements GameDirFinder {
    public static File DIR = new File("/Applications/Warcraft III");

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        return DIR;
    }
}
