package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;

public class Coords3DI {
	private final Wc3Int _x;
	private final Wc3Int _y;
	private final Wc3Int _z;

	@Nonnull
	public Wc3Int getX() {
		return _x;
	}

	@Nonnull
	public Wc3Int getY() {
		return _y;
	}

	@Nonnull
	public Wc3Int getZ() {
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

	public Coords3DI(@Nonnull Wc3Int x, @Nonnull Wc3Int y, @Nonnull Wc3Int z) {
		_x = x;
		_y = y;
		_z = z;
	}

	public Coords3DI(int x, int y, int z) {
		this(Wc3Int.valueOf(x), Wc3Int.valueOf(y), Wc3Int.valueOf(z));
	}
}
