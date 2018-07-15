package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class TerrainFog extends DataType {
	private TerrainFogType _type = TerrainFogType.NONE;
	
	public TerrainFogType getType() {
		return _type;
	}
	
	public void setType(@Nonnull TerrainFogType val) {
		_type = val;
	}
	
	private War3Real _zStart = War3Real.valueOf(0F);
	
	public War3Real getZStart() {
		return _zStart;
	}
	
	public void setZStart(@Nonnull War3Real val) {
		_zStart = val;
	}

	private War3Real _zEnd = War3Real.valueOf(0F);
	
	public War3Real getZEnd() {
		return _zEnd;
	}
	
	public void setZEnd(@Nonnull War3Real val) {
		_zEnd = val;
	}
	
	private War3Real _density = War3Real.valueOf(0F);
	
	public War3Real getDensity() {
		return _density;
	}
	
	public void setDensity(@Nonnull War3Real val) {
		_density = val;
	}
	
	private Color _color = Color.fromBGRA255(0, 0, 0, 0);

	@Nonnull
	public Color getColor() {
		return _color;
	}
	
	public void setColor(@Nonnull Color val) {
		_color = val;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof TerrainFog)
			return equals(((TerrainFog) other));

		return super.equals(other);
	}

	public boolean equals(TerrainFog other) {
		return getType().equals(other.getType()) &&
				getZStart().equals(other.getZStart()) &&
				getZEnd().equals(other.getZEnd()) &&
				getDensity().equals(other.getDensity()) &&
				getColor().equals(other.getColor());
	}

	@Override
	public String toString() {
		return String.format("type=[%s] zStart=%.2f zEnd=%.2f density=%.2f color=[%s]", getType(), getZStart().getVal(), getZEnd().getVal(), getDensity().getVal(), getColor());
	}
	
	public TerrainFog(@Nonnull TerrainFogType type, @Nonnull War3Real zStart, @Nonnull War3Real zEnd, @Nonnull War3Real density, @Nonnull Color color) {
		setType(type);
		setZStart(zStart);
		setZEnd(zEnd);
		setDensity(density);
		setColor(color);
	}

	@Override
	public DataType decode(Object val) {
		// TODO
		return null;
	}

	@Override
	public Object toSLKVal() {
		// TODO
		return null;
	}

	@Override
	public Object toTXTVal() {
		// TODO
		return null;
	}
}
