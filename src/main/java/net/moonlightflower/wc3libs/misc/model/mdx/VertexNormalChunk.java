package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class VertexNormalChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("NRMS");

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

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_vertices.size());

        for (Vertex vertex : _vertices) {
            vertex.write(stream);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public VertexNormalChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        long verticesCount = stream.readUInt32("verticesCount");

        while (verticesCount > 0) {
            addVertex(new Vertex(stream));

            verticesCount--;
        }
    }
}
