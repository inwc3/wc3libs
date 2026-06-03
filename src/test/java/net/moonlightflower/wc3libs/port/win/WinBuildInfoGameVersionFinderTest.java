package net.moonlightflower.wc3libs.port.win;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class WinBuildInfoGameVersionFinderTest {

    @Test
    public void picksActiveRecordVersion() {
        List<String> lines = List.of(
            "Branch!STRING:0|Active!DEC:1|Build Key!HEX:16|Version!STRING:0|Product!STRING:0",
            "wow|0|aaaa|1.0.0.1|stale",
            "w3|1|bbbb|1.36.0.20257|w3"
        );

        assertEquals(WinBuildInfoGameVersionFinder.parseVersion(lines), "1.36.0.20257");
    }

    @Test
    public void toleratesColumnReordering() {
        // Version before Active, and surrounded by unrelated columns.
        List<String> lines = List.of(
            "Version!STRING:0|Branch!STRING:0|Active!DEC:1|Tags!STRING:0",
            "1.27.1.7085|legacy|0|none",
            "2.0.1.22796|retail|1|speech?us"
        );

        assertEquals(WinBuildInfoGameVersionFinder.parseVersion(lines), "2.0.1.22796");
    }

    @Test
    public void fallsBackToFirstRecordWhenNoActiveColumn() {
        List<String> lines = List.of(
            "Branch!STRING:0|Version!STRING:0|Product!STRING:0",
            "w3|1.32.10.18067|w3"
        );

        assertEquals(WinBuildInfoGameVersionFinder.parseVersion(lines), "1.32.10.18067");
    }

    @Test
    public void returnsNullWhenNoVersionColumn() {
        List<String> lines = List.of(
            "Branch!STRING:0|Active!DEC:1|Product!STRING:0",
            "w3|1|w3"
        );

        assertNull(WinBuildInfoGameVersionFinder.parseVersion(lines));
    }

    @Test
    public void returnsNullForEmptyOrHeaderOnlyFile() {
        assertNull(WinBuildInfoGameVersionFinder.parseVersion(List.of()));
        assertNull(WinBuildInfoGameVersionFinder.parseVersion(List.of("Version!STRING:0|Active!DEC:1")));
    }
}
