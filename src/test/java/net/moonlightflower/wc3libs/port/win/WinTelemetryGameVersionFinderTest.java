package net.moonlightflower.wc3libs.port.win;

import net.moonlightflower.wc3libs.port.GameVersion;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static org.testng.Assert.assertEquals;

public class WinTelemetryGameVersionFinderTest {

    private static File fileWithTelemetry(String version) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write("some prefix bytes".getBytes(StandardCharsets.US_ASCII));
        out.write("Telemetry.ProgramVersion=".getBytes(StandardCharsets.US_ASCII));
        out.write(version.getBytes(StandardCharsets.US_ASCII));
        out.write(0x00);
        out.write("trailing".getBytes(StandardCharsets.US_ASCII));
        File f = File.createTempFile("wc3-telemetry", ".bin");
        f.deleteOnExit();
        Files.write(f.toPath(), out.toByteArray());
        return f;
    }

    @Test
    public void extractsEmbeddedVersion() throws Exception {
        File f = fileWithTelemetry("1.36.2.21230");
        assertEquals(new WinTelemetryGameVersionFinder().getVersion(f), new GameVersion("1.36.2.21230"));
    }

    @Test
    public void handlesVersionLongerThanLegacyBuffer() throws Exception {
        // The previous fixed 15-byte buffer would overflow on a longer value; this 17-char version must parse.
        File f = fileWithTelemetry("100.200.300.40000");
        assertEquals(new WinTelemetryGameVersionFinder().getVersion(f), new GameVersion("100.200.300.40000"));
    }
}
