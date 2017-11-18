package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import java.util.LinkedHashMap;
import java.util.Map;

public class UnitRace extends Wc3String {
	public static String getTranslatorSection() {
		return "unitRace";
	}
	
	private static Map<String, UnitRace> _map = new LinkedHashMap<>();
	
	public final static UnitRace HUMAN = new UnitRace("human");
	public final static UnitRace ORC = new UnitRace("orc");
	public final static UnitRace UNDEAD = new UnitRace("undead");
	public final static UnitRace NIGHT_ELF = new UnitRace("nightelf");
	public final static UnitRace DEMON = new UnitRace("demon");
	public final static UnitRace CREEPS = new UnitRace("creeps");
	public final static UnitRace CRITTERS = new UnitRace("critters");
	public final static UnitRace OTHER = new UnitRace("other");
	public final static UnitRace COMMONER = new UnitRace("commoner");
	public final static UnitRace NAGA = new UnitRace("naga");
	public final static UnitRace UNKNOWN = new UnitRace("unknown");

	@Override
	public boolean equals(Object other) {
		if (other instanceof UnitRace)
			return equals((UnitRace) other);

		return super.equals(other);
	}

	public boolean equals(UnitRace other) {
		return getVal().equals(other.getVal());
	}

	public UnitRace(@Nonnull String val) {
		super(val);
		
		_map.put(val, this);
	}

	public static UnitRace valueOf(@Nonnull String val) {
		return _map.get(val);
	}
	
	public static UnitRace decodeStatic(Object val) {
		return valueOf(val.toString());
	}
}
