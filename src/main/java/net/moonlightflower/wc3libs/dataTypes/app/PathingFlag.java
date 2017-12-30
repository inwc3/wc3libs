package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class PathingFlag extends Wc3Int {
	public static class Ints {
		public final static Integer NONE = 0x0;
		public final static Integer UNKNOWN = 0x1;
		public final static Integer NO_WALK = 0x2;
		public final static Integer NO_FLY = 0x4;
		public final static Integer NO_BUILD = 0x8;
		public final static Integer UNKNOWN2 = 0x10;
		public final static Integer BLIGHT = 0x20;
		public final static Integer NO_WATER = 0x40;
		public final static Integer UNKNOWN3 = 0x80;
	}
	
	private final static Map<Object, PathingFlag> _map = new LinkedHashMap<>();
	
	public final static PathingFlag BLIGHT = new PathingFlag("blighted", Ints.UNKNOWN);
	public final static PathingFlag UNAMPH = new PathingFlag("unamph", Ints.NONE);
	public final static PathingFlag UNBUILD = new PathingFlag("unbuildable", Ints.NO_BUILD);
	public final static PathingFlag UNFLOAT = new PathingFlag("unfloat", Ints.NONE);
	public final static PathingFlag UNFLY = new PathingFlag("unflyable", Ints.NO_FLY);
	public final static PathingFlag UNWALK = new PathingFlag("unwalkable", Ints.NO_WALK);

	public PathingFlag(@Nonnull String alias, @Nonnull Integer val) {
		super(val);
		
		_map.put(val, this);
		_map.put(alias, this);
	}

	@Nullable
	public static PathingFlag valueOf(@Nonnull Integer val) {
		return _map.get(val);
	}

	@Override
	public PathingFlag decode(Object val) {
		return _map.get(val);
	}
}
