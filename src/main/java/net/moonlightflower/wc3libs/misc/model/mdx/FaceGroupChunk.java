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

public class FaceGroupChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("PCNT");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private final LinkedHashSet<IndexReference> _faceGroups = new ObservableLinkedHashSet<>();

    public LinkedHashSet<IndexReference> getFaceTypeGroups() {
        return _faceGroups;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_faceGroups.size());

        for (IndexReference faceGroup : _faceGroups) {
            faceGroup.write(stream);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public FaceGroupChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        long faceGroupsCount = stream.readUInt32("faceGroupsCount");

        while (faceGroupsCount > 0) {
            _faceGroups.add(new IndexReference(stream));

            faceGroupsCount--;
        }
    }

    public FaceGroupChunk() {
    }
}
