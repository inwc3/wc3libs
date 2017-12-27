package wc3libs.misc;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;
import org.testng.Assert;

import javax.annotation.Nonnull;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public void readWriteCycle(@Nonnull Class<?> testClass, @Nonnull File file) throws IOException {
        Wc3BinInputStream inStream = new Wc3BinInputStream(file);

        byte[] bytes = inStream.readBytes((int) inStream.size());

        inStream.rewind();

        try {
            Constructor constructor = testClass.getConstructor(Wc3BinInputStream.class);

            Object testObj = constructor.newInstance(inStream);

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

            Wc3BinOutputStream outStream = new Wc3BinOutputStream(outByteStream);

            try {
                Method method = testClass.getMethod("write", Wc3BinOutputStream.class);

                method.invoke(testObj, outStream);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                outStream.close();
            }

            FileOutputStream fp = new FileOutputStream("E:\\work\\bla.mdx");

            fp.write(outByteStream.toByteArray());

            fp.close();

            Assert.assertEquals(outByteStream.toByteArray(), bytes);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

