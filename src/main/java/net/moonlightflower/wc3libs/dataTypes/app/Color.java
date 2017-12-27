package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class Color extends DataType {
	private int _red;
	private int _green;
	private int _blue;
	private int _alpha;
	
	public int getRed255() {
		return _red;
	}

	public int getGreen255() {
		return _green;
	}

	public int getBlue255() {
		return _blue;
	}
	
	public int getAlpha255() {
		return _alpha;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Color)
			return equals((Color) other);
		
		return super.equals(other);
	}

	public boolean equals(Color other) {
		return getRed255() == other.getRed255() &&
				getGreen255() == other.getGreen255() &&
				getBlue255() == other.getBlue255() &&
				getAlpha255() == other.getAlpha255();
	}

	@Override
	public String toString() {
		return String.format("red=%d green=%d blue=%d alpha=%d", _red, _green, _blue, _alpha);
	}
	
	private Color(int red, int green, int blue) {
		_red = red;
		_green = green;
		_blue = blue;
	}
	
	private Color(int red, int green, int blue, int alpha) {
		_red = red;
		_green = green;
		_blue = blue;
		_alpha = alpha;
	}
	
	/**
	 * Gets a color from red, green and blue values in the range 0-255.
	 */
	@Nonnull
	public static Color fromRGB255(int red, int green, int blue) {
		return new Color(red, green, blue);
	}

	@Nonnull
	public static Color fromRGBA255(int red, int green, int blue, int alpha) {
		return new Color(red, green, blue, alpha);
	}

	@Nonnull
	public static Color fromBGR255(int blue, int green, int red) {
		return new Color(red, green, blue);
	}

	@Nonnull
	public static Color fromBGRA255(int blue, int green, int red, int alpha) {
		return new Color(red, green, blue, alpha);
	}

	@Override
	public DataType decode(Object val) {
		return decodeStatic(val);
	}

	public static DataType decodeStatic(Object val) {
		if (val instanceof Color)
			return Color.fromBGRA255(((Color) val).getBlue255(), ((Color) val).getGreen255(), ((Color) val).getRed255(), ((Color) val).getAlpha255());
		
		return null;
	}
	
	@Override
	public Object toSLKVal() {
		// TODO
		return null;
	}

	@Override
	public Object toTXTVal() {
		// TODO
		return null;
	}
}
