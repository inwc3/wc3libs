package net.moonlightflower.wc3libs.misc;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.image.Wc3RasterImg;

import javax.annotation.Nonnull;

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
		ShadowMap other = new ShadowMap(getBounds());

		other.mergeCells(this);
		
		return other;
	}

	@Nonnull
	public Wc3RasterImg toImg() {
		WritableImage fxImg = new WritableImage(getWidth(), getHeight());
		
		PixelWriter pxWriter = fxImg.getPixelWriter();
		
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				pxWriter.setColor(x, y, get(new Coords2DI(x, getHeight() - 1 - y)) ? Color.WHITE : Color.BLACK);
			}
		}
		
		return new Wc3RasterImg(fxImg);
	}
	
	public ShadowMap(Bounds bounds) {
		setBounds(bounds, false, false);
	}
}
