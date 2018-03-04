package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class Coords2DI extends DataType {
	private final int _x;
	private final int _y;
	
	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Coords2DI)
			return equals((Coords2DI) other);
		
		return super.equals(other);
	}
	
	public boolean equals(Coords2DI other) {
		return getX() == (other).getX() &&
				getY() == (other).getY();
	}

	@Nonnull
	public Coords2DF toReal() {
		return new Coords2DF(Real.valueOf(getX()), Real.valueOf(getY()));
	}

	@Nonnull
	public Coords2DI scale(double factor) {
		Double x = getX() * factor;
		Double y = getY() * factor;
		
		return new Coords2DI(x.intValue(), y.intValue());
	}
	
	public Coords2DI(int x, int y) {
		_x = x;
		_y = y;
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
