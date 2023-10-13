package net.moonlightflower.wc3libs.misc.exeversion;

import dorkbox.peParser.PE;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExeVersionPe implements ExeVersion {
    private static final Pattern versionOnly = Pattern.compile("([0-9.]*)");

    /**
     * Extracts Version information from a PE executable using a PE parser library.
     * @param executablePath Path to executable file
     * @return Version string with only numbers, if file contains one. Empty string otherwise.
     * @throws VersionExtractionException An exception is thrown when extraction fails.
     */
    public static String getVersion(String executablePath) throws VersionExtractionException {
        Objects.requireNonNull(executablePath);
        try {
            // The dorkbox PE version will return more than just the version if available:
            // dorkbox: "6.1.7601.23403 (win7sp1_ldr.160325-0600)" for cmd.exe
            // wmic Version: "6.1.7601.23403"
            Matcher m = versionOnly.matcher(PE.Companion.getVersion(executablePath));
            if (m.find()) {
                return m.group(1);
            } else {
                return "";
            }
        } catch (Exception e) {
            throw new VersionExtractionException(executablePath, VersionExtractionException.getMethodPeHeader(), e);
        }
    }
}
