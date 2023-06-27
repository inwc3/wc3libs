package net.moonlightflower.wc3libs.misc.exeversion;

public class VersionExtractionException extends Exception {

    protected enum METHOD {
        PEHEADER, WMIC
    }

    public VersionExtractionException(String exePathForWmic, int exitCode, String stdOut, String stdErr) {
        super("Failed to extract executable version data via " + getMethodWmic().toString() +
            " (exitcode=" + exitCode + "):\n" +
                "File path: " + exePathForWmic + "\n" +
                "STDOUT: " + stdOut + "\n" +
                "STDERR: " + stdErr);
    }

    public VersionExtractionException(String executablePath, METHOD method, Throwable cause) {
        super("Failed to extract executable version data via '" + method.toString() +
            "' for file: " + executablePath,
            cause
        );
    }

    public VersionExtractionException(String message) {
        super(message);
    }

    public VersionExtractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public static METHOD getMethodWmic() {
        return METHOD.WMIC;
    }

    public static METHOD getMethodPeHeader() {
        return METHOD.PEHEADER;
    }
}
