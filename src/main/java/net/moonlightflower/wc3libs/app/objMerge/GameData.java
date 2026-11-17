package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * A read-only view of game files, addressed by their in-game path such as
 * {@code Units\UnitData.slk}.
 * <p>
 * Lookups ignore case and treat {@code /} and {@code \} alike, the way the
 * game resolves them. A path the view does not hold reads as {@code null}
 * rather than failing, because "the map does not override this" is the
 * ordinary case when views are layered.
 */
public interface GameData {
    /** @return the file's bytes, or {@code null} if this view does not hold it */
    @Nullable
    byte[] read(@Nonnull String path) throws IOException;

    @Nullable
    default byte[] read(@Nonnull File path) throws IOException {
        return read(path.toString());
    }

    default boolean contains(@Nonnull String path) throws IOException {
        return read(path) != null;
    }

    /** The key paths are compared by: lower case, forward slashes, no leading slash. */
    @Nonnull
    static String normalize(@Nonnull String path) {
        String ret = path.replace('\\', '/').toLowerCase(Locale.ROOT);

        while (ret.startsWith("/")) ret = ret.substring(1);

        return ret;
    }
}
