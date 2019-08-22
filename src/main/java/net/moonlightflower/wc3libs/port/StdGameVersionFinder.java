package net.moonlightflower.wc3libs.port;

import dorkbox.peParser.PE;
import net.moonlightflower.wc3libs.bin.GameExe;
import net.moonlightflower.wc3libs.port.mac.MacGameExeFinder;
import net.moonlightflower.wc3libs.port.mac.MacGameVersionFinder;
import net.moonlightflower.wc3libs.port.win.WinGameVersionFinder;

import javax.annotation.Nonnull;
import java.io.File;

public class StdGameVersionFinder extends GameVersionFinder {
    public StdGameVersionFinder() {
    }

    protected GameVersionFinder getMacGameVersionFinder() {
        return new MacGameVersionFinder();
    }

    protected GameVersionFinder getWinGameVersionFinder() {
        return new WinGameVersionFinder();
    }

    @Nonnull
    public GameVersion find() throws NotFoundException {
        if (Orient.isMacSystem()) {
            try {
                GameVersionFinder macGameVersionFinder = getMacGameVersionFinder();

                return macGameVersionFinder.get();
            } catch (NotFoundException e) {
                throw new NotFoundException(e);
            }
        } else if (Orient.isWindowsSystem()) {
            try {
                GameVersionFinder winGameVersionFinder = getWinGameVersionFinder();

                return winGameVersionFinder.get();
            } catch (NotFoundException e) {
                throw new NotFoundException(e);
            }
        } else {
            throw new NotFoundException(new Exception("system not supported: " + Orient.getSystem()));
        }
    }
}
