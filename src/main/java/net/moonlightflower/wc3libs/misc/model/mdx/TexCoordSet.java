package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class TexCoordSet {
    public final Id TOKEN = Id.valueOf("UVBS");

    private List<TexCoord> _texCoords = new ArrayList<>();

    public List<TexCoord> getTexCoords() {
        return new ArrayList<>(_texCoords);
    }

    public void addTexCoord(@Nonnull TexCoord val) {
        if (!_texCoords.contains(val)) {
            _texCoords.add(val);
        }
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        stream.writeId(TOKEN);
    }

    public TexCoordSet(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        long texCoordsCount = stream.readUInt32("texCoordsCount");

        while (texCoordsCount > 0) {
            addTexCoord(new TexCoord(stream));

            texCoordsCount--;
        }
    }
}
