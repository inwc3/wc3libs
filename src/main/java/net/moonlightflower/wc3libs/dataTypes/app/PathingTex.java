package net.moonlightflower.wc3libs.dataTypes.app;

public class PathingTex extends War3String {
	public PathingTex(String stringVal, String... aliases) {
		super(stringVal, aliases);
	}

	@Override
	public PathingTex decode(Object val) {
		return new PathingTex(val.toString());
	}

}
