package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.Stringable;
import net.moonlightflower.wc3libs.misc.ObjId;

public class WeatherId extends ObjId {	
	protected WeatherId(String idString) {
		super(idString);
	}

	public static WeatherId valueOf(String id) {
		return new WeatherId(id);
	}
	
	public static WeatherId valueOf(Stringable id) {
		return new WeatherId(id.toString());
	}
	
	@Override
	public WeatherId decode(Object val) {
		return valueOf(val.toString());
	}

	public static WeatherId decodeStatic(Object val) {
		if (val == null) return null;

		return valueOf(val.toString());
	}
}
