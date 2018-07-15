package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CombatTarget extends War3String {
	private final static Map<String, CombatTarget> _map = new LinkedHashMap<>();

	public final static CombatTarget AIR = new CombatTarget("air");
	public final static CombatTarget ALIVE = new CombatTarget("alive", "aliv");
	public final static CombatTarget ALLY = new CombatTarget("allies", "alli", "ally");
	public final static CombatTarget ANCIENT = new CombatTarget("ancient");
	public final static CombatTarget BRIDGE = new CombatTarget("bridge");
	public final static CombatTarget DEAD = new CombatTarget("dead");
	public final static CombatTarget DEBRIS = new CombatTarget("debris", "debr");
	public final static CombatTarget DECORATION = new CombatTarget("decoration", "deco");
	public final static CombatTarget ENEMY = new CombatTarget("enemies", "enem", "enemy");
	public final static CombatTarget FRIEND = new CombatTarget("friend", "frie");
	public final static CombatTarget GROUND = new CombatTarget("ground", "grou");
	public final static CombatTarget HERO = new CombatTarget("hero");
	public final static CombatTarget INVUL = new CombatTarget("invu", "invulnerable");
	public final static CombatTarget ITEM = new CombatTarget("item");
	public final static CombatTarget MECH = new CombatTarget("mechanical", "mech");
	public final static CombatTarget NEUTRAL = new CombatTarget("neutral", "neut");
	public final static CombatTarget NON_ANCIENT = new CombatTarget("nonancient");
	public final static CombatTarget NON_HERO = new CombatTarget("nonhero", "nonh");
	public final static CombatTarget NON_SAPPER = new CombatTarget("nonsapper");
	public final static CombatTarget NONE = new CombatTarget("none");
	public final static CombatTarget NOT_SELF = new CombatTarget("notself", "nots");
	public final static CombatTarget ORGANIC = new CombatTarget("organic", "orga");
	public final static CombatTarget PLAYER = new CombatTarget("player", "play");
	public final static CombatTarget SAPPER = new CombatTarget("sapper");
	public final static CombatTarget SELF = new CombatTarget("self");
	public final static CombatTarget STRUCTURE = new CombatTarget("structure", "stru");
	public final static CombatTarget TERRAIN = new CombatTarget("terrain", "terr");
	public final static CombatTarget TREE = new CombatTarget("tree");
	public final static CombatTarget VUL = new CombatTarget("vulnerable", "vuln");
	public final static CombatTarget WALL = new CombatTarget("wall");
	public final static CombatTarget WARD = new CombatTarget("ward");

	@Override
	public boolean equals(Object other) {
		if (other instanceof CombatTarget)
			return equals((CombatTarget) other);

		return super.equals(other);
	}

	public boolean equals(CombatTarget other) {
		return getVal().equals(other.getVal());
	}

	@Nullable
	public static CombatTarget valueOf(@Nonnull String val) {
		return _map.get(val);
	}
	
	public CombatTarget(String name, String... aliases) {
		super(name);
		
		_map.put(name, this);
		
		for (String alias : aliases) {
			_map.put(alias, this);
		}
	}

	@Override
	public CombatTarget decode(Object val) {
		return valueOf(val.toString());
	}
}
