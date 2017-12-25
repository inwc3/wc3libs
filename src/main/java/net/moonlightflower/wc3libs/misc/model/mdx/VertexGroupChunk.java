package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class VertexGroupChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("GNDX");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<VertexGroup> _vertexGroups = new ArrayList<>();

    public List<VertexGroup> getVertexGroups() {
        return new ArrayList<>(_vertexGroups);
    }

    public void addVertexGroup(@Nonnull VertexGroup val) {
        if (!_vertexGroups.contains(val)) {
            _vertexGroups.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_vertexGroups.size());

        for (VertexGroup vertexGroup : _vertexGroups) {
            vertexGroup.write(stream);
        }
    }

    public VertexGroupChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long vertexGroupsCount = stream.readUInt32("vertexGroupsCount");

        while (vertexGroupsCount > 0) {
            addVertexGroup(new VertexGroup(stream));

            vertexGroupsCount--;
        }
    }
}
