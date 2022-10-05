package net.moonlightflower.wc3libs.misc.exeversion;

public interface ExeVersion {

    /**
     * Extracts Version information from a PE executable on Windows.
     * @param executablePath Path to executable file
     * @return Version string with only numbers, if file contains one. Empty string otherwise.
     * @throws VersionExtractionException An exception is thrown when extraction fails.
     */
    static String getVersion(String executablePath) throws VersionExtractionException {
        throw new VersionExtractionException("Cannot call this function on the interface.");
    }
}
