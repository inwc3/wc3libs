package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class DoodadClass extends Wc3String {
	private final static Map<Object, DoodadClass> _map = new HashMap<>();
	
	public final static DoodadClass CINEMATIC = new DoodadClass("Z");
	public final static DoodadClass CLIFF = new DoodadClass("C");
	public final static DoodadClass ENVIRONMENT = new DoodadClass("E");
	public final static DoodadClass ITEM = new DoodadClass("O");
	public final static DoodadClass STRUCTURE = new DoodadClass("S");
	public final static DoodadClass WATER = new DoodadClass("W");
	
	private DoodadClass(String val) {
		super(val);
		
		_map.put(val, this);
	}

	public DoodadClass valueOf(String val) {
		return _map.get(val);
	}
	
	@Override
	public DoodadClass decode(Object val) {
		return valueOf(val.toString());
	}
}
