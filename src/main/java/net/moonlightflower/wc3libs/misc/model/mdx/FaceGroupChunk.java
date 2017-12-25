package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FaceGroupChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("PCNT");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<FaceGroup> _faceGroups = new ArrayList<>();

    public List<FaceGroup> getFaceTypeGroups() {
        return new ArrayList<>(_faceGroups);
    }

    public void addFaceGroup(@Nonnull FaceGroup val) {
        if (!_faceGroups.contains(val)) {
            _faceGroups.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_faceGroups.size());

        for (FaceGroup faceGroup : _faceGroups) {
            faceGroup.write(stream);
        }
    }

    public FaceGroupChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long faceGroupsCount = stream.readUInt32("faceGroupsCount");

        while (faceGroupsCount > 0) {
            addFaceGroup(new FaceGroup(stream));

            faceGroupsCount--;
        }
    }
}
