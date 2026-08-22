package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpellDetail extends War3Int {
	private static Map<Integer, SpellDetail> _indexMap = new LinkedHashMap<>();

	public final static SpellDetail LOW = new SpellDetail(0);
	public final static SpellDetail MEDIUM = new SpellDetail(1);
	public final static SpellDetail HIGH = new SpellDetail(2);

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
		if (other instanceof SpellDetail spellDetail)
			return equals(spellDetail);

		return super.equals(other);
	}

	public boolean equals(SpellDetail other) {
		return getVal().equals(other.getVal());
	}

	public SpellDetail(int val) {
		super(val);
		
		_indexMap.put(val, this);
	}

	@Nullable
	public static SpellDetail valueOf(int val) {
		return _indexMap.get(val);
	}
	
	@Override
	public SpellDetail decode(Object val) {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (Exception e) {
		}
		
		return null;
	}
}
