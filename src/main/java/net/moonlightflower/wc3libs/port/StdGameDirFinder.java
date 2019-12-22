package net.moonlightflower.wc3libs.port;

import net.moonlightflower.wc3libs.port.mac.MacGameDirFinder;
import net.moonlightflower.wc3libs.port.win.WinGameDirFinder;

import javax.annotation.Nonnull;
import java.io.File;

public class StdGameDirFinder extends GameDirFinder {
    public StdGameDirFinder() {

    }

    protected GameDirFinder getMacGameDirFinder() {
        return new MacGameDirFinder();
    }

    protected GameDirFinder getWinGameDirFinder() {
        return new WinGameDirFinder();
    }

    @Nonnull
    @Override
    public File find() throws NotFoundException {
        String envWar3DirS = System.getenv("war3dir");

        if (envWar3DirS != null) {
            File envWar3Dir = new File(envWar3DirS);

            if (envWar3Dir.exists()) {
                return envWar3Dir;
            }
        }

        if (Orient.isMacSystem()) {
            GameDirFinder macGameDirFinder = getMacGameDirFinder();

            return macGameDirFinder.get();
        } else if (Orient.isWindowsSystem()) {
            GameDirFinder winGameDirFinder = getWinGameDirFinder();

            return winGameDirFinder.get();
        } else {
            throw new NotFoundException(new Exception("system not supported: " + Orient.getSystem()));
        }
    }
}
