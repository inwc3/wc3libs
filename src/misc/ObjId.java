package misc;

import dataTypes.Stringable;

public class ObjId extends Id {
	@Override
	public Object toSLKVal() {
		return toString();
	}
	
	@Override
	public Object toTXTVal() {
		return toString();
	}
	
	protected ObjId(String idString) {
		super(idString);
	}

	public static ObjId valueOf(String idString) {
		return new ObjId(idString);
	}

	public static ObjId valueOf(Stringable id) {
		return valueOf(id.toString());
	}
}
