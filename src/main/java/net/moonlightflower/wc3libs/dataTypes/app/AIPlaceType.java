package net.moonlightflower.wc3libs.dataTypes.app;

import java.util.LinkedHashMap;
import java.util.Map;

//aiBuffer
public class AIPlaceType extends Wc3String {
	public final static AIPlaceType FACTORY = new AIPlaceType(3, "factory");
	public final static AIPlaceType GENERAL = new AIPlaceType(4, "buffer");
	public final static AIPlaceType NONE = new AIPlaceType(0, "_");
	public final static AIPlaceType RESOURCE = new AIPlaceType(2, "resource");
	public final static AIPlaceType TOWNHALL = new AIPlaceType(1, "townhall");	
	
	private Map<String, AIPlaceType> _map = new LinkedHashMap<>();
	
	public AIPlaceType(int val, String name) {
		super(name);
	}

	@Override
	public AIPlaceType decode(Object val) {
		return _map.get(val);
	}
}
