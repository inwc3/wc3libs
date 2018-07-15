package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArmorSound extends War3String {
	private final static Map<String, ArmorSound> _nameMap = new LinkedHashMap<>();

	public final static ArmorSound ETHEREAL = new ArmorSound("Ethereal");
	public final static ArmorSound FLESH = new ArmorSound("Flesh");
	public final static ArmorSound METAL = new ArmorSound("Metal");
	public final static ArmorSound STONE = new ArmorSound("Stone");
	public final static ArmorSound WOOD = new ArmorSound("Wood");
	
	public ArmorSound(@Nonnull String name) {
		super(name);

		_nameMap.put(name, this);
	}

	@Nullable
	public static ArmorSound valueOf(String name) {
		return _nameMap.get(name);
	}

	@Override
	public ArmorSound decode(Object val) {
		return new ArmorSound(val.toString());
	}
}
