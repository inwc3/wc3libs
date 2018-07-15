package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefType extends War3String {
	private final static Map<Integer, DefType> _indexMap = new LinkedHashMap<>();
	private final static Map<String, DefType> _nameMap = new LinkedHashMap<>();

	public final static DefType DIVINE = new DefType(6, "divine");
	public final static DefType FORT = new DefType(3, "fort");
	public final static DefType HERO = new DefType(5, "hero");
	public final static DefType LARGE = new DefType(2, "large");
	public final static DefType MEDIUM = new DefType(1, "medium");
	public final static DefType NONE = new DefType(7, "none");
	public final static DefType NORMAL = new DefType(4, "normal");
	public final static DefType SMALL = new DefType(0, "small");

	@Override
	public boolean equals(Object other) {
		if (other instanceof DefType)
			return equals((DefType) other);

		return super.equals(other);
	}

	public boolean equals(DefType other) {
		return getVal().equals(other.getVal());
	}

	private DefType(int val, @Nonnull String name) {
		super(name);

		_indexMap.put(val, this);
		_nameMap.put(name, this);
	}

	@Nullable
	public static DefType valueOf(int index) {
		return _indexMap.get(index);
	}

	@Nullable
	public static DefType valueOf(@Nonnull String name) {
		return _nameMap.get(name);
	}

	@Override
	public DefType decode(Object val) {
		return _nameMap.get(val.toString());
	}
}
