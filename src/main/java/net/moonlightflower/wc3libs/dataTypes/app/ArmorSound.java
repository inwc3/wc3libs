package net.moonlightflower.wc3libs.dataTypes.app;

public class ArmorSound extends Wc3String {
	public final static ArmorSound ETHEREAL = new ArmorSound("Ethereal");
	public final static ArmorSound FLESH = new ArmorSound("Flesh");
	public final static ArmorSound METAL = new ArmorSound("Metal");
	public final static ArmorSound STONE = new ArmorSound("Stone");
	public final static ArmorSound WOOD = new ArmorSound("Wood");
	
	public ArmorSound(String name) {
		super(name);
	}

	@Override
	public ArmorSound decode(Object val) {
		return new ArmorSound(val.toString());
	}
}
