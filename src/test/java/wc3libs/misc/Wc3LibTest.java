package wc3libs.misc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Wc3LibTest {

    public static InputStream getFileStream(String name) {
        return Wc3LibTest.class.getResourceAsStream(name.startsWith("/") ? name : "/" + name);
    }

    /** Returns the specific file from resources dir */
    public static File getFile(String name) {
        return new File(Wc3LibTest.class.getResource(name.startsWith("/") ? name : "/" + name).getFile());
    }

    /** Returns all files from the specified directory */
    public static List<Path> getFiles(String name) throws IOException {
        URL resource = Wc3LibTest.class.getResource(name.startsWith("/") ? name : "/" + name);
        Path dir = Paths.get(resource.getPath().replaceFirst("^/(.:/)", "$1"));
        if(!Files.exists(dir)) {
            throw new IllegalArgumentException(name + " is not a directory.");
        }
        ArrayList<Path> paths = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, (Path p) -> !Files.isDirectory(p))) {
            for (Path path : stream) {
                paths.add(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paths;
    }

}

