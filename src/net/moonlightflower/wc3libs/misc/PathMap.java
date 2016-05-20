package net.moonlightflower.wc3libs.misc;

import java.util.Arrays;

import net.moonlightflower.wc3libs.bin.app.WPM;
import net.moonlightflower.wc3libs.bin.app.W3E.Tile;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

public class PathMap extends Raster<Integer> {	
	public final static int CELL_SIZE = 32;
	
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

	@Override
	public void setBoundsByWorld(Bounds val, boolean retainContents) {
		val = val.scale(1D / CELL_SIZE);
		
		setBounds(val, retainContents);
	}
	
	@Override
	public int getIndexByXY(int x, int y) {
		return (y * getWidth() + x);
	}

	@Override
	protected int size() {
		return _cells.length;
	}
	
	@Override
	public void setSize(int cellsCount) {
		_cells = new Integer[cellsCount];
		
	}
	
	@Override
	public Integer get(int index) {
		return _cells[index];
	}
	
	public Coords2DI indexToCoords(int index) {
		if ((index < 0) || (index >= size())) return null; 
		
		return new Coords2DI(index % getWidth(), index / getWidth());
	}
	
	private int CoordsToIndex(Coords2DI pos) {
		return (pos.getY() * getWidth() + pos.getX());
	}
	
	@Override
	public Integer get(Coords2DI pos) {
		return get(CoordsToIndex(pos));
	}
	
	public void set(int index, int val) {
		_cells[index] = val;
	}
	
	public void set(Coords2DI pos, int val) {
		set(CoordsToIndex(pos), val);
	}
	
	public boolean get(int index, Integer flag) {
		return ((get(index) & flag) >= flag);
	}
	
	public void set(int index, Integer flag, boolean val) {
		if (val) {
			set(index, get(index) | flag);
		} else {
			set(index, get(index) & ~flag);
		}
	}
	
	public Coords2DI worldToLocalCoords(Coords2DF pos) {
		int x = ((int) (pos.getX().toFloat() - getCenterX())) / CELL_SIZE + getWidth() / 2;
		int y = ((int) (pos.getY().toFloat() - getCenterY())) / CELL_SIZE + getHeight() / 2;

		return new Coords2DI(x, y);
	}
	
	@Override
	public Integer getByPos(Coords2DF pos) {
		return get(worldToLocalCoords(pos));
	}
	
	public void setByPos(Coords2DF pos, int val) {
		set(worldToLocalCoords(pos), val);
	}
	
	@Override
	public void clear() {
		for (int i = 0; i < size(); i++) {
			_cells[i] = 0x0;
		}
	}

	@Override
	public Integer mergeCellVal(Integer oldVal, Integer other) {
		return (oldVal | other);
	}
	
	public void mergeCells(PathMap other) {
		for (int i = 0; i < other.size(); i++) {
			set(i, get(i));
		}
	}
	
	public void mergeCellsByPos(PathMap other, boolean additive) {
		assert getBounds() != null: "no bounds set yet";
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
				Coords2DI coords = new Coords2DI(x, y);

				if (additive) {
					set(coords, get(coords) & other.get(coords));
				} else {
					set(coords, other.get(coords));
				}
			}
		}
	}
	
	@Override
	public PathMap clone() {
		PathMap other = new PathMap(getBounds());
		
		other.mergeCells(this);
		
		return other;
	}
	
	public PathMap(Bounds bounds) {
		System.out.println("create " + bounds);
		setBounds(bounds, false);
	}
}
