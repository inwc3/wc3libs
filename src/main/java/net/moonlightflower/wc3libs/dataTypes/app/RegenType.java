package net.moonlightflower.wc3libs.dataTypes.app;

public class RegenType extends Wc3String {
	public final static RegenType ALWAYS = new RegenType(1, "always");
	public final static RegenType BLIGHT = new RegenType(2, "blight");
	public final static RegenType DAY = new RegenType(3, "day");
	public final static RegenType NIGHT = new RegenType(4, "night");
	public final static RegenType NONE = new RegenType(0, "none");
	
	private RegenType(int val, String name) {
		super(name);
	}

	public static RegenType valueOf(String val) {
		return new RegenType(0, val);
	}
	
	@Override
	public RegenType decode(Object val) {
		return valueOf(val.toString());
	}
}
