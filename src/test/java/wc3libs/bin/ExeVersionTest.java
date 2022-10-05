package wc3libs.bin;

import net.moonlightflower.wc3libs.misc.exeversion.ExeVersionPe;
import net.moonlightflower.wc3libs.misc.exeversion.ExeVersionWmic;
import net.moonlightflower.wc3libs.misc.exeversion.VersionExtractionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.testng.Assert.*;

public class ExeVersionTest {
    private static final Logger log = LoggerFactory.getLogger(ExeVersionTest.class.getName());

    @Test()
    public void extractExeVersionTest() throws VersionExtractionException, Exception {
        // These tests need some static .exe files we don't carry around currently.
        // Instead test java.exe and cmd.exe on current system.
        // Hopefully these won't fail and be /sufficient/

        String javahome = System.getProperty("java.home"); // ends without backslash
        String javaExe = Paths.get(javahome, "bin", "java.exe").toAbsolutePath().toString();
        String cmdExe = System.getenv("COMSPEC");

        String javaVersionWmic = ExeVersionWmic.getVersion(javaExe);
        String javaVersionPe = ExeVersionPe.getVersion(javaExe);

        String onlyNumbers = "^[0-9.]+$";


        assertFalse(javaVersionWmic.isEmpty(), "WMIC: java.exe version must not be empty!");
        assertTrue(javaVersionWmic.matches(onlyNumbers));
        assertTrue(javaVersionPe.matches(onlyNumbers));
        assertEquals(javaVersionPe, javaVersionWmic);


        String cmdVersionWmic = ExeVersionWmic.getVersion(cmdExe);
        assertFalse(cmdVersionWmic.isEmpty(), "WMIC: cmd.exe version must not be empty!");
        assertTrue(cmdVersionWmic.matches(onlyNumbers),
            "WMIC Extractor: cmd.exe version must only contain numbers: " + cmdVersionWmic);

        String cmdVersionPe = ExeVersionPe.getVersion(cmdExe);

        assertFalse(cmdVersionPe.isEmpty());
        assertTrue(cmdVersionPe.matches(onlyNumbers),
            "PE Extractor: cmd.exe version must only contain numbers");

        assertEquals(cmdVersionPe, cmdVersionWmic, "cmd.exe version, compare PE and WMIC");

        /*
        log.info("WMIC CMD: " + cmdVersionWmic);
        log.info("PE CMD: " + cmdVersionWmic);
        log.info("WMIC JAVA: " + javaVersionWmic);
        log.info("PE JAVA: " + javaVersionPe);
         */
    }
}
