package dataTypes.app;

import dataTypes.DataType;
import misc.Size;

public class Bounds extends DataType {
	private Coords2DI _center;
	
	public Coords2DI getCenter() {
		return _center;
	}
	
	private Size _size;
	
	public Size getSize() {
		return _size;
	}
	
	public int getMinX() {
		return getCenter().getX() - getSize().getX() / 2;
	}
	
	public int getMinY() {
		return getCenter().getY() - getSize().getY() / 2;
	}

	public int getMaxX() {
		return getCenter().getX() +- getSize().getX() / 2;
	}
	
	public int getMaxY() {
		return getCenter().getY() + getSize().getY() / 2;
	}
	
	public Bounds scale(double scale) {
		return new Bounds(_size.scale(scale), _center);
	}
	
	public Bounds(Size size, Coords2DI center) {
		_center = center;
		_size = size;
	}
	
	public Bounds(int minX, int maxX, int minY, int maxY) {
		this(new Size(maxX - minX, maxY - minY), new Coords2DI((maxX - minX) / 2, (maxY - minY) / 2));
	}
	
	public static Bounds valueOf(int minX, int maxX, int minY, int maxY) {
		return new Bounds(minX, maxX, minY, maxY);
	}

	@Override
	public Bounds decode(Object val) {
		return valueOf(0, 0, 0, 0);
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
