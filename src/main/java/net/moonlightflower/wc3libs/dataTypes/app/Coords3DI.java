package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;

public class Coords3DI {
	private Int _x;
	private Int _y;
	private Int _z;

	@Nonnull
	public Int getX() {
		return _x;
	}

	@Nonnull
	public Int getY() {
		return _y;
	}

	@Nonnull
	public Int getZ() {
		return _z;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Coords3DI)
			return equals((Coords3DI) other);

		return super.equals(other);
	}

	public boolean equals(Coords3DI other) {
		return getX() == other.getX() &&
				getY() == other.getY() &&
				getZ() == other.getZ();
	}

	public Coords3DI(@Nonnull Int x, @Nonnull Int y, @Nonnull Int z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public Coords3DI(int x, int y, int z) {
		this(Int.valueOf(x), Int.valueOf(y), Int.valueOf(z));
	}
}
