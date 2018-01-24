package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Geoset extends MDXObject {
    private long _inclusiveSize = 0;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private VertexChunk _vertexChunk;

    public VertexChunk getVertexChunk() {
        return _vertexChunk;
    }

    public void setVertexChunk(@Nonnull VertexChunk vertexChunk) {
        _vertexChunk = vertexChunk;
    }

    private VertexNormalChunk _vertexNormalChunk;

    @Nonnull
    public VertexNormalChunk getVertexNormalChunk() {
        return _vertexNormalChunk;
    }

    public void setVertexNormalChunk(@Nonnull VertexNormalChunk vertexNormalChunk) {
        _vertexNormalChunk = vertexNormalChunk;
    }

    private FaceTypeGroupChunk _faceTypeGroupChunk;

    @Nonnull
    public FaceTypeGroupChunk getFaceTypeGroupChunk() {
        return _faceTypeGroupChunk;
    }

    public void setFaceTypeGroupChunk(@Nonnull FaceTypeGroupChunk faceTypeGroupChunk) {
        _faceTypeGroupChunk = faceTypeGroupChunk;
    }

    private FaceGroupChunk _faceGroupChunk;

    @Nonnull
    public FaceGroupChunk getFaceGroupChunk() {
        return _faceGroupChunk;
    }

    public void setFaceGroupChunk(@Nonnull FaceGroupChunk faceGroupChunk) {
        _faceGroupChunk = faceGroupChunk;
    }

    private FaceChunk _faceChunk;

    @Nonnull
    public FaceChunk getFaceChunk() {
        return _faceChunk;
    }

    public void setFaceChunk(@Nonnull FaceChunk faceChunk) {
        _faceChunk = faceChunk;
    }

    private VertexGroupChunk _vertexGroupChunk;

    @Nonnull
    public VertexGroupChunk getVertexGroupChunk() {
        return _vertexGroupChunk;
    }

    public void setVertexGroupChunk(@Nonnull VertexGroupChunk vertexGroupChunk) {
        _vertexGroupChunk = vertexGroupChunk;
    }

    private MatrixGroupChunk _matrixGroupChunk;

    @Nonnull
    public MatrixGroupChunk getMatrixGroupChunk() {
        return _matrixGroupChunk;
    }

    public void setMatrixGroupChunk(@Nonnull MatrixGroupChunk matrixGroupChunk) {
        _matrixGroupChunk = matrixGroupChunk;
    }

    private MatrixIndexChunk _matrixIndexChunk;

    @Nonnull
    public MatrixIndexChunk getMatrixIndexChunk() {
        return _matrixIndexChunk;
    }

    public void setMatrixIndexChunk(@Nonnull MatrixIndexChunk matrixIndexChunk) {
        _matrixIndexChunk = matrixIndexChunk;
    }

    private long _materialId = 0;

    public long getMaterialId() {
        return _materialId;
    }

    public void setMaterialId(long materialId) {
        _materialId = materialId;
    }

    private long _selectionGroup = 0;

    public long getSelectionGroup() {
        return _selectionGroup;
    }

    public void setSelectionGroup(long selectionGroup) {
        _selectionGroup = selectionGroup;
    }

    private long _selectionFlags = 0;

    public long getSelectionFlags() {
        return _selectionFlags;
    }

    public void setSelectionFlags(long selectionFlags) {
        _selectionFlags = selectionFlags;
    }

    private Extent _extent;

    @Nonnull
    public Extent getExtent() {
        return _extent;
    }

    public void setExtent(@Nonnull Extent extent) {
        _extent = extent;
    }

    private final LinkedHashSet<Extent> _extents = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Extent> getExtents() {
        return _extents;
    }

    private TexCoordSetChunk _texCoordSetChunk;

    @Nonnull
    public TexCoordSetChunk getTexCoordSetChunk() {
        return _texCoordSetChunk;
    }

    public void setTexCoordSetChunk(@Nonnull TexCoordSetChunk texCoordSetChunk) {
        _texCoordSetChunk = texCoordSetChunk;
    }

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
            _extents.add(new Extent(stream));

            extentsCount--;
        }

        _texCoordSetChunk = new TexCoordSetChunk(stream);
    }

    public Geoset() {
        _vertexChunk = new VertexChunk();
        _vertexNormalChunk = new VertexNormalChunk();
        _faceTypeGroupChunk = new FaceTypeGroupChunk();
        _faceGroupChunk = new FaceGroupChunk();
        _faceChunk = new FaceChunk();
        _vertexGroupChunk = new VertexGroupChunk();
        _matrixGroupChunk = new MatrixGroupChunk();
        _matrixIndexChunk = new MatrixIndexChunk();

        _texCoordSetChunk = new TexCoordSetChunk();
    }
}
