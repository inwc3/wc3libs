package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class CollisionShape {
    private Node _node;

    public Node getNode() {
        return _node;
    }

    public enum Type {
        CUBE,
        PLANE,
        SPHERE,
        CYLINDER
    }

    private Type _type;

    public Type getType() {
        return _type;
    }

    private List<Vertex> _vertices = new ArrayList<>();

    public List<Vertex> getVertices() {
        return new ArrayList<>(_vertices);
    }

    public void addVertex(@Nonnull Vertex val) {
        if (!_vertices.contains(val)) {
            _vertices.add(val);
        }
    }

    private float _radius;

    public float getRadius() {
        return _radius;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        _node.write(stream);
        stream.writeUInt32(_type.ordinal());

        for (Vertex vertex : getVertices()) {
            vertex.write(stream);
        }
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
            addVertex(new Vertex(stream));

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
            _radius = stream.readFloat8("radius");
        }
    }
}
