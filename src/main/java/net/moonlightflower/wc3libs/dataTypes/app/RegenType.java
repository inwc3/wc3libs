package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegenType extends Wc3String {
    private final static Map<String, RegenType> _nameMap = new LinkedHashMap<>();
    private final static Map<Integer, RegenType> _indexMap = new LinkedHashMap<>();

	public final static RegenType ALWAYS = new RegenType(1, "always");
	public final static RegenType BLIGHT = new RegenType(2, "blight");
	public final static RegenType DAY = new RegenType(3, "day");
	public final static RegenType NIGHT = new RegenType(4, "night");
	public final static RegenType NONE = new RegenType(0, "none");

	@Override
	public boolean equals(Object other) {
		if (other instanceof RegenType)
			return equals((RegenType) other);

		return super.equals(other);
	}

	public boolean equals(RegenType other) {
		return getVal().equals(other.getVal());
	}

	private RegenType(int index, String name) {
		super(name);

		_indexMap.put(index, this);
        _nameMap.put(name, this);
	}

    @Nullable
    public static RegenType valueOf(int index) {
        return _indexMap.get(index);
    }

	@Nullable
	public static RegenType valueOf(String name) {
		return _nameMap.get(name);
	}
	
	@Override
	public RegenType decode(Object val) {
		return valueOf(val.toString());
	}
}
