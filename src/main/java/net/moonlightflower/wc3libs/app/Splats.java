package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.slk.app.splats.LightningSLK;
import net.moonlightflower.wc3libs.slk.app.splats.SpawnSLK;
import net.moonlightflower.wc3libs.slk.app.splats.SplatSLK;
import net.moonlightflower.wc3libs.slk.app.splats.UberSplatSLK;

public class Splats {
	private LightningSLK _lightningData;
	private SpawnSLK _spawnData;
	private SplatSLK _splatData;
	private UberSplatSLK _uberSplatData;
	
	public LightningSLK getLightningData() {
		return _lightningData;
	}
	
	public SpawnSLK getSpawnData() {
		return _spawnData;
	}
	
	public SplatSLK getSplatData() {
		return _splatData;
	}
	
	public UberSplatSLK getUberSplatData() {
		return _uberSplatData;
	}
	
	public void addLightningData(LightningSLK val) {
		_lightningData.merge(val, true);
	}
	
	public void addSpawnData(SpawnSLK val) {
		_spawnData.merge(val, true);
	}
	
	public void addSplatData(SplatSLK val) {
		_splatData.merge(val, true);
	}
	
	public void addUberSplatData(UberSplatSLK val) {
		_uberSplatData.merge(val, true);
	}
	
	public Splats() {
		_lightningData = new LightningSLK();
		_spawnData = new SpawnSLK();
		_splatData = new SplatSLK();
		_uberSplatData = new UberSplatSLK();
	}
}
