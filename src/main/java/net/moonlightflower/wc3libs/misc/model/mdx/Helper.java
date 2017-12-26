package net.moonlightflower.wc3libs.misc.model.mdx;

import net.moonlightflower.wc3libs.bin.BinStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

public class Helper {
    private Node _node;

    public Node getNode() {
        return _node;
    }

    public void write(@Nonnull Wc3BinOutputStream stream) throws BinStream.StreamException {
        _node.write(stream);
    }

    public Helper(@Nonnull Wc3BinInputStream stream) throws BinStream.StreamException {
        _node = new Node(stream);
    }
}
