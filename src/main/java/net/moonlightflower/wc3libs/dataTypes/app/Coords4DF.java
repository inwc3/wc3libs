package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Coords4DF extends DataType {
	private final War3Real _x;
	private final War3Real _y;
	private final War3Real _z;
	private final War3Real _a;

	@Nonnull
	public War3Real getX() {
		return _x;
	}

	@Nonnull
	public War3Real getY() {
		return _y;
	}

	@Nonnull
	public War3Real getZ() {
		return _z;
	}

	@Nonnull
	public War3Real getA() {
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

	public Coords4DF(@Nonnull War3Real x, @Nonnull War3Real y, @Nonnull War3Real z, @Nonnull War3Real a) {
		_x = x;
		_y = y;
		_z = z;
		_a = a;
	}

	public Coords4DF(float x, float y, float z, float a) {
		this(War3Real.valueOf(x), War3Real.valueOf(y), War3Real.valueOf(z), War3Real.valueOf(a));
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

    public Coords4DF squish() {
        return new Coords4DF(Objects.requireNonNull(War3Real.valueOf(Math.floor(_x.getVal() * 1000f) / 1000f)),
            Objects.requireNonNull(War3Real.valueOf(Math.floor(_y.getVal() * 1000f) / 1000f)),
            Objects.requireNonNull(War3Real.valueOf(Math.floor(_z.getVal() * 1000f) / 1000f)),
            Objects.requireNonNull(War3Real.valueOf(Math.floor(_a.getVal() * 1000f) / 1000f)));
    }
}
