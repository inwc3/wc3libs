package net.moonlightflower.wc3libs.misc.image;

import javax.annotation.Nonnull;
import java.awt.image.BufferedImage;

public abstract class Wc3Img {
	public abstract FxImg getFXImg();

	@Nonnull
	private BufferedImage toBufImg() {
		return getFXImg().toBufImg();
	}

	@Nonnull
	public Wc3RasterImg toRasterImg() {
		return new Wc3RasterImg(toBufImg());
	}
	
	protected Wc3Img() {
	}
}
