package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Node {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private String _name;

    public String getName() {
        return _name;
    }

    private long _objectId;

    private long getObjectId() {
        return _objectId;
    }

    private long _parentId;

    public long getParentId() {
        return _parentId;
    }

    public enum Flag {
        HELPER(0x0),
        DONT_INHERIT_TRANSLATION(0x1),
        DONT_INHERIT_ROTATION(0x2),
        DONT_INHERIT_SCALING(0x4),
        BILLBOARDED(0x8),
        BILLBOARDED_LOCK_X(0x10),
        BILLBOARDED_LOCK_Y(0x20),
        BILLBOARDED_LOCK_Z(0x40),
        CAMERA_ANCHORED(0x80),
        BONE(0x100),
        LIGHT(0x200),
        EVENT_OBJECT(0x400),
        ATTACHMENT(0x800),
        PARTICLE_EMITTER(0x1000),
        COLLISION_SHAPE(0x2000),
        RIBBON_EMITTER(0x4000),
        EMITTER_USES_MDL_OR_UNSHADED(0x8000),
        EMITTER_USES_TGA_OR_SORT_PRIMITIVES_FAR_Z(0x10000),
        LINE_EMITTER(0x20000),
        UNFOGGED(0x40000),
        MODEL_SPACE(0x80000),
        XY_QUAD(0x100000);

        private int _index;

        public int getIndex() {
            return _index;
        }

        Flag(int index) {
            _index = index;
        }
    }

    private long _flags;

    public long getFlags() {
        return _flags;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        stream.writeBytes(Arrays.copyOf(_name.getBytes(), 80));
        stream.writeUInt32(_objectId);
        stream.writeUInt32(_parentId);
        stream.writeUInt32(_flags);

        //TODO: KGTR, KGRT, KGSC
    }

    public Node(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _name = new String(stream.readBytes(80), StandardCharsets.US_ASCII);
        _objectId = stream.readUInt32("objectId");
        _parentId = stream.readUInt32("parentId");
        _flags = stream.readUInt32("flags");

        //TODO: KGTR, KGRT, KGSC
    }
}
