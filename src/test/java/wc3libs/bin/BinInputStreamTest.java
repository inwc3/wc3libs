package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.StdBinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinInputStreamTest {
    @Test
    public void wc3ReadStringConsumesNullTerminator() throws IOException {
        byte[] bytes = new byte[]{'a', 'b', 'c', 0, 'x'};

        Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bytes));

        Assert.assertEquals(stream.readString(), "abc");
        Assert.assertEquals(stream.getPos(), 4L);
        Assert.assertEquals(stream.readByte("next").byteValue(), (byte) 'x');
    }

    @Test
    public void wc3ReadStringAtEofWithoutTerminator() throws IOException {
        byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);

        Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bytes));

        Assert.assertEquals(stream.readString(), "abc");
        Assert.assertEquals(stream.getPos(), 3L);
        Assert.assertTrue(stream.eof());
    }

    @Test
    public void stdReadStringOnEmptyStreamIsStable() throws IOException {
        StdBinInputStream stream = new StdBinInputStream(new ByteArrayInputStream(new byte[0]));

        Assert.assertEquals(stream.readString(), "");
        Assert.assertEquals(stream.getPos(), 0L);
        Assert.assertTrue(stream.eof());
    }

    @Test
    public void fileConstructorReleasesTheSourceAfterBuffering() throws IOException {
        Path file = Files.createTempFile("wc3libs-bin-input-", ".bin");
        Files.write(file, new byte[] { 1, 2, 3 });

        Wc3BinInputStream stream = new Wc3BinInputStream(file.toFile());
        Assert.assertEquals(stream.readBytes(3), new byte[] { 1, 2, 3 });

        // This fails on Windows while the source file is still open.
        Files.delete(file);
        stream.close();
    }
}
