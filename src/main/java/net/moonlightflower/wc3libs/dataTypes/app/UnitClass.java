package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;

public class UnitClass extends War3String {
	private static Map<String, UnitClass> _nameMap = new LinkedHashMap<>();

	public final static UnitClass ANCIENT = new UnitClass("ancient");
	public final static UnitClass GIANT = new UnitClass("giant");
	public final static UnitClass MECH = new UnitClass("mechanical");
	public final static UnitClass NEUTRAL = new UnitClass("neutral");
	public final static UnitClass PEON = new UnitClass("peon");
	public final static UnitClass SAPPER = new UnitClass("sapper");
	public final static UnitClass SUMMON = new UnitClass("summoned");
	public final static UnitClass TAUREN = new UnitClass("tauren");
	public final static UnitClass TOWNHALL = new UnitClass("townhall");
	public final static UnitClass TREE = new UnitClass("tree");
	public final static UnitClass UNDEAD = new UnitClass("undead");
	public final static UnitClass WALKABLE = new UnitClass("standon");
	public final static UnitClass WARD = new UnitClass("ward");

	@Override
	public boolean equals(Object other) {
		if (other instanceof UnitClass)
			return equals((UnitClass) other);

		return super.equals(other);
	}

	public boolean equals(UnitClass other) {
		return getVal().equals(other.getVal());
	}

	private UnitClass(@Nonnull String name) {
		super(name);

		_nameMap.put(name, this);
	}
	
	public static UnitClass valueOf(@Nonnull String val) {
		return _nameMap.get(val);
	}

	@Override
	public UnitClass decode(Object val) {
		return valueOf(val.toString());
	}
}
