package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObservableArrayList;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class TexCoordSet extends MDXObject {
    public final Id TOKEN = Id.valueOf("UVBS");

    private final ArrayList<TexCoord> _texCoords = new ObservableArrayList<>();

    public ArrayList<TexCoord> getTexCoords() {
        return _texCoords;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        stream.writeId(TOKEN);

        stream.writeUInt32(getTexCoords().size());

        for (TexCoord texCoord : getTexCoords()) {
            texCoord.write(stream);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinInputStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public TexCoordSet(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        Id token = stream.readId("token");

        long texCoordsCount = stream.readUInt32("texCoordsCount");

        while (texCoordsCount > 0) {
            _texCoords.add(new TexCoord(stream));

            texCoordsCount--;
        }
    }

    public TexCoordSet() {
    }
}
