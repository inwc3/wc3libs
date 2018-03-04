package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AttributeType extends Wc3String {
	private final static Map<String, AttributeType> _map = new LinkedHashMap<>();

	public final static AttributeType AGI = new AttributeType("AGI");
	public final static AttributeType INT = new AttributeType("INT");
	public final static AttributeType STR = new AttributeType("STR");

	@Override
	public boolean equals(Object other) {
		if (other instanceof AttributeType)
			return equals((AttributeType) other);

		return super.equals(other);
	}

	public boolean equals(AttributeType other) {
		return getVal().equals(other.getVal());
	}

	public AttributeType(@Nonnull String name) {
		super(name);

		_map.put(name, this);
	}

	@Nullable
	public static AttributeType valueOf(@Nonnull String name) {
		return _map.get(name);
	}

	@Override
	public AttributeType decode(Object val) {
		return new AttributeType(val.toString());
	}
}
