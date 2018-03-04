package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;

public class CliffId extends ObjId {
	protected CliffId(String idString) {
		super(idString);
	}

	@Nonnull
	public static CliffId valueOf(Id val) {
		return new CliffId(val.toString());
	}
}
