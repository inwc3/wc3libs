package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class Coords4DF extends DataType {
	private final Real _x;
	private final Real _y;
	private final Real _z;
	private final Real _a;

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

	@Nonnull
	public Real getA() {
		return _a;
	}

	@Override
	public String toString() {
		return "[" + getX() + ";" + getY() + ";" + getZ() + ";" + getA() + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Coords4DF)
			return equals((Coords4DF) other);

		return super.equals(other);
	}

	public boolean equals(Coords4DF other) {
		return getX().equals(other.getX()) &&
				getY().equals(other.getY()) &&
				getZ().equals(other.getZ()) &&
				getA().equals(other.getA());
	}

	public Coords4DF(@Nonnull Real x, @Nonnull Real y, @Nonnull Real z, @Nonnull Real a) {
		_x = x;
		_y = y;
		_z = z;
		_a = a;
	}

	public Coords4DF(float x, float y, float z, float a) {
		this(Real.valueOf(x), Real.valueOf(y), Real.valueOf(z), Real.valueOf(a));
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
