package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;

public class UnitClass extends Wc3String {
	public final UnitClass ANCIENT = new UnitClass("ancient");
	public final UnitClass GIANT = new UnitClass("giant");
	public final UnitClass MECH = new UnitClass("mechanical");
	public final UnitClass NEUTRAL = new UnitClass("neutral");
	public final UnitClass PEON = new UnitClass("peon");
	public final UnitClass SAPPER = new UnitClass("sapper");
	public final UnitClass SUMMON = new UnitClass("summoned");
	public final UnitClass TAUREN = new UnitClass("tauren");
	public final UnitClass TOWNHALL = new UnitClass("townhall");
	public final UnitClass TREE = new UnitClass("tree");
	public final UnitClass UNDEAD = new UnitClass("undead");
	public final UnitClass WALKABLE = new UnitClass("standon");
	public final UnitClass WARD = new UnitClass("ward");

	@Override
	public boolean equals(Object other) {
		if (other instanceof UnitClass)
			return equals((UnitClass) other);

		return super.equals(other);
	}

	public boolean equals(UnitClass other) {
		return getVal().equals(other.getVal());
	}

	private UnitClass(@Nonnull String val) {
		super(val);
	}
	
	public static UnitClass valueOf(@Nonnull String val) {
		return new UnitClass(val);
	}

	@Override
	public UnitClass decode(Object val) {
		return valueOf(val.toString());
	}
}
