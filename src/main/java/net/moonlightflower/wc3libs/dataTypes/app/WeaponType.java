package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeaponType extends War3String {
	private final static Map<String, WeaponType> _nameMap = new LinkedHashMap<>();

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

		_nameMap.put(name, this);
	}

	@Nullable
	public static WeaponType valueOf(@Nonnull String name) {
		return _nameMap.get(name);
	}
	
	@Override
	public WeaponType decode(Object val) {
		return valueOf(val.toString());
	}
}
