package net.moonlightflower.wc3libs.misc.image;

import net.moonlightflower.wc3libs.misc.Size;

import javax.annotation.Nonnull;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class JPG extends Wc3RasterImg {
	private void read(@Nonnull InputStream stream) throws IOException {
		BufferedImage img = ImageIO.read(stream);
		
		BufferedImage writeImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		//swap colors
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				java.awt.Color color = new java.awt.Color(img.getRGB(x, y));
				
				int red = color.getBlue();
				int green = color.getGreen();
				int blue = color.getRed();
				int alpha = color.getAlpha();

				color = new java.awt.Color(red, green, blue, alpha);
				
				writeImg.setRGB(x, y, color.getRGB());
			}
		}
		
		setFXImg(new FxImg(writeImg));
	}
	
	public JPG(@Nonnull InputStream stream) throws IOException {
		read(stream);
	}
	
	public JPG(@Nonnull File file) throws IOException {
		this(Files.newInputStream(file.toPath()));
	}
	
	public JPG(@Nonnull Size size) {
		super(size);
	}
}
