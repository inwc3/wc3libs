package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

public class Size extends Coords2DI {
	public int getWidth() {
		return getX();
	}

	public int getHeight() {
		return getY();
	}
	
	public int getArea() {
		return getWidth() * getHeight();
	}
	
	@Override
	public String toString() {
		return String.format("%dx%d", getWidth(), getHeight());
	}
	
	@Override
	public Size scale(double scale) {
		return new Size((int) (getX() * scale), (int) (getY() * scale));
	}
	
	public Size(int x, int y) {
		super(x, y);
	}
}
