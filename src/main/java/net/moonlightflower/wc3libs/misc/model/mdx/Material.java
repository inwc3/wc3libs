package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Material {
    private long _inclusiveSive;

    public long getInclusiveSize() {
        return _inclusiveSive;
    }

    private long _priorityPlane;

    public long getPriorityPlane() {
        return _priorityPlane;
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    private Set<Layer> _layers = new LinkedHashSet<>();

    public Set<Layer> getLayers() {
        return new LinkedHashSet<>(_layers);
    }

    public void addLayer(@Nonnull Layer val) {
        if (!_layers.contains(val)) {
            _layers.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeUInt32(_inclusiveSive);
        stream.writeUInt32(_priorityPlane);
        stream.writeUInt32(_flags);

        stream.writeId(LAYERS_TOKEN);

        stream.writeUInt32(getLayers().size());

        for (Layer layer : getLayers()) {
            layer.write(stream);
        }
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
            addLayer(new Layer(stream));

            layersCount--;
        }
    }
}
