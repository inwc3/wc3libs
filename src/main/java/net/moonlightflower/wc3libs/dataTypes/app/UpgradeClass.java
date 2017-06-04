package net.moonlightflower.wc3libs.dataTypes.app;

public class UpgradeClass extends Wc3String {
	public final static UpgradeClass ARMOR = new UpgradeClass("armor");
	public final static UpgradeClass ARTILLERY = new UpgradeClass("artillery");
	public final static UpgradeClass CASTER = new UpgradeClass("caster");
	public final static UpgradeClass MELEE = new UpgradeClass("melee");
	public final static UpgradeClass RANGED = new UpgradeClass("ranged");
	
	private UpgradeClass(String val) {
		super(val);
	}
	
	public static UpgradeClass valueOf(String val) {
		return new UpgradeClass(val);
	}

	@Override
	public UpgradeClass decode(Object val) {
		return UpgradeClass.valueOf(val.toString());
	}
}
