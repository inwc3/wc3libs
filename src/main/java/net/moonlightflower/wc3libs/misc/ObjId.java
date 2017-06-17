package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.Stringable;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;

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
