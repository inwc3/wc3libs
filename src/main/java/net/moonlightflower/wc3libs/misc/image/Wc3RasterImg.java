package net.moonlightflower.wc3libs.misc.image;

import net.moonlightflower.wc3libs.dataTypes.app.Bounds;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;
import net.moonlightflower.wc3libs.misc.Raster;
import net.moonlightflower.wc3libs.misc.Size;
import net.moonlightflower.wc3libs.misc.UnsupportedFormatException;
import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.MpqPort;
import net.moonlightflower.wc3libs.port.NotFoundException;
import net.moonlightflower.wc3libs.port.Orient;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Wc3RasterImg extends Wc3Img {	
	private BufferedImage _bufImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

	@Nonnull
	public BufferedImage getBufImg() {
		return _bufImg;
	}
	
	public int getWidth() {
		return _bufImg.getWidth();
	}
	
	public int getHeight() {
		return _bufImg.getHeight();
	}
	
	public void setRGB(int x, int y, @Nonnull Color color) {
		_bufImg.setRGB(x, y, color.getRGB());
	}
	
	@Override
	@Nonnull
	public FxImg getFXImg() {
		return new FxImg(_bufImg);
	}
	
	public void setBufImg(@Nonnull BufferedImage bufImg) {
		_bufImg = bufImg;
	}
	
	public void setFXImg(@Nonnull FxImg fxImg) {
		setBufImg(fxImg.toBufImg());
	}
	
	private static class Rastered extends Raster<Color> {
		@Override
		public void setSize(int cellsCount) {
			_cells = new Color[cellsCount];
		}

		@Override
		public Color mergeCellVal(@Nonnull Color oldVal, @Nonnull Color other) {
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
			super(new Bounds(0, 0, 0, 0));
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
	
	public void addHSV(double hue, double sat, double val) {
		for (int y = 0; y < _bufImg.getHeight(); y++) {
			for (int x = 0; x < _bufImg.getWidth(); x++) {
				Color oldColor = new Color(_bufImg.getRGB(x, y), true);
				
				float[] hsv = Color.RGBtoHSB(oldColor.getRed(), oldColor.getGreen(), oldColor.getBlue(), null);
				
				hsv[0] = (float) (hsv[0] + hue);
				hsv[1] = Math.max(Math.min((float) (hsv[1] + sat), 1), 0);
				hsv[2] = Math.max(Math.min((float) (hsv[2] + val), 1), 0);
				
				Color newColor = new Color(Color.HSBtoRGB(hsv[0], hsv[1], hsv[2]));
				
				newColor = new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), oldColor.getAlpha());
				//System.out.println(oldColor + "->" + newColor);
				//Color newColor = new Color(ColorSpace.getInstance(ColorSpace.HSV), hsv, ((float) (((double) oldColor.getAlpha255()) / 0xFF)));
				
				_bufImg.setRGB(x, y, newColor.getRGB());
			}
		}
		
		setBufImg(_bufImg);
	}
	
	public void enlarge(@Nonnull Size size, @Nonnull java.awt.Color color) {
		BufferedImage bufImg = new BufferedImage(Math.max(size.getWidth(), getWidth()), Math.max(size.getHeight(), getHeight()), BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < bufImg.getHeight(); y++) {
			for (int x = 0; x < bufImg.getWidth(); x++) {
				bufImg.setRGB(x, y, new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), 255).getRGB());
			}
		}

		FxImg fxImg = new FxImg(bufImg);

		Wc3RasterImg other = new Wc3RasterImg(fxImg);

		int xOff = (((int) fxImg.getWidth()) - getWidth())/2;
		int yOff = (((int) fxImg.getHeight()) - getHeight())/2;
		
		other.merge(this, new Coords2DI(xOff, yOff), false);
		
		setBufImg(other.getBufImg());
	}
	
	public void merge(@Nonnull Wc3RasterImg other, @Nonnull Coords2DI otherOffset, boolean retainBounds) {
		int width = getWidth();
		int height = getHeight();
		
		int minX = 0;
		int minY = 0;
		int maxX = width - 1;
		int maxY = height - 1;
		
		int otherMinX = otherOffset.getX();
		int otherMinY = otherOffset.getY();
		int otherMaxX = otherOffset.getX() + other.getWidth() - 1;
		int otherMaxY = otherOffset.getY() + other.getHeight() - 1;
		
		int newMinX = minX;
		int newMinY = minY;
		int newMaxX = maxX;
		int newMaxY = maxY;
		
		if (!retainBounds) {
			newMinX = Math.min(otherOffset.getX(), minX);
			newMinY = Math.min(otherOffset.getY(), minY);
			newMaxX = Math.max(maxX, otherOffset.getX() + other.getWidth() - 1);
			newMaxY = Math.max(maxY, otherOffset.getY() + other.getHeight() - 1);
		}
		
		int newWidth = maxX - minX + 1;
		int newHeight = maxY - minY + 1;
		
		Size newSize = new Size(newWidth, newHeight);
		
		Wc3RasterImg newImg = new Wc3RasterImg(newSize);
		
		BufferedImage bufImg = getBufImg();
		BufferedImage otherBufImg = other.getBufImg();
		
		BufferedImage newBufImg = newImg.getBufImg();
		
		for (int y = newMinY; y <= newMaxY; y++) {
			for (int x = newMinX; x <= newMaxX; x++) {
				if (x >= minX && y >= minY && x <= maxX && y <= maxY) {
					newBufImg.setRGB(x - newMinX, y - minY, bufImg.getRGB(x, y));
				}
				
				if (x >= otherMinX && y >= otherMinY && x <= otherMaxX && y <= otherMaxY) {
					Color otherColor = new Color(otherBufImg.getRGB(x - otherMinX, y - otherMinY), true);
					Color curColor = new Color(newBufImg.getRGB(x - newMinX, y - newMinY), true);
					
					double alphaFactor = ((double) otherColor.getAlpha()) / 0xFF;
					double oldAlphaFactor = ((double) curColor.getAlpha()) / 0xFF;

					int newRed = (int) (curColor.getRed() * (1D - alphaFactor) + otherColor.getRed() * alphaFactor);
					int newGreen = (int) (curColor.getGreen() * (1D - alphaFactor) + otherColor.getGreen() * alphaFactor);
					int newBlue = (int) (curColor.getBlue() * (1D - alphaFactor) + otherColor.getBlue() * alphaFactor);
					int newAlpha = (int) ((oldAlphaFactor + (1D - oldAlphaFactor) * alphaFactor) * 255);

					Color newColor = new Color(newRed, newGreen, newBlue, newAlpha);
					
					newBufImg.setRGB(x - newMinX, y - newMinY, newColor.getRGB());
				}
			}
		}
		
		setBufImg(newBufImg);
	}
	
	public void ignoreAlpha() {
		BufferedImage oldImg = getBufImg();

		BufferedImage newImg = oldImg;
		
		for (int y = 0; y < newImg.getHeight(); y++) {
			for (int x = 0; x < newImg.getWidth(); x++) {
				newImg.setRGB(x, y, new java.awt.Color(oldImg.getRGB(x, y), false).getRGB());
			}
		}
		
		setBufImg(newImg);
	}
	
	public void ignoreAlpha2() {
		BufferedImage oldImg = getBufImg();

		BufferedImage newImg = new BufferedImage(oldImg.getWidth(), oldImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < newImg.getHeight(); y++) {
			for (int x = 0; x < newImg.getWidth(); x++) {
				Color oldColor = new Color(oldImg.getRGB(x, y));

				Color newColor = new Color(oldColor.getRed(), oldColor.getGreen(), oldColor.getBlue(), oldColor.getAlpha());

				newImg.setRGB(x, y, newColor.getRGB());
			}
		}
		
		setFXImg(new FxImg(newImg));
	}
	
	public Wc3RasterImg copy() {
		BufferedImage bufImg = getFXImg().toBufImg();
		
		return new Wc3RasterImg(bufImg);
	}
	
	public Wc3RasterImg() {
		super();
	}
	
	public Wc3RasterImg(@Nonnull Size size) {
		this();

		BufferedImage bufImg = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_ARGB);

		int blankColor = new Color(0, 0, 0, 0).getRGB();

		for (int y = 0; y < size.getHeight(); y++) {
			for (int x = 0; x < size.getWidth(); x++) {
				bufImg.setRGB(x, y, blankColor);
			}
		}
		
		setFXImg(new FxImg(bufImg));
		
		//_bufImg = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
	
	public Wc3RasterImg(@Nonnull BufferedImage bufImg) {
		this();
		
		_bufImg = bufImg;
	}
	
	public Wc3RasterImg(@Nonnull FxImg fxImg) {
		this(fxImg.toBufImg());
	}

	@Nonnull
	public static Wc3RasterImg ofInputStream(@Nonnull InputStream inStream, @Nonnull String ext) throws UnsupportedFormatException, IOException {
		switch (ext.toLowerCase()) {
		case "jpeg":
		case "jpg": {
			return new JPG(inStream);
		}
		case "blp": {
			return new BLP(inStream);
		}
		case "tga": {
			return new TGA(inStream);
		}
		default:
			throw new UnsupportedFormatException(String.format("extension %s not supported", ext.toLowerCase()));
		}
	}

	@Nonnull
	public static Wc3RasterImg ofFile(@Nonnull File file) throws UnsupportedFormatException, IOException {
		Orient.checkFileExists(file);
		
		String ext = Orient.getFileExt(file);

		if (ext == null) throw new IllegalArgumentException(String.format("file %s has no extension", file));

		switch (ext.toLowerCase()) {
		case "jpeg":
		case "jpg": {
			return new JPG(file);
		}
		case "blp": {
			return new BLP(file);
		}
		case "tga": {
			return new TGA(file);
		}
		default:
			throw new UnsupportedFormatException(String.format("extension %s of file %s not supported", ext.toLowerCase(), file));
		}
	}

	@Nonnull
	protected static MpqPort getMpqPort() {
		return Context.getService(MpqPort.class);
	}

	@Nonnull
	public static Wc3RasterImg ofGameFile(@Nonnull File inFile) throws IOException, UnsupportedFormatException, NotFoundException {
		MpqPort.Out.Result portResult = getMpqPort().getGameFiles(inFile);

		Wc3RasterImg img = Wc3RasterImg.ofFile(portResult.getFile(inFile));
		
		return img;
	}
}
