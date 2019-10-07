package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Coords3DF extends DataType {
	private final War3Real _x;
	private final War3Real _y;
	private final War3Real _z;

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

	@Override
	public String toString() {
		return String.format("Coords3DF(x=%s y=%s z=%s)", _x, _y, _z);
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

	public Coords3DF(@Nonnull War3Real x, @Nonnull War3Real y, @Nonnull War3Real z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public Coords3DF(float x, float y, float z) {
		this(War3Real.valueOf(x), War3Real.valueOf(y), War3Real.valueOf(z));
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

    public Coords3DF squish() {
        return new Coords3DF(Objects.requireNonNull(War3Real.valueOf(Math.floor(_x.getVal() * 1000f) / 1000f)),
            Objects.requireNonNull(War3Real.valueOf(Math.floor(_y.getVal() * 1000f) / 1000f)),
            Objects.requireNonNull(War3Real.valueOf(Math.floor(_z.getVal() * 1000f) / 1000f)));
    }
}
