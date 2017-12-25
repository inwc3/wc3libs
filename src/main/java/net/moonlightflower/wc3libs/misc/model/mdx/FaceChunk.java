package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class FaceChunk extends Chunk {
    public static Id TOKEN = Id.valueOf("PVTX");

    @Override
    public Id getToken() {
        return TOKEN;
    }

    private List<FaceChunk> _faces = new ArrayList<>();

    public List<FaceChunk> getFaces() {
        return new ArrayList<>(_faces);
    }

    public void addFace(@Nonnull FaceChunk val) {
        if (!_faces.contains(val)) {
            _faces.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(_faces.size());

        for (FaceChunk face : _faces) {
            face.write(stream);
        }
    }

    public FaceChunk(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        long facesCount = stream.readUInt32("facesCount");

        while (facesCount > 0) {
            addFace(new FaceChunk(stream));

            facesCount--;
        }
    }
}
