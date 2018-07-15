package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;

public class Coords3DI {
	private final War3Int _x;
	private final War3Int _y;
	private final War3Int _z;

	@Nonnull
	public War3Int getX() {
		return _x;
	}

	@Nonnull
	public War3Int getY() {
		return _y;
	}

	@Nonnull
	public War3Int getZ() {
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

	public Coords3DI(@Nonnull War3Int x, @Nonnull War3Int y, @Nonnull War3Int z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public Coords3DI(int x, int y, int z) {
		this(War3Int.valueOf(x), War3Int.valueOf(y), War3Int.valueOf(z));
	}
}
