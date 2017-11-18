package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class DeathType extends Int {
	public static String getTranslatorSection() {
		return "deathType";
	}
	
	private static Map<Integer, DeathType> _map = new LinkedHashMap<>();
	
	public final static DeathType NONE = new DeathType(0x0);
	public final static DeathType RAISE = new DeathType(0x1);
	public final static DeathType DECAYS = new DeathType(0x2);

	@Override
	public boolean equals(Object other) {
		if (other instanceof DeathType)
			return equals((DeathType) other);

		return super.equals(other);
	}

	public boolean equals(DeathType other) {
		return getVal().equals(other.getVal());
	}

	private DeathType(int val) {
		super(val);
		
		_map.put(val, this);
	}

	@Nullable
	public static DeathType valueOf(int val) {
		return _map.get(val);
	}
	
	public static DeathType decodeStatic(Object val) {
		if (val != null)
			try {
				return valueOf(Integer.parseInt(val.toString()));
			} catch (NumberFormatException e) {
			}
		
		return null;
	}
}
