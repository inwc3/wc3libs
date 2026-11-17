package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Stacks the game's own data mods the way the game reads them: base data, a
 * balance mod and a locale mod. Unlike a map's files, which replace the
 * game's file of the same path ({@link LayeredGameData}), a mod ships only
 * what it changes -- a balance mod's {@code AbilityData.slk} holds about half
 * the rows and columns of the base one -- so a table is combined cell by cell
 * ({@link SlkTable#overlay}) and a profile file key by key
 * ({@link ProfileFile#overlay}). Other files are taken from the highest layer
 * holding them.
 */
public class MergedGameData implements GameData {
    private final List<GameData> _layers;
    private final Map<String, byte[]> _merged = new HashMap<>();
    private static final byte[] ABSENT = new byte[0];

    /** @param layers highest priority first */
    public MergedGameData(@Nonnull List<? extends GameData> layers) {
        _layers = Collections.unmodifiableList(new ArrayList<>(layers));
    }

    /** @param layers highest priority first */
    public MergedGameData(@Nonnull GameData... layers) {
        this(Arrays.asList(layers));
    }

    @Nonnull
    public List<GameData> getLayers() {
        return _layers;
    }

    @Nullable
    @Override
    public synchronized byte[] read(@Nonnull String path) throws IOException {
        String key = GameData.normalize(path);
        byte[] bytes = _merged.get(key);

        if (bytes == null) {
            bytes = merge(key);
            _merged.put(key, bytes == null ? ABSENT : bytes);
        }

        return bytes == ABSENT ? null : bytes;
    }

    @Nullable
    private byte[] merge(@Nonnull String path) throws IOException {
        // Lowest priority first, the order they are laid over each other.
        List<byte[]> found = new ArrayList<>();

        for (int i = _layers.size() - 1; i >= 0; i--) {
            byte[] bytes = _layers.get(i).read(path);

            if (bytes != null) found.add(bytes);
        }

        if (found.isEmpty()) return null;
        if (found.size() == 1) return found.get(0);

        String lower = path.toLowerCase(Locale.ROOT);

        if (lower.endsWith(".slk")) {
            SlkTable table = SlkTable.parse(found.get(0));

            for (int i = 1; i < found.size(); i++) table.overlay(SlkTable.parse(found.get(i)));

            return table.toBytes();
        }

        if (lower.endsWith(".txt")) {
            ProfileFile file = ProfileFile.parse(found.get(0));

            for (int i = 1; i < found.size(); i++) file.overlay(ProfileFile.parse(found.get(i)));

            return file.toBytes();
        }

        return found.get(found.size() - 1);
    }
}
