package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public abstract class Raster<T> implements Boundable {
	private Bounds _bounds;

	@Nonnull
	public Bounds getBounds() {
		return _bounds;
	}

	@Nonnull
	@Override
	public Coords2DF getCenter() {
		return _bounds.getCenter();
	}

	@Override
	public float getCenterX() {
		return getCenter().getX().toFloat();
	}

	@Override
	public float getCenterY() {
		return getCenter().getY().toFloat();
	}

	@Nonnull
    @Override
	public Size getSize() {
		return _bounds.getSize();
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
	
	public void setBounds(@Nonnull Bounds val, boolean retainContents, boolean retainContentsByPos) {
		setSize(val.getSize().getArea(), retainContents);
		
		if (retainContents) {
			if (retainContentsByPos) {
				Raster<T> temp = clone();
				
				_bounds = val;
				
				mergeCellsByPos(temp);
			} else {
				_bounds = val;
			}
		} else {
			_bounds = val;
		}
	}
	
	public void setBoundsByWorld(@Nonnull Bounds val, boolean retainContents, boolean retainContentsByPos) {
		val = val.scale(1D / getCellSize());
		
		setBounds(val, retainContents, retainContentsByPos);
	}

	public int getIndexByXY(int x, int y) {
		return (y * getWidth() + x);
	}

	public int size() {
		return _cells.length;
	}
	
	public T get(int index) {
		return _cells[index];
	}
	
	private int coordsToIndex(@Nonnull Coords2DI pos) {
		return (pos.getY() * getWidth() + pos.getX());
	}
	
	public T get(@Nonnull Coords2DI pos) {
		return get(coordsToIndex(pos));
	}
	
	public void set(int index, T val) {
		_cells[index] = val;
	}
	
	public void set(@Nonnull Coords2DI pos, T val) {
		set(coordsToIndex(pos), val);
	}

	@Nonnull
	public Coords2DI worldToLocalCoords(@Nonnull Coords2DF pos) {
		int x = (int) ((pos.getX().toFloat() - getCenterX()) / getCellSize() + getWidth() / 2F);
		int y = (int) ((pos.getY().toFloat() - getCenterY()) / getCellSize() + getHeight() / 2F);

		return new Coords2DI(x, y);
	}
	
	public T getByPos(@Nonnull Coords2DF pos) {
		return get(worldToLocalCoords(pos));
	}
	
	public void setByPos(@Nonnull Coords2DF pos, T val) {
		set(worldToLocalCoords(pos), val);
	}
	
	public void clear() {
		for (int i = 0; i < size(); i++) {
			_cells[i] = null;
		}
	}
	
	public abstract T mergeCellVal(T oldVal, T other);
	
	public void mergeCell(int index, @Nonnull T otherCell) {
		set(index, mergeCellVal(get(index), otherCell));
	}

	public void mergeCells(@Nonnull Raster<T> other) {
		for (int i = 0; i < other.size(); i++) {
			set(i, get(i));
		}
	}
	
	public void mergeCellsByPos(@Nonnull Raster<T> other, boolean... extra) {
		Coords2DF center = getCenter();
		Coords2DF otherCenter = other.getCenter();
		
		Size size = getSize();
		Size otherSize = other.getSize();
		
		int minX = (int) ((otherCenter.getX().toFloat() - otherSize.getWidth() / 2F) - (center.getX().toFloat() - size.getWidth() / 2F));
		int maxX = minX + otherSize.getWidth() - 1;
		int minY = (int) ((otherCenter.getY().toFloat() - otherSize.getHeight() / 2F) - (center.getY().toFloat() - size.getHeight() / 2F));
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
	
	protected Raster(@Nonnull Bounds bounds) {
		_bounds = bounds;
	}
}
