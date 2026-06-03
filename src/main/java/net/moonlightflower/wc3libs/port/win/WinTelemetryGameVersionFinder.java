package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.*;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class WinTelemetryGameVersionFinder extends GameVersionFinder {
    public WinTelemetryGameVersionFinder() {
    }

    /**
     * Reads the game version from the {@code Telemetry.ProgramVersion=} string embedded in the executable.
     */
    @Nonnull
    GameVersion getVersion(@Nonnull File file) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r");
             FileChannel channel = raf.getChannel()) {
            long size = channel.size();
            if (size > Integer.MAX_VALUE) {
                size = Integer.MAX_VALUE;
            }
            int limit = (int) size;
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());

            for (int pos = 0; pos + KEY.length <= limit; pos++) {
                if (!matchesKeyAt(buffer, pos)) {
                    continue;
                }
                StringBuilder version = new StringBuilder();
                for (int i = pos + KEY.length; i < limit && version.length() < MAX_VERSION_LENGTH; i++) {
                    byte b = buffer.get(i);
                    if (b == 0x0) {
                        break;
                    }
                    version.append((char) (b & 0xFF));
                }
                if (version.length() > 0) {
                    return new GameVersion(version.toString());
                }
            }
            throw new IOException("telemetry data could not be extracted from " + file.getAbsolutePath());
        }
    }

    private static boolean matchesKeyAt(@Nonnull MappedByteBuffer buffer, int pos) {
        for (int i = 0; i < KEY.length; i++) {
            if (buffer.get(pos + i) != KEY[i]) {
                return false;
            }
        }
        return true;
    }

    /** Upper bound on the version value read after the key; guards against a missing null terminator. */
    private static final int MAX_VERSION_LENGTH = 32;

    private static final byte[] KEY = {0x54, 0x65, 0x6c, 0x65, 0x6d, 0x65, 0x74, 0x72, 0x79, 0x2e, 0x50, 0x72, 0x6f, 0x67, 0x72,
            0x61, 0x6d, 0x56, 0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x3d};

    @Nonnull
    @Override
    public GameVersion find() throws NotFoundException {
        GameExeFinder gameExeFinder = Context.getService(GameExeFinder.class);

        try {
            File gameExeFile = gameExeFinder.get();

            return getVersion(gameExeFile);
        } catch (Exception e) {
            throw new NotFoundException(e);
        }
    }
}
