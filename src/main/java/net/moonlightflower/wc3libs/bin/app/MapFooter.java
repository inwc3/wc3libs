package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.bin.Reader;
import net.moonlightflower.wc3libs.bin.Writer;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MapFooter {
    public final static Id START_TOKEN = Id.valueOf("NGIS");
    public final static int DATA_SIZE = 256;

    private byte[] _data;

    public byte[] getData() {
        return Arrays.copyOf(_data, DATA_SIZE);
    }

    public void setData(byte[] data) {
        _data = Arrays.copyOf(data, DATA_SIZE);
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            MAP_FOOTER_0x0,
        }

        public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, null);
        public final static EncodingFormat MAP_FOOTER_0x0 = new EncodingFormat(Enum.MAP_FOOTER_0x0, 0xB);

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

        private MapFooter _mapFooter;

        private void write_0x0(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
            long startPos = stream.getPos();

            stream.writeId(START_TOKEN);

            byte[] data = Arrays.copyOf(_mapFooter._data, DATA_SIZE);

            stream.writeBytes(data);
        }

        public void exec(@Nonnull MapFooter mapFooter) throws BinStream.StreamException {
            _mapFooter = mapFooter;

            switch (getFormat().toEnum()) {
                case AUTO:
                case MAP_FOOTER_0x0: {
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
        public MapFooter exec() throws IOException {
            return exec(new MapFooter());
        }

        private MapFooter _mapFooter;

        private MapFooter exec(@Nonnull MapFooter mapFooter) throws IOException {
            _mapFooter = mapFooter;

            read(getFormat());

            return _mapFooter;
        }

        @Nonnull
        private MapFooter read(@Nonnull EncodingFormat format) throws IOException {
            switch (format.toEnum()) {
                case AUTO: {
                    return read_auto();
                }
                case MAP_FOOTER_0x0: {
                    return read_0x0();
                }
                default:
                    throw new EncodingFormat.InvalidFormatException(format);
            }
        }

        @Nonnull
        private MapFooter read_0x0() throws BinInputStream.StreamException {
            Wc3BinInputStream stream = getStream();

            Id startToken = stream.readId("startToken");

            if (!startToken.equals(START_TOKEN)) throw new IllegalArgumentException("wrong startToken " + startToken);

            _mapFooter._data = Arrays.copyOf(stream.readBytes(DATA_SIZE), DATA_SIZE);

            return _mapFooter;
        }

        @Nonnull
        private MapFooter read_auto() throws IOException {
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

        fp.seek(fp.length() - DATA_SIZE - 4);

        byte[] startTokenBytes = new byte[4];

        fp.read(startTokenBytes, 0, startTokenBytes.length);

        fp.close();

        Id startToken = Id.valueOf(new String(startTokenBytes, StandardCharsets.US_ASCII));

        fp = new RandomAccessFile(file, "rw");

        if (startToken.equals(START_TOKEN)) {
            //start token is existing, assume that footer must be existing, substitute old one
            fp.seek(fp.length() - DATA_SIZE - 4);
        } else {
            fp.seek(fp.length());
        }

        fp.write(bytes);

        fp.close();
    }

    public static MapFooter ofMapFile(@Nonnull File file) throws IOException {
        RandomAccessFile fp = new RandomAccessFile(file, "r");

        fp.seek(fp.length() - DATA_SIZE - 4);

        byte[] bytes = new byte[4 + DATA_SIZE];

        fp.read(bytes);

        fp.close();

        Wc3BinInputStream stream = new Wc3BinInputStream(new ByteArrayInputStream(bytes));

        Reader reader = new Reader(stream);

        MapFooter mapFooter = reader.exec();

        stream.close();

        return mapFooter;
    }

    public static MapFooter ofFile(@Nonnull File file) throws IOException {
        Wc3BinInputStream stream = new Wc3BinInputStream(file);

        Reader reader = new Reader(stream);

        MapFooter mapFooter = reader.exec();

        stream.close();

        return mapFooter;
    }
}
