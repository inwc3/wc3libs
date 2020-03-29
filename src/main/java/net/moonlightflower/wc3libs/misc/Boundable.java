package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Coords2DF;
import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

import javax.annotation.Nonnull;

public interface Boundable {
	@Nonnull
    Coords2DF getCenter();

    float getCenterX();
    float getCenterY();
	
	@Nonnull
	Size getSize();
	
	int getWidth();
	int getHeight();
}
