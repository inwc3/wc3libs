package net.moonlightflower.wc3libs.misc.image;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javax.annotation.Nonnull;

public abstract class Wc3Img {
	public abstract javafx.scene.image.Image getFXImg();

	@Nonnull
	private BufferedImage toBufImg() {
		return SwingFXUtils.fromFXImage(getFXImg(), null);
	}

	@Nonnull
	public Wc3RasterImg toRasterImg() {
		return new Wc3RasterImg(toBufImg());
	}
	
	protected Wc3Img() {
	}
}
