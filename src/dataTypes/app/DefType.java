package dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class DefType extends Wc3String {
	public final static DefType DIVINE = new DefType(6, "divine");
	public final static DefType FORT = new DefType(3, "fort");
	public final static DefType HERO = new DefType(5, "hero");
	public final static DefType LARGE = new DefType(2, "large");
	public final static DefType MEDIUM = new DefType(1, "medium");
	public final static DefType NONE = new DefType(7, "none");
	public final static DefType NORMAL = new DefType(4, "normal");
	public final static DefType SMALL = new DefType(0, "small");
	
	private Map<String, DefType> _map = new HashMap<>();
	
	public DefType(int val, String name) {
		super(name);
	}

	@Override
	public DefType decode(Object val) {
		return _map.get(val.toString());
	}
}
