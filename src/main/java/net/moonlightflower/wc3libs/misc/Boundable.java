package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

import javax.annotation.Nonnull;

public interface Boundable {
	@Nonnull
	Coords2DI getCenter();
	
	int getCenterX();
	int getCenterY();
	
	@Nonnull
	Size getSize();
	
	int getWidth();
	int getHeight();
}
