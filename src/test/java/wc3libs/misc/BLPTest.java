package wc3libs.misc;

import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import net.moonlightflower.wc3libs.misc.image.BLP;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class BLPTest extends Wc3LibTest {
    private static void writeIntLE(ByteArrayOutputStream out, int value) {
        out.write(value & 0xFF);
        out.write((value >>> 8) & 0xFF);
        out.write((value >>> 16) & 0xFF);
        out.write((value >>> 24) & 0xFF);
    }

    private static void setIntLE(byte[] bytes, int offset, int value) {
        bytes[offset] = (byte) (value & 0xFF);
        bytes[offset + 1] = (byte) ((value >>> 8) & 0xFF);
        bytes[offset + 2] = (byte) ((value >>> 16) & 0xFF);
        bytes[offset + 3] = (byte) ((value >>> 24) & 0xFF);
    }

    private static byte[] buildBlp1Indexed(int width, int height, int alphaBitsField, byte[] indices, byte[] alphaPacked, int mipOffsetPad, int mipSizeOverride) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // BLP1 header
        out.write('B');
        out.write('L');
        out.write('P');
        out.write('1');
        writeIntLE(out, 1); // CONTENT_DIRECT
        writeIntLE(out, alphaBitsField);
        writeIntLE(out, width);
        writeIntLE(out, height);
        writeIntLE(out, 5); // extra
        writeIntLE(out, 0); // hasMipmaps

        int headerSize = 4 + 4 + 4 + 4 + 4 + 4 + 4 + (16 * 4) + (16 * 4) + (256 * 4);
        int mipOffset = headerSize + mipOffsetPad;
        int chunkSize = indices.length + alphaPacked.length;
        int mipSize = (mipSizeOverride >= 0) ? mipSizeOverride : chunkSize;

        for (int i = 0; i < 16; i++) {
            writeIntLE(out, (i == 0) ? mipOffset : 0);
        }
        for (int i = 0; i < 16; i++) {
            writeIntLE(out, (i == 0) ? mipSize : 0);
        }

        // cmap[0] = red in 0xBBGGRR packing.
        out.write(0x00);
        out.write(0x00);
        out.write(0xFF);
        out.write(0x00);
        for (int i = 1; i < 256; i++) {
            out.write(0x00);
            out.write(0x00);
            out.write(0x00);
            out.write(0x00);
        }

        for (int i = 0; i < mipOffsetPad; i++) out.write(0x00);
        out.writeBytes(indices);
        out.writeBytes(alphaPacked);

        return out.toByteArray();
    }

    @Test
    public void testBlp() throws IOException, UnsupportedFormatException {
        File file = getFile("images/test.blp");
        BLP blp = new BLP(file);

        Assert.assertEquals(blp.getHeight(), 64);
        Assert.assertEquals(blp.getWidth(), 64);
    }

    @Test
    public void testBlpJpegAlphaDecode() throws IOException, UnsupportedFormatException {
        File file = getFile("images/transparent_example.blp");
        BLP blp = new BLP(file);
        BufferedImage image = blp.getBufImg();

        int nonOpaque = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int alpha = (image.getRGB(x, y) >> 24) & 0xFF;

                if (alpha < 255) nonOpaque++;
            }
        }

        Assert.assertTrue(nonOpaque > 0, "Expected at least one transparent/semi-transparent pixel after BLP decode");
    }

    @Test
    public void testBlp1DirectLargeDimensions() throws IOException, UnsupportedFormatException {
        int width = 1024;
        int height = 1;
        byte[] indices = new byte[width * height];
        Arrays.fill(indices, (byte) 0);

        BLP blp = new BLP(new ByteArrayInputStream(buildBlp1Indexed(width, height, 0, indices, new byte[0], 37, -1)));
        BufferedImage image = blp.getBufImg();

        Assert.assertEquals(image.getWidth(), width);
        Assert.assertEquals(image.getHeight(), height);
    }

    @Test
    public void testBlp1DirectInvalidAlphaBitsFallbacksToOpaque() throws IOException, UnsupportedFormatException {
        int width = 2;
        int height = 1;
        byte[] indices = new byte[]{0, 0};

        BLP blp = new BLP(new ByteArrayInputStream(buildBlp1Indexed(width, height, 7, indices, new byte[0], 0, -1)));
        BufferedImage image = blp.getBufImg();

        int alpha0 = (image.getRGB(0, 0) >> 24) & 0xFF;
        int alpha1 = (image.getRGB(1, 0) >> 24) & 0xFF;

        Assert.assertEquals(alpha0, 255);
        Assert.assertEquals(alpha1, 255);
    }

    @Test
    public void testBlp1DirectTruncatedMipmapChunkIsHandled() throws IOException, UnsupportedFormatException {
        int width = 4;
        int height = 1;
        byte[] indices = new byte[]{0, 0}; // intentionally too short
        int oversizedDeclaredMipSize = 128;

        BLP blp = new BLP(new ByteArrayInputStream(buildBlp1Indexed(width, height, 0, indices, new byte[0], 0, oversizedDeclaredMipSize)));
        BufferedImage image = blp.getBufImg();

        Assert.assertEquals(image.getWidth(), width);
        Assert.assertEquals(image.getHeight(), height);
    }

    @Test
    public void testBlpJpegMipmapDimensionMismatchCropPad() throws IOException, UnsupportedFormatException {
        byte[] bytes = Files.readAllBytes(getFile("images/transparent_example.blp").toPath());

        // BLP1 layout: alphaBits@8, width@12, height@16
        setIntLE(bytes, 8, 0);   // force no alpha split for this test
        setIntLE(bytes, 12, 48); // smaller than JPEG payload dimensions in this sample
        setIntLE(bytes, 16, 48);

        BLP blp = new BLP(new ByteArrayInputStream(bytes));
        BufferedImage image = blp.getBufImg();

        Assert.assertEquals(image.getWidth(), 48);
        Assert.assertEquals(image.getHeight(), 48);
    }
}
