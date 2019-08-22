package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;

public class WinDefaultGameDirFinder extends GameDirFinder {
    public final static File WIN_DEFAULT_PATH = new File("C:/Program Files/Warcraft III");
    public final static File WIN_DEFAULT_X86_PATH = new File("C:/Program Files (86)/Warcraft III");

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        if (WIN_DEFAULT_PATH.exists()) return WIN_DEFAULT_PATH;
        if (WIN_DEFAULT_X86_PATH.exists()) return WIN_DEFAULT_X86_PATH;

        throw new NotFoundException();
    }
}
