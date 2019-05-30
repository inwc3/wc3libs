package net.moonlightflower.wc3libs.bin;

import dorkbox.peParser.PE;
import net.moonlightflower.wc3libs.misc.Registry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameExe {
    public final static File WAR3_EXE_PATH = new File("war3.exe");
    public final static File WARCRAFT_III_EXE_PATH = new File("Warcraft III.exe");
    public final static File FROZEN_THRONE_EXE_PATH = new File("Frozen Throne.exe");

    public final static File X86_DIR = new File("x86");
    public final static File X64_DIR = new File("x86_64");

    public final static File X86_EXE_PATH_131 = new File(X86_DIR, WARCRAFT_III_EXE_PATH.toString());
    public final static File X64_EXE_PATH_131 = new File(X64_DIR, WARCRAFT_III_EXE_PATH.toString());

    private final File _file;

    public File getFile() {
        return _file;
    }

    public String getVersionString() throws IOException {
        try {
            return PE.getVersion(_file.getAbsolutePath());
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public Version getVersion() throws IOException {
        try {
            return new Version(PE.getVersion(_file.getAbsolutePath()));
        } catch (Exception _e) {
            try {
                return getVersionFallback();
            } catch (Exception e) {
                throw new IOException(e);
            }
        }
    }

    /**
     * Reads the game version from embedded telemetry data
     */
    private Version getVersionFallback() throws IOException {
        FileChannel ch1 = new RandomAccessFile(_file, "r").getChannel();
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
                return new Version(verString);
            }
        }
        throw new IOException("telemetry data could not be extracted");
    }

    private static final byte[] KEY = {0x54, 0x65, 0x6c, 0x65, 0x6d, 0x65, 0x74, 0x72, 0x79, 0x2e, 0x50, 0x72, 0x6f, 0x67, 0x72,
            0x61, 0x6d, 0x56, 0x65, 0x72, 0x73, 0x69, 0x6f, 0x6e, 0x3d};

    public GameExe(@Nonnull File file) {
        _file = file;
    }

    @Nullable
    public static GameExe fromRegistry() {
        try {
            return new GameExe(new File(Registry.get("HKCU\\Software\\Blizzard Entertainment\\Warcraft III", "Program")));
        } catch (IOException e) {
            return null;
        }
    }

    public static class Version implements Comparable<Version> {
        private final List<Integer> _versionNumList;

        public Version(@Nonnull List<Integer> versionNumList) {
            _versionNumList = new ArrayList<>(versionNumList);
        }

        public Version(@Nonnull String versionS) {
            Pattern pattern = Pattern.compile("(\\d+)", Pattern.DOTALL);

            Matcher matcher = pattern.matcher(versionS);

            int start = 0;
            List<Integer> versionNumList = new ArrayList<>();

            while (start < versionS.length() && matcher.find(start)) {
                int version = Integer.parseInt(matcher.group(1));

                start = matcher.end() + 1;

                versionNumList.add(version);
            }

            _versionNumList = versionNumList;
        }

        @Override
        public int compareTo(@Nonnull Version other) {
            for (int i = 0; ; i++) {
                int curNum = (i < _versionNumList.size()) ? _versionNumList.get(i) : 0;
                int otherCurNum = (i < other._versionNumList.size()) ? other._versionNumList.get(i) : 0;

                if (curNum > otherCurNum) return 1;
                if (curNum < otherCurNum) return -1;

                if (i == _versionNumList.size() && i == other._versionNumList.size()) return 0;
            }
        }
    }

    public static final Version VERSION_1_29 = new Version(Arrays.asList(1, 29));
    public static final Version VERSION_1_31 = new Version(Arrays.asList(1, 31));

    public static Version _curVersion = null;

    @Nullable
    public static Version getVersion_simple() {
        if (_curVersion == null) {
            GameExe gameExe = fromRegistry();

            if (gameExe != null) {
                try {
                    _curVersion = gameExe.getVersion();
                } catch (IOException e) {
                    return null;
                }
            }
        }

        return _curVersion;
    }

    public static void setVersion(@Nullable Version version) {
        _curVersion = version;
    }

    @Nonnull
    public static GameExe fromDir(@Nonnull File dir, @Nonnull Version version, boolean x64) {
        if (version.compareTo(VERSION_1_31) >= 0) {
            if (x64) return new GameExe(new File(dir, WARCRAFT_III_EXE_PATH.toString()));

            return new GameExe(new File(dir, WARCRAFT_III_EXE_PATH.toString()));
        }
        if (version.compareTo(VERSION_1_29) >= 0) return new GameExe(new File(dir, WARCRAFT_III_EXE_PATH.toString()));

        return new GameExe(new File(dir, WAR3_EXE_PATH.toString()));
    }

    @Nullable
    public static GameExe fromDir(@Nonnull File dir, boolean x64) {
        List<File> relativeSearchPaths = Arrays.asList(WARCRAFT_III_EXE_PATH,
                FROZEN_THRONE_EXE_PATH,
                WAR3_EXE_PATH,
                X86_EXE_PATH_131,
                X64_EXE_PATH_131
        );

        for (File relativeSearchPath : relativeSearchPaths) {
            File inDirPath = new File(dir, relativeSearchPath.toString());

            if (inDirPath.exists()) {
                try {
                    Version version = new GameExe(inDirPath).getVersion();

                    return fromDir(dir, version, x64);
                } catch (IOException ignored) {
                }
            }
        }

        return null;
    }
}