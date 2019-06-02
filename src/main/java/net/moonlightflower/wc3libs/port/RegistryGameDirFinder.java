package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RegistryGameDirFinder implements GameDirFinder {
    public RegistryGameDirFinder() {
    }

    private File getRegEntry(@Nonnull WinRegistryHandler.Entry entry) {
        try {
            String fileS = getRegistryHandler().get(entry);

            if (fileS == null) throw new NotFoundException();

            return new File(fileS);
        } catch (UnsupportedOperationException | NotFoundException ignored) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    protected WinRegistryHandler getRegistryHandler() {
        return new WinRegistryHandler();
    }

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        List<WinRegistryHandler.Wc3Entry> entries = Arrays.asList(
                WinRegistryHandler.Wc3Entry.INSTALL_PATH,
                WinRegistryHandler.Wc3Entry.INSTALL_PATH_X,
                WinRegistryHandler.Wc3LocalMachineEntry.INSTALL_PATH,
                WinRegistryHandler.Wc3LocalMachineEntry.INSTALL_PATH_X,
                WinRegistryHandler.Wc3LocalMachineEntry.WAR3_INSTALL_PATH
        );

        for (WinRegistryHandler.Wc3Entry entry : entries) {
            File dir = getRegEntry(entry);

            if (dir != null) return dir;
        }

        throw new NotFoundException();
    }
}
