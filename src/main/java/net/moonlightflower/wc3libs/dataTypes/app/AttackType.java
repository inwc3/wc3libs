package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class AttackType extends Wc3String {
	public final static AttackType CHAOS = new AttackType(5, "chaos");
	public final static AttackType HERO = new AttackType(7, "hero");
	public final static AttackType MAGIC = new AttackType(6, "magic");
	public final static AttackType NORMAL = new AttackType(1, "normal");
	public final static AttackType PIERCE = new AttackType(2, "pierce");
	public final static AttackType SPELLS = new AttackType(4, "spells");
	public final static AttackType SIEGE = new AttackType(3, "siege");
	public final static AttackType UNKNOWN = new AttackType(0, "unknown");
	
	private Map<String, AttackType> _map = new HashMap<>();
	
	public AttackType(int val, String name) {
		super(name);
	}

	@Override
	public AttackType decode(Object val) {
		return _map.get(val.toString());
	}
}
