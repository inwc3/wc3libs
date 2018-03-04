package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class AttackType extends Wc3String {
	private final static Map<Integer, AttackType> _indexMap = new LinkedHashMap<>();
	private final static Map<String, AttackType> _nameMap = new LinkedHashMap<>();

	public final static AttackType CHAOS = new AttackType(5, "chaos");
	public final static AttackType HERO = new AttackType(7, "hero");
	public final static AttackType MAGIC = new AttackType(6, "magic");
	public final static AttackType NORMAL = new AttackType(1, "normal");
	public final static AttackType PIERCE = new AttackType(2, "pierce");
	public final static AttackType SPELLS = new AttackType(4, "spells");
	public final static AttackType SIEGE = new AttackType(3, "siege");
	public final static AttackType UNKNOWN = new AttackType(0, "unknown");
	
	public AttackType(int index, @Nonnull String name) {
		super(name);

		_indexMap.put(index, this);
		_nameMap.put(name, this);
	}

	@Nullable
	public static AttackType valueOf(@Nonnull Integer index) {
		return _indexMap.get(index);
	}

	@Nullable
	public static AttackType valueOf(@Nonnull String name) {
		return _nameMap.get(name);
	}

	@Override
	public AttackType decode(Object val) {
		if (val != null)
			return _nameMap.get(val.toString());

		return null;
	}
}
