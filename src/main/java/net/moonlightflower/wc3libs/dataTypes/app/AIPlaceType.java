package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

//aiBuffer
public class AIPlaceType extends Wc3String {
	private static Map<Integer, AIPlaceType> _indexMap = new LinkedHashMap<>();
	private static Map<String, AIPlaceType> _nameMap = new LinkedHashMap<>();

	public final static AIPlaceType FACTORY = new AIPlaceType(3, "factory");
	public final static AIPlaceType GENERAL = new AIPlaceType(4, "buffer");
	public final static AIPlaceType NONE = new AIPlaceType(0, "_");
	public final static AIPlaceType RESOURCE = new AIPlaceType(2, "resource");
	public final static AIPlaceType TOWNHALL = new AIPlaceType(1, "townhall");
	
	public AIPlaceType(int index, @Nonnull String name) {
		super(name);

		_indexMap.put(index, this);
		_nameMap.put(name, this);
	}

	@Nullable
	public static AIPlaceType valueOf(@Nonnull Integer index) {
		return _indexMap.get(index);
	}

	@Nullable
	public static AIPlaceType valueOf(@Nonnull String name) {
		return _nameMap.get(name);
	}

	@Override
	public AIPlaceType decode(Object val) {
		AIPlaceType ret = _nameMap.get(val);

		if (ret == null) ret = _indexMap.get(val);

		return ret;
	}
}
