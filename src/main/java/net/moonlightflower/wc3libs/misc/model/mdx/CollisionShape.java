package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.misc.ObservableLinkedHashSet;
import net.moonlightflower.wc3libs.misc.model.MDX;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class CollisionShape extends MDXObject {
    private Node _node;

    @Nonnull
    public Node getNode() {
        return _node;
    }

    public void setNode(@Nonnull Node node) {
        _node = node;
    }

    public enum Type {
        CUBE,
        PLANE,
        SPHERE,
        CYLINDER
    }

    private Type _type = Type.CUBE;

    @Nonnull
    public Type getType() {
        return _type;
    }

    public void setType(@Nonnull Type type) {
        _type = type;
    }

    private final LinkedHashSet<Vertex> _vertices = new ObservableLinkedHashSet<>();

    public LinkedHashSet<Vertex> getVertices() {
        return _vertices;
    }

    private float _radius = 0F;

    public float getRadius() {
        return _radius;
    }

    public void setRadius(float radius) {
        _radius = radius;
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream, @Nonnull MDX.EncodingFormat format) throws BinStream.StreamException {
        _node.write(stream);
        stream.writeUInt32(_type.ordinal());

        for (Vertex vertex : getVertices()) {
            vertex.write(stream);
        }
    }

    @Override
    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        write(stream, MDX.EncodingFormat.AUTO);
    }

    public CollisionShape(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _node = new Node(stream);

        long typeL = stream.readUInt32("type");

        if (typeL >= Type.values().length) throw new IllegalArgumentException("type out of bounds 0-" + Type.values().length + " (" + typeL + ")");

        _type = Type.values()[(int) typeL];

        long verticesCount = 0L;

        switch (_type) {
            case CUBE: {
                verticesCount = 2L;

                break;
            }
            case PLANE: {
                verticesCount = 1L;

                break;
            }
        }

        while (verticesCount > 0) {
            _vertices.add(new Vertex(stream));

            verticesCount--;
        }

        boolean hasRadius = false;

        switch (_type) {
            case SPHERE: {
                hasRadius = true;

                break;
            }
            case CYLINDER: {
                hasRadius = true;

                break;
            }
        }

        if (hasRadius) {
            _radius = stream.readFloat32("radius");
        }
    }

    public CollisionShape() {
        _node = new Node();
    }
}
