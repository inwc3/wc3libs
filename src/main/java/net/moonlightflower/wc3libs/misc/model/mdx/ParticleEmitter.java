package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ParticleEmitter {
    private long _inclusiveSize;

    public long getInclusiveSize() {
        return _inclusiveSize;
    }

    private Node _node;

    public Node getNode() {
        return _node;
    }

    private float _emissionRate;

    public float getEmissionRate() {
        return _emissionRate;
    }

    private float _gravity;

    public float getGravity() {
        return _gravity;
    }

    private float _longitude;

    public float getLongitude() {
        return _longitude;
    }

    private float _latitude;

    public float getLatitude() {
        return _latitude;
    }

    private String _fileName;

    public String getFileName() {
        return _fileName;
    }

    private float _lifespan;

    public float getLifespan() {
        return _lifespan;
    }

    private float _initialSpeed;

    public float getInitialSpeed() {
        return _initialSpeed;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        stream.writeUInt32(_inclusiveSize);
        _node.write(stream);
        stream.writeFloat32(_emissionRate);
        stream.writeFloat32(_gravity);
        stream.writeFloat32(_longitude);
        stream.writeFloat32(_latitude);
        stream.writeBytes(Arrays.copyOf(_fileName.getBytes(), 260));
        stream.writeFloat32(_lifespan);
        stream.writeFloat32(_initialSpeed);

        //TODO: KPEE, KPEG, KPLN, KPLT, KPEL, KPES, KPEV
    }

    public ParticleEmitter(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _inclusiveSize = stream.readUInt32("inclusiveSize");
        _node = new Node(stream);
        _emissionRate = stream.readFloat8("emissionRate");
        _gravity = stream.readFloat8("gravity");
        _longitude = stream.readFloat8("longitude");
        _latitude = stream.readFloat8("latitude");
        _fileName = new String(stream.readBytes(260, "spawnModeLFileName"), StandardCharsets.US_ASCII);
        _lifespan = stream.readFloat8("lifespan");
        _initialSpeed = stream.readFloat8("initialVelocity");

        //TODO: KPEE, KPEG, KPLN, KPLT, KPEL, KPES, KPEV
    }
}
