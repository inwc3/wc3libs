package net.moonlightflower.wc3libs.port;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameVersion implements Comparable<GameVersion> {
    private final List<Integer> _versionNumList;

    public static final GameVersion VERSION_1_29 = new GameVersion(Arrays.asList(1, 29));
    public static final GameVersion VERSION_1_31 = new GameVersion(Arrays.asList(1, 31));
    public static final GameVersion VERSION_1_32 = new GameVersion(Arrays.asList(1, 32));

    public GameVersion(@Nonnull List<Integer> versionNumList) {
        _versionNumList = new ArrayList<>(versionNumList);
    }

    public GameVersion(@Nonnull String versionS) {
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
    public String toString() {
        return _versionNumList.toString();
    }

    @Override
    public int compareTo(@Nonnull GameVersion other) {
        for (int i = 0; ; i++) {
            int curNum = (i < _versionNumList.size()) ? _versionNumList.get(i) : 0;
            int otherCurNum = (i < other._versionNumList.size()) ? other._versionNumList.get(i) : 0;

            if (curNum > otherCurNum) return 1;
            if (curNum < otherCurNum) return -1;

            if (i == _versionNumList.size() && i == other._versionNumList.size()) return 0;
        }
    }

    @Override
    public boolean equals(@Nonnull Object other) {
        if (!(other instanceof GameVersion)) return false;

        return compareTo((GameVersion) other) == 0;
    }
}
