package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tileset extends Char {
	private static Map<Character, Tileset> _map = new LinkedHashMap<>();
	
	public final static Tileset ASHENVALE = new Tileset('A', "ASHENVALE");
	public final static Tileset BARRENS = new Tileset('B', "BARRENS");
	public final static Tileset FELWOOD = new Tileset('C', "FELWOOD");
	public final static Tileset DUNGEON = new Tileset('D', "DUNGEON");
	public final static Tileset LORDAERON_FALL = new Tileset('F', "LORDAERON_FALL");
	public final static Tileset UNDERGROUND = new Tileset('G', "DUNGEON2");
	public final static Tileset LORDAERON_SUMMER = new Tileset('L', "LORDAERON_SUMMER");
	public final static Tileset NORTHREND = new Tileset('N', "NORTHREND");
	public final static Tileset VILLAGE_FALL = new Tileset('Q', "VILLAGEFALL");
	public final static Tileset VILLAGE = new Tileset('V', "VILLAGE");
	public final static Tileset LORDAERON_WINTER = new Tileset('W', "LORDAERON_WINTER");
	public final static Tileset DALARAN = new Tileset('X', "DALARAN");
	public final static Tileset CITYSCAPE = new Tileset('Y', "CITYSCAPE");
	public final static Tileset SUNKEN_RUINS = new Tileset('Z', "RUINS");
	public final static Tileset ICECROWN = new Tileset('I', "ICECROWN");
	public final static Tileset DALARAN_RUINS = new Tileset('J', "DALARANRUINS");
	public final static Tileset OUTLAND = new Tileset('O', "OUTLAND");
	public final static Tileset BLACK_CITADEL = new Tileset('K', "BLACKCITADEL");
	
	public final static Tileset ALL = new Tileset('*', "ALL");
	
	private String _label;
	
	@Override
	public String toString() {
		return _label;
	}
	
	private Tileset(char val, String label) {
		super(val);
		
		_map.put(val, this);
		
		_label = String.format("WESTRING_LOCALE_%s", label);
	}
	
	public static Tileset valueOf(char val) {
		return _map.get(val);
	}
	
	@Override
	public Tileset decode(Object val) {
		return valueOf(val.toString().charAt(0));
	}
}
