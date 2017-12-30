package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;

public class MetaFieldId extends FieldId {
	protected MetaFieldId(@Nonnull String idString) {
		super(idString);
	}

	public static MetaFieldId valueOf(@Nonnull String idString) {
		return new MetaFieldId(idString);
	}

	public static MetaFieldId valueOf(@Nonnull Id id) {
		return valueOf(id.toString());
	}
}
