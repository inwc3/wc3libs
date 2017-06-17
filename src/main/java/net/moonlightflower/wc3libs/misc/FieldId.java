package net.moonlightflower.wc3libs.misc;

public class FieldId extends Id {
	protected FieldId(String idString) {
		super(idString);
	}

	public static FieldId valueOf(String idString) {
		return new FieldId(idString);
	}

	public static FieldId valueOf(Id id) {
		return valueOf(id.toString());
	}
	
	@Override
	public FieldId lower() {
		return FieldId.valueOf(super.lower());
	}
}
