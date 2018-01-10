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

public class FaceGroupChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("PCNT");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<IndexReference> _faceGroups = new ArrayList<>();

    public List<IndexReference> getFaceTypeGroups() {
        return new ArrayList<>(_faceGroups);
    }

    public void addFaceGroup(@Nonnull IndexReference val) {
        if (!_faceGroups.contains(val)) {
            _faceGroups.add(val);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_faceGroups.size());

        for (IndexReference faceGroup : _faceGroups) {
            faceGroup.write(stream);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public FaceGroupChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        long faceGroupsCount = stream.readUInt32("faceGroupsCount");

        while (faceGroupsCount > 0) {
            addFaceGroup(new IndexReference(stream));

            faceGroupsCount--;
        }
    }
}
