package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderString extends Wc3String {
	private final static Map<Object, OrderString> _map = new LinkedHashMap<>();
	
	public OrderString(String val, String... aliases) {
		super(val, aliases);
		
		_map.put(val, this);
		for (String alias : aliases) {
			_map.put(alias, this);
		}
	}

	@Override
	public OrderString decode(Object val) {
		return _map.get(val);
	}
}
