package dataTypes.app;

import dataTypes.DataType;

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
	public static Color fromRGB(int red, int green, int blue) {
		return new Color(red, green, blue);
	}

	public static Color fromRGBA(int red, int green, int blue, int alpha) {
		return new Color(red, green, blue, alpha);
	}
	
	public static Color fromBGR(int blue, int green, int red) {
		return new Color(red, green, blue);
	}

	public static Color fromBGRA(int blue, int green, int red, int alpha) {
		return new Color(red, green, blue, alpha);
	}
}
