package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

public class Color extends DataType {
	private int _red;
	private int _green;
	private int _blue;
	private int _alpha;
	
	public int getRed() {
		return _red;
	}

	public int getGreen() {
		return _green;
	}

	public int getBlue() {
		return _blue;
	}
	
	public int getAlpha() {
		return _alpha;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Color)
			return getRed() == ((Color) other).getRed() &&
					getGreen() == ((Color) other).getGreen() &&
					getBlue() == ((Color) other).getBlue() &&
					getAlpha() == ((Color) other).getAlpha();
		
		return super.equals(other);
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
	public static Color fromRGB255(int red, int green, int blue) {
		return new Color(red, green, blue);
	}

	public static Color fromRGBA255(int red, int green, int blue, int alpha) {
		return new Color(red, green, blue, alpha);
	}
	
	public static Color fromBGR255(int blue, int green, int red) {
		return new Color(red, green, blue);
	}

	public static Color fromBGRA255(int blue, int green, int red, int alpha) {
		return new Color(red, green, blue, alpha);
	}

	@Override
	public DataType decode(Object val) {
		return decodeStatic(val);
	}

	public static DataType decodeStatic(Object val) {
		if (val instanceof Color)
			return Color.fromBGRA255(((Color) val).getBlue(), ((Color) val).getGreen(), ((Color) val).getRed(), ((Color) val).getAlpha());
		
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
