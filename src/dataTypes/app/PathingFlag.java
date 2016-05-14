package dataTypes.app;

import java.util.HashMap;
import java.util.Map;

import dataTypes.DataType;

public class PathingFlag extends Int {
	public static class Ints {
		public final static Integer UNKNOWN = 0x1;
		public final static Integer NO_WALK = 0x2;
		public final static Integer NO_FLY = 0x4;
		public final static Integer NO_BUILD = 0x8;
		public final static Integer UNKNOWN2 = 0x10;
		public final static Integer BLIGHT = 0x20;
		public final static Integer NO_WATER = 0x40;
		public final static Integer UNKNOWN3 = 0x80;
	}
	
	private final static Map<Object, PathingFlag> _map = new HashMap<>();
	
	public final static PathingFlag BLIGHT = new PathingFlag("blighted", Ints.UNKNOWN);
	public final static PathingFlag UNAMPH = new PathingFlag("unamph", null);
	public final static PathingFlag UNBUILD = new PathingFlag("unbuildable", Ints.NO_BUILD);
	public final static PathingFlag UNFLOAT = new PathingFlag("unfloat", null);
	public final static PathingFlag UNFLY = new PathingFlag("unflyable", Ints.NO_FLY);
	public final static PathingFlag UNWALK = new PathingFlag("unwalkable", Ints.NO_WALK);
	
	public PathingFlag(String alias, Integer val) {
		super(val);
		
		_map.put(val, this);
		_map.put(alias, this);
	}

	@Override
	public PathingFlag decode(Object val) {
		return _map.get(val);
	}
}
