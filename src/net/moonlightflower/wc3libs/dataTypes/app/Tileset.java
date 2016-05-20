package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

public class Tileset extends Char {
	private Tileset(char val) {
		super(val);
	}
	
	public static Tileset valueOf(char val) {
		return new Tileset(val);
	}
	
	@Override
	public Tileset decode(Object val) {
		return valueOf(val.toString().charAt(0));
	}
}
