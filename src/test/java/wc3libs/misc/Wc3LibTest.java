package wc3libs.misc;

import net.moonlightflower.wc3libs.bin.ObjMod;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.bin.app.WCT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Wc3LibTest {
    private static final Logger log = LoggerFactory.getLogger(Wc3LibTest.class.getName());

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
            log.error(e.getMessage(), e);
        }
        return paths;
    }

    public void readWriteCycle(@Nonnull Object testObj, @Nonnull File file) throws IOException {
        Wc3BinInputStream inStream = new Wc3BinInputStream(file);

        byte[] bytes = inStream.readBytes((int) inStream.size());

        inStream.rewind();

        try {
            Method readMethod = testObj.getClass().getMethod("read", Wc3BinInputStream.class);

            readMethod.invoke(testObj, inStream);

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

            Wc3BinOutputStream outStream = new Wc3BinOutputStream(outByteStream);

            try {
                Method writeMethod = testObj.getClass().getMethod("write", Wc3BinOutputStream.class);

                writeMethod.invoke(testObj, outStream);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                outStream.close();
            }

            /*FileOutputStream fp = new FileOutputStream("E:\\work\\bla.mdx");

            fp.write(outByteStream.toByteArray());

            fp.close();*/

            Assert.assertEquals(outByteStream.toByteArray(), bytes);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void readWriteCycle(@Nonnull Class<?> testClass, @Nonnull File file) throws IOException {
        Wc3BinInputStream inStream = new Wc3BinInputStream(file);

        byte[] bytes = inStream.readBytes((int) inStream.size());

        inStream.rewind();

        try {
            Constructor constructor = testClass.getConstructor(Wc3BinInputStream.class);

            Object testObj = constructor.newInstance(inStream);

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

            try (Wc3BinOutputStream outStream = new Wc3BinOutputStream(outByteStream)) {
                if (ObjMod.class.isAssignableFrom(testClass)) {
                    Method writeMethod = testClass.getMethod("write", Wc3BinOutputStream.class, ObjMod.EncodingFormat.class);

                    writeMethod.invoke(testObj, outStream, ObjMod.EncodingFormat.AS_DEFINED);
                } else if (WCT.class.isAssignableFrom(testClass)) {
                    Method writeMethod = testClass.getMethod("write", Wc3BinOutputStream.class, WCT.EncodingFormat.class);

                    writeMethod.invoke(testObj, outStream, WCT.EncodingFormat.AS_DEFINED);
                } else {
                    Method writeMethod = testClass.getMethod("write", Wc3BinOutputStream.class);

                    writeMethod.invoke(testObj, outStream);
                }
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            byte[] outBytes = outByteStream.toByteArray();
            int maxBytes = Math.max(outBytes.length, bytes.length);

            Assert.assertEquals(Arrays.copyOf(outByteStream.toByteArray(), maxBytes), Arrays.copyOf(bytes, maxBytes));

            Assert.assertEquals(outByteStream.toByteArray(), bytes);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

