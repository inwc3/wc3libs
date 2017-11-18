package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;

public class WeaponType extends Wc3String {
	public final static WeaponType ARTILLERY = new WeaponType("artillery");
	public final static WeaponType ARTILLERY_LINE = new WeaponType("aline");
	public final static WeaponType INSTANT = new WeaponType("instant");
	public final static WeaponType MISSILE = new WeaponType("missile");
	public final static WeaponType MISSILE_SPLASH = new WeaponType("msplash");
	public final static WeaponType MISSILE_BOUNCE = new WeaponType("mbounce");
	public final static WeaponType MISSILE_LINE = new WeaponType("mline");
	public final static WeaponType NORMAL = new WeaponType("normal");

	@Override
	public boolean equals(Object other) {
		if (other instanceof WeaponType)
			return equals((WeaponType) other);

		return super.equals(other);
	}

	public boolean equals(WeaponType other) {
		return getVal().equals(other.getVal());
	}

	public WeaponType(@Nonnull String name) {
		super(name);
	}

	@Nonnull
	public static WeaponType valueOf(@Nonnull String val) {
		return new WeaponType(val);
	}
	
	@Override
	public WeaponType decode(Object val) {
		return valueOf(val.toString());
	}
}
