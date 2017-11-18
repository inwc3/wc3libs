package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpellDetail extends Int {
	private static Map<Integer, SpellDetail> _map = new LinkedHashMap<>();

	public final static SpellDetail LOW = new SpellDetail(0);
	public final static SpellDetail MEDIUM = new SpellDetail(1);
	public final static SpellDetail HIGH = new SpellDetail(2);

	@Override
	public boolean equals(Object other) {
		if (other instanceof SpellDetail)
			return equals((SpellDetail) other);

		return super.equals(other);
	}

	public boolean equals(SpellDetail other) {
		return getVal().equals(other.getVal());
	}

	public SpellDetail(int val) {
		super(val);
		
		_map.put(val, this);
	}

	@Nullable
	public static SpellDetail valueOf(int val) {
		return _map.get(val);
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
