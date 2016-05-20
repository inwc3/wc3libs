package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class DeathType extends Int {
	public static String getTranslatorSection() {
		return "deathType";
	}
	
	private static Map<Integer, DeathType> _map = new HashMap<>();
	
	public final static DeathType NONE = new DeathType(0x0);
	public final static DeathType RAISE = new DeathType(0x1);
	public final static DeathType DECAYS = new DeathType(0x2);
	
	private DeathType(int val) {
		super(val);
		
		_map.put(val, this);
	}
	
	public static DeathType valueOf(int val) {
		return _map.get(val);
	}
	
	public static DeathType decodeStatic(Object val) {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (NumberFormatException e) {
		}
		
		return null;
	}
}
