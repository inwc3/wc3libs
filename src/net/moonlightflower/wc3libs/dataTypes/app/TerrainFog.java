package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

public class TerrainFog extends DataType {
	private TerrainFogType _type = TerrainFogType.NONE;
	
	public TerrainFogType getType() {
		return _type;
	}
	
	public void setType(TerrainFogType val) {
		_type = val;
	}
	
	private float _zStart = 0;
	
	public float getZStart() {
		return _zStart;
	}
	
	public void setZStart(float val) {
		_zStart = val;
	}

	private float _zEnd = 0;
	
	public float getZEnd() {
		return _zEnd;
	}
	
	public void setZEnd(float val) {
		_zEnd = val;
	}
	
	private float _density = 0;
	
	public float getDensity() {
		return _density;
	}
	
	public void setDensity(float val) {
		_density = val;
	}
	
	private Color _color = Color.fromBGRA(0, 0, 0, 0);
	
	public Color getColor() {
		return _color;
	}
	
	public void setColor(Color val) {
		_color = val;
	}
	
	@Override
	public String toString() {
		return String.format("type=[%s] zStart=%.2f zEnd=%.2f density=%.2f color=[%s]", getType(), getZStart(), getZEnd(), getDensity(), getColor());
	}
	
	public TerrainFog(TerrainFogType type, float zStart, float zEnd, float density, Color color) {
		setType(type);
		setZStart(zStart);
		setZEnd(zEnd);
		setDensity(density);
		setColor(color);
	}
}
