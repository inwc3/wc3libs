package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class MatrixGroupChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("MTGC");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<MatrixGroup> _matrixGroups = new ArrayList<>();

    public List<MatrixGroup> getMatrixGroups() {
        return new ArrayList<>(_matrixGroups);
    }

    public void addMatrixGroup(@Nonnull MatrixGroup val) {
        if (!_matrixGroups.contains(val)) {
            _matrixGroups.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_matrixGroups.size());

        for (MatrixGroup vertexGroup : _matrixGroups) {
            vertexGroup.write(stream);
        }
    }

    public MatrixGroupChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long matrixGroupsCount = stream.readUInt32("matrixGroupsCount");

        while (matrixGroupsCount > 0) {
            addMatrixGroup(new MatrixGroup(stream));

            matrixGroupsCount--;
        }
    }
}
