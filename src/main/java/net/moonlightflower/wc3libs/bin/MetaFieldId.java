package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;

public class MetaFieldId extends Id {	
	protected MetaFieldId(@Nonnull String idString) {
		super(idString);
	}
	
	static public MetaFieldId valueOf(@Nonnull String idString) {
		return new MetaFieldId(idString);
	}
	
	public static MetaFieldId valueOf(@Nonnull Id id) {
		return valueOf(id.toString());
	}
}