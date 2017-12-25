package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class VertexChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("VRTX");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<Vertex> _vertices = new ArrayList<>();

    public List<Vertex> getVertices() {
        return new ArrayList<>(_vertices);
    }

    public void addVertex(@Nonnull Vertex val) {
        if (!_vertices.contains(val)) {
            _vertices.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_vertices.size());

        for (Vertex vertex : _vertices) {
            vertex.write(stream);
        }
    }

    public VertexChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long verticesCount = stream.readUInt32("verticesCount");

        while (verticesCount > 0) {
            addVertex(new Vertex(stream));

            verticesCount--;
        }
    }
}
