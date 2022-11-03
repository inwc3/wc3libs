package wc3libs.misc;

import com.sun.nio.file.ExtendedCopyOption;
import net.moonlightflower.wc3libs.misc.exeversion.ExeVersionPe;
import net.moonlightflower.wc3libs.misc.exeversion.ExeVersionWmic;
import net.moonlightflower.wc3libs.misc.exeversion.VersionExtractionException;
import net.moonlightflower.wc3libs.port.Orient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.testng.Assert.*;

public class ExeVersionTest extends Wc3LibTest {
    private static final Logger log = LoggerFactory.getLogger(ExeVersionTest.class.getName());

    private static File peTempFile;
    private static final String dotnetVersionExpected = "1.2.3.400";

    @BeforeSuite
    public void preparePeFile() throws IOException {
        // https://github.com/Luashine/dotnet-pe-version
        File peResourceFile = getFile("pefile/dotnet-pe-version.dll");

        try (InputStream peResourceIn = new FileInputStream(peResourceFile);
             InputStream peResourceBufferedIn = new BufferedInputStream(peResourceIn)) {

            peTempFile = File.createTempFile("wc3libs-peversion-test-" + System.currentTimeMillis(), ".dl_");
            Files.copy(peResourceBufferedIn, peTempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Test()
    public void extractExeVersionWmic() throws VersionExtractionException {
        if (!Orient.isWindowsSystem()) throw new SkipException("Skipping WMIC Version extractor test, not running Windows");

        String peTempPath = peTempFile.toPath().toString();
        String peVersionWmic = ExeVersionWmic.getVersion(peTempPath);
        assertEquals(peVersionWmic, dotnetVersionExpected);
    }

    @Test()
    public void extractExeVersionPe() throws VersionExtractionException {
        String peTempPath = peTempFile.toPath().toString();
        String peVersionPe = ExeVersionPe.getVersion(peTempPath);
        assertEquals(peVersionPe, dotnetVersionExpected);
    }
}
