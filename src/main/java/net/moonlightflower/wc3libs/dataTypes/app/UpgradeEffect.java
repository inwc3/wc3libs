package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.misc.Id;

public class UpgradeEffect extends Id {
	public static UpgradeEffect ABIL_LEVEL_UP = new UpgradeEffect("rlev");
	public static UpgradeEffect ARMOR_CHANGE = new UpgradeEffect("rart");
	public static UpgradeEffect ARMOR_UP_USE = new UpgradeEffect("rarm");
	public static UpgradeEffect AURA_DATA_UP = new UpgradeEffect("raud");
	public static UpgradeEffect ATTACK_SPILL_DIST_BONUS = new UpgradeEffect("rasd");
	public static UpgradeEffect ATTACK_SPILL_RAD_BONUS = new UpgradeEffect("rasr");
	public static UpgradeEffect ATTACK_DMG_UP = new UpgradeEffect("ratx");
	public static UpgradeEffect ATTACK_DMG_LOSS_UP = new UpgradeEffect("radl");
	public static UpgradeEffect ATTACK_ENABLE = new UpgradeEffect("renw");
	public static UpgradeEffect ATTACK_ENABLE_ROOT = new UpgradeEffect("rroo");
	public static UpgradeEffect ATTACK_ENABLE_UNROOT = new UpgradeEffect("ruro");
	public static UpgradeEffect ATTACK_RANGE_UP = new UpgradeEffect("ratr");
	public static UpgradeEffect ATTACK_SPEED_UP_PERC = new UpgradeEffect("rats");
	public static UpgradeEffect ATTACK_TARGS_COUNT_UP = new UpgradeEffect("ratc");
	public static UpgradeEffect ATTACK_UP_DICE = new UpgradeEffect("ratd");
	public static UpgradeEffect ATTACK_UP_USE = new UpgradeEffect("ratt");
	public static UpgradeEffect HARVEST_GOLD_UP = new UpgradeEffect("rmin");
	public static UpgradeEffect HARVEST_GOLD_UP_ENTANGLED = new UpgradeEffect("rent");
	public static UpgradeEffect HARVEST_LUMBER_UP = new UpgradeEffect("rlum");
	public static UpgradeEffect LIFE_MAX = new UpgradeEffect("rhpx");
	public static UpgradeEffect LIFE_MAX_PERC = new UpgradeEffect("rhpo");
	public static UpgradeEffect LIFE_REGEN = new UpgradeEffect("rhpr");
	public static UpgradeEffect MAGIC_IMMUNITY = new UpgradeEffect("rmim");
	public static UpgradeEffect MANA_MAX = new UpgradeEffect("rmnx");
	public static UpgradeEffect MANA_MAX_PERC = new UpgradeEffect("rmax");
	public static UpgradeEffect MANA_REGEN = new UpgradeEffect("rmnr");
	public static UpgradeEffect MOVE_SPEED_UP = new UpgradeEffect("rmvx");
	public static UpgradeEffect MOVE_SPEED_UP_PERC = new UpgradeEffect("rmov");
	public static UpgradeEffect RAISE_DEAD_DURATION_UP = new UpgradeEffect("rrai");
	public static UpgradeEffect SIGHT_RANGE_UP = new UpgradeEffect("rsig");
	public static UpgradeEffect SPIKE_BARRIER = new UpgradeEffect("rspi");
	public static UpgradeEffect ULTRA_VISION = new UpgradeEffect("rauv");
	public static UpgradeEffect UNIT_AVAIL_CHANGE = new UpgradeEffect("rtma");
	
	private UpgradeEffect(String val) {
		super(val);
	}
	
	public static UpgradeEffect valueOf(String val) {
		return new UpgradeEffect(val);
	}

	@Override
	public UpgradeEffect decode(Object val) {
		return valueOf(val.toString());
	}
}
