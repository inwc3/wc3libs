package net.moonlightflower.wc3libs.bin.app;

import net.moonlightflower.wc3libs.bin.BinInputStream;
import net.moonlightflower.wc3libs.bin.Format;
import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.Wc3BinOutputStream;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.ShadowMap;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * shadow map file file for wrapping war3map.SHD
 */
public class SHD {
    public final static File GAME_PATH = new File("war3map.shd");

    private ShadowMap _shadowMap;

    @Nonnull
    public ShadowMap getShadowMap() {
        return _shadowMap;
    }

    public void setBounds(@Nonnull Bounds val, boolean b) {
        _shadowMap.setBounds(val, b, b);
    }

    public static class EncodingFormat extends Format<EncodingFormat.Enum> {
        public enum Enum {
            AUTO,
            SHD_0x0,
        }

        public final static EncodingFormat AUTO = new EncodingFormat(Enum.AUTO, -1);
        public final static EncodingFormat SHD_0x0 = new EncodingFormat(Enum.SHD_0x0, 0x0);

        @Nullable
        public static EncodingFormat valueOf(@Nonnull Integer version) {
            return get(EncodingFormat.class, version);
        }

        private EncodingFormat(@Nonnull Enum enumVal, int version) {
            super(enumVal, version);
        }
    }

    private void write_0x0(@Nonnull Wc3BinOutputStream stream) {
        for (int i = 0; i < _shadowMap.size(); i++) {
            if (_shadowMap.get(i)) {
                stream.writeByte((byte) 0xFF);
            } else {
                stream.writeByte((byte) 0x00);
            }
        }
    }

    private void read_0x0(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        //TODO: long?
        int sizeI = (int) stream.size();

        _shadowMap.setBounds(new Bounds(new Size(1, sizeI)), false, false);

        for (int i = 0; i < sizeI; i++) {
            Boolean val = ((stream.readByte() & 0xFF) == 0xFF);

            _shadowMap.set(i, val);
        }
    }

    private void read_auto(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        read(stream, EncodingFormat.SHD_0x0);
    }

    private void read(@Nonnull Wc3BinInputStream stream, @Nonnull EncodingFormat format) throws BinInputStream.StreamException {
        switch (format.toEnum()) {
            case AUTO: {
                read_auto(stream);

                break;
            }
            case SHD_0x0: {
                read_0x0(stream);

                break;
            }
        }
    }

    private void write(@Nonnull Wc3BinOutputStream stream, @Nonnull EncodingFormat format) {
        switch (format.toEnum()) {
            case AUTO:
            case SHD_0x0: {
                write_0x0(stream);

                break;
            }
        }
    }

    private void read(@Nonnull Wc3BinInputStream stream) throws BinInputStream.StreamException {
        read(stream, EncodingFormat.AUTO);
    }

    public void write(@Nonnull Wc3BinOutputStream stream) {
        write(stream, EncodingFormat.AUTO);
    }

    private void read(@Nonnull File file) throws IOException {
        Wc3BinInputStream inStream = new Wc3BinInputStream(file);

        read(inStream, EncodingFormat.AUTO);

        inStream.close();
    }

    public void write(@Nonnull File file) throws IOException {
        Wc3BinOutputStream outStream = new Wc3BinOutputStream(file);

        write(outStream);

        outStream.close();
    }

    @Override
    public SHD clone() throws CloneNotSupportedException {
        SHD shd = (SHD) super.clone();
        ShadowMap shadowMap = _shadowMap;

        SHD other = new SHD(shadowMap.getBounds());

        other.getShadowMap().mergeCells(shadowMap);

        return other;
    }

    public SHD(@Nonnull BufferedImage img) {
        this(new Bounds(new Size(img.getWidth(), img.getHeight())));

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                _shadowMap.set(new Coords2DI(x, y), img.getRGB(x, y) != Color.BLACK.getRGB());
            }
        }
    }

    public SHD(@Nonnull ShadowMap shadowMap) {
        this();

        _shadowMap = shadowMap.clone();
    }

    public SHD(@Nonnull Bounds bounds) {
        this(new ShadowMap(bounds));
    }

    public SHD(@Nonnull Wc3BinInputStream stream) throws IOException {
        this();

        read(stream);
    }

    public SHD() {
        _shadowMap = new ShadowMap(new Bounds(new Size(0, 0)));
    }

    @Nonnull
    public static SHD ofMap(@Nonnull File mapFile) throws IOException {
        MpqPort.Out portOut = new JMpqPort.Out();

        portOut.add(GAME_PATH);

        MpqPort.Out.Result portResult;

        try {
            portResult = portOut.commit(mapFile);
        } catch (Exception e) {
            throw new IOException(String.format("could not extract %s", GAME_PATH.toString()));
        }

        return new SHD(new Wc3BinInputStream(portResult.getInputStream(GAME_PATH)));
    }
}
