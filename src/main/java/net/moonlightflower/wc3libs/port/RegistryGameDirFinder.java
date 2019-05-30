package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.misc.Registry;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RegistryGameDirFinder implements GameDirFinder {
    public RegistryGameDirFinder() {
    }

    private static File getRegEntry(@Nonnull Registry.Entry entry) {
        try {
            return new File(Registry.get(entry));
        } catch (UnsupportedOperationException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        List<Registry.Wc3Entry> entries = Arrays.asList(
                Registry.Wc3Entry.INSTALL_PATH,
                Registry.Wc3Entry.INSTALL_PATH_X,
                Registry.Wc3LocalMachineEntry.INSTALL_PATH,
                Registry.Wc3LocalMachineEntry.INSTALL_PATH_X,
                Registry.Wc3LocalMachineEntry.WAR3_INSTALL_PATH
        );

        for (Registry.Wc3Entry entry : entries) {
            File dir = getRegEntry(entry);

            if (dir != null) return dir;
        }

        throw new NotFoundException();
    }
}
