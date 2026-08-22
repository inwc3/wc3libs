package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import java.util.Objects;

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

	/**
	 * Consistent with {@link #equals(Object)}, which this class overrides
	 * without having overridden this: instances that compare equal hashed
	 * differently, so a set or map key made of them did not work.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(getX(), getY(), getZ());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Coords3DI)
			return equals((Coords3DI) other);

		return super.equals(other);
	}

	/**
	 * The components are {@link War3Int} objects, not ints. Comparing them with
	 * {@code ==} compared references, and each coordinate holds its own, so no
	 * two separately built instances were ever equal.
	 */
	public boolean equals(Coords3DI other) {
		return Objects.equals(getX(), other.getX()) &&
				Objects.equals(getY(), other.getY()) &&
				Objects.equals(getZ(), other.getZ());
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
