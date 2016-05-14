package dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class TerrainFogType extends Int {
	public final static TerrainFogType EXP = new TerrainFogType(2);
	public final static TerrainFogType EXP2 = new TerrainFogType(3);
	public final static TerrainFogType LINEAR = new TerrainFogType(1);
	public final static TerrainFogType NONE = new TerrainFogType(0);
	
	private static Map<Integer, TerrainFogType> _map = new HashMap<>();
	
	public TerrainFogType(int val) {
		super(val);
		
		_map.put(val, this);
	}
	
	public static TerrainFogType valueOf(int val) {
		return _map.get(val);
	}
}
