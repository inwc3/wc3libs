package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.bin.Wc3BinInputStream;
import net.moonlightflower.wc3libs.bin.app.MMP;
import net.moonlightflower.wc3libs.bin.app.SHD;
import net.moonlightflower.wc3libs.bin.app.W3E;
import net.moonlightflower.wc3libs.bin.app.WPM;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.port.JMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Env {
    private SHD _shd;

    @Nonnull
    public SHD getSHD() {
        return _shd;
    }

    private W3E _w3e;

    @Nonnull
    public W3E getW3E() {
        return _w3e;
    }

    private WPM _wpm;

    @Nonnull
    public WPM getWPM() {
        return _wpm;
    }

    private MMP _mmp;

    @Nonnull
    public MMP getMMP() {
        return _mmp;
    }

    public int getWidth() {
        return _w3e.getWidth();
    }

    public int getHeight() {
        return _w3e.getHeight();
    }

    public void setBounds(@Nonnull Bounds val) {
        _shd.setBounds(val, true);
        _w3e.setBounds(val, true, true);
        _wpm.setBounds(val, true, true);
    }

    public boolean getShadow(@Nonnull Coords2DF pos) {
        return _shd.getShadowMap().getByPos(pos);
    }

    public void setShadow(@Nonnull Coords2DF pos, boolean val) {
        _shd.getShadowMap().setByPos(pos, val);
    }


    public Env(@Nonnull W3E w3e, @Nonnull SHD shd, @Nonnull WPM wpm, @Nonnull MMP mmp) {
        _mmp = mmp;
        _shd = shd;
        _w3e = w3e;
        _wpm = wpm;

//        _shd.setBounds(_w3e.getBounds().scale(4), true);
//        _wpm.setBounds(_w3e.getBounds().scale(4), true, true);
    }

    public Env(@Nonnull Bounds bounds) {
        this(new W3E(bounds), new SHD(bounds), new WPM(bounds), new MMP());
    }

    public static Env ofMapFile(File map) throws Exception {
        if (!map.exists()) throw new IOException(String.format("file %s does not exist", map));

        MpqPort.Out port = new JMpqPort.Out();

        port.add(W3E.GAME_PATH);
        port.add(SHD.GAME_PATH);
        port.add(WPM.GAME_PATH);
        port.add(MMP.GAME_PATH);

        MpqPort.Out.Result portResult = port.commit(map);

        W3E w3e = null;
        SHD shd = null;
        WPM wpm = null;
        MMP mmp = null;

        InputStream inputStream = portResult.getInputStream(W3E.GAME_PATH);
        if (inputStream != null) {
            w3e = new W3E(new Wc3BinInputStream(inputStream));
        }
        InputStream inputStream2 = portResult.getInputStream(SHD.GAME_PATH);
        if (inputStream2 != null) {
            shd = new SHD(new Wc3BinInputStream(inputStream2));
        }
        InputStream inputStream3 = portResult.getInputStream(WPM.GAME_PATH);
        if (inputStream3 != null) {
            wpm = new WPM(new Wc3BinInputStream(inputStream3));
        }
        InputStream inputStream4 = portResult.getInputStream(MMP.GAME_PATH);
        if (inputStream4 != null) {
            mmp = new MMP(new Wc3BinInputStream(inputStream4));
        }

        if (w3e != null && shd != null && wpm != null && mmp != null) {
            return new Env(w3e, shd, wpm, mmp);
        }
        return null;
    }
}
