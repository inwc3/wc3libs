package net.moonlightflower.wc3libs.misc;

public class MetaFieldId extends FieldId {	
	protected MetaFieldId(String idString) {
		super(idString);
	}

	public static MetaFieldId valueOf(String idString) {
		return new MetaFieldId(idString);
	}

	public static MetaFieldId valueOf(Id id) {
		return valueOf(id.toString());
	}
}
