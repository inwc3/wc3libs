package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.port.mac.MacGameExeFinder;
import net.moonlightflower.wc3libs.port.win.WinGameExeFinder;

import javax.annotation.Nonnull;
import java.io.File;

public class StdGameExeFinder extends GameExeFinder {
    public StdGameExeFinder() {
    }

    protected GameExeFinder getMacGameExeFinder() {
        return Context.getService(MacGameExeFinder.class);
    }

    protected GameExeFinder getWinGameExeFinder() {
        return Context.getService(WinGameExeFinder.class);
    }

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        if (Orient.isMacSystem()) {
            GameExeFinder macGameExeFinder = getMacGameExeFinder();

            return macGameExeFinder.get();
        } else if (Orient.isWindowsSystem()) {
            GameExeFinder winGameExeFinder = getWinGameExeFinder();

            return winGameExeFinder.get();
        } else {
            throw new UnsupportedPlatformException("system not supported: " + Orient.getSystem());
        }
    }
}
