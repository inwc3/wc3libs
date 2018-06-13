package net.moonlightflower.wc3libs.bin;

import dorkbox.peParser.PE;
import net.moonlightflower.wc3libs.misc.Registry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

public class GameExe {
    private final File _file;

    public String getVersion() throws IOException {
        try {
            return PE.getVersion(_file.getAbsolutePath());
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public GameExe(@Nonnull File file) {
        _file = file;
    }

    @Nullable
    public static GameExe fromRegistry() {
        try {
            return new GameExe(new File(Registry.get("HKCU\\Software\\Blizzard Entertainment\\Warcraft III", "Program")));
        } catch (IOException e) {
            return null;
        }
    }

    /*@Nullable
    public static GameExe fromDir(@Nonnull File dir) {

    }*/
}
