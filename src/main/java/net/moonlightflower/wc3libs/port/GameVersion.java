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

    private static final Pattern pattern = Pattern.compile("(\\d+)", Pattern.DOTALL);

    public GameVersion(@Nonnull List<Integer> versionNumList) {
        _versionNumList = new ArrayList<>(versionNumList);
    }

    public GameVersion(@Nonnull String versionS) {
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

    /**
     * Compares component by component, treating an absent component as zero, so
     * {@code 1.29} and {@code 1.29.0} are the same version.
     * <p>
     * The loop used to end only when the index reached the length of
     * <em>both</em> lists, which for two versions of different length that never
     * differ -- exactly the {@code 1.29} against {@code 1.29.0} case -- it never
     * did. Running past the longer list is enough: everything beyond it is zero
     * on both sides.
     */
    @Override
    public int compareTo(@Nonnull GameVersion other) {
        int components = java.lang.Math.max(_versionNumList.size(), other._versionNumList.size());

        for (int i = 0; i < components; i++) {
            int curNum = (i < _versionNumList.size()) ? _versionNumList.get(i) : 0;
            int otherCurNum = (i < other._versionNumList.size()) ? other._versionNumList.get(i) : 0;

            if (curNum > otherCurNum) return 1;
            if (curNum < otherCurNum) return -1;
        }

        return 0;
    }

    /**
     * Consistent with {@link #compareTo(GameVersion)}, and therefore with
     * {@link #equals(Object)}: trailing zeros do not distinguish a version, so
     * they must not distinguish its hash either.
     */
    @Override
    public int hashCode() {
        int end = _versionNumList.size();

        while (end > 0 && _versionNumList.get(end - 1) == 0) end--;

        return _versionNumList.subList(0, end).hashCode();
    }

    @Override
    public boolean equals(@Nonnull Object other) {
        if (!(other instanceof GameVersion)) return false;

        return compareTo((GameVersion) other) == 0;
    }
}
