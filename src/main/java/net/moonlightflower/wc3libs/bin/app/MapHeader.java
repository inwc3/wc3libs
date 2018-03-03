package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class MapHeader {
    public final static Id START_TOKEN = Id.valueOf("HM3W");
    public final static long HEADER_BYTES_SIZE = 512;

    private int _unknown;

    public int getUnknown() {
        return _unknown;
    }

    public void setUnknown(int unknown) {
        _unknown = unknown;
    }

    private String _mapName;

    @Nullable
    private String getMapName() {
        return _mapName;
    }

    public void setMapName(@Nullable String mapName) {
        _mapName = mapName;
    }

    private int _flags;

    public int getFlags() {
        return _flags;
    }

    private int _maxPlayersCount;

    public int getMaxPlayersCount() {
        return _maxPlayersCount;
    }

    public void setMaxPlayersCount(int val) {
        _maxPlayersCount = val;
    }

    public void setFlags(int flags) {
        _flags = flags;
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            MAP_HEADER_0x0,
        }

        public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, null);
        public final static EncodingFormat MAP_HEADER_0x0 = new EncodingFormat(Enum.MAP_HEADER_0x0, 0xB);

        @Nullable
        public static EncodingFormat valueOf(@Nonnull Integer version) {
            return get(EncodingFormat.class, version);
        }

        private EncodingFormat(@Nonnull Enum enumVal, @Nullable Integer version) {
            super(enumVal, version);
        }
    }

    public static class Writer extends net.moonlightflower.wc3libs.bin.Writer<EncodingFormat> {
        @Override
        public EncodingFormat getAutoFormat() {
            return EncodingFormat.AUTO;
        }

        private MapHeader _mapHeader;

        private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
            long startPos = stream.getPos();

            stream.writeId(START_TOKEN);

            stream.writeInt32(_mapHeader._unknown);

            stream.writeString(_mapHeader._mapName);

            stream.writeInt32(_mapHeader._flags);

            stream.writeInt32(_mapHeader._maxPlayersCount);

            long fillBytesSize = HEADER_BYTES_SIZE - stream.getPos() - startPos;

            if (fillBytesSize > 0) {
                do {
                    int fillBytesSizeI = (int) fillBytesSize;

                    stream.writeBytes(new byte[fillBytesSizeI]);

                    fillBytesSize -= fillBytesSizeI;
                } while (fillBytesSize > 0);
            }
        }

        public void exec(@Nonnull MapHeader mapHeader) throws BinStream.StreamException {
            _mapHeader = mapHeader;

            switch (getFormat().toEnum()) {
                case AUTO:
                case MAP_HEADER_0x0: {
                    write_0x0(getStream());

                    break;
                }
            }
        }

        public Writer(@Nonnull Wc3BinOutputStream stream) {
            super(stream);
        }
    }

    public static class Reader extends net.moonlightflower.wc3libs.bin.Reader<EncodingFormat> {
        public Reader(@Nonnull Wc3BinInputStream stream) {
            super(stream);
        }

        @Override
        public EncodingFormat getAutoFormat() {
            return EncodingFormat.AUTO;
        }

        @Nonnull
        public MapHeader exec() throws IOException {
            return exec(new MapHeader());
        }

        private MapHeader _mapHeader;

        private MapHeader exec(@Nonnull MapHeader mapHeader) throws IOException {
            _mapHeader = mapHeader;

            read(getFormat());

            return _mapHeader;
        }

        @Nonnull
        private MapHeader read(@Nonnull EncodingFormat format) throws IOException {
            switch (format.toEnum()) {
                case AUTO: {
                    return read_auto();
                }
                case MAP_HEADER_0x0: {
                    return read_0x0();
                }
                default:
                    throw new EncodingFormat.InvalidFormatException(format);
            }
        }

        @Nonnull
        private MapHeader read_0x0() throws BinInputStream.StreamException {
            Wc3BinInputStream stream = getStream();

            Id startToken = stream.readId("startToken");

            _mapHeader._unknown = stream.readInt32("unknown");
            _mapHeader._mapName = stream.readString("mapName");
            _mapHeader._flags = stream.readInt32("flags");
            _mapHeader._maxPlayersCount = stream.readInt32("maxPlayersCount");

            return _mapHeader;
        }

        @Nonnull
        private MapHeader read_auto() throws IOException {
            return read_0x0();
        }
    }

    public void writeToMapFile(@Nonnull File file) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

        Wc3BinOutputStream stream = new Wc3BinOutputStream(byteStream);

        Writer writer = new Writer(stream);

        writer.exec(this);

        stream.close();

        byte[] bytes = byteStream.toByteArray();

        RandomAccessFile fp = new RandomAccessFile(file, "r");

        byte[] startTokenBytes = new byte[4];

        fp.read(startTokenBytes, 0, startTokenBytes.length);

        fp.close();

        Id startToken = Id.valueOf(new String(startTokenBytes, StandardCharsets.US_ASCII));

        if (!startToken.equals(START_TOKEN)) {
            //start token is missing, assume that header must be missing, prepend new one
            fp = new RandomAccessFile(file, "rw");

            long length = fp.length();

            long endPos = length - 1;

            int maxChunkSize = Integer.MAX_VALUE / 2;

            int chunkSize = (int) Math.min(length, maxChunkSize);

            long startPos = endPos - chunkSize + 1;

            while (chunkSize > 0) {
                fp.seek(startPos);

                byte[] chunkBytes = new byte[chunkSize];

                fp.read(chunkBytes, 0, chunkBytes.length);

                fp.write(chunkBytes);

                length = startPos;

                chunkSize = (int) Math.min(length, maxChunkSize);

                endPos = startPos - 1;

                startPos = endPos - chunkSize + 1;
            }
        } else {
            fp = new RandomAccessFile(file, "rw");
        }

        fp.write(bytes);

        fp.close();
    }

    public static MapHeader ofFile(@Nonnull File file) throws IOException {
        Wc3BinInputStream stream = new Wc3BinInputStream(file);

        Reader reader = new Reader(stream);

        MapHeader mapHeader = reader.exec();

        stream.close();

        return mapHeader;
    }
}
