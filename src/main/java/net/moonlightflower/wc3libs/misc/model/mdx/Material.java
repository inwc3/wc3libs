package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Material extends MDXObject {
    private long _inclusiveSive;

    public long getInclusiveSize() {
        return _inclusiveSive;
    }

    private long _priorityPlane;

    public long getPriorityPlane() {
        return _priorityPlane;
    }

    public enum Flag {
        CONST_COLOR(0x1),
        UNKNOWN_A(0x2),
        UNKNOWN_B(0x4),
        UNKNOWN_C(0x8),
        SORT_PRIMITIVES_FAR_Z(0x10),
        FULL_RESOLUTION(0x20);

        private int _index;

        public int getIndex() {
            return _index;
        }

        Flag(int index) {
            _index = index;
        }
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    private final LinkedHashSet<Layer> _layers = new LinkedHashSet<>();

    public LinkedHashSet<Layer> getLayers() {
        return new LinkedHashSet<>(_layers);
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSive);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        stream.writeUInt32(_priorityPlane);
        stream.writeUInt32(_flags);

        stream.writeId(LAYERS_TOKEN);

        stream.writeUInt32(getLayers().size());

        for (Layer layer : getLayers()) {
            layer.write(stream);
        }

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public final Id LAYERS_TOKEN = Id.valueOf("LAYS");

    public Material(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSive = stream.readUInt32("inclusiveSize");
        _priorityPlane = stream.readUInt32("priorityPlane");
        _flags = stream.readUInt32("flags");

        Id layersToken = stream.readId("layersToken");

        if (!layersToken.equals(LAYERS_TOKEN)) throw new IllegalArgumentException("invalid " + LAYERS_TOKEN + " token (" + layersToken + ")");

        long layersCount = stream.readUInt32("layersCount");

        while (layersCount > 0) {
            _layers.add(new Layer(stream));

            layersCount--;
        }
    }
}
