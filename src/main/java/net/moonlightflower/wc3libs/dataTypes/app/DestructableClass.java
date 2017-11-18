package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class DestructableClass extends Wc3String {
	private final static Map<Object, DestructableClass> _map = new LinkedHashMap<>();
	
	public final static DestructableClass BRIDGE = new DestructableClass("B");
	public final static DestructableClass DESTRUCTABLE = new DestructableClass("D");
	public final static DestructableClass PATHING = new DestructableClass("P");

	@Override
	public boolean equals(Object other) {
		if (other instanceof DestructableClass)
			return equals((DestructableClass) other);

		return super.equals(other);
	}

	public boolean equals(DestructableClass other) {
		return getVal().equals(other.getVal());
	}

	private DestructableClass(@Nonnull String val) {
		super(val);
		
		_map.put(val, this);
	}

	@Nullable
	public DestructableClass valueOf(String val) {
		return _map.get(val);
	}
	
	@Override
	public DestructableClass decode(Object val) {
		return valueOf(val.toString());
	}
}
