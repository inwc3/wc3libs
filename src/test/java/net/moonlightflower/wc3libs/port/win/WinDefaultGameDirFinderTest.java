package net.moonlightflower.wc3libs.port.win;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class WinDefaultGameDirFinderTest {

    @Test
    public void x86DefaultPathUsesProgramFilesX86() {
        // Regression guard for the "(86)" typo that prevented discovery of the standard 32-bit install location.
        String path = WinDefaultGameDirFinder.WIN_DEFAULT_X86_PATH.getPath();
        assertTrue(path.contains("Program Files (x86)"), path);
    }
}
