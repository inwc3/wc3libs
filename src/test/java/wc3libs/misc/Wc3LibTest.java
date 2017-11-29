package wc3libs.misc;

import java.io.File;
import java.io.InputStream;

public abstract class Wc3LibTest {

    public static InputStream getFileStream(String name) {
        return Wc3LibTest.class.getResourceAsStream(name.startsWith("/") ? name : "/" + name);
    }

    public static File getFile(String name) {
        return new File(Wc3LibTest.class.getResource(name.startsWith("/") ? name : "/" + name).getFile());
    }

}

