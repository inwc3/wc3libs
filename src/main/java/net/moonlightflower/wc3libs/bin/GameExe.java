package net.moonlightflower.wc3libs.bin;

import dorkbox.peParser.PE;
import net.moonlightflower.wc3libs.misc.ProcCaller;
import net.moonlightflower.wc3libs.port.GameVersion;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameExe {
    private static final Logger log = LoggerFactory.getLogger(GameExe.class.getName());

    @Nonnull
    public static String getVersionString(@Nonnull File file) throws IOException {
        try {
            String s = file.getAbsolutePath();
            log.info("Querying {} with dorkbox PE", s);

            return PE.getVersion(s);
        } catch (Exception e) {
            log.info("Falling back to WMIC due to {}", e);

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

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(tmpOut), StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String versionString = sb.toString().replaceAll("[^0-9.]", "");

            if (versionString.isEmpty()) versionString = null;

            if (versionString == null) {
                throw new IOException(
                    "Version string "
                    + sb.toString()
                    + " from file produced by WMIC wasn't readable. "
                    + "This is the second failed attempt after dorkbox PE failed with exception.",
                    e);
            }

            return versionString;
        }
    }

    @Nonnull
    public static GameVersion getVersion(@Nonnull File file) throws IOException {
        return new GameVersion(getVersionString(file));
    }
}