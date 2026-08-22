package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Coords2DI extends DataType {
	private final int _x;
	private final int _y;
	
	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}
	
	/**
	 * Consistent with {@link #equals(Object)}, which this class overrides
	 * without having overridden this: instances that compare equal hashed
	 * differently, so a set or map key made of them did not work.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY());
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
		return new Coords2DF(War3Real.valueOf(getX()), War3Real.valueOf(getY()));
	}

	@Nonnull
	public Coords2DI scale(double factor) {
		double x = getX() * factor;
		double y = getY() * factor;
		
		return new Coords2DI((int) x, (int) y);
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
