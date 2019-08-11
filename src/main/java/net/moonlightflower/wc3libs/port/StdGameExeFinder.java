package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.port.mac.MacGameExeFinder;
import net.moonlightflower.wc3libs.port.win.WinGameExeFinder;
import net.moonlightflower.wc3libs.port.win.registry.WinRegistryGameExeFinder;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class StdGameExeFinder implements GameExeFinder {
    public StdGameExeFinder() {
    }

    protected GameExeFinder getMacGameExeFinder() {
        return Context.getService(MacGameExeFinder.class);
    }

    protected GameExeFinder getWinGameExeFinder() {
        return Context.getService(WinGameExeFinder.class);
    }

    private boolean _entered = false;

    @Nonnull
    @Override
    public File get() throws NotFoundException {
        if (_entered) throw new NotFoundException(new Exception("already tried"));

        _entered = true;

        try {
            if (Orient.isMacSystem()) {
                GameExeFinder macGameExeFinder = getMacGameExeFinder();

                try {
                    return macGameExeFinder.get();
                } catch (NotFoundException e) {
                    throw e;
                }
            } else if (Orient.isWindowsSystem()) {
                GameExeFinder winGameExeFinder = getWinGameExeFinder();

                try {
                    return winGameExeFinder.get();
                } catch (NotFoundException e) {
                    throw e;
                }
            } else {
                throw new NotFoundException(new Exception("system not supported: " + Orient.getSystem()));
            }
        } finally {
            _entered = false;
        }
    }
}
