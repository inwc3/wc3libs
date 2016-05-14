package misc.image;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public abstract class Wc3Img {
	public abstract javafx.scene.image.Image getFXImg();
	
	private BufferedImage toBufImg() {
		return SwingFXUtils.fromFXImage(getFXImg(), null);
	}
	
	public Wc3RasterImg toRasterImg() {
		return new Wc3RasterImg(toBufImg());
	}
	
	protected Wc3Img() {
	}
}
