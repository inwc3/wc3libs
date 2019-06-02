package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.image.FxImg;
import net.moonlightflower.wc3libs.misc.image.Wc3RasterImg;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;

public class ShadowMap extends Raster<Boolean> {
	public final static int CELL_SIZE = 32;
	
	@Override
	public int getCellSize() {
		return CELL_SIZE;
	}

	@Override
	public void setSize(int cellsCount) {
		_cells = new Boolean[cellsCount];
	}

	@Override
	public Boolean mergeCellVal(Boolean oldVal, Boolean other) {
		return (oldVal || other);
	}

	@Override
	public ShadowMap clone() {
		ShadowMap other = getShadowMap(getBounds());

		other.mergeCells(this);
		
		return other;
	}

	protected ShadowMap getShadowMap(@Nonnull Bounds bounds) {
		return new ShadowMap(bounds);
	}

	@Nonnull
	public Wc3RasterImg toImg() {
		BufferedImage bufImg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				bufImg.setRGB(x, y, (get(new Coords2DI(x, getHeight() - 1 - y)) ? java.awt.Color.WHITE : java.awt.Color.BLACK).getRGB());
			}
		}
		
		return new Wc3RasterImg(new FxImg(bufImg));
	}
	
	public ShadowMap(@Nonnull Bounds bounds) {
		super(bounds);

		//TODO: needed?
		setBounds(bounds, false, false);
	}
}
