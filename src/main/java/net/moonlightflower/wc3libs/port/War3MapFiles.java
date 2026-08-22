package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The file names a Warcraft III map holds at fixed paths.
 * <p>
 * MPQ addresses files by a hash of their name, so an archive can serve a file
 * whose name it cannot produce: the name lives in the optional
 * {@code (listfile)}, and a map protector's first move is to remove or mangle
 * it. Such an archive still plays, because the game asks for these paths by
 * name, but it cannot be enumerated -- and rebuilding it from its own listing
 * therefore drops everything the listing does not mention.
 * <p>
 * These are the paths the game itself asks for, so they can be recovered by
 * asking rather than by listing. That does not recover imported assets, whose
 * names are arbitrary and genuinely unknowable; it does mean injecting a file
 * into an unlisted map no longer discards the map's terrain, objects and
 * script along the way.
 *
 * @see JMpqPort
 */
public final class War3MapFiles {
    /**
     * Paths a Warcraft III map is expected to hold, spelled as the game
     * addresses them. The list is deliberately literal rather than gathered
     * from each format class's {@code GAME_PATH}: it must stay usable without
     * initialising two dozen unrelated classes, and these names are fixed by
     * the game rather than by this library.
     */
    private final static List<String> PATHS = List.of(
        // Map metadata and scripts
        "war3map.w3i",
        "war3map.wts",
        "war3map.j",
        "war3map.lua",
        "war3map.wct",
        "war3map.wtg",
        "war3map.imp",
        "war3mapExtra.txt",
        "war3mapMisc.txt",
        "war3mapSkin.txt",
        "war3mapMap.blp",
        "war3mapMap.tga",
        "war3mapMap.b00",
        "war3mapPreview.blp",
        "war3mapPreview.tga",
        "war3map.shd",
        "war3map.mmp",
        "war3map.wpm",
        "war3map.w3e",
        "war3map.w3c",
        "war3map.w3r",
        "war3map.w3s",
        "war3map.doo",
        "war3mapUnits.doo",
        // Object modifications
        "war3map.w3a",
        "war3map.w3b",
        "war3map.w3d",
        "war3map.w3h",
        "war3map.w3q",
        "war3map.w3t",
        "war3map.w3u",
        // Overridden game data a map may carry
        "conversation.json",
        "Scripts\\war3map.j",
        "Scripts\\war3map.lua",
        "Units\\MiscData.txt",
        "Units\\MiscGame.txt"
    );

    /**
     * @return the standard map file paths, in a stable order.
     */
    @Nonnull
    public static List<String> paths() {
        return PATHS;
    }

    /**
     * @return the standard map file paths as {@link File}s, for the parts of
     *         this library that address archive entries that way.
     */
    @Nonnull
    public static List<File> files() {
        return PATHS.stream().map(File::new).collect(Collectors.toList());
    }

    private War3MapFiles() {
    }
}
