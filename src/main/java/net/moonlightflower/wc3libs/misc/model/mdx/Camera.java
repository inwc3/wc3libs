package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Coords3DF;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Camera {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private String _name;

    public String getName() {
        return _name;
    }

    private Coords3DF _pos;

    public Coords3DF getPos() {
        return _pos;
    }

    private float _fieldOfView;

    public float getFieldOfView() {
        return _fieldOfView;
    }

    private float _farClippingPlane;

    public float getFarClippingPlane() {
        return _farClippingPlane;
    }

    private float _nearClippingPlane;

    public float getNearClippingPlane() {
        return _nearClippingPlane;
    }

    private Coords3DF _targetPos;

    public Coords3DF getTargetPos() {
        return _targetPos;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        stream.writeBytes(Arrays.copyOf(_name.getBytes(), 60));

        stream.writeFloat32(_pos.getX());
        stream.writeFloat32(_pos.getY());
        stream.writeFloat32(_pos.getZ());

        stream.writeFloat32(_fieldOfView);
        stream.writeFloat32(_farClippingPlane);
        stream.writeFloat32(_nearClippingPlane);

        stream.writeFloat32(_targetPos.getX());
        stream.writeFloat32(_targetPos.getY());
        stream.writeFloat32(_targetPos.getZ());

        //TODO: KCTR, KTTR, KCRL
    }

    public Camera(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _name = new String(stream.readBytes(60, "name"), StandardCharsets.US_ASCII);
        _pos = new Coords3DF(stream.readFloat8("x"), stream.readFloat8("y"), stream.readFloat8("z"));
        _fieldOfView = stream.readFloat8("fieldOfView");
        _farClippingPlane = stream.readFloat8("farClippingPlane");
        _nearClippingPlane = stream.readFloat8("nearClippingPlane");
        _targetPos = new Coords3DF(stream.readFloat8("targetX"), stream.readFloat8("targetY"), stream.readFloat8("targetZ"));

        //TODO: KCTR, KTTR, KCRL
    }
}
