package net.moonlightflower.wc3libs.dataTypes.app;

public class AttributeType extends Wc3String {
	public final static AttributeType AGI = new AttributeType("AGI");
	public final static AttributeType INT = new AttributeType("INT");
	public final static AttributeType STR = new AttributeType("STR");
	
	public AttributeType(String name) {
		super(name);
	}

	@Override
	public AttributeType decode(Object val) {
		return new AttributeType(val.toString());
	}
}
