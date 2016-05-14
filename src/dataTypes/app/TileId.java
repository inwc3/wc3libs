package dataTypes.app;

import misc.Id;
import misc.ObjId;

public class TileId extends ObjId {
	protected TileId(String idString) {
		super(idString);
	}
	
	public static TileId valueOf(Id val) {
		return new TileId(val.toString());
	}
}
