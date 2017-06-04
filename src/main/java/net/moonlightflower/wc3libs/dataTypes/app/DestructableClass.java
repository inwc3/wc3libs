package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class DestructableClass extends Wc3String {
	private final static Map<Object, DestructableClass> _map = new HashMap<>();
	
	public final static DestructableClass BRIDGE = new DestructableClass("B");
	public final static DestructableClass DESTRUCTABLE = new DestructableClass("D");
	public final static DestructableClass PATHING = new DestructableClass("P");
	
	private DestructableClass(String val) {
		super(val);
		
		_map.put(val, this);
	}

	public DestructableClass valueOf(String val) {
		return _map.get(val);
	}
	
	@Override
	public DestructableClass decode(Object val) {
		return valueOf(val.toString());
	}
}
