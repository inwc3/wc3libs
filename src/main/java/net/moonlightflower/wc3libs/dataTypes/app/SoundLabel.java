package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SoundLabel extends Wc3String {
	@Override
	public boolean equals(Object other) {
		if (other instanceof SoundLabel)
			return equals((SoundLabel) other);

		return super.equals(other);
	}

	public boolean equals(SoundLabel other) {
		return getVal().equals(other.getVal());
	}
	
	public SoundLabel(String name) {
		super(name);
	}

	@Nonnull
	public static SoundLabel valueOf(String name) {
		return new SoundLabel(name);
	}

	@Override
	public SoundLabel decode(Object val) {
		return decodeStatic(val);
	}

	public static SoundLabel decodeStatic(Object val) {
		if (val != null)
			return valueOf(val.toString());

		return null;
	}
}
