package net.moonlightflower.wc3libs.port.win.registry;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;
import net.moonlightflower.wc3libs.port.GameExeFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class WinRegistryGameExeFinder implements GameExeFinder {
    public WinRegistryGameExeFinder() {
    }

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        try {
            String fileS = getRegistryHandler().get("HKCU\\Software\\Blizzard Entertainment\\Warcraft III", "Program");

            if (fileS == null) throw new NotFoundException();

            File file = new File(fileS);

            if (file.exists()) {
                return file;
            }
        } catch (IOException ignored) {
        }

        throw new NotFoundException();
    }

    protected WinRegistryHandler getRegistryHandler() {
        return new WinRegistryHandler();
    }
}
