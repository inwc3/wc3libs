package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class RegistryGameExeFinder implements GameExeFinder {
    public RegistryGameExeFinder() {
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
