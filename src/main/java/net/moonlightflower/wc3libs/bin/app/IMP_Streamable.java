package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;

import javax.annotation.Nonnull;

/**
 * Something in an imports file that can read and write itself.
 * <p>
 * This lived alongside {@link IMP} in that class's own file, which javac warns
 * about for good reason: a second top-level type in a file named after the
 * first is invisible to anything that goes looking for it by name.
 */
interface IMP_Streamable {
    void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException;

    void write(@Nonnull Wc3BinOutputStream stream);
}
