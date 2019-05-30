package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;

public interface GameVersionFinder {
    @Nonnull
    GameVersion get() throws NotFoundException;
}
