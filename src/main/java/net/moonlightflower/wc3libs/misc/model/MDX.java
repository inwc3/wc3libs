package net.moonlightflower.wc3libs.misc.model;

import net.moonlightflower.wc3libs.bin.*;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.mdx.VersionChunk;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MDX {
    private List<VersionChunk> _versionChunks = new ArrayList<>();

    @Nonnull
    public List<VersionChunk> getVersions() {
        return new ArrayList<>(_versionChunks);
    }

    private void addVersion(@Nonnull VersionChunk val) {
        _versionChunks.add(val);
    }

    public VersionChunk addVersion() {
        VersionChunk versionChunk = new VersionChunk();

        addVersion(versionChunk);

        return versionChunk;
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            MDX_0x0
        }

        private static Map<Integer, MDX.EncodingFormat> _map = new LinkedHashMap<>();

        public final static EncodingFormat AUTO = new MDX.EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat MDX_0x0 = new MDX.EncodingFormat(Enum.MDX_0x0, 0x0);

        public static EncodingFormat valueOf(int version) {
            return _map.get(version);
        }

        private EncodingFormat(@Nonnull Enum enumVal, int version) {
            super(enumVal, version);

            _map.put(version, this);
        }
    }

    public static Id TOKEN = Id.valueOf("MDLX");

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        Id startToken = stream.readId("startToken");

        if (!startToken.equals(TOKEN)) throw new RuntimeException("invalid " + TOKEN + " startToken (" + startToken + ")");

        while (!stream.eof()) {
            Id chunkToken = stream.readId("chunkToken");

            if (chunkToken.equals(VersionChunk.TOKEN)) {
                stream.rewind(4);

                addVersion(new VersionChunk(stream, EncodingFormat.MDX_0x0));
            } else {
                long size = stream.readUInt32("header_size");

                stream.skip(size);
            }
        }
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
        stream.writeId(TOKEN);

        stream.writeInt(_versionChunks.size());

        for (VersionChunk versionChunk : _versionChunks) {
            versionChunk.write(stream, EncodingFormat.MDX_0x0);
        }
    }

    private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        read(stream, EncodingFormat.MDX_0x0);
    }

    private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
        switch (format.toEnum()) {
            case AUTO: {
                read_auto(stream);

                break;
            }
            case MDX_0x0: {
                read_0x0(stream);

                break;
            }
        }
    }

    private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
        switch (format.toEnum()) {
            case AUTO:
            case MDX_0x0: {
                write_0x0(stream);

                break;
            }
        }
    }

    private void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        read(stream, MDX.EncodingFormat.AUTO);
    }

    private void write(@Nonnull Wc3BinOutputStream stream) {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    private void read(@Nonnull File file, @Nonnull MDX.EncodingFormat format) throws IOException {
        read(new Wc3BinInputStream(file), format);
    }

    public void write(@Nonnull File file, @Nonnull MDX.EncodingFormat format) throws IOException {
        Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

        write(outStream, format);

        outStream.close();
    }

    private void read(@Nonnull File file) throws IOException {
        read(file, MDX.EncodingFormat.AUTO);
    }

    public void write(@Nonnull File file) throws IOException {
        write(new Wc3BinOutputStream(file));
    }

    public MDX() {
    }

    public MDX(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        read(stream);
    }

    public MDX(@Nonnull File file) throws IOException {
        read(file);
    }
}
