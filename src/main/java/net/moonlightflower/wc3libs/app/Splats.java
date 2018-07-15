package net.moonlightflower.wc3libs.app;

import net.moonlightflower.wc3libs.slk.app.splats.LightningSLK;
import net.moonlightflower.wc3libs.slk.app.splats.SpawnSLK;
import net.moonlightflower.wc3libs.slk.app.splats.SplatSLK;
import net.moonlightflower.wc3libs.slk.app.splats.UberSplatSLK;

import javax.annotation.Nonnull;

public class Splats {
	private final LightningSLK _lightningData;
	private final SpawnSLK _spawnData;
	private final SplatSLK _splatData;
	private final UberSplatSLK _uberSplatData;

	@Nonnull
	public LightningSLK getLightningData() {
		return _lightningData;
	}

	@Nonnull
	public SpawnSLK getSpawnData() {
		return _spawnData;
	}

	@Nonnull
	public SplatSLK getSplatData() {
		return _splatData;
	}

	@Nonnull
	public UberSplatSLK getUberSplatData() {
		return _uberSplatData;
	}
	
	public void addLightningData(@Nonnull LightningSLK val) {
		_lightningData.merge(val, true);
	}
	
	public void addSpawnData(@Nonnull SpawnSLK val) {
		_spawnData.merge(val, true);
	}
	
	public void addSplatData(@Nonnull SplatSLK val) {
		_splatData.merge(val, true);
	}
	
	public void addUberSplatData(@Nonnull UberSplatSLK val) {
		_uberSplatData.merge(val, true);
	}
	
	public Splats() {
		_lightningData = new LightningSLK();
		_spawnData = new SpawnSLK();
		_splatData = new SplatSLK();
		_uberSplatData = new UberSplatSLK();
	}
}
