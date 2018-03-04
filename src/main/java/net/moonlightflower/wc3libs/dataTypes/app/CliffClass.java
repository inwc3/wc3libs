package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class CliffClass extends Wc3String {
	private final static Map<String, CliffClass> _map = new LinkedHashMap<>();

	public final static CliffClass CLIFF1 = new CliffClass("c1");
	public final static CliffClass CLIFF2 = new CliffClass("c2");

	@Override
	public boolean equals(Object other) {
		if (other instanceof CliffClass)
			return equals((CliffClass) other);

		return super.equals(other);
	}

	public boolean equals(CliffClass other) {
		return getVal().equals(other.getVal());
	}

	private CliffClass(@Nonnull String name) {
		super(name);

		_map.put(name, this);
	}

	@Nullable
	public static CliffClass valueOf(@Nonnull String name) {
		return _map.get(name);
	}

	@Override
	public CliffClass decode(Object val) {
		return new CliffClass(val.toString());
	}
}
