package net.moonlightflower.wc3libs.dataTypes.app;

public class SoundLabel extends Wc3String {
	@Override
	public boolean equals(Object other) {
		if (other instanceof SoundLabel)
			return this.equals((SoundLabel) other);
		
		return super.equals(other);
	}
	
	public boolean equals(SoundLabel other) {
		return getVal().equals(other.getVal());
	}
	
	public SoundLabel(String name) {
		super(name);
	}
	
	public static SoundLabel valueOf(String name) {
		return new SoundLabel(name);
	}

	@Override
	public SoundLabel decode(Object val) {
		return decodeStatic(val);
	}
	
	public static SoundLabel decodeStatic(Object val) {
		return valueOf(val.toString());
	}
}
