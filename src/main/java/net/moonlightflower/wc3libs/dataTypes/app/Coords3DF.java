package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class Coords3DF extends DataType {
	private Real _x;
	private Real _y;
	private Real _z;

	@Nonnull
	public Real getX() {
		return _x;
	}

	@Nonnull
	public Real getY() {
		return _y;
	}

	@Nonnull
	public Real getZ() {
		return _z;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Coords3DF)
			return equals((Coords3DF) other);

		return super.equals(other);
	}

	public boolean equals(Coords3DF other) {
		return getX().equals(other.getX()) &&
				getY().equals(other.getY()) &&
				getZ().equals(other.getZ());
	}

	public Coords3DF(@Nonnull Real x, @Nonnull Real y, @Nonnull Real z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public Coords3DF(float x, float y, float z) {
		this(Real.valueOf(x), Real.valueOf(y), Real.valueOf(z));
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
