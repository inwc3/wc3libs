package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

public interface GameDirFinder {
    @Nonnull
    File get() throws NotFoundException;
}
