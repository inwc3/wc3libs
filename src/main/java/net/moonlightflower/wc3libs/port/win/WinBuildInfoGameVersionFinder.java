package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.*;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

/**
 * Reads the game version from the Battle.net {@code .build.info} file at the install root.
 * <p>
 * {@code .build.info} is a pipe-delimited text file whose first row names each column as {@code Name!TYPE:SIZE}
 * (e.g. {@code Version!STRING:0}); subsequent rows are records. The active record's {@code Version} column holds
 * the client build (e.g. {@code 1.36.0.20257}). This is the most reliable source for 1.30+/Reforged installs and,
 * unlike executable parsing, is unaffected by the install-root launcher stub. It is the Windows analogue of the
 * macOS {@code Info.plist} version parser.
 */
public class WinBuildInfoGameVersionFinder extends GameVersionFinder {
    public static final String BUILD_INFO_FILE_NAME = ".build.info";

    protected GameDirFinder getGameDirFinder() {
        return Context.getService(WinGameDirFinder.class);
    }

    @Nonnull
    @Override
    public GameVersion find() throws NotFoundException {
        File gameDir = getGameDirFinder().get();
        File buildInfo = new File(gameDir, BUILD_INFO_FILE_NAME);
        if (!buildInfo.isFile()) {
            throw new NotFoundException(BUILD_INFO_FILE_NAME + " not found in " + gameDir.getAbsolutePath());
        }
        try {
            String version = parseVersion(Files.readAllLines(buildInfo.toPath(), StandardCharsets.UTF_8));
            if (version == null || version.isBlank()) {
                throw new NotFoundException("no Version column value in " + buildInfo.getAbsolutePath());
            }
            return new GameVersion(version);
        } catch (IOException e) {
            throw new NotFoundException(e);
        }
    }

    /**
     * Parses the {@code Version} column of the active record from {@code .build.info} lines.
     * <p>
     * Columns are matched by name from the header row (not by position), so the parser tolerates column reordering
     * across Battle.net versions. The chosen record is the one whose {@code Active} column equals {@code 1},
     * falling back to the first data row when no {@code Active} column is present.
     *
     * @return the version string, or {@code null} if the file has no usable {@code Version} column/value.
     */
    static String parseVersion(List<String> lines) {
        if (lines == null || lines.size() < 2) {
            return null;
        }
        String[] header = lines.get(0).split("\\|", -1);
        int versionCol = -1;
        int activeCol = -1;
        for (int i = 0; i < header.length; i++) {
            String name = columnName(header[i]);
            if (name.equalsIgnoreCase("Version")) {
                versionCol = i;
            } else if (name.equalsIgnoreCase("Active")) {
                activeCol = i;
            }
        }
        if (versionCol < 0) {
            return null;
        }
        String firstValue = null;
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                continue;
            }
            String[] cols = line.split("\\|", -1);
            if (versionCol >= cols.length) {
                continue;
            }
            String value = cols[versionCol].trim();
            if (firstValue == null) {
                firstValue = value;
            }
            if (activeCol >= 0 && activeCol < cols.length && cols[activeCol].trim().equals("1")) {
                return value;
            }
        }
        return firstValue;
    }

    /** Strips the {@code !TYPE:SIZE} suffix from a {@code .build.info} header cell, leaving the column name. */
    private static String columnName(String headerCell) {
        String name = headerCell.trim();
        int bang = name.indexOf('!');
        if (bang >= 0) {
            name = name.substring(0, bang);
        }
        return name.trim();
    }
}
