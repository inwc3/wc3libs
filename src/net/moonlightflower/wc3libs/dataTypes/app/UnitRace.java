package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class UnitRace extends Wc3String {
	public static String getTranslatorSection() {
		return "unitRace";
	}
	
	private static Map<String, UnitRace> _map = new HashMap<>();
	
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
	
	public UnitRace(String val) {
		super(val);
		
		_map.put(val, this);
	}

	public static UnitRace valueOf(String val) {
		return _map.get(val);
	}
	
	public static UnitRace decodeStatic(Object val) {
		return valueOf(val.toString());
	}
}
