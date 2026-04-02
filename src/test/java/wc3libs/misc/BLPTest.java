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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class BLPTest extends Wc3LibTest {
    private static void assertBetweenInclusive(int value, int min, int max, String label) {
        Assert.assertTrue(value >= min && value <= max,
                String.format("%s expected in [%d, %d] but was %d", label, min, max, value));
    }

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

    private static BufferedImage flattenForJpeg(BufferedImage src) {
        BufferedImage flattened = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < src.getHeight(); y++) {
            for (int x = 0; x < src.getWidth(); x++) {
                java.awt.Color c = new java.awt.Color(src.getRGB(x, y), true);

                int bg = ((x / 8 + y / 8) & 1) == 0 ? 230 : 190;
                double a = c.getAlpha() / 255.0;
                int r = (int) (c.getRed() * a + bg * (1.0 - a));
                int g = (int) (c.getGreen() * a + bg * (1.0 - a));
                int b = (int) (c.getBlue() * a + bg * (1.0 - a));

                flattened.setRGB(x, y, new java.awt.Color(r, g, b).getRGB());
            }
        }

        return flattened;
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
        Assert.assertEquals(image.getWidth(), 64);
        Assert.assertEquals(image.getHeight(), 64);
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

    @Test
    public void testBlpBinaryRoundtripDirect() throws IOException, UnsupportedFormatException {
        byte[] bytes = Files.readAllBytes(getFile("images/test.blp").toPath());

        BLP blp = new BLP(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        blp.write(out);

        Assert.assertEquals(out.toByteArray(), bytes);
    }

    @Test
    public void testBlpBinaryRoundtripJpegAlpha() throws IOException, UnsupportedFormatException {
        byte[] bytes = Files.readAllBytes(getFile("images/transparent_example.blp").toPath());

        BLP blp = new BLP(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        blp.write(out);

        Assert.assertEquals(out.toByteArray(), bytes);
    }

    // --- fake_alpha.blp ---
    // BLP1 JPEG with alphaBitsRaw=7. The header value is invalid and must be ignored. This file
    // still carries a fourth decoded JPEG band, so wc3libs should preserve transparency by
    // trusting the raster instead of the broken header.

    @Test
    public void testFakeAlphaBlpDimensions() throws IOException, UnsupportedFormatException {
        BLP blp = new BLP(getFile("images/fake_alpha.blp"));
        BufferedImage image = blp.getBufImg();
        Assert.assertEquals(image.getWidth(), 256);
        Assert.assertEquals(image.getHeight(), 128);
    }

    @Test
    public void testFakeAlphaBlpUsesRasterAlpha() throws IOException, UnsupportedFormatException {
        BLP blp = new BLP(getFile("images/fake_alpha.blp"));
        BufferedImage image = blp.getBufImg();
        int w = image.getWidth();
        int h = image.getHeight();

        int[][] probes = {{0,0},{w-1,0},{0,h-1},{w-1,h-1},{w/2,h/2},{w/4,h/4},{3*w/4,3*h/4}};
        boolean foundTransparentPixel = false;
        for (int[] p : probes) {
            int alpha = (image.getRGB(p[0], p[1]) >>> 24) & 0xFF;
            if (alpha < 255) {
                foundTransparentPixel = true;
                break;
            }
        }
        Assert.assertTrue(foundTransparentPixel, "fake_alpha should preserve transparency from the decoded JPEG raster");
    }

    @Test
    public void testBinaryRoundtripFakeAlpha() throws IOException, UnsupportedFormatException {
        byte[] bytes = Files.readAllBytes(getFile("images/fake_alpha.blp").toPath());
        BLP blp = new BLP(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        blp.write(out);
        Assert.assertEquals(out.toByteArray(), bytes);
    }

    // --- no_alpha.blp ---
    // BLP1 JPEG with alphaBitsRaw=0 (explicit no-alpha). All pixels should be opaque.

    @Test
    public void testNoAlphaBlpDimensions() throws IOException, UnsupportedFormatException {
        BLP blp = new BLP(getFile("images/no_alpha.blp"));
        BufferedImage image = blp.getBufImg();
        Assert.assertEquals(image.getWidth(), 771);
        Assert.assertEquals(image.getHeight(), 133);
    }

    @Test
    public void testNoAlphaBlpAllPixelsOpaque() throws IOException, UnsupportedFormatException {
        BLP blp = new BLP(getFile("images/no_alpha.blp"));
        BufferedImage image = blp.getBufImg();
        int w = image.getWidth();
        int h = image.getHeight();
        int[][] probes = {{0,0},{w-1,0},{0,h-1},{w-1,h-1},{w/2,h/2},{w/4,h/4},{3*w/4,3*h/4}};
        for (int[] p : probes) {
            int alpha = (image.getRGB(p[0], p[1]) >>> 24) & 0xFF;
            Assert.assertEquals(alpha, 255,
                    String.format("no_alpha pixel (%d,%d) should be opaque", p[0], p[1]));
        }
    }

    @Test
    public void testBinaryRoundtripNoAlpha() throws IOException, UnsupportedFormatException {
        byte[] bytes = Files.readAllBytes(getFile("images/no_alpha.blp").toPath());
        BLP blp = new BLP(new ByteArrayInputStream(bytes));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        blp.write(out);
        Assert.assertEquals(out.toByteArray(), bytes);
    }

    @Test
    public void testBlpExportInspectableImages() throws IOException, UnsupportedFormatException {
        Path exportDir = Paths.get("build", "test-artifacts", "blp-exports");
        Files.createDirectories(exportDir);

        BLP transparent = new BLP(getFile("images/transparent_example.blp"));
        BufferedImage transparentImg = transparent.getBufImg();

        Path transparentPng = exportDir.resolve("transparent_example-from-blp.png");
        Path transparentJpg = exportDir.resolve("transparent_example-from-blp.jpg");

        Assert.assertTrue(javax.imageio.ImageIO.write(transparentImg, "png", transparentPng.toFile()));
        Assert.assertTrue(javax.imageio.ImageIO.write(flattenForJpeg(transparentImg), "jpg", transparentJpg.toFile()));

        BLP test = new BLP(getFile("images/test.blp"));
        BufferedImage testImg = test.getBufImg();

        Path testPng = exportDir.resolve("test-from-blp.png");
        Path testJpg = exportDir.resolve("test-from-blp.jpg");

        Assert.assertTrue(javax.imageio.ImageIO.write(testImg, "png", testPng.toFile()));
        Assert.assertTrue(javax.imageio.ImageIO.write(flattenForJpeg(testImg), "jpg", testJpg.toFile()));

        Assert.assertTrue(Files.size(transparentPng) > 0);
        Assert.assertTrue(Files.size(transparentJpg) > 0);
        Assert.assertTrue(Files.size(testPng) > 0);
        Assert.assertTrue(Files.size(testJpg) > 0);

        BufferedImage transparentPngImg = javax.imageio.ImageIO.read(transparentPng.toFile());
        BufferedImage transparentJpgImg = javax.imageio.ImageIO.read(transparentJpg.toFile());
        BufferedImage testPngImg = javax.imageio.ImageIO.read(testPng.toFile());
        BufferedImage testJpgImg = javax.imageio.ImageIO.read(testJpg.toFile());

        Assert.assertNotNull(transparentPngImg);
        Assert.assertNotNull(transparentJpgImg);
        Assert.assertNotNull(testPngImg);
        Assert.assertNotNull(testJpgImg);

        // Transparent BLP PNG export should keep fully transparent outer border.
        int transparentPngCorner = transparentPngImg.getRGB(0, 0);
        int transparentPngCenter = transparentPngImg.getRGB(32, 32);
        Assert.assertEquals((transparentPngCorner >>> 24) & 0xFF, 0);
        Assert.assertEquals((transparentPngCenter >>> 24) & 0xFF, 255);
        assertBetweenInclusive((transparentPngCenter >>> 16) & 0xFF, 0, 40, "transparent png center red");
        assertBetweenInclusive((transparentPngCenter >>> 8) & 0xFF, 70, 150, "transparent png center green");
        assertBetweenInclusive(transparentPngCenter & 0xFF, 160, 230, "transparent png center blue");

        // Flattened JPG has no alpha, but corner should remain bright background and center remain blue-ish.
        int transparentJpgCorner = transparentJpgImg.getRGB(0, 0);
        int transparentJpgCenter = transparentJpgImg.getRGB(32, 32);
        assertBetweenInclusive((transparentJpgCorner >>> 16) & 0xFF, 190, 255, "transparent jpg corner red");
        assertBetweenInclusive((transparentJpgCorner >>> 8) & 0xFF, 190, 255, "transparent jpg corner green");
        assertBetweenInclusive(transparentJpgCorner & 0xFF, 180, 255, "transparent jpg corner blue");
        assertBetweenInclusive((transparentJpgCenter >>> 16) & 0xFF, 0, 80, "transparent jpg center red");
        assertBetweenInclusive((transparentJpgCenter >>> 8) & 0xFF, 70, 170, "transparent jpg center green");
        assertBetweenInclusive(transparentJpgCenter & 0xFF, 140, 255, "transparent jpg center blue");

        // test.blp regression guard: center should be warm yellow/orange, not magenta-green corruption.
        int testPngCenter = testPngImg.getRGB(32, 32);
        assertBetweenInclusive((testPngCenter >>> 16) & 0xFF, 170, 255, "test png center red");
        assertBetweenInclusive((testPngCenter >>> 8) & 0xFF, 170, 255, "test png center green");
        assertBetweenInclusive(testPngCenter & 0xFF, 0, 150, "test png center blue");

        int testJpgCenter = testJpgImg.getRGB(32, 32);
        assertBetweenInclusive((testJpgCenter >>> 16) & 0xFF, 170, 255, "test jpg center red");
        assertBetweenInclusive((testJpgCenter >>> 8) & 0xFF, 150, 255, "test jpg center green");
        assertBetweenInclusive(testJpgCenter & 0xFF, 20, 200, "test jpg center blue");
    }
}
