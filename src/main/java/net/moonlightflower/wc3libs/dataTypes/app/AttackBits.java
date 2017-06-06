package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttackBits extends Int {
	public static String getTranslatorSection() {
		return "attackBits";
	}
	
	private static Map<Integer, AttackBits> _map = new LinkedHashMap<>();
	
	public final static AttackBits NONE = new AttackBits(0x0);
	public final static AttackBits FIRST = new AttackBits(0x1);
	public final static AttackBits SECOND = new AttackBits(0x2);
	
	private AttackBits(int val) {
		super(val);
		
		_map.put(val, this);
	}
	
	public static AttackBits valueOf(int val) {
		return _map.get(val);
	}
	
	public static AttackBits decodeStatic(Object val) {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (NumberFormatException e) {
		}
		
		return null;
	}
}
