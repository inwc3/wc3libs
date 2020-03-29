package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.dataTypes.app.FlagsInt;
import net.moonlightflower.wc3libs.misc.image.FxImg;
import net.moonlightflower.wc3libs.misc.image.Wc3RasterImg;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.image.BufferedImage;

public class PathMap extends Raster<Integer> {
	public static class PathingInt extends FlagsInt {
		static FlagsInt.Flag UNUSED = new FlagsInt.Flag("unused (0)", 0);
		static FlagsInt.Flag UNWALK = new FlagsInt.Flag("nowalk", 1);
		static FlagsInt.Flag UNFLY = new FlagsInt.Flag("nofly", 2);
		static FlagsInt.Flag UNBUILD = new FlagsInt.Flag("nobuild", 3);
		static FlagsInt.Flag UNUSED4 = new FlagsInt.Flag("unused (4)", 4);
		static FlagsInt.Flag BLIGHT = new FlagsInt.Flag("blight", 5);
		static FlagsInt.Flag UNWATER = new FlagsInt.Flag("nowater", 6);
		static FlagsInt.Flag UNKNOWN = new FlagsInt.Flag("unknown", 7);

		@Nonnull
		public java.awt.Color getColor() {
			int red = containsFlag(UNWALK) ? 255 : 0;
			int green = containsFlag(UNFLY) ? 255 : 0;
			int blue = containsFlag(UNBUILD) ? 255 : 0;
			
			return new java.awt.Color(red, green, blue, 255);
		}
		
		private PathingInt(int val) {
			super(val);
		}

		public static PathingInt valueOf(int val) {
			return new PathingInt(val);
		}

		@Override
		public DataType decode(Object val) {
			// TODO
			return null;
		}

		@Override
		public Object toSLKVal() {
			// TODO
			return null;
		}

		@Override
		public Object toTXTVal() {
			// TODO
			return null;
		}
	}
	
	public final static int CELL_SIZE = 32;
	
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

	@Override
	public void setBoundsByWorld(@Nonnull Bounds val, boolean retainContents, boolean retainContentsByPos) {
		val = val.scale(1D / CELL_SIZE);
		
		setBounds(val, retainContents, retainContentsByPos);
	}
	
	@Override
	public int getIndexByXY(int x, int y) {
		return (y * getWidth() + x);
	}

	@Override
	public int size() {
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

	@Nullable
	public Coords2DI indexToCoords(int index) {
		if ((index < 0) || (index >= size())) return null; 
		
		return new Coords2DI(index % getWidth(), index / getWidth());
	}
	
	private int coordsToIndex(@Nonnull Coords2DI pos) {
		return (pos.getY() * getWidth() + pos.getX());
	}
	
	@Override
	public Integer get(@Nonnull Coords2DI pos) {
		return get(coordsToIndex(pos));
	}
	
	public void set(int index, int val) {
		_cells[index] = val;
	}
	
	public void set(@Nonnull Coords2DI pos, int val) {
		set(coordsToIndex(pos), val);
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
	
	@Override
	public Coords2DI worldToLocalCoords(@Nonnull Coords2DF pos) {
		int x = ((int) (pos.getX().toFloat() - getCenterX())) / CELL_SIZE + getWidth() / 2;
		int y = ((int) (pos.getY().toFloat() - getCenterY())) / CELL_SIZE + getHeight() / 2;

		return new Coords2DI(x, y);
	}
	
	@Override
	public Integer getByPos(@Nonnull Coords2DF pos) {
		return get(worldToLocalCoords(pos));
	}
	
	public void setByPos(@Nonnull Coords2DF pos, int val) {
		set(worldToLocalCoords(pos), val);
	}
	
	@Override
	public void clear() {
		for (int i = 0; i < size(); i++) {
			_cells[i] = 0x0;
		}
	}

	@Override
	public Integer mergeCellVal(@Nonnull Integer oldVal, @Nonnull Integer other) {
		return (oldVal | other);
	}
	
	public void mergeCells(@Nonnull PathMap other) {
		for (int i = 0; i < other.size(); i++) {
			set(i, get(i));
		}
	}
	
	public void mergeCellsByPos(@Nonnull PathMap other, boolean additive) {
		Coords2DF center = getCenter();
		Coords2DF otherCenter = other.getCenter();
		
		Size size = getSize();
		Size otherSize = other.getSize();
		
		int minX = (int) ((otherCenter.getX().toFloat() - otherSize.getWidth() / 2) - (center.getX().toFloat() - size.getWidth() / 2));
		int maxX = minX + otherSize.getWidth() - 1;
		int minY = (int) ((otherCenter.getY().toFloat() - otherSize.getHeight() / 2) - (center.getY().toFloat() - size.getHeight() / 2));
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

	@Nonnull
	public Wc3RasterImg toImg() {
		BufferedImage bufImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				bufImg.setRGB(x, y, PathingInt.valueOf(get(new Coords2DI(x, getHeight() - 1 - y))).getColor().getRGB());
			}
		}
		
		return new Wc3RasterImg(new FxImg(bufImg));
	}
	
	public PathMap(@Nonnull Bounds bounds) {
		super(bounds);

		//TODO: needed?
		setBounds(bounds, false, false);
	}
}
