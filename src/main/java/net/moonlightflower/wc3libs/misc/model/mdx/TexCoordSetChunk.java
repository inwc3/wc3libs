package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TexCoordSetChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("UVAS");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<TexCoordSet> _texCoordSets = new ArrayList<>();

    public List<TexCoordSet> getMatrixGroups() {
        return new ArrayList<>(_texCoordSets);
    }

    public void addMatrixGroup(@Nonnull TexCoordSet val) {
        if (!_texCoordSets.contains(val)) {
            _texCoordSets.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_texCoordSets.size());

        for (TexCoordSet texCoordSet : _texCoordSets) {
            texCoordSet.write(stream);
        }
    }

    public TexCoordSetChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long matrixGroupsCount = stream.readUInt32("texCoordSetCount");

        while (matrixGroupsCount > 0) {
            addMatrixGroup(new TexCoordSet(stream));

            matrixGroupsCount--;
        }
    }
}
