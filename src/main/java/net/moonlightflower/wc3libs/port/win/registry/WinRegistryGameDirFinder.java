package net.moonlightflower.wc3libs.port.win.registry;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;
import net.moonlightflower.wc3libs.port.GameDirFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class WinRegistryGameDirFinder extends GameDirFinder {
    public WinRegistryGameDirFinder() {
    }

    private File getRegEntry(@Nonnull WinRegistryHandler.Entry entry) {
        try {
            String fileS = getRegistryHandler().get(entry);

            if (fileS == null) throw new NotFoundException();

            File file = new File(fileS);

            if (file.exists()) {
                return file;
            }
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
    public File find() throws NotFoundException {
        List<WinRegistryHandler.Wc3Entry> entries = Arrays.asList(
                WinRegistryHandler.Wc3Entry.INSTALL_PATH,
                WinRegistryHandler.Wc3Entry.INSTALL_PATH_X,
                WinRegistryHandler.Wc3LocalMachineEntry.INSTALL_PATH,
                WinRegistryHandler.Wc3LocalMachineEntry.INSTALL_PATH_X,
                WinRegistryHandler.Wc3LocalMachineEntry.WAR3_INSTALL_PATH,
                WinRegistryHandler.Wc3ReforgedEntry.INSTALL_PATH
        );

        for (WinRegistryHandler.Wc3Entry entry : entries) {
            File dir = getRegEntry(entry);

            if (dir != null && dir.exists()) return dir;
        }

        throw new NotFoundException();
    }
}
