package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MatrixIndexChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("MATS");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<MatrixIndex> _matrixIndexes = new ArrayList<>();

    public List<MatrixIndex> getMatrixIndexGroups() {
        return new ArrayList<>(_matrixIndexes);
    }

    public void addMatrixIndexGroup(@Nonnull MatrixIndex val) {
        if (!_matrixIndexes.contains(val)) {
            _matrixIndexes.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_matrixIndexes.size());

        for (MatrixIndex vertexGroup : _matrixIndexes) {
            vertexGroup.write(stream);
        }
    }

    public MatrixIndexChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long matrixGroupsCount = stream.readUInt32("matrixIndexCount");

        while (matrixGroupsCount > 0) {
            addMatrixIndexGroup(new MatrixIndex(stream));

            matrixGroupsCount--;
        }
    }
}
