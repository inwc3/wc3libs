package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;

public class WaterId extends ObjId {
	protected WaterId(String idString) {
		super(idString);
	}

	@Nonnull
	public static WaterId valueOf(Id val) {
		return new WaterId(val.toString());
	}
}
