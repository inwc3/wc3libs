package net.moonlightflower.wc3libs.app.objMerge;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Game files extracted to a directory, laid out by their in-game paths
 * ({@code <root>/Units/UnitData.slk}). Names are matched without regard to
 * case, so an extraction with lower-cased names works as well as one that
 * kept the game's spelling.
 */
public class DirGameData implements GameData {
    private final Map<String, Path> _files = new LinkedHashMap<>();

    public DirGameData(@Nonnull Path root) throws IOException {
        if (!Files.isDirectory(root)) throw new IOException("not a directory: " + root);

        try (Stream<Path> paths = Files.walk(root)) {
            paths.filter(Files::isRegularFile).forEach(path ->
                _files.putIfAbsent(GameData.normalize(root.relativize(path).toString()), path));
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    @Nullable
    @Override
    public byte[] read(@Nonnull String path) throws IOException {
        Path file = _files.get(GameData.normalize(path));

        return file == null ? null : Files.readAllBytes(file);
    }
}
