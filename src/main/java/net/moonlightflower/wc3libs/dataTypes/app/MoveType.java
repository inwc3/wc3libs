package net.moonlightflower.wc3libs.dataTypes.app;

public class MoveType extends Wc3String {
	public final static MoveType AMPHIBIOUS = new MoveType("amph");
	public final static MoveType FLOAT = new MoveType("float");
	public final static MoveType FLY = new MoveType("fly");
	public final static MoveType FOOT = new MoveType("foot");
	public final static MoveType HORSE = new MoveType("horse");
	public final static MoveType HOVER = new MoveType("hover");
	
	private MoveType(String val) {
		super(val);
	}

	public static MoveType valueOf(String val) {
		return new MoveType(val);
	}
	
	@Override
	public MoveType decode(Object val) {
		return valueOf(val.toString());
	}
}
