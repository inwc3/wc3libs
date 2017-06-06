package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.LinkedHashMap;
import java.util.Map;

public class TerrainFogType extends Int {
	private static Map<Integer, TerrainFogType> _map = new LinkedHashMap<>();
	
	public final static TerrainFogType EXP = new TerrainFogType(2);
	public final static TerrainFogType EXP2 = new TerrainFogType(3);
	public final static TerrainFogType LINEAR = new TerrainFogType(1);
	public final static TerrainFogType NONE = new TerrainFogType(0);
	
	private TerrainFogType(int val) {
		super(val);
		
		_map.put(val, this);
	}
	
	public static TerrainFogType valueOf(Integer val) {
		return _map.get(val);
	}
}
