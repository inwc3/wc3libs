package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

public interface Boundable {
	Coords2DI getCenter();
	
	int getCenterX();
	int getCenterY();
	
	Size getSize();
	
	int getWidth();
	int getHeight();
}
