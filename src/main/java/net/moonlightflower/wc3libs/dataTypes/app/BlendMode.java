package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlendMode extends War3Int {
	private final static Map<Integer, BlendMode> _map = new LinkedHashMap<>();
	
	private BlendMode(int val) {
		super(val);

		_map.put(val, this);
	}

	@Nullable
	public static BlendMode valueOf(int val) {
		return _map.get(val);
	}
}
