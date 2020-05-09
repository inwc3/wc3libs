package net.moonlightflower.wc3libs.port.win.registry;

import net.moonlightflower.wc3libs.misc.WinRegistryHandler;
import net.moonlightflower.wc3libs.misc.WinRegistryHandler.Wc3Entry;
import net.moonlightflower.wc3libs.port.GameExeFinder;
import net.moonlightflower.wc3libs.port.NotFoundException;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

public class WinRegistryGameExeFinder extends GameExeFinder {
    public WinRegistryGameExeFinder() {
    }

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        try {
            String fileS = getRegistryHandler().get(Wc3Entry.PROGRAM);

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
