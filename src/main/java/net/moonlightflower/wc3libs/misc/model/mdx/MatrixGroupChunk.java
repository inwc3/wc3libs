package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.LinkedHashSet;

public class MatrixGroupChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("MTGC");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private final LinkedHashSet<IndexReference> _matrixGroups = new ObservableLinkedHashSet<>();

    public LinkedHashSet<IndexReference> getMatrixGroups() {
        return _matrixGroups;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_matrixGroups.size());

        for (IndexReference vertexGroup : _matrixGroups) {
            vertexGroup.write(stream);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public MatrixGroupChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        long matrixGroupsCount = stream.readUInt32("matrixGroupsCount");

        while (matrixGroupsCount > 0) {
            _matrixGroups.add(new IndexReference(stream));

            matrixGroupsCount--;
        }
    }

    public MatrixGroupChunk() {
    }
}
