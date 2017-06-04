package net.moonlightflower.wc3libs.misc.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import net.moonlightflower.wc3libs.misc.Size;

public class JPG extends Wc3RasterImg {
	private void read(InputStream stream) {
		Image img = new Image(stream);
		
		WritableImage writeImg = new WritableImage((int) img.getWidth(), (int) img.getHeight());
		
		PixelReader pixelReader = img.getPixelReader();
		PixelWriter pixelWriter = writeImg.getPixelWriter();
		
		//swap colors
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Color color = pixelReader.getColor(x, y);
				
				double red = color.getBlue();
				double green = color.getGreen();
				double blue = color.getRed();
				double alpha = color.getOpacity();

				color = new Color(red, green, blue, alpha);
				
				pixelWriter.setColor(x, y, color);
			}
		}
		
		setFXImg(writeImg);
	}
	
	public JPG(InputStream stream) {
		read(stream);
	}
	
	public JPG(File file) throws FileNotFoundException {
		this(new FileInputStream(file));
	}
	
	public JPG(Size size) {
		super(size);
	}
}
