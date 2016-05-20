package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

public class PlayerColor extends DataType {
	public final static PlayerColor RED = new PlayerColor(0x03, 0x03, 0xFF, 0xFF);
	public final static PlayerColor BLUE = new PlayerColor(0xFF, 0x42, 0x00, 0xFF);
	public final static PlayerColor TEAL = new PlayerColor(0xB9, 0xE6, 0x1C, 0xFF);
	public final static PlayerColor PURPLE = new PlayerColor(0x81, 0x00, 0x54, 0xFF);
	public final static PlayerColor YELLOW = new PlayerColor(0x00, 0xFC, 0xFF, 0xFF);
	public final static PlayerColor ORANGE = new PlayerColor(0x0E, 0x8A, 0xFE, 0xFF);
	public final static PlayerColor GREEN = new PlayerColor(0x00, 0xC0, 0x20, 0xFF);
	public final static PlayerColor PINK = new PlayerColor(0xB0, 0x5B, 0xE5, 0xFF);
	public final static PlayerColor LIGHT_GRAY = new PlayerColor(0x97, 0x96, 0x95, 0xFF);
	public final static PlayerColor LIGHT_BLUE = new PlayerColor(0xF1, 0xBF, 0x7E, 0xFF);
	public final static PlayerColor DARK_GREEN = new PlayerColor(0x46, 0x62, 0x10, 0xFF);
	public final static PlayerColor BROWN = new PlayerColor(0x04, 0x2A, 0x49, 0xFF);
	public final static PlayerColor BLACK = new PlayerColor(0xFF, 0xFF, 0xFF, 0xFF);
	
	private Color _color;
	
	public Color getColor() {
		return _color;
	}
	
	public Object getVal() {
		return getColor();
	}
	
	public PlayerColor(int blue, int green, int red, int alpha) {
		_color = Color.fromBGRA(blue, green, red, alpha);
	}

	@Override
	public DataType decode(Object val) {
		return null;
	}

	@Override
	public Object toSLKVal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object toTXTVal() {
		// TODO Auto-generated method stub
		return null;
	}
}
