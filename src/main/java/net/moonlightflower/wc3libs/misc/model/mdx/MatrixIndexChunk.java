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

public class MatrixIndexChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("MATS");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<IndexReference> _IndexReferences = new ArrayList<>();

    public List<IndexReference> getMatrixIndexGroups() {
        return new ArrayList<>(_IndexReferences);
    }

    public void addMatrixIndexGroup(@Nonnull IndexReference val) {
        if (!_IndexReferences.contains(val)) {
            _IndexReferences.add(val);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_IndexReferences.size());

        for (IndexReference indexReference : _IndexReferences) {
            indexReference.write(stream);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public MatrixIndexChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        long matrixGroupsCount = stream.readUInt32("matrixIndexCount");

        while (matrixGroupsCount > 0) {
            addMatrixIndexGroup(new IndexReference(stream));

            matrixGroupsCount--;
        }
    }
}
