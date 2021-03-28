package net.moonlightflower.wc3libs.bin;

import dorkbox.peParser.PE;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import net.jodah.failsafe.event.ExecutionCompletedEvent;
import net.moonlightflower.wc3libs.misc.ProcCaller;
import net.moonlightflower.wc3libs.port.GameVersion;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;


public class GameExe {
    private static final Logger log = LoggerFactory.getLogger(GameExe.class.getName());

    @Nonnull
    public static String getVersionString(@Nonnull File file) throws IOException {
        return Failsafe.with(
            new RetryPolicy<String>()
                .handle(IOException.class)
                .withBackoff(500, 10_000, ChronoUnit.MILLIS)
                .withMaxRetries(3)
                .onRetriesExceeded(GameExe::logRetriesExceededGettingVersion)
        ).get(() -> GameExe.getVersionStringInternal(file));
    }

    private static void logRetriesExceededGettingVersion(ExecutionCompletedEvent<String> event) {
        log.warn("Failed to get version string from either dorkbox PE or WMIC. Last event was {}", event);
    }

    /**
     * Attempts to read windows exe metadata to obtain version string. Uses dorkbox PE, and falls back to WMIC if there
     * is an exception.
     * @param file location of exe
     * @return version string, e.g. `1.32.9.16589`
     * @throws IOException if dorkbox PE encounters an exception and then WMIC subsequently fails to emit a version.
     */
    @Nonnull
    private static String getVersionStringInternal(@Nonnull File file) throws IOException {
        try {
            String s = file.getAbsolutePath();
            log.info("Querying {} with dorkbox PE", s);

            return PE.getVersion(s);
        } catch (Exception e) {
            log.info("Falling back to WMIC due to {}", e);

            return getVersionStringInternalWmic(file);
        }
    }

    @Nonnull
    private static String getVersionStringInternalWmic(@Nonnull File file) throws IOException {
        File tmpBat = File.createTempFile("getVersionString", "tmp_proxy.bat");

        tmpBat.deleteOnExit();

        File tmpOut = File.createTempFile("getVersionString", "tmp_out.txt");

        tmpOut.deleteOnExit();

        String query = "\"%SystemRoot%\\System32\\Wbem\\wmic.exe\""+
                " datafile"+
                " where"+
                String.format(" name=\"%s\"", file.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"))+
                " get"+
                " Version"+
                " /value"+
                String.format(" 1>\"%s\"", tmpOut.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"));

        //System.out.println("load " + query);

        Files.write(tmpBat.toPath(), Arrays.asList("chcp 65001", query, "EXIT /B %ERRORLEVEL%"));

        ProcCaller proc = new ProcCaller(tmpBat.getAbsolutePath());

        proc.exec();

        if (proc.exitVal() != 0) {
            throw new IOException(proc.getErrString());
        }

        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStream = new InputStreamReader(new FileInputStream(tmpOut), StandardCharsets.UTF_8);

        try(BufferedReader reader = new BufferedReader(inputStream)) {
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        };

        String versionString = sb.toString().replaceAll("[^0-9.]", "");

        if (versionString.isEmpty()) versionString = null;

        if (versionString == null) {
            throw new IOException(
                "Version string "
                + sb.toString()
                + " from file produced by WMIC wasn't readable. "
                + "This is the second failed attempt after dorkbox PE failed with exception."
            );
        }

        return versionString;
    }

    @Nonnull
    public static GameVersion getVersion(@Nonnull File file) throws IOException {
        return new GameVersion(getVersionString(file));
    }
}