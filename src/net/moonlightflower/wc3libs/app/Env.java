package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.bin.app.MMP;
import net.moonlightflower.wc3libs.bin.app.SHD;
import net.moonlightflower.wc3libs.bin.app.W3E;
import net.moonlightflower.wc3libs.bin.app.WPM;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;

public class Env {
	private MMP _mmp;
	private SHD _shd;
	private W3E _w3e;
	private WPM _wpm;
	
	public SHD getSHD() {
		return _shd;
	}
	
	public W3E getW3E() {
		return _w3e;
	}
	
	public int getWidth() {
		return _w3e.getWidth();
	}
	
	public int getHeight() {
		return _w3e.getHeight();
	}
	
	public void setBounds(Bounds val) {
		_shd.setBounds(val, true);
		_w3e.setBounds(val, true);
		_wpm.setBounds(val, true);
	}
	
	public boolean getShadow(Coords2DF pos) {
		return _shd.getByPos(pos);
	}
	
	public void setShadow(Coords2DF pos, boolean val) {
		_shd.setByPos(pos, val);
	}
	
	public Env(W3E w3e, SHD shd, WPM wpm, MMP mmp) {
		_mmp = mmp;
		_shd = shd;
		_w3e = w3e;
		_wpm = wpm;

		_shd.setBounds(_w3e.getBounds().scale(W3E.CELL_SIZE / SHD.CELL_SIZE), true);
		_wpm.setBounds(_w3e.getBounds().scale(W3E.CELL_SIZE / SHD.CELL_SIZE), true);
	}
	
	public Env(Bounds bounds) {
		this(new W3E(bounds), new SHD(bounds), new WPM(bounds), new MMP());
	}
}
