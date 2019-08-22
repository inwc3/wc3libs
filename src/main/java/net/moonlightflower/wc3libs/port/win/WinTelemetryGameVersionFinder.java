package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.*;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class WinTelemetryGameVersionFinder extends GameVersionFinder {
    public WinTelemetryGameVersionFinder() {
    }

    /**
     * Reads the game version from embedded telemetry data
     */
    @Nonnull
    private GameVersion getVersion(@Nonnull File file) throws IOException {
        FileChannel ch1 = new RandomAccessFile(file, "r").getChannel();
        long size = ch1.size();
        ByteBuffer m1 = ch1.map(FileChannel.MapMode.READ_ONLY, 0L, size);
        byte[] buffer = new byte[KEY.length];
        ByteBuffer verBuffer = ByteBuffer.allocate(15);
        for (int pos = 0; pos < size - KEY.length - 1; pos++) {
            m1.position(pos);
            m1.get(buffer);
            if (Arrays.equals(buffer, KEY)) {
                byte b = m1.get();
                while (verBuffer.get(verBuffer.position()) != 0x0 || b != 0x0) {
                    verBuffer.put(b);
                    b = m1.get();
                }
                String verString = new String(verBuffer.array()).substring(0, verBuffer.position());
                return new GameVersion(verString);
            }
        }
        throw new IOException("telemetry data could not be extracted");
    }

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
