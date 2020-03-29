package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.misc.Size;

import javax.annotation.Nonnull;

public class Bounds extends DataType {
	private Coords2DF _center;

	@Nonnull
	public Coords2DF getCenter() {
		return _center;
	}
	
	private Size _size;

	@Nonnull
	public Size getSize() {
		return _size;
	}
	
	public int getMinX() {
		return (int) (getCenter().getX().toFloat() - getSize().getX() / 2F);
	}
	
	public int getMinY() {
		return (int) (getCenter().getY().toFloat() - getSize().getY() / 2F);
	}

	public int getMaxX() {
		return (int) (getCenter().getX().toFloat() + getSize().getX() / 2F);
	}
	
	public int getMaxY() {
		return (int) (getCenter().getY().toFloat() + getSize().getY() / 2F);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Bounds)
			return equals((Bounds) other);
		
		return super.equals(other);
	}
	
	public boolean equals(Bounds other) {
		return getCenter().equals((other).getCenter()) &&
				getSize().equals((other).getSize());
	}
	
	@Override
	public String toString() {
		return String.format("minX=%d minY=%d maxX=%d maxY=%d [%s]", getMinX(), getMinY(), getMaxX(), getMaxY(), getSize().toString());
	}

	@Nonnull
	public Bounds scale(double scale) {
		return new Bounds(_size.scale(scale), _center);
	}
	
	public Bounds(@Nonnull Size size, @Nonnull Coords2DF center) {
		_center = center;
		_size = size;
	}

    public Bounds(@Nonnull Size size) {
        this(size, new Coords2DF(0F, 0F));
    }

	public Bounds(int minX, int maxX, int minY, int maxY) {
		this(new Size(maxX - minX, maxY - minY), new Coords2DF((maxX + minX) / 2F, (maxY + minY) / 2F));
	}
	
	public static Bounds valueOf(int minX, int maxX, int minY, int maxY) {
		return new Bounds(minX, maxX, minY, maxY);
	}

	@Override
	public Bounds decode(Object val) {
		return valueOf(0, 0, 0, 0);
	}

	public static Bounds decodeStatic(Object val) {
		if (val instanceof Bounds)
			return valueOf(((Bounds) val).getMinX(), ((Bounds) val).getMaxX(), ((Bounds) val).getMinY(), ((Bounds) val).getMaxY());
		
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
