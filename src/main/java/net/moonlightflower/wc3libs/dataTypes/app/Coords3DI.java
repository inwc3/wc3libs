package net.moonlightflower.wc3libs.dataTypes.app;

public class Coords3DI {
	private int _x;
	private int _y;
	private int _z;
	
	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}
	
	public int getZ() {
		return _z;
	}
	
	public Coords3DI(int x, int y, int z) {
		_x = x;
		_y = y;
		_z = z;
	}
}
