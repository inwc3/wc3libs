package net.moonlightflower.wc3libs.misc.exeversion;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExeVersionWmic implements ExeVersion {
    // Match version number or empty string. Both are valid.
    private static final Pattern versionPattern = Pattern.compile("Version=([0-9.]*)");

    /**
     * Extracts Version information from a PE executable using WMIC on Windows.
     * This launches a WMI Console and is slow, hence to be used as a fallback option.
     * @param exePath Path to executable file
     * @return Version string with only numbers, if file contains one. Empty string otherwise.
     * @throws VersionExtractionException An exception is thrown when extraction fails.
     */
    public static String getVersion(String exePath) throws VersionExtractionException {
        /* Microsoft is insane.

        > wmic datafile where "name=C:\\WC3-Versions\\w129.exe" get version /value
        Returns:
        Node - MYCOMPUTER
        ERROR:
        Description = Invalid query

        It wants:
        1. Double backslashes
            Why? Because the query is actually an SQL query that's passed without escaping user input by wmic.
            You can see it fail in Windows Event Viewer > Microsoft/Windows/WMI-Activity/Operational
            You can test it yourself with Powershell: Get-WmiObject -Query "__raw_query__"

        2. Quotes (double or single) around the path
        > wmic datafile where name="C:\\WC3\\w129.exe" get version /value
        > wmic datafile where "name='C:\\WC3\\w129.exe'" get version /value
        Version=1.29.2.9231

        But not:
        > wmic datafile where "name=C:\\WC3-Versions\\w129.exe"
        Node - MYCOMPUTER
        ERROR:
        Description = Invalid query

        3. It does not accept / forward slashes.
        4. The output is preceded by 2 empty lines and ends with 3 empty lines.
        -
        When file not found, the query is technically considered successful with the following message:
        No Instance(s) Available.
        */
        // Must give double-backslash paths to wmic
        String exePathForWmic = exePath.replaceAll("[/\\\\]", "\\\\\\\\");

        ProcessBuilder wmicBuilder = new ProcessBuilder("wmic",
                "datafile", "where", "name='" + exePathForWmic + "'",
                "get", "Version", "/value");

        Process wmic;
        try {
            wmic = wmicBuilder.start();
        } catch (IOException e) {
            throw new VersionExtractionException(
                "Failed to start wmic to get version of exe file: " + exePathForWmic, e);
        }

        // Prepare STDOUT/STDERR
        StringBuilder strErrBuilder = new StringBuilder();
        StringBuilder strOutBuilder = new StringBuilder();

        AtomicReference<Optional<String>> versionRef = new AtomicReference<>(Optional.empty());
        CallableInputStreamProcessor stdOutReader = new CallableInputStreamProcessor(wmic.getInputStream(), line -> {
            if (line.length() > 0) {
                strOutBuilder.append(line);
                Matcher matcher = versionPattern.matcher(line);
                if (matcher.lookingAt()) {
                    versionRef.set(Optional.of(matcher.group(1)));
                }
            }
        });
        CallableInputStreamProcessor stdErrReader = new CallableInputStreamProcessor(wmic.getErrorStream(), line -> {
            if (line.length() > 0) {
                strErrBuilder.append(line);
            }
        });

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> stdOutFuture = es.submit(stdOutReader);
        Future<Integer> stdErrFuture = es.submit(stdErrReader);


        int exitCode;
        int stdErrLines;
        while (true) {
            try {
                stdOutFuture.get();
                stdErrLines = stdErrFuture.get();
                exitCode = wmic.waitFor();
                break;
            } catch (InterruptedException e) {
                // ignored
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        es.shutdownNow();

        Optional<String> version = versionRef.get();
        if (version.isPresent()) {
            return version.get();
        } else {
            if (exitCode == 0 && stdErrLines == 0) {
                return "";
            } else {
                throw new VersionExtractionException(
                    exePathForWmic, exitCode,
                    strOutBuilder.toString(), strErrBuilder.toString()
                );
            }
        }
    }

}
