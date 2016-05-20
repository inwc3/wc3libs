package net.moonlightflower.wc3libs.dataTypes.app;

public class SoundLabel extends Wc3String {
	public SoundLabel(String name) {
		super(name);
	}
	
	public static SoundLabel valueOf(String name) {
		return new SoundLabel(name);
	}

	@Override
	public SoundLabel decode(Object val) {
		return valueOf(val.toString());
	}
}
