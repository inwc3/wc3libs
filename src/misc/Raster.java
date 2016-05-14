package misc;

import java.util.Arrays;

import bin.Wc3bin;
import dataTypes.app.Bounds;
import dataTypes.app.Coords2DF;
import dataTypes.app.Coords2DI;

public abstract class Raster<T> extends Wc3bin implements Boundable {
	private Bounds _bounds;
	
	public Bounds getBounds() {
		return _bounds;
	}
	
	@Override
	public Coords2DI getCenter() {
		return getBounds().getCenter();
	}

	@Override
	public int getCenterX() {
		return getCenter().getX();
	}

	@Override
	public int getCenterY() {
		return getCenter().getY();
	}

	@Override
	public Size getSize() {
		return getBounds().getSize();
	}

	@Override
	public int getWidth() {
		return getSize().getWidth();
	}

	@Override
	public int getHeight() {
		return getSize().getHeight();
	}
	
	protected T _cells[];
	
	public abstract void setSize(int cellsCount);
	
	public void setSize(int cellsCount, boolean retainContents) {		
		if (retainContents) {
			_cells = Arrays.copyOf(_cells, cellsCount);
		} else {
			setSize(cellsCount);
		}
	}
	
	public void setBounds(Bounds val, boolean retainContents) {
		setSize(val.getSize().getArea(), retainContents);
		
		if (retainContents) {
			Raster<T> temp = clone();
			
			_bounds = val;
			
			mergeCellsByPos(temp);
		} else {
			_bounds = val;
		}
	}
	
	public void setBoundsByWorld(Bounds val, boolean retainContents) {
		val = val.scale(1D / getCellSize());
		
		setBounds(val, retainContents);
	}

	public int getIndexByXY(int x, int y) {
		return (y * getWidth() + x);
	}

	protected int size() {
		return _cells.length;
	}
	
	public T get(int index) {
		return _cells[index];
	}
	
	private int CoordsToIndex(Coords2DI pos) {
		return (pos.getY() * getWidth() + pos.getX());
	}
	
	public T get(Coords2DI pos) {
		return get(CoordsToIndex(pos));
	}
	
	public void set(int index, T val) {
		_cells[index] = val;
	}
	
	public void set(Coords2DI pos, T val) {
		set(CoordsToIndex(pos), val);
	}
	
	public Coords2DI worldToSHDCoords(Coords2DF pos) {
		int x = ((int) (pos.getX().toFloat() - getCenterX())) / getCellSize() + getWidth() / 2;
		int y = ((int) (pos.getY().toFloat() - getCenterY())) / getCellSize() + getHeight() / 2;

		return new Coords2DI(x, y);
	}
	
	public T getByPos(Coords2DF pos) {
		return get(worldToSHDCoords(pos));
	}
	
	public void setByPos(Coords2DF pos, T val) {
		set(worldToSHDCoords(pos), val);
	}
	
	public void clear() {
		for (int i = 0; i < size(); i++) {
			_cells[i] = null;
		}
	}
	
	public abstract T mergeCellVal(T oldVal, T other);
	
	public void mergeCell(int index, T otherCell) {
		set(index, mergeCellVal(get(index), otherCell));
	}

	public void mergeCells(Raster<T> other) {
		for (int i = 0; i < other.size(); i++) {
			set(i, get(i));
		}
	}
	
	public void mergeCellsByPos(Raster<T> other, boolean... extra) {
		assert _bounds != null: "no bounds set yet";
		assert other.getBounds() != null: "no bounds of other set yet";
		
		Coords2DI center = getCenter();
		Coords2DI otherCenter = other.getCenter();
		
		Size size = getSize();
		Size otherSize = other.getSize();
		
		int minX = (((int) otherCenter.getX()) - otherSize.getWidth() / 2) - (((int) center.getX()) - size.getWidth() / 2);
		int maxX = minX + otherSize.getWidth() - 1;
		int minY = (((int) otherCenter.getY()) - otherSize.getHeight() / 2) - (((int) center.getY()) - size.getHeight() / 2);
		int maxY = minY + otherSize.getHeight() - 1;

		for (int y = minY; y <= maxY; y++) {
			for (int x = minX; x <= maxX; x++) {
				T otherVal = other.get(new Coords2DI(x, y));
				
				mergeCell(getIndexByXY(x, y), otherVal);
			}
		}
	}
	
	@Override
	public abstract Raster<T> clone();
	public abstract int getCellSize();
	
	protected Raster() {
	}
}
