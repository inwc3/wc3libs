package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.LinkedHashMap;
import java.util.Map;

public class TerrainFogType extends War3Int {
	private static Map<Integer, TerrainFogType> _indexMap = new LinkedHashMap<>();
	
	public final static TerrainFogType EXP = new TerrainFogType(2);
	public final static TerrainFogType EXP2 = new TerrainFogType(3);
	public final static TerrainFogType LINEAR = new TerrainFogType(1);
	public final static TerrainFogType NONE = new TerrainFogType(0);

	/**
	 * Consistent with {@link #equals(Object)}, which this class overrides
	 * without having overridden this: instances that compare equal hashed
	 * differently, so a set or map key made of them did not work.
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(getVal());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof TerrainFogType terrainFogType)
			return equals(terrainFogType);

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
