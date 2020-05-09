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
    }
}
