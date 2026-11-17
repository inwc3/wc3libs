package net.moonlightflower.wc3libs.app.objMerge;

import net.moonlightflower.wc3libs.bin.app.W3I;

import javax.annotation.Nonnull;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * The object data of one Warcraft III build, shipped with this library so
 * that merging needs no game install.
 * <p>
 * Reforged layers its data: the base mod, a balance mod chosen by the map's
 * game data set ({@code custom_v1} for custom maps, {@code melee_v0} for melee
 * ones) and a locale mod holding the display strings. The snapshot keeps the
 * layers apart and {@link #forDataSet} stacks them the way the game does.
 * Only the enUS locale is bundled, so strings a custom object inherits from
 * its base object are baked in English.
 * <p>
 * The snapshot is regenerated from an install with
 * {@code tools/gamedata/extract-gamedata.mjs}.
 */
public final class BundledGameData {
    public static final String RESOURCE = "net/moonlightflower/wc3libs/app/objMerge/gamedata.zip";

    public static final String LAYER_BASE = "base";
    public static final String LAYER_CUSTOM = "custom_v1";
    public static final String LAYER_MELEE = "melee_v0";
    public static final String LAYER_LOCALE = "enus";

    private static SoftReference<Snapshot> _cache = new SoftReference<>(null);

    private BundledGameData() {
    }

    private static final class Snapshot {
        private final Map<String, MemoryGameData> _layers = new LinkedHashMap<>();
        private final Properties _manifest = new Properties();
        private final Map<String, GameData> _stacks = new LinkedHashMap<>();
    }

    @Nonnull
    private static synchronized Snapshot snapshot() throws IOException {
        Snapshot snapshot = _cache.get();

        if (snapshot != null) return snapshot;

        snapshot = new Snapshot();

        try (InputStream resource = BundledGameData.class.getClassLoader().getResourceAsStream(RESOURCE)) {
            if (resource == null) throw new IOException("missing resource " + RESOURCE);

            try (ZipInputStream zip = new ZipInputStream(resource)) {
                ZipEntry entry;

                while ((entry = zip.getNextEntry()) != null) {
                    if (entry.isDirectory()) continue;

                    String name = entry.getName();
                    byte[] bytes = zip.readAllBytes();

                    if (name.equals("manifest.properties")) {
                        snapshot._manifest.load(new ByteArrayInputStream(bytes));

                        continue;
                    }

                    int slash = name.indexOf('/');

                    if (slash < 0) continue;

                    snapshot._layers.computeIfAbsent(name.substring(0, slash), k -> new MemoryGameData())
                        .put(name.substring(slash + 1), bytes);
                }
            }
        }

        _cache = new SoftReference<>(snapshot);

        return snapshot;
    }

    /** @return the game build the snapshot was taken from, such as {@code 3.0.1.24371} */
    @Nonnull
    public static String getVersion() throws IOException {
        return snapshot()._manifest.getProperty("version", "unknown");
    }

    /** @return one layer of the snapshot, unstacked */
    @Nonnull
    public static GameData layer(@Nonnull String name) throws IOException {
        MemoryGameData layer = snapshot()._layers.get(name);

        if (layer == null) throw new IOException("snapshot has no layer " + name);

        return layer;
    }

    /** The data a custom (non-melee) map with the default data set plays with. */
    @Nonnull
    public static GameData custom() throws IOException {
        return stack(LAYER_CUSTOM);
    }

    /** The data a melee map with the default data set plays with. */
    @Nonnull
    public static GameData melee() throws IOException {
        return stack(LAYER_MELEE);
    }

    /**
     * The data a map plays with, as chosen by its {@code war3map.w3i}: an
     * explicit data set wins, and the default one follows the melee flag.
     */
    @Nonnull
    public static GameData forDataSet(@Nonnull W3I.GameDataSet dataSet, boolean meleeMap) throws IOException {
        String balance = W3I.getGameDataSetPath(dataSet, meleeMap).toString().toLowerCase(java.util.Locale.ROOT);

        return stack(balance.equals(LAYER_MELEE) ? LAYER_MELEE : LAYER_CUSTOM);
    }

    /** The layers combined as the game combines them; the result is kept with the snapshot, as merging takes time. */
    @Nonnull
    private static GameData stack(@Nonnull String balance) throws IOException {
        Snapshot snapshot = snapshot();

        synchronized (snapshot._stacks) {
            GameData stack = snapshot._stacks.get(balance);

            if (stack == null) {
                stack = new MergedGameData(Arrays.asList(layer(balance), layer(LAYER_LOCALE), layer(LAYER_BASE)));
                snapshot._stacks.put(balance, stack);
            }

            return stack;
        }
    }
}
