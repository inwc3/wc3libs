package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FaceChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("PVTX");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<Face> _faces = new ArrayList<>();

    public List<Face> getFaces() {
        return new ArrayList<>(_faces);
    }

    public void addFace(@Nonnull Face val) {
        if (!_faces.contains(val)) {
            _faces.add(val);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_faces.size());

        for (Face face : _faces) {
            face.write(stream);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public FaceChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        if (!token.equals(getToken())) throw new IllegalArgumentException("invalid " + getToken() + " startToken (" + token + ")");

        long facesCount = stream.readUInt32("facesCount");

        while (facesCount > 0) {
            addFace(new Face(stream));

            facesCount--;
        }
    }
}
