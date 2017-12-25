package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FaceTypeGroupChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("PTYP");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<FaceTypeGroup> _faceTypeGroups = new ArrayList<>();

    public List<FaceTypeGroup> getFaceTypeGroups() {
        return new ArrayList<>(_faceTypeGroups);
    }

    public void addVertex(@Nonnull FaceTypeGroup val) {
        if (!_faceTypeGroups.contains(val)) {
            _faceTypeGroups.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_faceTypeGroups.size());

        for (FaceTypeGroup faceTypeGroup : _faceTypeGroups) {
            faceTypeGroup.write(stream);
        }
    }

    public FaceTypeGroupChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long faceTypeGroupsCount = stream.readUInt32("faceTypeGroupsCount");

        while (faceTypeGroupsCount > 0) {
            addVertex(new FaceTypeGroup(stream));

            faceTypeGroupsCount--;
        }
    }
}
