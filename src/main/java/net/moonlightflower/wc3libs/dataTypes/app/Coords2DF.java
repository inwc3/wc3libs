package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class Coords2DF extends DataType {
	private Real _x;
	private Real _y;

	@Nonnull
	public Real getX() {
		return _x;
	}

	@Nonnull
	public Real getY() {
		return _y;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Coords2DF)
			return equals((Coords2DF) other);

		return super.equals(other);
	}

	public boolean equals(Coords2DF other) {
		return getX().equals(other.getX()) &&
				getY().equals(other.getY());
	}

	@Override
	public String toString() {
		return String.format("%s;%s", getX(), getY());
	}
	
	/*public Coords2D<Real> toReal() {
		return new Coords2D<>(Real.valueOf(getX()), Real.valueOf(getY()));
	}*/
	
	public Coords2DF scale(double factor) {
		Double x = getX().toFloat() * factor;
		Double y = getY().toFloat() * factor;
		
		return new Coords2DF(x.intValue(), y.intValue());
	}
	
	public Coords2DF(float x, float y) {
		_x = Real.valueOf(x);
		_y = Real.valueOf(y);
	}
	
	public Coords2DF(@Nonnull Real x, @Nonnull Real y) {
		this(x.toFloat(), y.toFloat());
	}

	@Override
	public int hashCode() {
		int result = _x != null ? _x.hashCode() : 0;
		result = 31 * result + (_y != null ? _y.hashCode() : 0);
		return result;
	}

	@Override
	public DataType decode(Object val) {
		return null;
	}

	public static DataType decodeStatic(Object val) {
		if (val instanceof Coords2DF)
			return new Coords2DF(((Coords2DF) val).getX(), ((Coords2DF) val).getY());

		return null;
	}

	@Override
	public Object toSLKVal() {
		return null;
	}

	@Override
	public Object toTXTVal() {
		return null;
	}
}
