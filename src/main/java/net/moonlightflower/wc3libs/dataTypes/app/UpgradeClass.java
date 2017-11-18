package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;

public class UpgradeClass extends Wc3String {
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

	private UpgradeClass(@Nonnull String val) {
		super(val);
	}
	
	public static UpgradeClass valueOf(@Nonnull String val) {
		return new UpgradeClass(val);
	}

	@Override
	public UpgradeClass decode(Object val) {
		return UpgradeClass.valueOf(val.toString());
	}
}
