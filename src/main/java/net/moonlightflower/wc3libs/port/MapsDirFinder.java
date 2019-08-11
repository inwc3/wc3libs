package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;
import java.io.File;

public interface MapsDirFinder {
    @Nonnull
    File get() throws NotFoundException;
}
