package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/** Game files held in memory, such as the ones a map overrides. */
public class MemoryGameData implements GameData {
    private final Map<String, byte[]> _files = new LinkedHashMap<>();

    public MemoryGameData() {
    }

    public MemoryGameData(@Nonnull Map<String, byte[]> files) {
        files.forEach(this::put);
    }

    public void put(@Nonnull String path, @Nonnull byte[] bytes) {
        _files.put(GameData.normalize(path), bytes);
    }

    /** @return the normalized paths held */
    @Nonnull
    public Set<String> getPaths() {
        return Collections.unmodifiableSet(_files.keySet());
    }

    @Nullable
    @Override
    public byte[] read(@Nonnull String path) {
        return _files.get(GameData.normalize(path));
    }
}
