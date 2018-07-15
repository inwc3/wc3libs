package net.moonlightflower.wc3libs.bin;

import dorkbox.peParser.PE;
import net.moonlightflower.wc3libs.misc.Registry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameExe {
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
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

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
            for (int i = 0;; i++) {
                int curNum = (i < _versionNumList.size()) ? _versionNumList.get(i) : 0;
                int otherCurNum = (i < other._versionNumList.size()) ? other._versionNumList.get(i) : 0;

                if (curNum > otherCurNum) return 1;
                if (curNum < otherCurNum) return -1;

                if (i == _versionNumList.size() && i == other._versionNumList.size()) return 0;
            }
        }
    }

    public static final Version VERSION_1_29 = new Version(Arrays.asList(1, 29));

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
    public static GameExe fromDir(@Nonnull File dir, @Nonnull Version version) {
        if (version.compareTo(VERSION_1_29) >= 0) return new GameExe(new File(dir, "Warcraft III.exe"));

        return new GameExe(new File(dir, "war3.exe"));
    }

    @Nullable
    public static GameExe fromDir(@Nonnull File dir) {
        Version version;

        File warcraftIIIFile = new File(dir, "Warcraft III.exe");

        if (warcraftIIIFile.exists()) {
            try {
                version = new GameExe(warcraftIIIFile).getVersion();

                return fromDir(dir, version);
            } catch (IOException ignored) {
            }
        }

        File frozenThroneFile = new File(dir, "Frozen Throne.exe");

        if (frozenThroneFile.exists()) {
            try {
                version = new GameExe(frozenThroneFile).getVersion();

                return fromDir(dir, version);
            } catch (IOException ignored) {
            }
        }

        File war3File = new File(dir, "war3.exe");

        if (war3File.exists()) {
            try {
                version = new GameExe(war3File).getVersion();

                return fromDir(dir, version);
            } catch (IOException ignored) {
            }
        }

        return null;
    }
}
