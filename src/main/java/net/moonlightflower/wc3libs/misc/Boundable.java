package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Coords2DI;

public interface Boundable {
	public Coords2DI getCenter();
	
	public int getCenterX();
	public int getCenterY();
	
	public Size getSize();
	
	public int getWidth();
	public int getHeight();
}
