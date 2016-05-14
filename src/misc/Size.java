package misc;

import dataTypes.app.Coords2DI;

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
	public Size scale(double scale) {
		return new Size((int) (getX() * scale), (int) (getY() * scale));
	}
	
	public Size(int x, int y) {
		super(x, y);
	}
}
