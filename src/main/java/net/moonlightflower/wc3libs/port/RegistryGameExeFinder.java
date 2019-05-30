package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.misc.Registry;

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
            String fileS = Registry.get("HKCU\\Software\\Blizzard Entertainment\\Warcraft III", "Program");

            if (fileS == null) throw new NotFoundException();

            return new File(fileS);
        } catch (IOException ignored) {
        }

        throw new NotFoundException();
    }
}
