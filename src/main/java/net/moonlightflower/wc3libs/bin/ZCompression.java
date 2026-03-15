package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.misc.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ZCompression {
    private static final Logger log = LoggerFactory.getLogger(ZCompression.class.getName());

    public static byte[] compress(byte[] inputBytes) throws BinStream.StreamException {
        try (final Wc3BinInputStream inputStream = new Wc3BinInputStream(new ByteArrayInputStream(inputBytes))) {
            return compress(inputStream);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    private static byte[] compressBlock(byte[] uncompressedBlock) {
        Deflater deflater = new Deflater();

        deflater.setLevel(Deflater.NO_COMPRESSION);
        deflater.setInput(uncompressedBlock);

        deflater.finish();

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream compressedByteArrayOutputStream = new ByteArrayOutputStream();

        while (!deflater.finished()) {
            int writtenLength = deflater.deflate(buffer);
            compressedByteArrayOutputStream.write(buffer, 0, writtenLength);
        }

        return compressedByteArrayOutputStream.toByteArray();
    }

    public static byte[] compress(@Nonnull Wc3BinInputStream inStream) throws BinStream.StreamException {
        //deflate
        final List<byte[]> compressedBlocks = new ArrayList<>();
        final List<Integer> uncompressedBlockSizes = new ArrayList<>();

        long compressedSize = 0;
        long uncompressedSize = 0;

        while (!inStream.eof()) {
            //TODO: long?
            final byte[] uncompressedBlock = inStream.readBytes((int) java.lang.Math.min(2048, inStream.size() - inStream.getPos()));
            final byte[] compressedBlock = compressBlock(uncompressedBlock);

            uncompressedBlockSizes.add(uncompressedBlock.length);
            compressedBlocks.add(compressedBlock);
            uncompressedSize += uncompressedBlock.length;
            compressedSize += compressedBlock.length;
        }



        /*final Iterator<byte[]> uncompressedBlockIterator = uncompressedBlocks.iterator();

        while (uncompressedBlockIterator.hasNext()) {
            final Deflater deflater = new Deflater();

            deflater.setLevel(Deflater.NO_COMPRESSION);

            byte[] deflaterOutput = new byte[2048];

            int len;

            deflater.deflate(deflaterOutput);

            do {
                deflater.setInput(uncompressedBlockIterator.next());
                if (!uncompressedBlockIterator.hasNext()) {
                    deflater.finish();
                }
                len = deflater.deflate(deflaterOutput);
            } while (len == 0 && deflater.needsInput());

            final byte[] compressedBlock = Arrays.copyOf(deflaterOutput, len);

            blocks.add(compressedBlock);
            compressedSize += compressedBlock.length;
        }*/

        //write
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final Wc3BinOutputStream outStream = new Wc3BinOutputStream(byteArrayOutputStream);

        final String startTokenS = "Warcraft III recorded game\u001A";

        outStream.writeBytes(Arrays.copyOfRange(startTokenS.getBytes(StandardCharsets.US_ASCII), 0, 28));

        outStream.writeUInt32(0x44);  //headerSize

        outStream.writeUInt32(compressedSize);  //compressedSize

        outStream.writeUInt32(0x01);  //headerVersion

        outStream.writeUInt32(uncompressedSize);  //uncompressedSize

        outStream.writeUInt32(compressedBlocks.size());

        outStream.writeId(Id.valueOf("W3XP"));

        outStream.writeUInt32(0);  //version

        outStream.writeUInt16(0);  //buildNum

        outStream.writeUInt16(0);  //flags

        outStream.writeUInt32(0);  //len in milliseconds

        final CRC32 crc = new CRC32();

        for (int i = 0; i < outStream.size(); i++) {
            crc.update(outStream.get(i));
        }

        outStream.writeUInt32(crc.getValue());  //crc32

        int c = 0;

        for (final byte[] block : compressedBlocks) {
            outStream.writeUInt32(block.length);
            outStream.writeUInt32(uncompressedBlockSizes.get(c));
            outStream.writeUInt32(0);  //hash

            outStream.writeBytes(block);

            c++;
        }

        try {
            outStream.close();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decompress(@Nonnull byte[] inputBytes) {
        try (final Wc3BinInputStream inputStream = new Wc3BinInputStream(new ByteArrayInputStream(inputBytes))) {
            return decompress(inputStream).writeToByteArray();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public static Wc3BinInputStream decompress(@Nonnull Wc3BinInputStream inStream) throws BinStream.StreamException {
        final byte[] startToken = inStream.readBytes(28);

        final long headerSize = inStream.readUInt32();
        /*
         * 0x40 up to 1.06
         * 0x44 else
         */

        final long compressedFileSize = inStream.readUInt32();

        final long headerVersion = inStream.readUInt32();
        /*
         * 0x00 up to 1.06
         * 0x01 else
         */

        final long uncompressedFileSize = inStream.readUInt32();

        final long blocksCount = inStream.readUInt32();

        final long version;

        switch ((int) headerVersion) {
            case 0x00: {
                final int unknown = inStream.readUInt16();
                version = inStream.readUInt32();

                break;
            }
            default: {
                final Id id = inStream.readId(); //WAR3 or W3XP
                version = inStream.readUInt32();
            }
        }

        final int buildNum = inStream.readUInt16();
        final int flags = inStream.readUInt16();
        /*
         * 0x0000 - singleplayer
         * 0x8000 - multiplayer
         */

        final long len = inStream.readUInt32();
        /*
         * milli seconds of replays, otherwise 0
         */

        final long crc32 = inStream.readUInt32();

        final List<byte[]> blocks = new ArrayList<>();
        final List<byte[]> uncompressedBlocks = new ArrayList<>();

        for (int i = 0; i < blocksCount; i++) {
            final long compressedSize = inStream.readUInt32();
            final long uncompressedSize = inStream.readUInt32();
            /*
             * multiple of 2048, rest padded with 0x00
             */
            final long hash = inStream.readUInt32();

            blocks.add(inStream.readBytes((int) compressedSize));
            uncompressedBlocks.add(new byte[(int) uncompressedSize]);
        }

        int c = 0;

        //inflate
        for (final byte[] block : blocks) {
            final Inflater inflater = new Inflater();

            inflater.setInput(block);

            try {
                inflater.inflate(uncompressedBlocks.get(c));
            } catch (DataFormatException e) {
                log.error(e.getMessage(), e);
            }

            inflater.end();

            c++;
        }

        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        try {
            for (final byte[] block : uncompressedBlocks) {
                outStream.write(block);
            }

            outStream.close();

            return new Wc3BinInputStream(new ByteArrayInputStream(outStream.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
