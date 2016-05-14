package misc.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import misc.PathMap;
import misc.Size;
import misc.Raster;

public class Wc3RasterImg extends Wc3Img {
	private BufferedImage _bufImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	
	public BufferedImage getBufImg() {
		return _bufImg;
	}
	
	public void setRGB(int x, int y, Color color) {
		_bufImg.setRGB(x, y, color.getRGB());
	}
	
	private javafx.scene.image.Image toFXImg() {
		WritableImage wr = new WritableImage(_bufImg.getWidth(), _bufImg.getHeight());
		
		PixelWriter pw = wr.getPixelWriter();
		
		for (int x = 0; x < _bufImg.getWidth(); x++) {
			for (int y = 0; y < _bufImg.getHeight(); y++) {					
				pw.setArgb(x, y, _bufImg.getRGB(x, y));
			}
		}
		
		return wr;
	}
	
	@Override
	public Image getFXImg() {
		return toFXImg();
	}
	
	public void setBufImg(BufferedImage bufImg) {
		_bufImg = bufImg;
	}
	
	public Wc3RasterImg(Size size) {
		this();
		
		_bufImg = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
	
	public Wc3RasterImg(BufferedImage bufImg) {
		this();
		
		_bufImg = bufImg;
	}
	
	private static class Rastered extends Raster<Color> {
		@Override
		public void setSize(int cellsCount) {
			_cells = new Color[cellsCount];
		}

		@Override
		public Color mergeCellVal(Color oldVal, Color other) {
			return other;
		}

		@Override
		public Raster<Color> clone() {
			Raster<Color> other = new Rastered();
			
			other.mergeCells(this);
			
			return other;
		}

		@Override
		public int getCellSize() {
			return 1;
		}
		
		private Rastered() {
		}
	}
	
	public Raster<Color> toRaster() {
		return new Rastered();
	}
	
	public interface RasterConverter<valType> {
		public Color getColor(valType pathMapVal); 
	}
	
	public <valType> Wc3RasterImg(Raster<valType> pathMap, RasterConverter<valType> converter) {
		this(pathMap.getSize());
		
		for (int x = 0; x < pathMap.getWidth(); x++) {
			for (int y = 0; y < pathMap.getHeight(); y++) {
				setRGB(x, y, converter.getColor(pathMap.get(pathMap.getIndexByXY(x, y))));
			}
		}
	}
	
	public Wc3RasterImg() {
		super();
	}
}
