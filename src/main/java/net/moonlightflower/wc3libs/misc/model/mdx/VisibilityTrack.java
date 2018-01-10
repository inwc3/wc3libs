package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;

public class VisibilityTrack extends Track {
    private float _visibility;

    public float getVisibility() {
        return _visibility;
    }

    private float _inTan_visibility;

    public float getInTanVisibility() {
        return _inTan_visibility;
    }

    private float _outTan_visibility;

    public float getOutTanVisibility() {
        return _outTan_visibility;
    }

    @Override
    protected void readSpec(@Nonnull Wc3BinInputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType) throws BinStream.StreamException {
        _visibility = stream.readFloat32("visibility");

        if (interpolationType.equals(TrackChunk.InterpolationType.HERMITE) || interpolationType.equals(TrackChunk.InterpolationType.BEZIER)) {
            _inTan_visibility = stream.readFloat32("inTan_visibility");
            _outTan_visibility = stream.readFloat32("outTan_visibility");
        }
    }

    @Override
    protected void writeSpec(@Nonnull Wc3BinOutputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType) throws BinStream.StreamException {
        stream.writeFloat32(_visibility);

        if (interpolationType.equals(TrackChunk.InterpolationType.HERMITE) || interpolationType.equals(TrackChunk.InterpolationType.BEZIER)) {
            stream.writeFloat32(_inTan_visibility);
            stream.writeFloat32(_outTan_visibility);
        }
    }

    public VisibilityTrack(@Nonnull Wc3BinInputStream stream, @Nonnull TrackChunk.InterpolationType interpolationType, @Nonnull MDX.EncodingFormat format)
            throws BinStream.StreamException {
        super(stream, interpolationType, format);
    }
}
