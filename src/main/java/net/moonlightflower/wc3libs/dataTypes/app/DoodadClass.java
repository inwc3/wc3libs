package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class DoodadClass extends Wc3String {
	private final static Map<Object, DoodadClass> _map = new LinkedHashMap<>();
	
	public final static DoodadClass CINEMATIC = new DoodadClass("Z");
	public final static DoodadClass CLIFF = new DoodadClass("C");
	public final static DoodadClass ENVIRONMENT = new DoodadClass("E");
	public final static DoodadClass ITEM = new DoodadClass("O");
	public final static DoodadClass STRUCTURE = new DoodadClass("S");
	public final static DoodadClass WATER = new DoodadClass("W");

	@Override
	public boolean equals(Object other) {
		if (other instanceof DoodadClass)
			return equals((DoodadClass) other);

		return super.equals(other);
	}

	public boolean equals(DoodadClass other) {
		return getVal().equals(other.getVal());
	}

	private DoodadClass(String val) {
		super(val);
		
		_map.put(val, this);
	}

	@Nullable
	public static DoodadClass valueOf(String val) {
		return _map.get(val);
	}
	
	@Override
	public DoodadClass decode(Object val) {
		if (val != null)
			return valueOf(val.toString());

		return null;
	}
}
