package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Stacks views so that the first one holding a path wins, the way a map's
 * files shadow the game's and the game's balance and locale mods shadow its
 * base data. A file is replaced as a whole, never merged with the one beneath.
 */
public class LayeredGameData implements GameData {
    private final List<GameData> _layers;

    /** @param layers highest priority first */
    public LayeredGameData(@Nonnull List<? extends GameData> layers) {
        _layers = Collections.unmodifiableList(new ArrayList<>(layers));
    }

    /** @param layers highest priority first */
    public LayeredGameData(@Nonnull GameData... layers) {
        this(Arrays.asList(layers));
    }

    @Nonnull
    public List<GameData> getLayers() {
        return _layers;
    }

    @Nullable
    @Override
    public byte[] read(@Nonnull String path) throws IOException {
        for (GameData layer : _layers) {
            byte[] bytes = layer.read(path);

            if (bytes != null) return bytes;
        }

        return null;
    }
}
