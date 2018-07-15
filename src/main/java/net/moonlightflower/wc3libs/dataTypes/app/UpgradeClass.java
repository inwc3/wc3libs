package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;

public class UpgradeClass extends War3String {
	private final static Map<String, UpgradeClass> _nameMap = new LinkedHashMap<>();

	public final static UpgradeClass ARMOR = new UpgradeClass("armor");
	public final static UpgradeClass ARTILLERY = new UpgradeClass("artillery");
	public final static UpgradeClass CASTER = new UpgradeClass("caster");
	public final static UpgradeClass MELEE = new UpgradeClass("melee");
	public final static UpgradeClass RANGED = new UpgradeClass("ranged");

	@Override
	public boolean equals(Object other) {
		if (other instanceof UpgradeClass)
			return equals((UpgradeClass) other);

		return super.equals(other);
	}

	public boolean equals(UpgradeClass other) {
		return getVal().equals(other.getVal());
	}

	private UpgradeClass(@Nonnull String name) {
		super(name);

		_nameMap.put(name, this);
	}
	
	public static UpgradeClass valueOf(@Nonnull String name) {
		return _nameMap.get(name);
	}

	@Override
	public UpgradeClass decode(Object val) {
		return UpgradeClass.valueOf(val.toString());
	}
}
