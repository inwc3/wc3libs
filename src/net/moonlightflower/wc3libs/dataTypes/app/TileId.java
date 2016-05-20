package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

public class TileId extends ObjId {
	protected TileId(String idString) {
		super(idString);
	}
	
	public static TileId valueOf(Id val) {
		return new TileId(val.toString());
	}
}
