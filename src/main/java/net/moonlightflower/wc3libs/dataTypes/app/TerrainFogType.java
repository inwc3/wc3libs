package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class TerrainFogType extends Wc3Int {
	private static Map<Integer, TerrainFogType> _indexMap = new LinkedHashMap<>();
	
	public final static TerrainFogType EXP = new TerrainFogType(2);
	public final static TerrainFogType EXP2 = new TerrainFogType(3);
	public final static TerrainFogType LINEAR = new TerrainFogType(1);
	public final static TerrainFogType NONE = new TerrainFogType(0);

	@Override
	public boolean equals(Object other) {
		if (other instanceof TerrainFogType)
			return equals((TerrainFogType) other);

		return super.equals(other);
	}

	public boolean equals(TerrainFogType other) {
		return getVal().equals(other.getVal());
	}

	private TerrainFogType(int val) {
		super(val);
		
		_indexMap.put(val, this);
	}

	@Nullable
	public static TerrainFogType valueOf(Integer val) {
		return _indexMap.get(val);
	}
}
