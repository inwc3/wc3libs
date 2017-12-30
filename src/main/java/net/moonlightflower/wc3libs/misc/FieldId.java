package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;

public class FieldId extends Id {
	protected FieldId(@Nonnull String idString) {
		super(idString);
	}

	public static FieldId valueOf(@Nonnull String idString) {
		return new FieldId(idString);
	}

	public static FieldId valueOf(@Nonnull Id id) {
		return valueOf(id.toString());
	}
	
	@Override
	public FieldId lower() {
		return FieldId.valueOf(super.lower());
	}
}
