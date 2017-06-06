package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlendMode extends Int {
	private static Map<Integer, BlendMode> _map = new LinkedHashMap<>();
	
	private BlendMode(int val) {
		super(val);
	}
	
	public static BlendMode valueOf(int val) {
		return _map.get(val);
	}
}
