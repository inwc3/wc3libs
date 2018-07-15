package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MoveType extends War3String {
	public final static MoveType AMPHIBIOUS = new MoveType("amph");
	public final static MoveType FLOAT = new MoveType("float");
	public final static MoveType FLY = new MoveType("fly");
	public final static MoveType FOOT = new MoveType("foot");
	public final static MoveType HORSE = new MoveType("horse");
	public final static MoveType HOVER = new MoveType("hover");

	@Override
	public boolean equals(Object other) {
		if (other instanceof MoveType)
			return equals((MoveType) other);

		return super.equals(other);
	}

	public boolean equals(MoveType other) {
		return getVal().equals(other.getVal());
	}

	private MoveType(String val) {
		super(val);
	}

	@Nullable
	public static MoveType valueOf(@Nonnull String val) {
		return new MoveType(val);
	}
	
	@Override
	public MoveType decode(Object val) {
		return valueOf(val.toString());
	}
}
