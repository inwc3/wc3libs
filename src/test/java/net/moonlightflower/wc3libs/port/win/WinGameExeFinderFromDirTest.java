package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.GameVersion;
import net.moonlightflower.wc3libs.port.NotFoundException;
import net.moonlightflower.wc3libs.port.Orient;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class WinGameExeFinderFromDirTest {

    private static File touch(Path dir, String relative) throws Exception {
        Path p = dir.resolve(relative);
        Files.createDirectories(p.getParent());
        Files.writeString(p, "stub");
        return p.toFile();
    }

    @Test
    public void prefersRetailBinaryForReforged() throws Exception {
        Path dir = Files.createTempDirectory("wc3-reforged");
        // Both the real _retail_ (1.32+) binary and a root "Warcraft III.exe" exist; the _retail_ binary must win.
        File retail = touch(dir, "_retail_/x86_64/Warcraft III.exe");
        touch(dir, "Warcraft III.exe");

        File found = WinGameExeFinder.fromDir(dir.toFile(), new GameVersion("1.36.2"), Orient.WinArch.X64);
        assertEquals(found.getAbsoluteFile(), retail.getAbsoluteFile());
    }

    @Test
    public void findsClassic131Layout() throws Exception {
        Path dir = Files.createTempDirectory("wc3-131");
        File exe = touch(dir, "x86_64/Warcraft III.exe");

        File found = WinGameExeFinder.fromDir(dir.toFile(), GameVersion.VERSION_1_31, Orient.WinArch.X64);
        assertEquals(found.getAbsoluteFile(), exe.getAbsoluteFile());
    }

    @Test
    public void findsLegacyWar3Exe() throws Exception {
        Path dir = Files.createTempDirectory("wc3-legacy");
        File exe = touch(dir, "war3.exe");

        File found = WinGameExeFinder.fromDir(dir.toFile(), new GameVersion("1.28"), Orient.WinArch.X64);
        assertEquals(found.getAbsoluteFile(), exe.getAbsoluteFile());
    }

    @Test
    public void throwsWhenNoExecutablePresent() throws Exception {
        Path dir = Files.createTempDirectory("wc3-empty");
        // Must throw, not return a File pointing at a non-existent path.
        assertThrows(NotFoundException.class,
            () -> WinGameExeFinder.fromDir(dir.toFile(), new GameVersion("1.36.2"), Orient.WinArch.X64));
    }
}
