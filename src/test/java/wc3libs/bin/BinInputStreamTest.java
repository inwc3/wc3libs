package wc3libs.bin;

import net.moonlightflower.wc3libs.bin.StdBinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

    @Test
    public void wc3StringsPreserveValidUtf8AndMalformedLegacyBytes() throws IOException {
        byte[] original = new byte[]{'A', (byte) 0xC3, (byte) 0xA4, (byte) 0xE4, 'Z', 0};
        Wc3BinInputStream input = new Wc3BinInputStream(new ByteArrayInputStream(original));
        String value = input.readString();

        Assert.assertTrue(value.startsWith("Aä"), "valid UTF-8 remains normal Unicode");

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (Wc3BinOutputStream output = new Wc3BinOutputStream(bytes)) {
            output.writeString(value);
        }

        Assert.assertEquals(bytes.toByteArray(), original,
            "non-UTF-8 locale bytes must not become replacement characters");
    }

    @Test
    public void wc3StringCyclePreservesEveryNonzeroByteValue() throws IOException {
        byte[] original = new byte[256];
        for (int i = 1; i < original.length; i++) original[i - 1] = (byte) i;
        original[original.length - 1] = 0;

        Wc3BinInputStream input = new Wc3BinInputStream(new ByteArrayInputStream(original));
        String value = input.readString();
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (Wc3BinOutputStream output = new Wc3BinOutputStream(bytes)) {
            output.writeString(value);
        }

        Assert.assertEquals(bytes.toByteArray(), original);
    }

    @Test
    public void floatCyclePreservesRawNanPayload() throws IOException {
        byte[] original = new byte[]{0x45, 0x23, (byte) 0xA1, 0x7F};
        Wc3BinInputStream input = new Wc3BinInputStream(new ByteArrayInputStream(original));
        float value = input.readFloat32();
        Assert.assertTrue(Float.isNaN(value));

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (Wc3BinOutputStream output = new Wc3BinOutputStream(bytes)) {
            output.writeFloat32(value);
        }

        Assert.assertEquals(bytes.toByteArray(), original);
    }

    @Test
    public void idCyclePreservesAllFourRawBytes() throws IOException {
        byte[] original = new byte[]{(byte) 0x80, (byte) 0xFF, 'A', 'B'};
        Wc3BinInputStream input = new Wc3BinInputStream(new ByteArrayInputStream(original));
        Id value = input.readId();

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (Wc3BinOutputStream output = new Wc3BinOutputStream(bytes)) {
            output.writeId(value);
        }

        Assert.assertEquals(bytes.toByteArray(), original);
    }
}
