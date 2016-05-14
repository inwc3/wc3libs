package dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class BlendMode extends Int {
	private static Map<Integer, BlendMode> _map = new HashMap<>();
	
	private BlendMode(int val) {
		super(val);
	}
	
	public static BlendMode valueOf(int val) {
		return _map.get(val);
	}
}
