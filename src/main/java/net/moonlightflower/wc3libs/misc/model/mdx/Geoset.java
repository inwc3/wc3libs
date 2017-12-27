package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Geoset extends MDXObject {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private VertexChunk _vertexChunk;
    private VertexNormalChunk _vertexNormalChunk;
    private FaceTypeGroupChunk _faceTypeGroupChunk;
    private FaceGroupChunk _faceGroupChunk;
    private FaceChunk _faceChunk;
    private VertexGroupChunk _vertexGroupChunk;
    private MatrixGroupChunk _matrixGroupChunk;
    private MatrixIndexChunk _matrixIndexChunk;

    private long _materialId;

    public long getMaterialId() {
        return _materialId;
    }

    private long _selectionGroup;

    public long getSelectionGroup() {
        return _selectionGroup;
    }

    private long _selectionFlags;

    public long getSelectionFlags() {
        return _selectionFlags;
    }

    private Extent _extent;

    public Extent getExtent() {
        return _extent;
    }

    private List<Extent> _extents = new ArrayList<>();

    public List<Extent> getExtents() {
        return new ArrayList<>(_extents);
    }

    public void addExtent(@Nonnull Extent val) {
        if (!_extents.contains(val)) {
            _extents.add(val);
        }
    }

    private TexCoordSetChunk _texCoordSetChunk;

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        //stream.writeUInt32(_inclusiveSize);
        SizeWriter sizeWriter = new SizeWriter();

        sizeWriter.write(stream);

        _vertexChunk.write(stream);
        _vertexNormalChunk.write(stream);
        _faceTypeGroupChunk.write(stream);
        _faceGroupChunk.write(stream);
        _faceChunk.write(stream);
        _vertexGroupChunk.write(stream);
        _matrixGroupChunk.write(stream);
        _matrixIndexChunk.write(stream);

        stream.writeUInt32(_materialId);
        stream.writeUInt32(_selectionGroup);
        stream.writeUInt32(_selectionFlags);

        _extent.write(stream);

        stream.writeUInt32(_extents.size());

        for (Extent extent : _extents) {
            extent.write(stream);
        }

        _texCoordSetChunk.write(stream);

        sizeWriter.rewrite();
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public Geoset(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");

        _vertexChunk = new VertexChunk(stream);
        _vertexNormalChunk = new VertexNormalChunk(stream);
        _faceTypeGroupChunk = new FaceTypeGroupChunk(stream);
        _faceGroupChunk = new FaceGroupChunk(stream);
        _faceChunk = new FaceChunk(stream);
        _vertexGroupChunk = new VertexGroupChunk(stream);
        _matrixGroupChunk = new MatrixGroupChunk(stream);
        _matrixIndexChunk = new MatrixIndexChunk(stream);

        _materialId = stream.readInt32("materialId");
        _selectionGroup = stream.readInt32("selectionGroup");
        _selectionFlags = stream.readInt32("selectionFlags");

        _extent = new Extent(stream);

        long extentsCount = stream.readUInt32("extentsCount");

        while (extentsCount > 0) {
            addExtent(new Extent(stream));

            extentsCount--;
        }

        _texCoordSetChunk = new TexCoordSetChunk(stream);
    }
}
